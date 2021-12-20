package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Form_Sub extends JFrame implements ActionListener {
	// create all elements of first Form
		JTextArea screen_field = new JTextArea("");
		JTextField first_name_field = new JTextField(10);
		JTextField last_name_field = new JTextField(10);
		JTextField id_field = new JTextField(10);
		JTextField phone_number_field = new JTextField(10);
		JTextField password_field = new JTextField(10);
		JLabel first_name_Label = new JLabel("First Name:");
	    JLabel last_name_Label = new JLabel("Last Name:");
	    JLabel id_Label = new JLabel("ID Number:");
	    JLabel phone_number_Label = new JLabel("Phone Number:");
	    JLabel password_Label = new JLabel("Password:");
	    JButton submit = new JButton("Submit");
	    JButton back = new JButton("Back");
	    JButton quit = new JButton("Quit");
	    String my_string_to_send;
	    Form_Main my_main_form;    
	    Socket my_socket;
	    
	    public Form_Sub(Form_Main My_main_form)
	    {
	    	super("Client: Subscription form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
	    private void Add_Panels()
	    {
	    	JPanel info_panel = new JPanel(new GridLayout(1,1)); // Creating the first panel	
	    	screen_field.setPreferredSize(new Dimension(200,50));
	    	String string = "Please fill your personal details";
	    	screen_field.setText(string);
	    	screen_field.setEditable(false);
	    	info_panel.add(screen_field);
	    	
	    	JPanel fields_panel = new JPanel(new GridLayout(5,2)); // Creating the first panel
	    	fields_panel.add(first_name_Label);
	    	fields_panel.add(first_name_field);
	    	fields_panel.add(last_name_Label);
	    	fields_panel.add(last_name_field);
	    	fields_panel.add(id_Label);
	    	fields_panel.add(id_field);
	    	fields_panel.add(phone_number_Label);
	    	fields_panel.add(phone_number_field);
	    	fields_panel.add(password_Label);
	    	fields_panel.add(password_field);

	    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the second panel
	    	buttons_panel.add(submit);
	    	buttons_panel.add(quit);
	    	buttons_panel.add(back);
	    	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	add(info_panel, BorderLayout.NORTH);
	    	add(fields_panel, BorderLayout.CENTER);
	    	add(buttons_panel, BorderLayout.SOUTH);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.pack();
	    }
	    
	    private void Add_Function()
	    {
	    	first_name_field.addActionListener(this);
	    	last_name_field.addActionListener(this);
	    	id_field.addActionListener(this);
	    	phone_number_field.addActionListener(this);
	    	password_field.addActionListener(this);
	        submit.addActionListener(this); 
	        quit.addActionListener(this); 
	        back.addActionListener(this); 
	    }
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == submit)
		{
			if (first_name_field.getText().equals("") || last_name_field.getText().equals("") ||
					id_field.getText().equals("") || phone_number_field.getText().equals("") || password_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			// chain all the fields to a single String to sent
			my_string_to_send = "new_subscriber" + "-" + first_name_field.getText() + "-" + last_name_field.getText() + "-" + 
					id_field.getText() + "-" + phone_number_field.getText() + "-" + password_field.getText();
			
			Client_System.my_address_form = new Form_address(my_string_to_send);
			Client_System.my_address_form.setVisible(true);
			this.setVisible(false);
			return;
			
		}
			
		if(action.getSource() == back)
		{			
			Client_System.my_main_form.setVisible(true);
			this.dispose();
		}
		
		if(action.getSource() == quit)
		{
			my_string_to_send = "quit";
			Client_System.my_to_Server_stream.println(my_string_to_send);
			Client_System.my_string_to_send = "quit";
			this.dispose();
		}		
	}
}
