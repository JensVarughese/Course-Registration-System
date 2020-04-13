package controller;

import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommController {

	private Socket aSocket; 
	private PrintWriter socketOut; //for writing to the stream
	private BufferedReader socketIn; 
	private ServerSocket serverSocket; 
	
	private CourseCatalogue cat;
	private Student user;
	
	private String[] data = new String[4]; 
	
	public ServerCommController(int port) {
		try {
			serverSocket = new ServerSocket(port); 
			System.out.println("Server is now running.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
 
	public void start() {
		String line = null; 
		int selection = 0;
		
		String name = "";
		int id = 0;
		try {
			name = socketIn.readLine();
			id = Integer.parseInt(socketIn.readLine());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		user = newStudent(name, id); //creating a new student with the data given
		
		while(true) {
			try {
				line = socketIn.readLine();
				if(line == "" || line == null)
					return;

					makeArray(line); 
					selection = Integer.parseInt(data[0]); //making the switch string into a number
					selectFunctionality(selection); 	//sending the output of the switch statement back to the Client			
		
			} catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
	
	public Student newStudent(String name, int id)
	{
		for(Student s: cat.getStudentList())
		{
			if(s.getStudentName().equals(name) && s.getStudentId() == id)
			{
				return s;
			}
		}
		return new Student(name, id);
	}
	
	public void makeArray(String input) {	//this function splits the incoming string from the client into separate words, saves into "data"
		String[] array = input.split("_"); 
		for(int i = 0; i < array.length; i++) {
			data[i] = array[i]; 
		}

	}
	
	public void selectFunctionality(int selection) {
		switch(selection){
			case 1:
				printCourse( data[1], Integer.parseInt(data[2]) );
				break;
			case 2:
				addCourse( data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]) );
				break;
			case 3:
				socketOut.println( user.removeCourse(data[1], Integer.parseInt(data[2])) );
				break;
			case 4:
				socketOut.println(cat);
				break;
			case 5:
				socketOut.println(user.returnCourses());
				break;
			default:
				socketOut.println("Error");
				break;
		}
	}
	
	private void printCourse(String name, int num) {
		Course c = cat.searchCat(name, num);
		if(c == null)
			socketOut.println("Course not found!");
		else
			if(c.getClassSize() == false)
				socketOut.println(c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.");
			else
				socketOut.println(c);
	}
	
	private void addCourse(String name, int num, int sec) {
		if(user.checkCourseAmount() == false) {
			socketOut.println("You can only register in a maximum of 6 courses!");
			return;
		}else {
			Course c = cat.searchCat(name, num);
			if(c != null) {
				if(c.getClassSize() == false) {
					socketOut.println("\n"+c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.");
					return;
				}
				else {
					socketOut.println(user.addCourse(c, sec));
					return;
				}
			}
			else {
				socketOut.println("Course not found!");
				return;
			}
		}
	}

	
	public static void main(String [] args) throws IOException{		
		try {
			ServerCommController myServer = new ServerCommController(9898); 
			myServer.cat = new CourseCatalogue(); 
			myServer.aSocket = myServer.serverSocket.accept(); 
			System.out.println("Connection accepted by server"); 
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true); 
			
			myServer.start(); 
		
			myServer.socketIn.close(); 
			myServer.socketOut.close();
		} catch(IOException e) {
			e.getStackTrace();
		}
	}

}
