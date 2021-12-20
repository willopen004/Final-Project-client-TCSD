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


public class Form_acceptance extends JFrame implements ActionListener {
	// create all elements of first Form
		JLabel form_Label = new JLabel("A drone has arrived with a package for you!");
	    JButton send_drone = new JButton("Accept Package");  
	    String my_string_to_send;    
	    Socket my_socket;
	    Form_Main my_main_form;
	    String my_drone_by_serial;
	    

		public Form_acceptance()
	    {
	    	super("Client: acceptance form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
	    private void Add_Panels()
	    {
	    	JPanel buttons_panel = new JPanel(new GridLayout(2,1)); // Creating the second panel
	    	buttons_panel.add(form_Label); 
	    	buttons_panel.add(send_drone); 	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	add(buttons_panel, BorderLayout.CENTER);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.pack();
	    }
	    
	    private void Add_Function()
	    {
	    	send_drone.addActionListener(this);
	    }
	    
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == send_drone)
		{			
			// chain all the fields to a single String to sent
			my_string_to_send = "package recived!" + "-" + Client_System.my_split_string[1] + "-" + Client_System.my_id;
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);	
			this.setVisible(false);
			return;		
		}
	}
}
