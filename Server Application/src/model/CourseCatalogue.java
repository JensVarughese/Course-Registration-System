package model;

import java.util.ArrayList;

/**
 * This class represents the course catalogue, which contains a list of all courses and a list of all students in the database.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0
 * @since April 20, 2020
 *
 */
public class CourseCatalogue {
	
	/** 
	 * An ArrayList to store the courses.
	 * */
	private ArrayList <Course> courseList;
	
	/** 
	 * An ArrayList to store the students.
	 * */
	private ArrayList <Student> studentList;
	
	/** 
	 * A variable of type DBManager for reading in data from the database.
	 * */
	public DBManager db;
	
	/** 
	 * This constructor loads data from the database.
	 * */
	public CourseCatalogue () {
		loadFromDataBase ();
	}
	
	/** 
	 * This function creates a new DBManager and sets courseList and studentList to the lists of students and courses in the database.
	 * */
	private void loadFromDataBase() {
		db = new DBManager();
		setCourseList(db.getCourseList());
		studentList = db.getStudentList();
	}
	
	/** 
	 * This function creates a new course offering for a particular course and adds it to that course's list of offerings.
	 * 
	 * @param c, the course.
	 * @param secNum, the section number of the offering.
	 * @param secCap, the capacity of that offering.
	 * */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/** 
	 * This function searches for a particular course in the catalogue and returns that course.
	 * 
	 * @param courseName, the name of the course.
	 * @param courseNum, the number of the course.
	 * @return the course if found, else return null.
	 * */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		return null;
	}
	
	/** 
	 * A getter for courseList.
	 * 
	 * @return courseList, the list of courses.
	 * */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/** 
	 * A setter for courseList.
	 * 
	 * @param courseList, the list of courses.
	 * */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
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
	 * This function returns a String displaying each course in courseList. 
	 * 
	 * @return st, the String.
	 * */
	@Override
	public String toString () {
		String st = "All courses in the catalogue: |";
		for (Course c : courseList) {
			if(c.getClassSize() == true) {
				st += c;  //This line invokes the toString() method of Course
				st += "|";
			}
			else
				st += c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.|";
		}
		return st;
	}

}
