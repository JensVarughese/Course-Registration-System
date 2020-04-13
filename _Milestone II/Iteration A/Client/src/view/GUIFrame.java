package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
//This class should not need to be edited(hopefully!).
public class GUIFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel app = new JLabel("Welcome to the course registration system!");
	private JButton addCourse = new JButton("Add a course");
	private JButton search = new JButton("Search for a course");
	private JButton drop = new JButton("Drop a course");
	private JButton showAll = new JButton("Show all courses");
	private JButton show = new JButton("Show your courses");
	
	String studentName;
	String studentId;
	
	public GUIFrame() {
		super("Main Frame");
		JPanel mainPanel = new JPanel();
		
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		app.setAlignmentX(Component.CENTER_ALIGNMENT);
		addCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
		search.setAlignmentX(Component.CENTER_ALIGNMENT);
		drop.setAlignmentX(Component.CENTER_ALIGNMENT);
		showAll.setAlignmentX(Component.CENTER_ALIGNMENT);
		show.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(app);
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(search);
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(addCourse);
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(drop);
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(showAll);
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(show);
		add(mainPanel);
	}
	//gets student name and ID number
	public void getStudentInfo() {
		String []options = {"OK"};
		JPanel namePanel = new JPanel();
		JPanel numberPanel = new JPanel();
		JLabel nameLabel = new JLabel("Please enter your name:");
		JLabel numberLabel = new JLabel("Please enter your ID number:");
		JTextField nameBox = new JTextField(10);
		JTextField numberBox = new JTextField(10);
		
		namePanel.add(nameLabel);
		namePanel.add(nameBox);
		
		numberPanel.add(numberLabel);
		numberPanel.add(numberBox);
		
		String name = "";
		while(name.equals("")) {
			JOptionPane.showOptionDialog(null, namePanel, "",JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
			name = nameBox.getText();
		}
		
		int number = 0;
		while(true) {
			try {
				JOptionPane.showOptionDialog(null, numberPanel, "",JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
				number = Integer.parseInt(numberBox.getText());
				break;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"Your ID must be a number");
				continue;
			}
		}
		studentName = name;
		studentId = String.valueOf(number);
	}
	
	public void setActionListener(ActionListener listener) {
		addCourse.addActionListener(listener);
		search.addActionListener(listener);
		drop.addActionListener(listener);
		showAll.addActionListener(listener);
		show.addActionListener(listener);
	}
	
	public JButton getShowAll() {
		return showAll;
	}
	
	public JButton getShow() {
		return show;
	}
	
	public JButton getAddCourse() {
		return addCourse;
	}
	
	public JButton getDrop() {
		return drop;
	}
	
	public JButton getSearch() {
		return search;
	}
	
	public String getStudentName()
	{
		return studentName;
	}
	
	public String getStudentId()
	{
		return studentId;
	}
}
