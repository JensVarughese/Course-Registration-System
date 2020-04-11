package Server;
import java.util.ArrayList;

//THIS NEEDS TO CHANGE WITH JDBC STUFF

public class DBManager {
	
	ArrayList <Course> courseList;

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	//This needs to change, add more courses
	public ArrayList readFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		return courseList;
	}

}
