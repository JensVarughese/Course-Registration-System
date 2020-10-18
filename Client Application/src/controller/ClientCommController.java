package controller;
import view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class is the client, which represents one user's connection with the server. It sends instructions to the server to perform desired functionality.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class ClientCommController {
	
	/**
	 * The ip address of the server.
	 * In order to run on separate machines, the server will run on a network that is not NAT.
	 */
	private final static String IPADDRESS = "localhost";

	/** 
	 * A socket variable.
	 * */
	private Socket aSocket; 
	
	/** 
	 * A PrintWriter variable to write to the stream.
	 * */
	private PrintWriter socketOut; 
	
	/** 
	 * A BufferedReader variable to read from the stream. 
	 * */
	private BufferedReader socketIn; 
	
	/** 
	 * A GUIController variable that represents the GUI
	 * */
	private GUIController gui; 
	
	/** 
	 * This class constructor initializes aSocket, socketIn, and socketOut using the given parameters.
	 * 
	 * @param serverName, the name of the server.
	 * @param portNumber, the port number.
	 * */
	public ClientCommController (String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			//Socket input stream
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/** 
	 * This function communicates with the client by reading a response. 
	 * */
	public void communicate()  
	{
		String response = "";
		login(); //calling the function to prompt the user to login
		boolean running = true;
		while (running) 
		{
			try 
			{	
				response = socketIn.readLine(); //reading a response from the server
				gui.displayData(response); //displaying this response by calling a function in the GUI
				
			} catch (IOException e) 
			{
				System.out.println("Sending error: " + e.getMessage());
			}
			
		}
		try 
		{
			socketIn.close();
			socketOut.close();
		} catch (IOException e) 
		{
			System.out.println("Closing error: " + e.getMessage());
		}

	}
	
	/** 
	 * This function prompts the user to login and writes their data to the socket.
	 * */
	private void login()
	{
		socketOut.println(gui.getView().getStudentName());
		socketOut.println(gui.getView().getStudentId());
	}
	
	/** 
	 * This is the main function, which initializes the client, creates a new GUI, displays the menu, and begins communication with the server.
	 * 
	 * @param args, the system arguments.
	 * @throws IOException if there is a problem initializing the client.
	 * */
	public static void main(String []args) throws IOException{
		ClientCommController myClient = new ClientCommController(IPADDRESS, 9898); 
		myClient.gui = new GUIController(myClient.aSocket);
		myClient.gui.displayMenu();
		myClient.communicate(); 
	}

}
