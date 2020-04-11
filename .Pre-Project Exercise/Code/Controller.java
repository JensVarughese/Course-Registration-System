import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.*;

public class Controller {
	
	private View myView;
	BinSearchTree tree;
	AddListener listener;
/**
 * Handles the funcionality of te application
 */
public class Controller {
	
	/**
	 * The GUI window
	 */
	private View myView;
	/**
	 * The data tree to store the data
	 */
	BinSearchTree tree;
	/**
	 * The listner for the requests from the user
	 */
	AddListener listener;
	/**
	 * The list to store each line from the input file
	 */
	ArrayList<String> list;
	
	public static void main(String[] args) {
		Controller myController = new Controller();
		myController.myView.setVisible(true);
	}
	
	public Controller(){
		myView= new View();
		tree = new BinSearchTree();
		listener = new AddListener();
		myView.setActionListener(listener);
		list = new ArrayList<String>();
	}
	
	/**
	 * A custom ActionListner to handle the requests of the application
	 */
	class AddListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==myView.getInsert()) {
				JPanel pane = new JPanel();
				JTextField id = new JTextField(10);
				JTextField faculty = new JTextField(10);
				JTextField major = new JTextField(10);
				JTextField year = new JTextField(10);
				pane.add(new JLabel("Enter Student ID:"));
				pane.add(id);
				pane.add(new JLabel("Enter Faculty:"));
				pane.add(faculty);
				pane.add(new JLabel("Enter Student's Major:"));
				pane.add(major);
				pane.add(new JLabel("Enter Year:"));
				pane.add(year);
				int result = JOptionPane.showConfirmDialog(null, pane,
						"Insert a new node",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					insert(id.getText(), faculty.getText(), 
							major.getText(), year.getText());
				}
			}
			else if(e.getSource()==myView.getFind()) {
				String id = JOptionPane.showInputDialog("Please enter the student's ID:");
				search(id);
			}
			else if(e.getSource()==myView.getBrowse()) {
				if(tree.root==null)
					JOptionPane.showMessageDialog(myView, "Tree is Empty! Add some students to it to use this function.");
				else
					print();
			}
			else if(e.getSource()==myView.getCreate()) {
				String fileName = JOptionPane.showInputDialog("Enter the input file name:");
				createTree(fileName);
			}
		}
	}

	/**
	 * Inserts a new student into the list
	 * @param id ID of student
	 * @param faculty Faculty f student
	 * @param major The major the student is in
	 * @param year The year of study of the student
	 */
	public void insert(String id, String faculty, String major, String year) {
		this.tree.insert(id, faculty, major, year);
	}

	/**
	 * Search for a student from the list using his/her id.
	 * If found, will display information with enw window.
	 * If not found, will display Student not found message through new window.
	 * @param id The ID of student.
	 */
	public void search(String id) {
		Node n = this.tree.find(tree.root, id);
		if(n!=null) {
			JOptionPane.showMessageDialog(myView,"ID: "+n.data.id+" Faculty: "+
					n.data.faculty+" Major: "+n.data.major+" Year: "+n.data.year);
		}
		else
			JOptionPane.showMessageDialog(myView,"Student not found");
	}
	
	/**
	 * Reads an input file an stores all the students' data to be inserted in a BST
	 * @param fileName The name of the file to be read.
	 */
	public void createTree(String fileName) {
		try {
			File file = new File(fileName); //file reading code was adapted from: https://www.javatpoint.com/how-to-read-file-line-by-line-in-java
			FileReader f = new FileReader(file); 
			BufferedReader b = new BufferedReader(f); 
			StringBuffer s = new StringBuffer(); 
			String line; 
		
			while((line = b.readLine())!= null ) {	//reads each line of the file
				if(line.isEmpty() == false) {
					s.append(line); 	
					this.list.add(line); 	//adds the line to "list" if it is not empty
					s.append("\n"); 
				}
			}
			f.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(myView, "File not found!");
		}catch (IOException e) {
			JOptionPane.showMessageDialog(myView, "An error occcured.");
		}
	
		ArrayList<String[]> array = this.makeArray(this.list);  	
		
		this.makeTree(array);

		print();
	}
	
	/**
	 * Takes in a list off strings and splits it up to the various sections of the student information
	 * @param list The list to read
	 * @return an array list of strings
	 */
	public ArrayList<String[]> makeArray(ArrayList<String> list) {
		String[] lineOfWords; 
		ArrayList<String[]> array = new ArrayList<String[]>(); //each index in this ArrayList has an array of strings which is one line from the file
		for(int i = 0; i < list.size(); i++) {
			lineOfWords = list.get(i).split("\\s+");
				array.add(lineOfWords); //adding the split String as a String array into "array"
			array.add(lineOfWords); //adding the split String as a String array into "array"
		}
		
		return array;		
	}
	
/**
	 * Takes in the arraylist of strings and inserts it in the BST
	 * @param array
	 */
	public void makeTree (ArrayList<String[]> array) {
		for(String s[]: array) {
			tree.insert(s[1], s[2], s[3], s[4]); 
		}
		
	}
	
	/**
	 * Displays the BST in the GUI window
	 */
	public void print() {
		StringWriter buffer = new StringWriter();
		PrintWriter writer = new PrintWriter(buffer);
		try {
			tree.print_tree(tree.root, writer);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(myView, "An error occured while printing the tree.");
		}
		String contents = buffer.toString();
		myView.getList().setText(contents);
		
	}
}
