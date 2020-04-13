package model;

import java.util.ArrayList;

public class CourseOffering {
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	private ArrayList<Student> studentList;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		studentList = new ArrayList <Student>();
		offeringRegList = new ArrayList <Registration>();
	}
	
	public int getSecNum() {
		return secNum;
	}
	
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	public int getSecCap() {
		return secCap;
	}
	
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	public Course getTheCourse() {
		return theCourse;
	}
	
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	@Override
	public String toString () {
		String st = "|";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "|";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"|";
		for(Student s: studentList)
			st += s;
		return st;
	}
	
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
		
	}
	
	public void removeRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.remove(registration);
		
	}
	
	public void addStudent(Student s) {
		studentList.add(s);
	}
	
	public void removeStudent(Student s) {
		studentList.remove(s);
	}
}
