package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Form_payment extends JFrame implements ActionListener {
	String deliveries[]={"99$ : 50 deliveries","179$ : 150 deliveries", "monthly 150$ : 100 deliveries"}; 
	JTextArea screen_field = new JTextArea("");
    JLabel deliveries_Label = new JLabel("package options:");
    JComboBox deliveries_combo = new JComboBox(deliveries);  
    JLabel credit_Label = new JLabel("Credit number:");
	JTextField credit_field = new JTextField(16);
    JLabel credit_date_Label = new JLabel("Credit exp date:");
	JTextField credit_date_field = new JTextField(16);
    JLabel credit_back_Label = new JLabel("Credit back code:");
	JTextField credit_back_field = new JTextField(16);
    JButton finish_button = new JButton("Finish");  
    JButton back_button = new JButton("Back");   
    JButton quit_button = new JButton("Quit");  
	static String my_string_to_send;
    
	public Form_payment(String my_string_to_send1)
    {
    	super("Client: payment form"); // headline of window
    	Add_Panels();
    	Add_Function();
    	my_string_to_send = my_string_to_send1;
    }
	
	public Form_payment()
	{
    	super("Client: payment form"); // headline of window
    	Add_Panels();
    	Add_Function();
	}
    
    private void Add_Panels()
    {
    	JPanel info_panel = new JPanel(new GridLayout(1,1)); // Creating the first panel	
    	screen_field.setPreferredSize(new Dimension(200,50));
    	String string = "Please pick a deliveries package" + "\n" + "and fill your credit details";
    	screen_field.setText(string);
    	screen_field.setEditable(false);
    	info_panel.add(screen_field);
    	
    	JPanel fields_panel = new JPanel(new GridLayout(4,2)); // Creating the second panel
    	fields_panel.add(deliveries_Label);
    	fields_panel.add(deliveries_combo);
    	fields_panel.add(credit_Label);
    	fields_panel.add(credit_field);
    	fields_panel.add(credit_date_Label);
    	fields_panel.add(credit_date_field);
    	fields_panel.add(credit_back_Label);
    	fields_panel.add(credit_back_field);
    	
    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the second panel	
    	buttons_panel.add(finish_button);
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
    	deliveries_combo.addActionListener(this); 
    	credit_field.addActionListener(this); 
    	credit_date_field.addActionListener(this); 
    	credit_back_field.addActionListener(this); 
		finish_button.addActionListener(this);    	
		back_button.addActionListener(this);
		quit_button.addActionListener(this);
    }
    
    

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == finish_button)
		{
			if (credit_field.getText().equals("") || credit_date_field.getText().equals("") ||
					credit_back_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			String payment = (String)deliveries_combo.getSelectedItem() + "-" + credit_field.getText() + "-" + 
					credit_date_field.getText() + "-" + credit_back_field.getText();
			
			my_string_to_send = my_string_to_send + "-" + payment;
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);		
			Client_System.my_main_form.setVisible(true);
			this.dispose();
			return;
		}
		
		if(action.getSource() == back_button)
		{
			Client_System.my_chat_form = new Form_client_chat();
			Client_System.my_chat_form.setVisible(true);
			this.setVisible(false);
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