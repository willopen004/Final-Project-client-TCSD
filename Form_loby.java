package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Form_loby extends JFrame implements ActionListener {
	JTextArea screen_field = new JTextArea("");
	int deliveries_left = 0;
    JButton orders_button = new JButton("Orders");
    JButton chat_button = new JButton("Chat");  
    JButton back_button = new JButton("Back");   
    JButton quit_button = new JButton("Quit");   
	static String my_string_to_send;
    
	public Form_loby()
    {

    }
	
	public Form_loby(String string)
    {
    	super("Client: loby form"); // headline of window
    	Add_Panels();
    	Add_Function();
    	deliveries_left = Client_System.deliveries_left;
    	this.screen_field.setText(string);
    }
    
    private void Add_Panels()
    {
    	JPanel fields_panel = new JPanel(new GridLayout(1,1)); // Creating the first panel	
    	screen_field.setPreferredSize(new Dimension(200,200));
    	fields_panel.add(screen_field);
    	
    	JPanel buttons_panel = new JPanel(new GridLayout(1,4)); // Creating the second panel
    	buttons_panel.add(orders_button);
    	buttons_panel.add(chat_button);
    	buttons_panel.add(back_button);
    	buttons_panel.add(quit_button);
    	
    	setLayout(new BorderLayout()); // insert panels to the main layout
    	add(fields_panel, BorderLayout.NORTH);
    	add(buttons_panel, BorderLayout.SOUTH);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.pack();
    }
    
    private void Add_Function()
    {
    	orders_button.addActionListener(this);    	
    	chat_button.addActionListener(this);    	
    	back_button.addActionListener(this);
    	quit_button.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == orders_button)
		{
			Client_System.my_order_form = new Form_Order();
			Client_System.my_order_form.order_sub_id_field.setText(Client_System.my_id);
			Client_System.my_order_form.order_sub_id_field.setEditable(false);			
			Client_System.my_order_form.setVisible(true);
			this.setVisible(false);
		}
		if(action.getSource() == chat_button)
		{
			Client_System.my_chat_form = new Form_client_chat();
			Client_System.my_chat_form.setVisible(true);
			this.setVisible(false);
		}
		
		if(action.getSource() == back_button)
		{
			Client_System.my_main_form.setVisible(true);
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
