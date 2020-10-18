package model;

import java.util.ArrayList;

/**
 * This class represents a Student, containing a list of their registrations and course offerings.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0 
 * @since April 20, 2020
 *
 */
public class Student {
	
	/** 
	 * A variable to represent the student name.
	 * */
	private String studentName;
	
	/** 
	 * A variable to represent the student ID number.
	 * */
	private int studentId;
	
	/** 
	 * An ArrayList to store the course offerings the student is enrolled in.
	 * */
	private ArrayList<CourseOffering> offeringList;
	
	/** 
	 * An ArrayList to store the registrations the student has.
	 * */
	private ArrayList<Registration> studentRegList;
	
	/** 
	 * This constructor sets studentName and studenId, and initializes both lists.
	 * 
	 * @param studentName, the student's name.
	 * @param studentId, the student's ID number.
	 * */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		offeringList = new ArrayList<CourseOffering>();
		studentRegList = new ArrayList<Registration>();
	}

	/** 
	 * A getter for studentName. 
	 * 
	 * @return studentName, the student's name.
	 * */
	public String getStudentName() {
		return studentName;
	}

	/** 
	 * A setter for studentName.
	 * 
	 * @param studentName, the student's name.
	 * */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/** 
	 * A getter for studentId.
	 * 
	 * @return studentId, the student's ID number.
	 * */
	public int getStudentId() {
		return studentId;
	}

	/** 
	 * A setter for studentId.
	 * 
	 * @param studentId, the student's ID number.
	 * */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	/** 
	 * A getter for offeringList. 
	 * 
	 * @return offeringList, the list of course offerings.
	 * */
	public ArrayList<CourseOffering> getOfferingList() {
		return offeringList;
	}
	
	/** 
	 * This function returns a String that contains all the data in this instance of Student.
	 * 
	 * @return st, the String.
	 * */
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "|";
		return st;
	}

	/** 
	 * This function adds a registration to the list of registrations for this student.
	 * 
	 * @param registration, the registration to be added.
	 * */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegList.add(registration);
	}
	
	/** 
	 * This function removes a registration from the list of registrations for this student.
	 * 
	 * @param registration, the registration to be removed.
	 * */
	public void removeRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegList.remove(registration);
	}
	
	/** 
	 * This function adds a course offering to the list of offerings for that student.
	 * 
	 * @param c, the course offering to be added.
	 * */
	public void addCourseOffering(CourseOffering c) {
		offeringList.add(c);
	}
	
	/** 
	 * This function removes a course offering from the list of offerings for that student. 
	 * 
	 * @param c, the course offering to be removed.
	 * */
	public void removeCourseOffering(CourseOffering c) {
		offeringList.remove(c);
	}
	
	/** 
	 * This function searches for a course offering in offeringList using the given parameters and returns that offering.
	 * 
	 * @param name, the name of the course in question.
	 * @param num, the number of the course.
	 * @return the course offering if found, else return null.
	 * */
	public CourseOffering searchCourse(String name, int num) {
		for(CourseOffering c: offeringList) {
			if(c.getTheCourse().getCourseName().contentEquals(name) && c.getTheCourse().getCourseNum() == num)
				return c;
		}
		return null;
	}
	
	/** 
	 * This functions searches for a registration in studentRegList using the given parameters and returns that registration. 
	 * 
	 * @param name, the name of the course.
	 * @param num, the number of the course.
	 * @return the registration if found, else return null.
	 * */
	public Registration searchRegistration(String name, int num) {
		for(Registration reg : studentRegList) {
			if(reg.getTheOffering().getTheCourse().getCourseName().contentEquals(name) && 
					reg.getTheOffering().getTheCourse().getCourseNum() == num)
				return reg;
		}
		return null;
	}
	
	/** 
	 * This function creates a string containing all course offerings the student is enrolled in. 
	 * 
	 * @return the string containing all course offerings if the offeringList size is greater than 0, else return an error message.
	 * */
	public String returnCourses() {
		if(offeringList.size() == 0)
			return "You are not enrolled in any courses!";
		String s = "";
		for(CourseOffering c : offeringList)
			s += c.toString();
		return s;
	}
	
	/** 
	 * This function adds a course offering to offeringList if the Student meets the pre-requisite requirements and the section exists. 
	 * 
	 * @param c, the course.
	 * @param sec, the section number.
	 * @return messages explaining that the student does not meet the requirements or that the section does not exist, else return "Course successfully
	 * 		   added" if the course can be added.
	 * */
	public String addCourse(Course c, int sec)
	{
		Registration reg = new Registration();
		if(c.checkPreReq(this) == false)
			return "You do not have the necessary prerequisites for this course!";
		else{
			if(c.getCourseOfferingAt(sec-1) == null)
				return "Section not found.";
			else {
				reg.completeRegistration(this, c.getCourseOfferingAt(sec-1)); //make the registration 
				c.getCourseOfferingAt(sec-1).addRegistration(reg); //add the registration in the offering's registration list
				c.getCourseOfferingAt(sec-1).addStudent(this); //add the student in the offering's student list
				this.addCourseOffering(c.getCourseOfferingAt(sec-1)); //add the offering to the student's offering list
				return "Course successfully added.";
			}
		}
	}
	
	/** 
	 * This function removes a course offering from a student's registration. 
	 * 
	 * @param name, the course name.
	 * @param num, the course number.
	 * @return "You are not enrolled in this course or it does not exist." if the course cannot be removed, else return "Course successfully removed.".
	 * */
	public String removeCourse(String name, int num)
	{
		CourseOffering c = this.searchCourse(name, num);
		if(c == null) {
			return "You are not enrolled in this course or it does not exist.";
		}
		else {
			Registration reg = this.searchRegistration(name, num);
			this.removeRegistration(reg); //remove the registration from the student's registration list
			this.removeCourseOffering(c); //remove the course offering from the student's offering list
			c.removeRegistration(reg); //remove the registration from the course offering's registration list
			c.removeStudent(this); //remove the student from the course offering's student list
			return "Course successfully removed.";
		}
	}
	
	/** 
	 * This function checks if the student is enrolled in less than 6 courses. 
	 * 
	 * @return true if they are enrolled in less than 6 courses, false if not.
	 * */
	public boolean checkCourseAmount() {
		if(offeringList.size() < 6)
			return true;
		return false;
	}
}
