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


public class Form_destination extends JFrame implements ActionListener {
	// create all elements of first Form
	JTextArea screen_field = new JTextArea("");
    JLabel city_Label = new JLabel("City:");
	JTextField city_field = new JTextField(16);
    JLabel street_Label = new JLabel("Street:");
	JTextField street_field = new JTextField(16);
    JLabel apartment_Label = new JLabel("apartment number:");
	JTextField apartment_field = new JTextField(16);
    JLabel zip_Label = new JLabel("Zip code:");
	JTextField zip_field = new JTextField(16);
	JButton send_drone = new JButton("Send drone");  
	static String my_string_to_send;
 
    Socket my_socket;
    Form_Main my_main_form;
    String my_drone_by_serial;
    String destination_id = null;
	    
	    public String getMy_drone_by_serial() {
			return my_drone_by_serial;
		}

		public void setMy_drone_by_serial(String my_drone_by_serial) {
			this.my_drone_by_serial = my_drone_by_serial;
		}

		public Form_destination()
	    {
	    	super("Client: destination form"); // headline of window
	    	Add_Panels();
	    	Add_Function();
	    }
	    
		private void Add_Panels()
	    {
	    	JPanel info_panel = new JPanel(new GridLayout(1,1)); // Creating the first panel	
	    	screen_field.setPreferredSize(new Dimension(200,50));
	    	String string = "This is the current address of the subscriber, \nload the package and send the drone,\n or change the address and then send the drone";
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
	    	
	    	JPanel buttons_panel = new JPanel(new GridLayout(1,1)); // Creating the second panel	
	    	buttons_panel.add(send_drone);

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
	    	send_drone.addActionListener(this);    	
	    }
	    
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == send_drone)
		{
			if (city_field.getText().equals("") || street_field.getText().equals("") ||
					apartment_field.getText().equals("") || zip_field.getText().equals(""))
			{
				System.out.println("not enough details!");
				return;
			}
			
			this.setVisible(false);
			
			// chain all the fields to a single String to sent
			my_string_to_send = "drone_destination_id" + "-" + this.destination_id +
					"-" + my_drone_by_serial + "-" + Client_System.getMy_id() + "-" + city_field.getText() + "-" + street_field.getText() + "-" + 
					apartment_field.getText() + "-" + zip_field.getText();
			System.out.println(my_string_to_send);
			Client_System.my_to_Server_stream.println(my_string_to_send);	
			Client_System.deliveries_left = Client_System.deliveries_left - 1;
			String string = "Welcome " + Client_System.getMy_first_name() + " " + Client_System.getMy_last_name() + ",\n" +
					"you have " + Client_System.deliveries_left + " deliveries left! \n" +  "what would you like to do?";
			
			Client_System.my_loby_form.screen_field.setText(string);
			Client_System.my_loby_form.setVisible(true);
			return;
		}
		}
	}

