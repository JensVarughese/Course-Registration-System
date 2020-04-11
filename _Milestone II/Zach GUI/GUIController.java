import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIController {
	private GUIFrame myView;
	private AddListener listener;
	private Student user;
	private String serverInput;
	private String serverOutput;
	
	//temporary main method
	public static void main(String[] args) {
		GUIController myController = new GUIController();
		myController.myView.setVisible(true);
	}
	
	public GUIController(){
		myView = new GUIFrame();
		listener = new AddListener();
		user = myView.getStudentInfo();
		myView.setActionListener(listener);
		setServerInput("");//default is -1 to avoid errors
		setServerOutput("1_null");
		myView.setVisible(true);
	}
	
	//the tag variable lets the server identify what method to call
	public void sendData(int tag, String name, int number, int section) {
		setServerInput(tag+"_"+user.getStudentName()+"_"+user.getStudentId()+"_"+name+"_"+number+"_"+section);
	}
	//The first string in the array is a tag that tells the client whether the action was successful(1) or not(0).
	//a tag of 2 tells the server to print the all courses to a JTextArea.
	public boolean receiveData() {
		String[]output = getServerOutput().split("_",2);
		switch(Integer.parseInt(output[0])) {
			case 0:
				myView.printMessage(output[1]);
				return false;
			case 1:
				myView.printMessage(output[1]);
				return true;
			case 2:
				listener.list.setText(output[1]);
				return true;
			default: 
				myView.printMessage("Uh oh, something went wrong.");
				return false;
		}
	}
	
	public String getServerInput() {
		return serverInput;
	}

	public void setServerInput(String serverInput) {
		this.serverInput = serverInput;
	}
	
	public String getServerOutput() {
		return serverOutput;
	}

	public void setServerOutput(String serverOutput) {
		this.serverOutput = serverOutput;
	}
	//contains all fields needed to make up the rest of the GUI; they are member variables to maximize code reuse.
	class AddListener implements ActionListener{
		JPanel searchPane = new JPanel();
		JPanel addPane = new JPanel();
		
		JTextField name = new JTextField(10);
		JTextField number = new JTextField(10);
		JTextField section = new JTextField(5);
		JLabel courseName = new JLabel("Course Name:");
		JLabel courseNumber = new JLabel("Course Number:");
		JLabel courseSection = new JLabel("Course Section:");
		
		JPanel listPane = new JPanel();
		JTextArea list = new JTextArea(10,20);
		JScrollPane bar = new JScrollPane(list);
		//Encompasses all possible actions the user can make.
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == myView.getShowAll()) {
				listPane.setLayout(new BorderLayout());
				list.setEditable(false);
				listPane.add("South",bar);
				sendData(4,"-",0,0);
				if(receiveData()) {
					JOptionPane.showConfirmDialog(null, listPane,
							"Course List",JOptionPane.OK_CANCEL_OPTION);
				}
				
			}else if(e.getSource()==myView.getShow()) {
				listPane.setLayout(new BorderLayout());
				list.setEditable(false);
				listPane.add("South",bar);
				list.setText(user.displayCourses());
				JOptionPane.showConfirmDialog(null, listPane,
						"Courses You are Enrolled in",JOptionPane.OK_CANCEL_OPTION);
				
			}else if(e.getSource()==myView.getAddCourse()) {//this option will most likely be the source of errors
				if(user.checkEnrollment()) {
					addPane.add(courseName);
					addPane.add(name);
					addPane.add(courseNumber);
					addPane.add(number);
					addPane.add(courseSection);
					addPane.add(section);
					int result = JOptionPane.showConfirmDialog(null, addPane,
							"Enroll in a Course",JOptionPane.OK_CANCEL_OPTION);
					if(result==JOptionPane.OK_OPTION) {
						sendData(1,name.getText(),Integer.parseInt(number.getText()),
								Integer.parseInt(number.getText()));
						if(receiveData()) {
							user.addCourse(name.getText(), Integer.parseInt(number.getText()),
								 Integer.parseInt(number.getText()));
						}
					}
				}
				else {
					myView.printMessage("You are already enrolled in the maximum number of courses!");
				}
				
			}else if(e.getSource()==myView.getDrop()) {
				searchPane.add(courseName);
				searchPane.add(name);
				searchPane.add(courseNumber);
				searchPane.add(number);
				int result = JOptionPane.showConfirmDialog(null, searchPane,
						"Drop a Course",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					sendData(2,name.getText(),Integer.parseInt(number.getText()),0);
					if(receiveData())
						user.removeCourse(name.getText(), Integer.parseInt(number.getText()));
				}
				
			}else if(e.getSource()==myView.getSearch()) {
				searchPane.add(courseName);
				searchPane.add(name);
				searchPane.add(courseNumber);
				searchPane.add(number);
				int result = JOptionPane.showConfirmDialog(null, searchPane,
						"Course Search",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					sendData(3,name.getText(),Integer.parseInt(number.getText()),0);
					receiveData();
				}
			}
		}
	}
}
