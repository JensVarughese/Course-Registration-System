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
	
	public void insert(String id, String faculty, String major, String year) {
		this.tree.insert(id, faculty, major, year);
	}
	
	public void search(String id) {
		Node n = this.tree.find(tree.root, id);
		if(n!=null) {
			JOptionPane.showMessageDialog(myView,"ID: "+n.data.id+" Faculty: "+
					n.data.faculty+" Major: "+n.data.major+" Year: "+n.data.year);
		}
		else
			JOptionPane.showMessageDialog(myView,"Student not found");
	}
	
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
	}
	
	public ArrayList<String[]> makeArray(ArrayList<String> list) {
		String[] lineOfWords; 
		ArrayList<String[]> array = new ArrayList<String[]>(); //each index in this ArrayList has an array of strings which is one line from the file
		for(int i = 0; i < list.size(); i++) {
			lineOfWords = list.get(i).split("\\s+");
				array.add(lineOfWords); //adding the split String as a String array into "array"
		}
		
		return array;		
	}
	
	public void makeTree (ArrayList<String[]> array) {
		for(String s[]: array) {
			tree.insert(s[1], s[2], s[3], s[4]); 
		}
		
	}
	
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
