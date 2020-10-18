package model;
import java.util.ArrayList;
import java.sql.*;

/**
 * This class reads data from the MySQL database, and updates the database with new data when prompted. 
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class DBManager {
	
	/** 
	 * This ArrayList stores all courses in the database.
	 * */
	ArrayList <Course> courseList;
	
	/** 
	 * This ArrayList stores all students in the database. 
	 * */
	ArrayList <Student> studentList;
	
	/** 
	 * A Connection variable to connect to the database.
	 * */
	Connection con;
	
	/** 
	 * A Statement variable to query the database.
	 * */
	Statement stat;
	
	/** 
	 * A ResultSet variable to store results from database.
	 * */
	ResultSet set;

	/** 
	 * This constructor establishes the connection with the server and populates studentList and courseList by reading from the database.
	 * */
	public DBManager() {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseData", "root", "ensf409"); //connecting to the database
			stat = con.createStatement();
			
			createCourseList(); //populating courseList
			createStudentList(); //populating studentList
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/** 
	 * This function runs through the student table in the database and reads in data to add to studentList. 
	 * 
	 * @throws SQLException if there's an error reading the database.
	 * */
	public void createStudentList() throws SQLException{
		set = stat.executeQuery("select * from CourseData.student"); //making the ResultSet select from the student data table
		
		while(set.next()) {
			Student s = new Student(set.getString("studentname"), set.getInt("studentid")); //creating a new Student from each row's data
			
			for(int i = 1; i <= 6;i++) {
				if(set.getInt("course"+i)!=0) {
					for(Course c: courseList) {
						if(set.getInt("course"+i)/10==c.getCourseID()) {
							s.addCourse(c, set.getInt("course"+i)%10); //populating the courseList for each Student
						}
					}
				}
			}
			studentList.add(s); //adding the new Student to studentList
		}
	}
	
	/** 
	 * This function runs through the course table in the database and reads in data to add to courseList.
	 * 
	 * @throws SQLException if there's an error reading the database.
	 * */
	public void createCourseList()throws SQLException{
		set = stat.executeQuery("select * from CourseData.courses"); //making the ResultSet select from the course data table
			
		while(set.next()) {
			Course temp = new Course(set.getString("coursename"), set.getInt("coursenum"), set.getInt("idcourses")); //creating a new Course from each row's data
			
			if(set.getInt("prereq")!=0){
				for(Course c: courseList) {
					if(set.getInt("prereq")==c.getCourseID()) {
						temp.addPreReq(c); //add the appropriate pre-Requisites for each course
					}
				}
			}
			courseList.add(temp); //adding the new Course to courseList
		}
			
		set = stat.executeQuery("select * from CourseData.courseoffering"); //making the ResultSet select from the course data table
		int i = 0;
		while(set.next()) {
			if(set.getInt("idcourseoffering")/10!=courseList.get(i).getCourseID()) {
				i++;
			}
			courseList.get(i).addOffering(new CourseOffering(set.getInt("secnum"), set.getInt("seccap"))); //adding the course offerings for each course
		}
	}
	
	/** 
	 * A getter for studentList.
	 * 
	 * @return studentList, the list of students.
	 * */
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	/** 
	 * A getter for courseList.
	 * 
	 * @return courseList, the list of courses.
	 * */
	public ArrayList<Course> getCourseList(){
		return courseList;
	}

	/** 
	 * This function searches for a courseOffering ID using the given parameters and returns the result.
	 * 
	 * @param name, the name of the course.
	 * @param num, the number of the course.
	 * @param sec, the section number to be searched for.
	 * @return result, the courseOffering ID for that section.
	 * */
	public int getOfferingID(String name, int num, int sec) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			set = stat.executeQuery("select * from CourseData.courseoffering");
			while(set.next()) {
				if(set.getString("coursename").equals(name) && 
						set.getInt("coursenum")==num && set.getInt("secnum")==sec) { //finding the ID corresponding to the desired course section
					break;
				}
			}
			result = set.getInt("idcourseoffering"); //setting the result
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}

	/** 
	 * This function adds a course that a student selected to their personal list of courses using the given parameters. 
	 * 
	 * @param offeringID, the ID number of the offering they desire.
	 * @param studentID, the ID number of the student.
	 * */
	public void addStudentCourse(int offeringID, int studentID) {
		// TODO Auto-generated method stub
		try {
			set = stat.executeQuery("select * FROM CourseData.student"); //making the ResultSet select from the student data table
			while(set.next()) {
				if(set.getInt("studentid")==studentID) //searching for the row with the student's data
					break;
			}
			String query = "";
			//these if statements search for the first course column that does not contain a course and add the course to it
			if(set.getInt("course1")==0)
				query = "update CourseData.student set course1= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course2")==0)
				query = "update CourseData.student set course2= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course3")==0)
				query = "update CourseData.student set course3= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course4")==0)
				query = "update CourseData.student set course4= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course5")==0)
				query = "update CourseData.student set course5= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course6")==0)
				query = "update CourseData.student set course6= '"+offeringID+"' WHERE (studentid = '"+studentID+"') ";
			stat.executeUpdate(query); //update the database
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * This function drops a course that a student selectd from their personal list of courses. 
	 * 
	 * @param offeringID, the course offering to be dropped.
	 * @param studentID, the student's ID number.
	 * */
	public void dropStudentCourse(int offeringID, int studentID) {
		// TODO Auto-generated method stub
		try {
			set = stat.executeQuery("select * FROM CourseData.student"); //making ResultSet select from the student data table
			while(set.next()) {
				if(set.getInt("studentid")==studentID)
					break;
			}
			String query = "";
			//these if statements search for the course column that contains the course to be dropped and sets its ID to 0
			if(set.getInt("course1")==offeringID)
				query = "update CourseData.student set course1= '0' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course2")==offeringID)
				query = "update CourseData.student set course2= '0' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course3")==offeringID)
				query = "update CourseData.student set course3= '0' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course4")==offeringID)
				query = "update CourseData.student set course4= '0' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course5")==offeringID)
				query = "update CourseData.student set course5= '0' WHERE (studentid = '"+studentID+"') ";
			else if(set.getInt("course6")==offeringID)
				query = "update CourseData.student set course6= '0' WHERE (studentid = '"+studentID+"') ";
			stat.executeUpdate(query); //updating the database
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * This function adds a student to the studentList using the given parameters.
	 * 
	 * @param name, the student's name.
	 * @param id, the student's ID number.
	 * @throws SQLException if there is an error accessing the database.
	 * */
	public void addStudent(String name, int id) throws SQLException{
		String query = "INSERT INTO CourseData.student (studentid,"
				+ " studentname) values('"+id+ "','"+name+"')"; //creating a query to send the new Student's data to the database.
		stat.executeUpdate(query);
		studentList.add(new Student(name, id)); 
	}
}
