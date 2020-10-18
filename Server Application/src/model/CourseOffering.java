package model;

import java.util.ArrayList;

/**
 * This class represents an offering for a particular course, with a section number and capacity. It also contains a list of students enrolled in 
 * this offering, and a list of registrations for this offering. 
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class CourseOffering {
	
	/** 
	 * A variable to represent the section number.
	 * */
	private int secNum;
	
	/** 
	 * A variable to represent the section capacity.
	 * */
	private int secCap;
	
	/** 
	 * A variable to represent the Course.
	 * */
	private Course theCourse;
	
	/** 
	 * An ArrayList of students enrolled in this offering.
	 * */
	private ArrayList<Student> studentList;
	
	/** 
	 * An ArrayList of registrations for this offering.
	 * */
	private ArrayList <Registration> offeringRegList;
	
	/** 
	 * This class constructor sets secNum and secCap from input and initalizes both lists.
	 * 
	 * @param secNum, the section number. 
	 * @param secCap, the section capacity.
	 * */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		studentList = new ArrayList <Student>();
		offeringRegList = new ArrayList <Registration>();
	}
	
	/** 
	 * A getter for secNum.
	 * 
	 * @return secNum, the section number.
	 * */
	public int getSecNum() {
		return secNum;
	}
	
	/** 
	 * A setter for secNum.
	 * 
	 * @param secNum, the section number.
	 * */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	/** 
	 * A getter for secCap. 
	 * 
	 * @return secCap, the section capacity.
	 * */
	public int getSecCap() {
		return secCap;
	}
	
	/** 
	 * A setter for secCap.
	 * 
	 * @param secCap, the section capacity.
	 * */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/** 
	 * A getter for theCourse.
	 * 
	 * @return theCourse, the course.
	 * */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/** 
	 * A setter for theCourse.
	 * 
	 * @param theCourse, the course.
	 * */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/** 
	 * A getter for studentList. 
	 * 
	 * @return studentList, the list of students.
	 * */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	/** 
	 * This function creates a String with the data from each student in studentList.
	 * 
	 * @return st, the String.
	 * */
	@Override
	public String toString () {
		String st = "|";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "|";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"|";
		for(Student s: studentList)
			st += s;
		return st;
	}
	
	/** 
	 * This function adds a registration to the list of registrations.
	 * 
	 * @param registration, the registration to be added.
	 * */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);		
	}
	
	/** 
	 * This function removes a registration from the list of registrations.
	 * 
	 * @param registration, the registration to be removed.
	 * */
	public void removeRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.remove(registration);		
	}
	
	/** 
	 * This function adds a student to the list of students.
	 * 
	 * @param s, the student to be added.
	 * */
	public void addStudent(Student s) {
		studentList.add(s);
	}
	
	/** 
	 * This function removes a student from the list of students.
	 * 
	 * @param s, the student to be removed.
	 * */
	public void removeStudent(Student s) {
		studentList.remove(s);
	}
}
