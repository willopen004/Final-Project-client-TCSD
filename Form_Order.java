package Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Form_Order extends JFrame implements ActionListener {
	// create all elements of first Form
		JTextField order_sub_id_field = new JTextField(10);
		JTextField order_des_id_field= new JTextField(10);
	    JLabel order_sub_id_Label = new JLabel("Subscriber ID:");
	    JLabel order_des_id_Label = new JLabel("Destination Subscriber ID:");
	    JButton submit = new JButton("Submit");
	    JButton my_orders = new JButton("My orders");
	    JButton back = new JButton("Back");
	    JButton quit = new JButton("Quit");    
	    String my_string_to_send;    
	    Socket my_socket;
	    Form_Main my_main_form;
	    
	    public Form_Order()
	    {
	    	super("Client: Order form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
	    private void Add_Panels()
	    {
	    	JPanel fields_panel = new JPanel(new GridLayout(5,2)); // Creating the first panel

	    	fields_panel.add(order_sub_id_Label);
	    	fields_panel.add(order_sub_id_field);
	    	fields_panel.add(order_des_id_Label);
	    	fields_panel.add(order_des_id_field);


	    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the second panel
	    	buttons_panel.add(submit);
	    	buttons_panel.add(my_orders);
	    	buttons_panel.add(back);
	    	buttons_panel.add(quit);
	    	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	add(buttons_panel, BorderLayout.SOUTH);
	    	add(fields_panel, BorderLayout.NORTH);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.pack();
	    }
	    
	    private void Add_Function()
	    {
	    //	order_num_field.addActionListener(this);
	    	order_sub_id_field.addActionListener(this);
	    	order_des_id_field.addActionListener(this);
	   // 	order_date_field.addActionListener(this);
	        submit.addActionListener(this); 
	        quit.addActionListener(this); 
	        my_orders.addActionListener(this);
	        back.addActionListener(this);
	    }
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == submit)
		{
			if (order_sub_id_field.getText().equals("") || order_des_id_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			// chain all the fields to a single String to sent
			my_string_to_send = "New_Order" + "-" + order_sub_id_field.getText() + "-" + order_des_id_field.getText();

			System.out.println("whats the problem?");
			//System.out.println(my_string_to_send);
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);
			
			System.out.println("whats the problem here?");
			
			// clear all the text fields
			//order_num_field.setText("");
			order_sub_id_field.setText("");
			order_des_id_field.setText("");
			//order_date_field.setText("");
			this.setVisible(false);
			return;
			
		}
		
		if(action.getSource() == my_orders)
		{
			this.setVisible(false);
			Client_System.my_orders_form = new Form_my_orders();
			Client_System.my_orders_form.setVisible(true);	
			my_string_to_send = "get all my orders";
			Client_System.my_to_Server_stream.println(my_string_to_send);
			return;
		}
			
		if(action.getSource() == quit)
		{
			my_string_to_send = "quit";
			Client_System.my_to_Server_stream.println(my_string_to_send);
			Client_System.my_string_to_send = "quit";
			this.dispose();
		}
		
		if(action.getSource() == back)
		{			
			Client_System.my_loby_form.setVisible(true);
			this.dispose();
		}
		
	}
}

