package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommController {

	private Socket aSocket; 
	private PrintWriter socketOut; //for writing to the stream
	private BufferedReader socketIn; 
	//private BufferedReader stdIn; 
	
	private GUIController gui; //this is an object of type GUIController that receives data from the user (eg. a course
	//they want to add). ClientCommController must take this data, make it into one string, and send to server
		
	//private String stringToSend; //this is the string received from the GUI to be sent to the server
	
	public ClientCommController (String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			//keyboard input stream
			//stdIn = new BufferedReader(new InputStreamReader(System.in)); 
			//Socket input stream
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void communicate() { //to communicate with the server
		String line = ""; 
		String response = ""; 
		while(!line.equals("QUIT")) {
			try {
				gui.displayMenu; //displays the main menu of the program for the user so they can make a decision
				line = gui.getServerInput(); //takes the GUI data and makes it into a String
				socketOut.write(line); //sends this string to the server
				
				response = socketIn.readLine(); //reads either a success message or an error message from the server socket
				gui.setServerOutput(response); //sets the output string to be used for display in the GUI
				gui.displayMessage(response); //displays a message for the user indicating a success or failure

			} catch(IOException e) {
				e.getStackTrace(); 
			}
		}
		
		try {
			socketIn.close(); 
			socketOut.close(); 
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	//this function is supposed to take the switch number (1 for add, 2 for remove, 3 for search),
	//the course data (name, id, section number),
	//the student data (name, id)
	//it puts all this data into the following form: "switch-courseName-courseId-courseSection-studentName-studentId" in ONE string
	//if user clicks the QUIT button, it simply creates a string of this form: "QUIT"
	public String makeString() { 
	
	}
	
	public static void main(String []args) throws IOException{
		ClientCommController myClient = new ClientCommController("localhost", 9898); 
		myClient.communicate(); 
	}

}
