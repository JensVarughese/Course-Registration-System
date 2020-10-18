package view;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class represents the view for the program; it represents the GUI frame.
 *  
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class GUIFrame extends JFrame{
	
	/** 
	 * A serialVersionUID.
	 * */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * A label containing the welcome message.
	 * */
	private JLabel app = new JLabel("Welcome to the course registration system!");
	
	/** 
	 * A button for adding a course.
	 * */
	private JButton addCourse = new JButton("Add a course");
	
	/** 
	 * A button for searching for a course.
	 * */
	private JButton search = new JButton("Search for a course");
	
	/** 
	 * A button for removing a course.
	 * */
	private JButton drop = new JButton("Drop a course");
	
	/** 
	 * A button for showing all courses in the catalogue.
	 * */
	private JButton showAll = new JButton("Show all courses");
	
	/** 
	 * A button for showing all courses that the student is enrolled in.
	 * */
	private JButton show = new JButton("Show your courses");
	
	/**
	 * The list that will display the responses from the server
	 */
	private JTextArea list = new JTextArea(20,40);
	
	/**
	 * The scroll pane that will display the list
	 */
	private JScrollPane bar = new JScrollPane(list);
	
	
	/** 
	 * A variable for the student name.
	 * */
	String studentName;
	
	/** 
	 * A variable for the student ID.
	 * */
	String studentId;
	
	/** 
	 * This class constructor creates the main panel and sets its dimensions.
	 * */
	public GUIFrame() {
		super("Main Frame");
		JPanel mainPanel = new JPanel();
		
		setSize(500,500); //setting the size of the main panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list.setEditable(false);
		
		//adding the buttons and list on the main panel
		mainPanel.add(app);
		mainPanel.add(bar);
		mainPanel.add(search);
		mainPanel.add(addCourse);
		mainPanel.add(drop);
		mainPanel.add(showAll);
		mainPanel.add(show);
		add(mainPanel);
	}
	
	/** 
	 * This function displays dialog boxes prompting the user for their information, then saves this input in studentName and studentInfo.
	 * */
	public void getStudentInfo() {
		String []options = {"OK"};
		
		//making the panels for both dialog boxes
		JPanel namePanel = new JPanel();
		JPanel numberPanel = new JPanel();
		
		//displaying the prompts
		JLabel nameLabel = new JLabel("Please enter your name:");
		JLabel numberLabel = new JLabel("Please enter your ID number:");
		
		//creating a text field
		JTextField nameBox = new JTextField(10);
		JTextField numberBox = new JTextField(10);
		
		namePanel.add(nameLabel);
		namePanel.add(nameBox);
		
		numberPanel.add(numberLabel);
		numberPanel.add(numberBox);
		
		String name = "";
		while(name.equals("")) { //continue displaying the dialog box while the user has not entered anything
			JOptionPane.showOptionDialog(null, namePanel, "",JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
			name = nameBox.getText(); //set the user name to their input
		}
		
		int number = 0;
		while(true) {
			try {
				JOptionPane.showOptionDialog(null, numberPanel, "",JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
				number = Integer.parseInt(numberBox.getText()); //set the user ID to their input
				break;
			}catch(Exception e) { //checking that the input is the correct format
				JOptionPane.showMessageDialog(this,"Your ID must be a number");
				continue;
			}
		}
		studentName = name; //saving the input for name into studentName
		studentId = String.valueOf(number); //saving the input for ID into studentId
	}
	
	/** 
	 * This functions sets action listeners for all buttons on the main menu.
	 * 
	 * @param listener, the ActionListener
	 * */
	public void setActionListener(ActionListener listener) {
		addCourse.addActionListener(listener);
		search.addActionListener(listener);
		drop.addActionListener(listener);
		showAll.addActionListener(listener);
		show.addActionListener(listener);
	}
	
	/** 
	 * A getter for showAll.
	 * 
	 * @return showAll, the button to show all courses.
	 * */
	public JButton getShowAll() {
		return showAll;
	}
	
	/** 
	 * A getter for show.
	 * 
	 * @return show, the button to show the student's courses.
	 * */
	public JButton getShow() {
		return show;
	}
	
	/** 
	 * A getter for addCourse.
	 * 
	 * @return addCourse, the button for adding a course.
	 * */
	public JButton getAddCourse() {
		return addCourse;
	}
	
	/** 
	 * A getter for drop.
	 * 
	 * @return drop, the button for dropping a course.
	 * */
	public JButton getDrop() {
		return drop;
	}
	
	/** 
	 * A getter for search.
	 * 
	 * @return search, the button for searching for a course.
	 * */
	public JButton getSearch() {
		return search;
	}
	
	/** 
	 * A getter for studentName.
	 * 
	 * @return studentName, the student's name.
	 * */
	public String getStudentName()
	{
		return studentName;
	}
	
	/** 
	 * A getter for studentId.
	 * 
	 * @return studentId, the student's ID number.
	 * */
	public String getStudentId()
	{
		return studentId;
	}
	
	/**
	 * A getter for the list
	 * @return the list
	 */
	public JTextArea getList() {
		return list;
	}
}
