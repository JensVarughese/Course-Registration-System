import java.util.ArrayList;
import java.util.Scanner;

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
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
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
	
	public void printCourses() {
		if(offeringList.size() == 0)
			System.out.println("\nYou are not enrolled in any courses!");
		for(CourseOffering c : offeringList)
			System.out.println(c);
	}
	
	public void addCourse(Course c) {
		if(c.getClassSize() == false)
			System.out.println("\n"+c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.");
		else {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			Registration reg = new Registration();
			if(c.checkPreReq(this) == false)
				System.out.println("\nYou do not have the necessary prerequisites for this course!");
			else{
				System.out.println(c);
				System.out.print("Please Select a Section: ");
				int i = scan.nextInt();
				if(c.getCourseOfferingAt(i-1) == null)
					System.out.println("/nSection not found.");
				else {
					reg.completeRegistration(this, c.getCourseOfferingAt(i-1));
					c.getCourseOfferingAt(i-1).addRegistration(reg);
					c.getCourseOfferingAt(i-1).addStudent(this);
					this.addCourseOffering(c.getCourseOfferingAt(i-1));
					System.out.println("\nCourse Succussfully added.");
				}
			}
		}
	}
	
	public void removeCourse() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter course name: ");
		String name = scan.nextLine();
		System.out.print("Please enter course number: ");
		int num = scan.nextInt();
		CourseOffering c = this.searchCourse(name, num);
		if(c == null) {
			System.out.println("\nYou are not enrolled in this course or it does not exist.");
		}
		else {
			Registration reg = this.searchRegistration(name, num);
			this.removeRegistration(reg);
			this.removeCourseOffering(c);
			c.removeRegistration(reg);
			c.removeStudent(this);
			System.out.println("\nCourse Successfully removed.");
		}
	}
	
	public boolean checkCourseAmount() {
		if(offeringList.size() < 6)
			return true;
		return false;
	}
}
