package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCommController {

	private Socket aSocket; 
	private PrintWriter socketOut; //for writing to the stream
	private BufferedReader socketIn; 
	private ServerSocket serverSocket; 
	
	private Student student; 
	private CourseCatalogue courseCatalogue; 
	
	//data[0] = switch number OR QUIT
	//data[1] = studentName
	//data[2] = studentId
	//data[3] = courseName
	//data[4] = courseId
	//data[5] = courseSection
	private ArrayList<String> data; 
	
	public ServerCommController(int port) {
		try {
			serverSocket = new ServerSocket(port); 
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
 
	public void doStuff() {
		String line = null; 
		int selection = 0;
		while(true) {
			try {
				line = socketIn.readLine(); 
//				if(line.equals("QUIT")) {
//					line = "Goodbye"; 
//					socketOut.write(line); 
//					break; 
//				}

					makeArray(line); 
					selection = Integer.parseInt(data.get(0)); //making the switch string into a number
					student = new Student(data.get(1), Integer.parseInt(data.get(2))); //creating a new student with the data given
					socketOut.write(selectFunctionality(selection)); 	//sending the output of the switch statement back to the Client			
		
			} catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
	
	
	public void makeArray(String input) {	//this function splits the incoming string from the client into separate words, saves into "data"
		String[] array = input.split("_"); 
		for(int i = 0; i < array.length; i++) {
			data.set(i, array[i]); 
		}

	}
	
	public String selectFunctionality(int selection) {
		Course course = courseCatalogue.searchCat(data.get(3), Integer.parseInt(data.get(4))); 
		switch(selection) {		
		case 1: //for adding a course 		
			Registration registration = new Registration(); 
			if(course == null)
				return "0_Course not found."; //tag indicates failure
			if(course.getCourseOffering(Integer.parseInt(data.get(5))) == null)
				return "0_Course registration failed."; //tag indicates failure
			registration.completeRegistration(student, course.getCourseOffering(Integer.parseInt(data.get(5))));			
			return "1_Course registration complete."; //tag indicates success, GUI should display new list of Student's courses
		case 2:	//for removing a course
			Registration registration2 = new Registration(); 
			if(course == null)
				return "0_Course not found in catalogue."; //tag indicates failure
			registration2.setTheStudent(student); 
			registration2.setTheOffering(course.getCourseOffering(Integer.parseInt(data.get(5))));
			if(student.searchForRegistration(registration2) == false)
				return "0_You are not registered in this course"; //tag indicates failure
			return "1_Course dropped successfully."; //tag indicates GUI should display new list of Student's courses
		case 3: //search for a course in the catalogue
			if(course == null)
				return "0_Course not found."; //the tag is to indicate a failure 
			return "1_Course found: " + course.getCourseName() + " " + course.getCourseNum() + "\n"; 
		case 4: //print the catalogue
			if(courseCatalogue.getCourses() == null)
				return "0_Course catalogue is empty."; 
			return "2_" + courseCatalogue.getCourses(); //returns the long string of courses which will be separated in the GUI
		default: //in case there's an error
			return "0_Error"; 
		}
	}
	
	public static void main(String [] args) throws IOException{

		
		try {
			ServerCommController myServer = new ServerCommController(9898); 
			myServer.courseCatalogue = new CourseCatalogue(); 
			myServer.aSocket = myServer.serverSocket.accept(); 
			System.out.println("Connection accepted by server"); 
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true); 
			
			myServer.doStuff(); 
		
			myServer.socketIn.close(); 
			myServer.socketOut.close();
		} catch(IOException e) {
			e.getStackTrace();
		}
	}

}
