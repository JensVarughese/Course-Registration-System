package model;

import java.util.ArrayList;

public class Student {
	
	private String studentName;
	private int studentId;
	private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		offeringList = new ArrayList<CourseOffering>();
		studentRegList = new ArrayList<Registration>();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public ArrayList<CourseOffering> getOfferingList() {
		return offeringList;
	}
	
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "|" +
				"Student Id: " + getStudentId() + "||";
		return st;
	}

	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegList.add(registration);
	}
	
	public void removeRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegList.remove(registration);
	}
	
	public void addCourseOffering(CourseOffering c) {
		offeringList.add(c);
	}
	
	public void removeCourseOffering(CourseOffering c) {
		offeringList.remove(c);
	}
	
	public CourseOffering searchCourse(String name, int num) {
		for(CourseOffering c: offeringList) {
			if(c.getTheCourse().getCourseName().contentEquals(name) && c.getTheCourse().getCourseNum() == num)
				return c;
		}
		return null;
	}
	
	public Registration searchRegistration(String name, int num) {
		for(Registration reg : studentRegList) {
			if(reg.getTheOffering().getTheCourse().getCourseName().contentEquals(name) && 
					reg.getTheOffering().getTheCourse().getCourseNum() == num)
				return reg;
		}
		return null;
	}
	
	// New method for server.
	public String returnCourses() {
		if(offeringList.size() == 0)
			return "You are not enrolled in any courses!";
		String s = "";
		for(CourseOffering c : offeringList)
			s += c.toString();
		return s;
	}
	
	public String addCourse(Course c, int sec)
	{
		Registration reg = new Registration();
		if(c.checkPreReq(this) == false)
			return "You do not have the necessary prerequisites for this course!";
		else{
			if(c.getCourseOfferingAt(sec-1) == null)
				return "Section not found.";
			else {
				reg.completeRegistration(this, c.getCourseOfferingAt(sec-1));
				c.getCourseOfferingAt(sec-1).addRegistration(reg);
				c.getCourseOfferingAt(sec-1).addStudent(this);
				this.addCourseOffering(c.getCourseOfferingAt(sec-1));
				return "Course Succussfully added.";
			}
		}
	}
	
	// New method for server.
	public String removeCourse(String name, int num)
	{
		CourseOffering c = this.searchCourse(name, num);
		if(c == null) {
			return "You are not enrolled in this course or it does not exist.";
		}
		else {
			Registration reg = this.searchRegistration(name, num);
			this.removeRegistration(reg);
			this.removeCourseOffering(c);
			c.removeRegistration(reg);
			c.removeStudent(this);
			return "Course Successfully removed.";
		}
	}
	
	public boolean checkCourseAmount() {
		if(offeringList.size() < 6)
			return true;
		return false;
	}
}
