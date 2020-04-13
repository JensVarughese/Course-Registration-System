package view;

import java.awt.BorderLayout;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.*;

public class GUIController {
	private GUIFrame myView;
	private AddListener listener;
	private Socket aSocket;
	private PrintWriter socketOut;
	
	public void displayMenu() {		
		myView.setVisible(true);
	}
	
	
	public GUIController(Socket s){
		myView = new GUIFrame();
		listener = new AddListener();
		myView.getStudentInfo();
		myView.setActionListener(listener);
		aSocket = s;
		try {
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myView.setVisible(true);
	}
	
	public void displayData(String response) {
		if(response.equals(""))
			return;
		
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
		
		JPanel listPane = new JPanel();
		JTextArea list = new JTextArea(30,50);
		JScrollPane bar = new JScrollPane(list);
		
		listPane.setLayout(new BorderLayout());
		list.setEditable(false);
		listPane.add("South",bar);
		list.setText(message);
		JOptionPane.showConfirmDialog(null, listPane,
				"Response",JOptionPane.DEFAULT_OPTION);
		
	}
	
	public GUIFrame getView(){
		return myView;
	}
	
	// Commands to send to server guide:
	// Search course catalog: 	1_"Course Name"_"Course Number"
	// Add a course: 			2_"Course Name"_"Course Number"_"Course Section"
	// Drop a course: 			3_"Course Name"_"Course Number"
	// View all courses: 		4
	// View your courses: 		5
	class AddListener implements ActionListener{
		JPanel searchPane = new JPanel();
		JPanel addPane = new JPanel();
		
		JTextField name = new JTextField(10);
		JTextField number = new JTextField(10);
		JTextField section = new JTextField(5);
		JLabel courseName = new JLabel("Course Name:");
		JLabel courseNumber = new JLabel("Course Number:");
		JLabel courseSection = new JLabel("Course Section:");
		
		//Encompasses all possible actions the user can make.
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == myView.getShowAll()) {
				socketOut.println("4");
				
			}else if(e.getSource()==myView.getShow()) {
				socketOut.println("5");
				
			}else if(e.getSource()==myView.getAddCourse()) {
					addPane.add(courseName);
					addPane.add(name);
					addPane.add(courseNumber);
					addPane.add(number);
					addPane.add(courseSection);
					addPane.add(section);
					int result = JOptionPane.showConfirmDialog(null, addPane,
							"Enroll in a Course",JOptionPane.OK_CANCEL_OPTION);
					if(result==JOptionPane.OK_OPTION) {
						socketOut.println("2_"+name.getText()+"_"+number.getText()+"_"+section.getText());
					}
		
			}else if(e.getSource()==myView.getDrop()) {
				searchPane.add(courseName);
				searchPane.add(name);
				searchPane.add(courseNumber);
				searchPane.add(number);
				int result = JOptionPane.showConfirmDialog(null, searchPane,
						"Drop a Course",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					socketOut.println("3_"+name.getText()+"_"+number.getText());
				}
				
			}else if(e.getSource()==myView.getSearch()) {
				searchPane.add(courseName);
				searchPane.add(name);
				searchPane.add(courseNumber);
				searchPane.add(number);
				int result = JOptionPane.showConfirmDialog(null, searchPane,
						"Course Search",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					socketOut.println("1_"+name.getText()+"_"+number.getText());
				}
			}
		}
	}
}
