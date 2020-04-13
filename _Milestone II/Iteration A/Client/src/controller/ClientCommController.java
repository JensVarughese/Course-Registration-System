package controller;
import view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommController {

	private Socket aSocket; 
	private PrintWriter socketOut; //for writing to the stream
	private BufferedReader socketIn; 
	
	private GUIController gui; //this is an object of type GUIController that receives data from the user (eg. a course
	//they want to add). ClientCommController must take this data, make it into one string, and send to server
	
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

	public void communicate()  
	{
		String response = "";
		login();
		boolean running = true;
		while (running) 
		{
			try 
			{	
				response = socketIn.readLine();
				gui.displayData(response);
				
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
	
	private void login()
	{
		socketOut.println(gui.getView().getStudentName());
		socketOut.println(gui.getView().getStudentId());
	}
	
	public static void main(String []args) throws IOException{
		ClientCommController myClient = new ClientCommController("localhost", 9898); 
		myClient.gui = new GUIController(myClient.aSocket);
		myClient.gui.displayMenu();
		myClient.communicate(); 
	}

}
