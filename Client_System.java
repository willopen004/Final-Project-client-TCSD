package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client_System {
	Socket my_socket;
	static DataInputStream my_from_Server_stream;
	static PrintStream my_to_Server_stream;
	static String my_string_to_send;
	static String my_id;
	static String my_first_name = null;
	static String my_last_name = null;
	String my_string_from_server;
	static int deliveries_left;
	static Form_Main my_main_form = new Form_Main();
	static Form_Sub my_sub_form = new Form_Sub(my_main_form);
	static Form_Order my_order_form = new Form_Order();
	static Form_destination my_destination_form = new Form_destination();
	static Form_acceptance my_acceptance_form = new Form_acceptance();
	static Form_my_orders my_orders_form = new Form_my_orders();
	static Form_client_chat my_chat_form = new Form_client_chat();
	static Form_loby my_loby_form = new Form_loby();
	static Form_payment my_payment_form = new Form_payment();
	static Form_address my_address_form = new Form_address();
	static String[] my_split_string;
	

	public static String getMy_id() {
		return my_id;
	}


	public static void setMy_id(String my_id) {
		Client_System.my_id = my_id;
	}


	public static String getMy_first_name() {
		return my_first_name;
	}


	public static void setMy_first_name(String my_first_name) {
		Client_System.my_first_name = my_first_name;
	}


	public static String getMy_last_name() {
		return my_last_name;
	}


	public static void setMy_last_name(String my_last_name) {
		Client_System.my_last_name = my_last_name;
	}


	Client_System()
	{

	}

	
	public static String[] Split_String(String my_String)
	{
		String StringToSplit = my_String;
		String[] Array_of_split_String = StringToSplit.split("-");
		return Array_of_split_String;
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	public void client_management() throws IOException
	{
		Socket socket = null;
		socket = new Socket("localhost",7000);
		DataInputStream fromServer;
		PrintStream toServer;
		String string_from_server="";
		String my_id = null;
		String[] split_string;
		System.out.println("Connected to server..." + socket.getInetAddress() + " " + socket.getPort()); 
		fromServer = new DataInputStream(socket.getInputStream());
		toServer = new PrintStream(socket.getOutputStream());
		my_to_Server_stream = toServer;
		string_from_server = fromServer.readLine();		
		System.out.println("Recieved from server..." + string_from_server);		
		do
		{
			System.out.println("What to send?");
			split_string = Split_String(string_from_server);
			my_split_string = split_string;		
			switch(split_string[0])
			{
				
				case ("Welcome to our server"):
				{
					my_main_form = new Form_Main();
					my_main_form.setVisible(true);	
					string_from_server = fromServer.readLine();	
					break;
				}
				
				case ("new subscriber created!"):
				{
					my_main_form.setVisible(true);	
					string_from_server = fromServer.readLine();	
					break;
				}
				
				case ("wrong password!"):
				{
					my_main_form.subscriber_password_field.setText("");
					my_main_form.screen_field.setText("wrong password! please enter another!");
					string_from_server = fromServer.readLine();	
					break;
				}
					
				
				case ("subscriber found"):
				{
					String string = "Welcome " + split_string[2] + " " + split_string[3] + ",\n" +
							"you have " + split_string[4] + " deliveries left! \n" +  "what would you like to do?";
					Client_System.my_first_name = split_string[2];
					Client_System.my_last_name = split_string[3];
					my_loby_form = new Form_loby(string);
					my_main_form.setVisible(false);	
					my_loby_form.setVisible(true);
					deliveries_left = Integer.parseInt(split_string[4]);
					my_id = split_string[1];
					string_from_server = fromServer.readLine();	
					break;
				}
			
		
				case ("subscriber not found!"):
				{	
					my_main_form.subscriber_password_field.setText("");
					my_main_form.subscriber_id_field.setText("");
					my_main_form.screen_field.setText("id " + split_string[1] +  " not found! please enter another!");
					string_from_server = fromServer.readLine();	
					break;
				}
				
				case ("your payment details has been updated!"):
				{	
					String string_for_main = "Welcome " + split_string[2] + " " + split_string[3] + ",\n" +
							"you have " + split_string[4] + " deliveries left! \n" +  "what would you like to do?";
					Client_System.my_first_name = split_string[2];
					Client_System.my_last_name = split_string[3];
					deliveries_left = Integer.parseInt(split_string[4]);
					my_loby_form.screen_field.setText(string_for_main);
					my_main_form.setVisible(false);	
					my_loby_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					break;
				}
						
				case ("subscriber found but you have no more deliveries left!"):					
				{
					String string_for_main = "Welcome " + split_string[2] + " " + split_string[3] + ",\n" +
							"you have no more deliveries left! \n" +  "what would you like to do?";
					Client_System.my_first_name = split_string[2];
					Client_System.my_last_name = split_string[3];
					my_loby_form = new Form_loby(string_for_main);
					my_main_form.setVisible(false);	
					my_loby_form.setVisible(true);
					String string_for_pay = "you have no more deliveries!" + "\n" +"Please pick a deliveries package" + "\n" + "and fill your credit details";
					my_string_to_send = "new payment";
					my_payment_form = new Form_payment(my_string_to_send);		
					my_payment_form.screen_field.setText(string_for_pay);
					my_payment_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					break;
				}
				
				case ("Drone arrived"):
				{
					my_destination_form = new Form_destination();
					my_destination_form.setMy_drone_by_serial(split_string[1]);	
					my_destination_form.destination_id = split_string[2];
					my_destination_form.city_field.setText(split_string[3]);
					my_destination_form.street_field.setText(split_string[4]);
					my_destination_form.apartment_field.setText(split_string[5]);
					my_destination_form.zip_field.setText(split_string[6]);
					my_destination_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					System.out.println("im not waiting");
					break;
				}
				
				case("your drone is lost!"):
				{
					System.out.println("proccess over!");
					 my_main_form.setVisible(false);
					 my_sub_form.setVisible(false);
					 my_order_form.setVisible(false);
					 my_destination_form.setVisible(false);
					 my_acceptance_form.setVisible(false);
					 my_orders_form.setVisible(false);
					 my_chat_form.setVisible(false);
					 my_payment_form.setVisible(false);
					 my_address_form.setVisible(false);
					 String string_to_text = "your package exchange between\n" + split_string[1] + " and " + split_string[2] +  " is lost!";
					 my_loby_form.setVisible(true);
					 my_loby_form.screen_field.setText(string_to_text);
					string_from_server = fromServer.readLine();	
					break;
				}
				
				case("you can't send that package"):
				{
					 my_main_form.setVisible(false);
					 my_sub_form.setVisible(false);
					 my_order_form.setVisible(false);
					 my_destination_form.setVisible(false);
					 my_acceptance_form.setVisible(false);
					 my_orders_form.setVisible(false);
					 my_chat_form.setVisible(false);
					 my_payment_form.setVisible(false);
					 my_address_form.setVisible(false);
					 String string_to_text = split_string[0] + split_string[1] + split_string[2] + split_string[3];
					 my_loby_form.setVisible(true);
					 my_loby_form.screen_field.setText(string_to_text);
					string_from_server = fromServer.readLine();	
					break;
				}
							
				case ("package delivered"):		
				{
					String string_to_text = "your package for " + split_string[1] + " has been delivered!";
					my_loby_form.screen_field.setText(string_to_text);
					my_loby_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("You got a package"):		
				{
					System.out.println("you got in the package!");
					my_acceptance_form = new Form_acceptance();
					my_acceptance_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("message from user"):		
				{
					Client_System.my_chat_form.destination_id_field.setText(split_string[2]);
					Client_System.my_chat_form.screen_field.append(split_string[4] + " said: " + split_string[3]);
					Client_System.my_chat_form.screen_field.append("\n");
					Client_System.my_chat_form.setVisible(true);
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("Client for chat not found!"):		
				{
					String string = "No such user! please insert another id!";
					my_chat_form.screen_field.setText(string);
					Client_System.my_chat_form.screen_field.append("\n");
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("order sent"):		
				{
					Client_System.my_orders_form.screen_field.setText("");
					do 
					{
						string_from_server = fromServer.readLine();	
						System.out.println(string_from_server);
						Client_System.my_orders_form.screen_field.append(string_from_server);
						Client_System.my_orders_form.screen_field.append("\n");
					} while (!string_from_server.equals("thats all the orders sent"));
					
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("order recived"):		
				{
					Client_System.my_orders_form.screen_field.setText("");
					do 
					{
						string_from_server = fromServer.readLine();	
						System.out.println(string_from_server);
						Client_System.my_orders_form.screen_field.append(string_from_server);
						Client_System.my_orders_form.screen_field.append("\n");
					} while (!string_from_server.equals("thats all the orders recived"));
					
					string_from_server = fromServer.readLine();	
					break;
				}	
				
				case ("goodbye!"):
				{
					System.out.println("proccess over!");
					socket.close();
					return;
				}
			} 	
			System.out.println("Recieved from server..." + string_from_server);	
		} while (string_from_server != "goodbye!");	
		
		System.out.println("im here also");
	}
	public static void main(String[] args) throws IOException {
		Client_System My_New_Client_system = new Client_System();
		My_New_Client_system.client_management();
	}
}