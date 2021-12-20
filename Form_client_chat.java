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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Form_client_chat extends JFrame implements ActionListener {
	// create all elements of first Form
	JLabel destination_id_label = new JLabel("Destination user:");
	JTextField destination_id_field = new JTextField(10);
	JTextArea screen_field = new JTextArea("");
	JTextArea message_field = new JTextArea("");
    JButton send_message = new JButton("Send message");
    JButton back_button = new JButton("Back");
    JButton quit_button = new JButton("Quit");  
	JLabel chat_label = new JLabel("Chat:");
	JLabel message_label = new JLabel("Your message:");
    String my_string_to_send;    
    Socket my_socket;
    Form_Main my_main_form;
	    

		public Form_client_chat()
	    {
	    	super("Client: chat form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
		private void Add_Panels()
	    {
	    	JPanel fields_panel = new JPanel(new GridLayout(1,2)); // Creating the first panel	
	    	destination_id_label.setPreferredSize(new Dimension(10,20));
	    	destination_id_field.setPreferredSize(new Dimension(10,20));
	    	fields_panel.add(destination_id_label);
	    	fields_panel.add(destination_id_field);

	    	JPanel chat_panel = new JPanel(new GridLayout(4,1)); // Creating the second panel
	    	screen_field.setPreferredSize(new Dimension(1000,1000));
	    	JScrollPane scroll = new JScrollPane (screen_field, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    	scroll.setMinimumSize(new Dimension(200, 100));
	    	scroll.setPreferredSize(new Dimension(200, 100));
	    	message_field.setPreferredSize(new Dimension(200,100));
	    	chat_label.setMaximumSize(new Dimension(10,10));
	    	message_label.setMaximumSize(new Dimension(10,10));
	    	chat_panel.add(chat_label);
	    	chat_panel.add(scroll);
	    	chat_panel.add(message_label);
	    	chat_panel.add(message_field);
	    	screen_field.setEditable(false);
	    	
	    	
	    	JPanel buttons_panel = new JPanel(new GridLayout(1,3)); // Creating the third panel
	    	buttons_panel.add(send_message);
	    	buttons_panel.add(back_button);
	    	buttons_panel.add(quit_button);
	    	
	    	setLayout(new BorderLayout()); // insert panels to the main layout
	    	fields_panel.setMaximumSize(new Dimension(10,20));
	    	chat_panel.setMaximumSize(new Dimension(200,100));
	    	add(fields_panel, BorderLayout.NORTH);
	    	add(chat_panel, BorderLayout.CENTER);
	    	add(buttons_panel, BorderLayout.SOUTH);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.pack();
	    }
		
	    private void Add_Function()
	    {
	    	destination_id_field.addActionListener(this);    	
	    	send_message.addActionListener(this);    	
	    	back_button.addActionListener(this);
	    	quit_button.addActionListener(this);
	    }
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == send_message)
		{
			if (destination_id_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			// chain all the fields to a single String to sent
			my_string_to_send = "message to client" + "-" + destination_id_field.getText() +
					"-" + Client_System.getMy_id() + "-" + message_field.getText();
			screen_field.append("I said: " + message_field.getText());
			screen_field.append("\n");
			message_field.setText("");
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);			
			return;		
		}
		
		if(action.getSource() == back_button)
		{
			Client_System.my_loby_form.setVisible(true);
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
