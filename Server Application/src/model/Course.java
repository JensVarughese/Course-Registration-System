package model;

import java.util.ArrayList;

/**
 * This class represent a course. It contains a list of that course's sections (course offerings) and a list of prerequisites.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0
 * @since April 20, 2020
 *
 */
public class Course {

	/** 
	 * A variable to represent the course name.
	 * */
	private String courseName;
	
	/** 
	 * A variable to represent the course number. 
	 * */
	private int courseNum;
	
	/** 
	 * A variable to represent the course ID number. 
	 * */
	private int courseID;
	
	/** 
	 * An ArrayList to store the course's pre-requisites.
	 * */
	private ArrayList<Course> preReq;
	
	/** 
	 * An ArrayList to store the course's sections (offerings). 
	 * */
	private ArrayList<CourseOffering> offeringList;

	public Course(String courseName, int courseNum, int courseID) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		this.setCourseID(courseID);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/** 
	 * This function adds a course offering to the course's list of course offerings. 
	 * 
	 * @param offering, the course offering to be added. 
	 * */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName) //checking if this offering already belongs to another course
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering); //add the offering
		}
	}

	/** 
	 * A getter for the course name. 
	 * 
	 * @return courseName, the course name.
	 * */
	public String getCourseName() {
		return courseName;
	}

	/** 
	 * A setter for the course name. 
	 * 
	 * @param courseName, the course name.
	 * */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/** 
	 * A getter for the course number. 
	 * 
	 * @return courseNum, the course number. 
	 * */
	public int getCourseNum() {
		return courseNum;
	}

	/** 
	 * A setter for the course number. 
	 * 
	 * @param courseNum, the course number. 
	 * */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/** 
	 * This functions makes the the data in Course into a String and returns the String.
	 * 
	 * @return st, the String of data.
	 * */
	@Override
	public String toString () {
		String st = "|";
		st += getCourseName() + " " + getCourseNum ();
		st += "|All course sections:|";
		for (CourseOffering c : offeringList)
			st += c; //appending each course offering to the String.
		st += "|-------|";
		return st;
	}

	/** 
	 * This function searches for a course offering in offeringList at a particular index and returns that offering. 
	 * 
	 * @param i, the index.
	 * @return offeringList.get(i), the offering at that index.
	 * */
	public CourseOffering getCourseOfferingAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}
	
	/** 
	 * A getter for offeringList.
	 * 
	 * @return offeringList, the list of course offerings.
	 * */
	public ArrayList<CourseOffering> getOfferingList()
	{
		return offeringList;
	}
	
	/** 
	 * This function adds a course to the pre-requisite list.
	 * 
	 * @param c, the course to be added.
	 * */
	public void addPreReq(Course c) {
		preReq.add(c);
	}
	
	/** 
	 * This function checks if a Student has all required pre-requisites for a particular course, returning true if they do and false if not.
	 * 
	 * @param s, the Student.
	 * @return true if the student has all the pre-requisites, false if not.
	 * */
	public boolean checkPreReq(Student s) {
		int i, count = 0;
		for(i = 0; i < preReq.size(); i++) {
			for(CourseOffering c: s.getOfferingList()) {
				if(preReq.get(i) == c.getTheCourse())
					count++; //counting the number of pre-requisites the student has
			}
		}
		if(count == i) //checking if this number matches the number of pre-requisites required for the course.
			return true;
		return false;
	}
	
	/** 
	 * This function searches for the number of students enrolled in each class and checks if that number is less than or equal to 8. 
	 * 
	 * @return true if the number of students is less than or equal to 8, false if not.
	 * */
	public boolean getClassSize() {
		int sum = 0;
		for(CourseOffering c : offeringList)
			sum += c.getStudentList().size();
		if(sum >= 8)
			return true;
		return false;
	}

	/** 
	 * A getter for courseID. 
	 * 
	 * @return courseID, the ID number of the course.
	 * */
	public int getCourseID() {
		return courseID;
	}

	/** 
	 * A setter for courseID.
	 * 
	 * @param courseID, the ID number of the course.
	 * */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
}
