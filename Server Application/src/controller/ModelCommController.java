package controller;

import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
/** 
 * This class contains the functionality that is called from the server when communicating with the client, and sends data back to the client.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0
 * @since April 20, 2020
 * 
 * */
public class ModelCommController implements Runnable {

	/** 
	 * A PrintWriter variable to write to the socket.
	 * */
	private PrintWriter socketOut;
	
	/** 
	 * A BufferedReader variable to read input from the socket
	 * */
	private BufferedReader socketIn;
	
	/** 
	 * A variable to represent the course catalogue.
	 * */
	private CourseCatalogue cat;
	
	/** 
	 * A variable to represent the Student for a particular client.
	 * */
	private Student user;
	
	/** 
	 * An array of five strings that contains five different strings that data that come from the client end (indicating the functionality desired).
	 * */
	private String[] data = new String[4];
	
	/** 
	 * The run function for this Runnable class. It reads data from socketIn to set the user and make a selection for the desired functionality 
	 * to be performed for that student.
	 * */
	@Override
	public void run() {
		String line = null; 
		int selection = 0;
		cat = new CourseCatalogue(); 
		
		String name = "";
		int id = 0;
		try { //this try/catch block reads the student name and ID number
			name = socketIn.readLine();
			id = Integer.parseInt(socketIn.readLine());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		user = newStudent(name, id); //creating a new student with the data given
		
		while(true) {
			try {
				line = socketIn.readLine();
				if(line == null)
					return;

					makeArray(line); //making an array using the input String
					selection = Integer.parseInt(data[0]); //making the switch string into a number
					selectFunctionality(selection); 	//sending the output of the switch statement back to the Client			
		
			} catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
	
	/** 
	 * This constructor sets socketIn and socketOut to the parameter values.
	 * 
	 * @param in, the BufferedReader that reads the input from the socket.
	 * @param out, the PrintWriter that writes to the socket.
	 * */
	public ModelCommController(BufferedReader in, PrintWriter out) {
		socketIn = in;
		socketOut = out;
	}
	
	/** 
	 * This function creates a student using the parameters given and returns this Student.
	 * 
	 * @param name, the student's name.
	 * @param id, the student's ID number.
	 * @return the student if it already exists in the catalogue, else returns a new Student object made from the given parameters.
	 * */
	private Student newStudent(String name, int id) {
		for(Student s: cat.getStudentList()) {
			if(s.getStudentName().equals(name) && s.getStudentId() == id) {
				return s; //returns the student if it already exists in the catalogue
			}
		}
		try {
			cat.db.addStudent(name, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Student(name, id); //creates a new student
	}
	
	/** 
	 * This function populates the member variable data with a String in each index. The Strings are retrieved from the line read from the socket.
	 * 
	 * @param input, the String read from the socket.
	 * */
	private void makeArray(String input) {	//this function splits the incoming string from the client into separate words, saves into "data"
		String[] array = input.split("_"); 
		for(int i = 0; i < array.length; i++) {
			data[i] = array[i]; 
		}

	}
	
	/** 
	 * This function uses the first index of the member array data, which indicates a switch number, to select the functionality desired from the user.
	 * It calls the appropriate functions to carry out this functionality in the switch statement.
	 * 
	 * @param selection, the number indicating the desired functionality to be performed.
	 * */
	private void selectFunctionality(int selection) {
		switch(selection){
			case 1: //printing a course that was searched for
				printCourse( data[1], Integer.parseInt(data[2]) );
				break;
			case 2: //adding a course to student's list
				addCourse( data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]) );
				break;
			case 3: //removing a course from a student's list
				String temp = user.removeCourse(data[1], Integer.parseInt(data[2]));
				socketOut.println(temp);
				if(temp.equals("Course successfully removed.")){
					cat.db.dropStudentCourse(cat.db.getOfferingID(data[1], Integer.parseInt(data[2]), //removing the course from the database
							Integer.parseInt(data[3])), user.getStudentId());
				}
				break;
			case 4: //printing everything in the course catalogue
				socketOut.println(cat);
				break;
			case 5: //printing all of the student's courses
				socketOut.println(user.returnCourses());
				break;
			default: //error
				socketOut.println("Error");
				break;
		}
	}
	
	/** 
	 * This function searches for a course in the catalogue and prints the course to the socket if the course was found and is available.
	 * 
	 * @param name, the course name.
	 * @param num, the course number.
	 * */
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
	
	/** 
	 * This function adds a course to a student's list if all requirements are met and writes a success message to the socket. If not, it writes
	 * an appropriate error message to the socket.
	 * 
	 * @param name, the course name.
	 * @param num, the course number.
	 * @param sec, the course offering section.
	 * */
	private void addCourse(String name, int num, int sec) {
		if(user.checkCourseAmount() == false) { //checking if the student is currently enrolled in less than 6 courses
			socketOut.println("You can only register in a maximum of 6 courses!");
			return;
		}
		else {
			Course c = cat.searchCat(name, num);
			if(c != null) {
				if(c.getClassSize() == false) { //checking if the course is available
					socketOut.println("\n"+c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.");
					return;
				}
				else {
					boolean match = false;
					for(CourseOffering co: c.getOfferingList()) { //checking if the offering wanted actually exists for that particular course
						if(co.getSecNum()==sec) {
							match = true;
							break;
						}
					}
					
					if(match == false) {
						socketOut.println("Course section " + sec + " does not exist.");
						return;
					}
					
					String temp = user.addCourse(c, sec); //adding the course
					socketOut.println(temp);
					if(temp.equals("Course successfully added."))
					{
						cat.db.addStudentCourse(cat.db.getOfferingID(name,num,sec), user.getStudentId()); //adding the course to the database
					}
					return;
				}
			}
			else {
				socketOut.println("Course not found!"); 
				return;
			}
		}
	}
	
}
