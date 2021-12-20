package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Form_address extends JFrame implements ActionListener {
	JTextArea screen_field = new JTextArea("");
    JLabel city_Label = new JLabel("City:");
	JTextField city_field = new JTextField(16);
    JLabel street_Label = new JLabel("Street:");
	JTextField street_field = new JTextField(16);
    JLabel apartment_Label = new JLabel("apartment number:");
	JTextField apartment_field = new JTextField(16);
    JLabel zip_Label = new JLabel("Zip code:");
	JTextField zip_field = new JTextField(16);
    JButton next_button = new JButton("Next");  
    JButton back_button = new JButton("Back");   
    JButton quit_button = new JButton("Quit");  
	static String my_string_to_send;
    	
	public Form_address()
    {

    }
	
	public Form_address(String my_string_to_send1)
    {
    	super("Client: Address form"); // headline of window
    	Add_Panels();
    	Add_Function();
    	my_string_to_send = my_string_to_send1;
    }
    
    private void Add_Panels()
    {
    	JPanel info_panel = new JPanel(new GridLayout(1,1)); // Creating the first panel	
    	screen_field.setPreferredSize(new Dimension(200,50));
    	String string = "Please fill your address details";
    	screen_field.setText(string);
    	screen_field.setEditable(false);
    	info_panel.add(screen_field);
    	
    	JPanel fields_panel = new JPanel(new GridLayout(4,2)); // Creating the second panel
    	fields_panel.add(city_Label);
    	fields_panel.add(city_field);
    	fields_panel.add(street_Label);
    	fields_panel.add(street_field);
    	fields_panel.add(apartment_Label);
    	fields_panel.add(apartment_field);
    	fields_panel.add(zip_Label);
    	fields_panel.add(zip_field);
    	
    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the second panel	
    	buttons_panel.add(next_button);
    	buttons_panel.add(back_button);
    	buttons_panel.add(quit_button);
    	
    	setLayout(new BorderLayout()); // insert panels to the main layout
    	add(info_panel, BorderLayout.NORTH);
    	add(fields_panel, BorderLayout.CENTER);
    	add(buttons_panel, BorderLayout.SOUTH);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.pack();
    }
    
    private void Add_Function()
    {  	
    	city_field.addActionListener(this); 
    	street_field.addActionListener(this); 
    	apartment_field.addActionListener(this); 
    	zip_field.addActionListener(this); 
		next_button.addActionListener(this);    	
		back_button.addActionListener(this);
		quit_button.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == next_button)
		{
			if (city_field.getText().equals("") || street_field.getText().equals("") ||
					apartment_field.getText().equals("") || zip_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			String address = city_field.getText() + "-" + street_field.getText() + "-" + 
					apartment_field.getText() + "-" + zip_field.getText();
			
			my_string_to_send = my_string_to_send + "-" + address;
			
			Client_System.my_payment_form = new Form_payment(my_string_to_send);
			Client_System.my_payment_form.setVisible(true);
			this.setVisible(false);
			return;
		}
		
		if(action.getSource() == back_button)
		{
			Client_System.my_sub_form.setVisible(true);
			this.dispose();
		}
		
		if(action.getSource() == quit_button)
		{
			my_string_to_send = "quit";
			Client_System.my_to_Server_stream.println(my_string_to_send);		
			this.dispose();		
			return;		
		}		
	}	
}
