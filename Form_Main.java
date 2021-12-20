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


public class Form_Main extends JFrame implements ActionListener {
	// create all elements of first Form
		JTextField subscriber_id_field = new JTextField(10);
		JLabel headline_Label = new JLabel("Welcome To Drone Post!");
		JTextArea screen_field = new JTextArea("");
		JLabel subscriber_id_Label = new JLabel("Subscriber ID:");
		JTextField subscriber_password_field = new JTextField(10);
	    JLabel subscriber_password_Label = new JLabel("Password:");
	    JButton enter = new JButton("Enter");
	    JButton make_new_subscriber = new JButton("Register new subscriber");
	    JButton quit = new JButton("Quit");
	    String my_string_to_send;
	      
	    public Form_Main()
	    {
	    	super("Client: Registration form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
	    private void Add_Panels()
	    {
	    	JPanel screen_panel = new JPanel(new BorderLayout()); // Creating the first panel
	    	screen_panel.add(headline_Label, BorderLayout.NORTH);
	    	screen_field.setPreferredSize(new Dimension(50,200));
	    	screen_panel.add(screen_field, BorderLayout.SOUTH);
	    	
	    	JPanel fields_panel = new JPanel(new GridLayout(2,2)); // Creating the first panel
	    	fields_panel.add(subscriber_id_Label);
	    	fields_panel.add(subscriber_id_field);
	    	fields_panel.add(subscriber_password_Label);
	    	fields_panel.add(subscriber_password_field);

	    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the second panel
	    	buttons_panel.add(enter);
	    	buttons_panel.add(make_new_subscriber);
	    	buttons_panel.add(quit);
	    	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	add(screen_panel,BorderLayout.NORTH); 
	    	add(buttons_panel, BorderLayout.SOUTH);
	    	add(fields_panel, BorderLayout.CENTER);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.pack();
	    }
	    
	    private void Add_Function()
	    {
	    	subscriber_id_field.addActionListener(this);
	    	subscriber_password_field.addActionListener(this);
	    	enter.addActionListener(this);
	    	make_new_subscriber.addActionListener(this);
	    	quit.addActionListener(this);
	    }
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == enter)
		{
			if (subscriber_id_field.getText().equals("") || subscriber_password_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			// chain all the fields to a single String to sent
			my_string_to_send = "existing_subscriber" + "-" + subscriber_id_field.getText() + "-" + subscriber_password_field.getText();
			Client_System.setMy_id(subscriber_id_field.getText());
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);		
			return;
		}
		
		if(action.getSource() == make_new_subscriber)
		{
			Client_System.my_sub_form = new Form_Sub(this);
			Client_System.my_sub_form.setVisible(true);
			this.setVisible(false);		
			return;
		}			
		
		if(action.getSource() == quit)
		{
			my_string_to_send = "quit";
			Client_System.my_to_Server_stream.println(my_string_to_send);		
			this.dispose();
			return;
		}		
	}
}
