package view;

import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.*;

/**
 * This class controls the GUI and communicates with the client to display messages for the user.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class GUIController {
	
	/** 
	 * A GUIFrame variable representing the GUI Frame.
	 * */
	private GUIFrame myView;
	
	/** 
	 * An AddListener variable for listening.
	 * */
	private AddListener listener;
	
	/** 
	 * A socket variable.
	 * */
	private Socket aSocket;
	
	/** 
	 * A PrintWriter variable for writing to the socket.
	 * */
	private PrintWriter socketOut;
	
	/** 
	 * This function displays the menu.
	 * */
	public void displayMenu() {		
		myView.setVisible(true);
	}
	
	/** 
	 * This class constructor initializes all member variables, gets the user information, and sets the ActionListener for myView.
	 * 
	 * @param s, the socket.
	 * */
	public GUIController(Socket s){
		myView = new GUIFrame();
		listener = new AddListener();
		myView.getStudentInfo(); //calling the function to get user information
		myView.setActionListener(listener);
		aSocket = s;
		try {
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myView.setVisible(true);
	}
	
	/** 
	 * This function displays the response from the server for the user.
	 * 
	 * @param response, the String received from the server to be displayed.
	 * */
	public void displayData(String response) {
		if(response.equals(""))
			return;
		else if(response.equals("Student ID already exists!")) {
			JOptionPane.showMessageDialog(myView, "Student ID already exists! Program Terminated."); //terminates the program if the user enters an ID that already exists
			System.exit(1);
		}
		StringWriter buffer = new StringWriter();
		PrintWriter writer = new PrintWriter(buffer);
		
		for(int i = 0; i < response.length(); i++)
		{
			if(response.charAt(i)=='|')
				writer.println();
			else
				writer.print(response.charAt(i));
		}
		writer.println();
		
		String message = buffer.toString();
		
		myView.getList().setText(message);
		
	}
	
	/** 
	 * A getter for myView. 
	 * 
	 * @return myView, the GUIFrame variable.
	 * */
	public GUIFrame getView(){
		return myView;
	}
	
	/** 
	 * This AddListener class sends the appropriate message to socketOut depending on the functionality the user desires to be performed. 
	 * */
	class AddListener implements ActionListener{
		JPanel searchPane = new JPanel();
		JPanel addPane = new JPanel();
		
		JTextField name = new JTextField(10);
		JTextField number = new JTextField(10);
		JTextField section = new JTextField(5);
		
		//making the labels for course name, course number, and course section
		JLabel courseName = new JLabel("Course Name:");
		JLabel courseNumber = new JLabel("Course Number:");
		JLabel courseSection = new JLabel("Course Section:");
		
		/** 
		 * This function sends a String to the socket encompassing all commands for the server to execute, depending on the functionality the user selected.
		 * 
		 * @param e, the ActionEvent.
		 * */
		@Override
		public void actionPerformed(ActionEvent e) {
			//Encompasses all possible actions the user can make.
			// Commands to send to server guide:
			// Search course catalog: 	1_"Course Name"_"Course Number"
			// Add a course: 			2_"Course Name"_"Course Number"_"Course Section"
			// Drop a course: 			3_"Course Name"_"Course Number"_"Course Section"
			// View all courses: 		4
			// View your courses: 		5
			if(e.getSource() == myView.getShowAll()) {
				socketOut.println("4"); //sends the selection number "4" if the user wants to see everything in the course catalogue
				
			}else if(e.getSource()==myView.getShow()) {
				socketOut.println("5"); //sends the selection number "5" if the user wants to see all of their courses
				
			}else if(e.getSource()==myView.getAddCourse()) { //this is for adding a course to a student's list
				addPane.add(courseName);
				addPane.add(name);
				addPane.add(courseNumber);
				addPane.add(number);
				addPane.add(courseSection);
				addPane.add(section);
				int result = JOptionPane.showConfirmDialog(null, addPane,
						"Enroll in a Course",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					socketOut.println("2_"+name.getText()+"_"+number.getText()+"_"+section.getText()); //sending a String with the selection key "2", the course name, the course number, and the course section
				}
		
			}else if(e.getSource()==myView.getDrop()) { //this is for dropping a course
				addPane.add(courseName);
				addPane.add(name);
				addPane.add(courseNumber);
				addPane.add(number);
				addPane.add(courseSection);
				addPane.add(section);
				int result = JOptionPane.showConfirmDialog(null, addPane,
						"Enroll in a Course",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					socketOut.println("3_"+name.getText()+"_"+number.getText()+"_"+section.getText()); //sending a String with the selection key "3", the course name, the course number, and the course section
				}
				
			}else if(e.getSource()==myView.getSearch()) { //this is for searching for a course
				searchPane.add(courseName);
				searchPane.add(name);
				searchPane.add(courseNumber);
				searchPane.add(number);
				int result = JOptionPane.showConfirmDialog(null, searchPane,
						"Course Search",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					socketOut.println("1_"+name.getText()+"_"+number.getText()); //sending a String with the selection key "1", the course name, and the course number
				}
			}
		}
	}
}
