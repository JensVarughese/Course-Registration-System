import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;


/** 
 * This class reads a text file based on the user's input of the file name. 
 * It stores the file contents into a list of Strings, then uses this to create a binary search tree. 
 * 
 * @author Salma Salhi
 * @version 1.0
 * @since April 4, 2020
 * */
public class ReadFromFile {
	
	/** 
	 * An ArrayList of Strings to store the words in the input file
	 * */
	private ArrayList<String> list; 
	
	/** 
	 * A binary search tree variable
	 * */
	private BinSearchTree tree; 
	

	
	/** 
	 * The constructor for ReadFromFile()
	 * */
	public ReadFromFile() {

		
		list = new ArrayList<String>(); 
		tree = new BinSearchTree(); 
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
	
	public static void main(String [] args) throws IOException {
		ReadFromFile rff = new ReadFromFile(); 
		try {
			File file = new File("input.txt"); //file reading code was adapted from: https://www.javatpoint.com/how-to-read-file-line-by-line-in-java
			FileReader f = new FileReader(file); 
			BufferedReader b = new BufferedReader(f); 
			StringBuffer s = new StringBuffer(); 
			String line; 
		
			while((line = b.readLine())!= null ) {	//reads each line of the file
				if(line.isEmpty() == false) {
					s.append(line); 	
					rff.list.add(line); 	//adds the line to "list" if it is not empty
					s.append("\n"); 
				}
			}
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ArrayList<String[]> array = rff.makeArray(rff.list);  	
		
		rff.makeTree(array);
		
		rff.tree.inOrderPrint(rff.tree.root);
		

		
		
	}
}

