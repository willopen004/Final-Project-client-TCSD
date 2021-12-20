package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Form_my_orders extends JFrame implements ActionListener {
	// create all elements of first Form
		JLabel all_my_orders_label = new JLabel("All Of My Orders");
		JTextArea screen_field = new JTextArea("");
	    JButton all_orders_sent = new JButton("All Orders Sent");
	    JButton all_orders_recived = new JButton("All Orders Recived");
	    JButton back = new JButton("Back");
	    JButton quit = new JButton("Quit");    
	    String my_string_to_send;    
	    Socket my_socket;
	    Form_Main my_main_form;
	    
	    public Form_my_orders()
	    {
	    	super("Client: all Orders form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
	    private void Add_Panels()
	    {
	    	JPanel fields_panel = new JPanel(new BorderLayout()); // Creating the first panel
	    	fields_panel.add(all_my_orders_label, BorderLayout.NORTH);
	    	JScrollPane scroll = new JScrollPane (screen_field, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    	scroll.setMinimumSize(new Dimension(160, 200));
	    	scroll.setPreferredSize(new Dimension(160, 200));
	    	fields_panel.add(scroll, BorderLayout.SOUTH);




	    	JPanel buttons_panel = new JPanel(new FlowLayout()); // Creating the second panel
	    	buttons_panel.add(all_orders_sent);
	    	buttons_panel.add(all_orders_recived);
	    	buttons_panel.add(back);
	    	buttons_panel.add(quit);
	    	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	add(buttons_panel, BorderLayout.SOUTH);
	    	add(fields_panel, BorderLayout.CENTER);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	        
	        
	  
	    	this.pack();
	    }
	    
	    private void Add_Function()
	    {
	    	all_orders_sent.addActionListener(this);
	    	all_orders_recived.addActionListener(this);
	    	back.addActionListener(this);
	    	quit.addActionListener(this);
	    	
	    }
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == all_orders_sent)
		{
			
			// chain all the fields to a single String to sent
			my_string_to_send = "all orders sent" + "-" + Client_System.getMy_id();
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);
			return;
			
		}
		
		if(action.getSource() == all_orders_recived)
		{
			my_string_to_send = "all orders recived" + "-" + Client_System.getMy_id();
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);
			return;
		}
			
		if(action.getSource() == back)
		{
			Client_System.my_order_form.setVisible(true);
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
	
	public static void main(String[] args) throws IOException {
		Form_my_orders My_New_Orders_Form = new Form_my_orders();
		My_New_Orders_Form.setVisible(true);
	}
	}


