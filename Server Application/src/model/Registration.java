package model;

/**
 * This class represents a student's registration in a particular course offering. 
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 * @version 3.0
 * @since April 20, 2020
 *
 */
public class Registration {
	
	/** 
	 * A variable to represent the student.
	 * */
	private Student theStudent;
	
	/** 
	 * A variable to represent the course offering.
	 * */
	private CourseOffering theOffering;
	
	/** 
	 * A variable to represent the student's grade.
	 * */
	private char grade;
	
	/** 
	 * This function adds a registration using the given parameters. 
	 * 
	 * @param st, the student. 
	 * @param of, the course offering.
	 * */
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration (); //calling the function to add the registration.
	}
	
	/** 
	 * This is a helper functiont that adds the registration for the student and for the course offering.
	 * */
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/** 
	 * A getter for theStudent.
	 * 
	 * @return theStudent, the student.
	 * */
	public Student getTheStudent() {
		return theStudent;
	}
	
	/** 
	 * A setter for theStudent.
	 * 
	 * @param theStudent, the student.
	 * */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	/** 
	 * A getter for theOffering.
	 * 
	 * @return theOffering, the course offering.
	 * */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/** 
	 * A setter for theOffering.
	 * 
	 * @param theOffering, the course offering.
	 * */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/** 
	 * A getter for the student's grade. 
	 * 
	 * @return grade, the grade. 
	 * */
	public char getGrade() {
		return grade;
	}
	
	/** 
	 * A setter for the student's grade. 
	 * 
	 * @param grade, the grade.
	 * */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/** 
	 * This function creates a String to contain the data for this instance of Registration. 
	 * 
	 * @return st, the String.
	 * */
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;		
	}
}
