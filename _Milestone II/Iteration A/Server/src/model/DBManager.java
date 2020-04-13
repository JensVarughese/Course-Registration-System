package model;
import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		
		studentList = new ArrayList<Student>();
		studentList.add(new Student("Salma", 100));
		studentList.add(new Student("Bryce", 101));
		studentList.add(new Student("Jens", 102));
		studentList.add(new Student("Billy", 103));
		studentList.add(new Student("Bob", 104));
		studentList.add(new Student("Joe", 105));
		studentList.add(new Student("Harry", 106));
		studentList.add(new Student("Sally", 107));
		studentList.add(new Student("Mabel", 108));
		studentList.add(new Student("Sofie", 109));
	}
	
	public ArrayList<Student> getStudentList(){
		return studentList;
	}

	public ArrayList<Course> readFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.get(0).addOffering(new CourseOffering(1, 100));
		courseList.get(0).addOffering(new CourseOffering(2, 100));
		courseList.get(0).addOffering(new CourseOffering(3, 100));
		courseList.get(0).addOffering(new CourseOffering(4, 100));
		
		studentList.get(0).addCourse(courseList.get(0), 1);
		studentList.get(1).addCourse(courseList.get(0), 1);
		studentList.get(2).addCourse(courseList.get(0), 2);
		studentList.get(3).addCourse(courseList.get(0), 2);
		studentList.get(4).addCourse(courseList.get(0), 3);
		studentList.get(5).addCourse(courseList.get(0), 3);
		studentList.get(6).addCourse(courseList.get(0), 4);
		studentList.get(7).addCourse(courseList.get(0), 4);
		
		courseList.add(new Course ("ENSF", 337));
		courseList.get(1).addOffering(new CourseOffering(1, 150));
		studentList.get(0).addCourse(courseList.get(1), 1);
		studentList.get(1).addCourse(courseList.get(1), 1);
		studentList.get(2).addCourse(courseList.get(1), 1);
		studentList.get(3).addCourse(courseList.get(1), 1);
		studentList.get(4).addCourse(courseList.get(1), 1);
		studentList.get(5).addCourse(courseList.get(1), 1);
		studentList.get(6).addCourse(courseList.get(1), 1);
		studentList.get(7).addCourse(courseList.get(1), 1);
		courseList.get(1).addPreReq(courseList.get(0));
		
		courseList.add(new Course ("ENSF", 409));
		courseList.get(2).addOffering(new CourseOffering(1, 150));
		courseList.get(2).addPreReq(courseList.get(0));
		studentList.get(0).addCourse(courseList.get(2), 1);
		studentList.get(1).addCourse(courseList.get(2), 1);
		studentList.get(2).addCourse(courseList.get(2), 1);
		studentList.get(3).addCourse(courseList.get(2), 1);
		studentList.get(4).addCourse(courseList.get(2), 1);
		studentList.get(5).addCourse(courseList.get(2), 1);
		studentList.get(6).addCourse(courseList.get(2), 1);
		studentList.get(7).addCourse(courseList.get(2), 1);
		courseList.get(2).addPreReq(courseList.get(1));
		
		courseList.add(new Course ("ENGG", 200));
		courseList.get(3).addOffering(new CourseOffering(1, 200));
		courseList.get(3).addOffering(new CourseOffering(2, 200));
		studentList.get(8).addCourse(courseList.get(3), 1);
		studentList.get(9).addCourse(courseList.get(3), 1);
		studentList.get(2).addCourse(courseList.get(3), 2);
		studentList.get(3).addCourse(courseList.get(3), 2);
		studentList.get(4).addCourse(courseList.get(3), 1);
		studentList.get(5).addCourse(courseList.get(3), 1);
		studentList.get(6).addCourse(courseList.get(3), 2);
		studentList.get(7).addCourse(courseList.get(3), 2);
		
		courseList.add(new Course ("ENGG", 201));
		courseList.get(4).addOffering(new CourseOffering(1, 200));
		courseList.get(4).addOffering(new CourseOffering(2, 200));
		studentList.get(8).addCourse(courseList.get(4), 1);
		studentList.get(9).addCourse(courseList.get(4), 1);
		studentList.get(2).addCourse(courseList.get(4), 2);
		studentList.get(3).addCourse(courseList.get(4), 2);
		studentList.get(4).addCourse(courseList.get(4), 1);
		studentList.get(5).addCourse(courseList.get(4), 1);
		studentList.get(0).addCourse(courseList.get(4), 2);
		studentList.get(1).addCourse(courseList.get(4), 2);
		
		courseList.add(new Course ("MATH", 275));
		courseList.get(5).addOffering(new CourseOffering(1, 100));
		courseList.get(5).addOffering(new CourseOffering(2, 100));
		courseList.get(5).addOffering(new CourseOffering(3, 100));
		courseList.get(5).addOffering(new CourseOffering(4, 100));
		studentList.get(8).addCourse(courseList.get(5), 1);
		studentList.get(9).addCourse(courseList.get(5), 2);
		studentList.get(2).addCourse(courseList.get(5), 3);
		studentList.get(3).addCourse(courseList.get(5), 4);
		studentList.get(4).addCourse(courseList.get(5), 1);
		studentList.get(5).addCourse(courseList.get(5), 2);
		studentList.get(6).addCourse(courseList.get(5), 3);
		studentList.get(7).addCourse(courseList.get(5), 4);
		
		courseList.add(new Course ("PHYS", 259));
		courseList.get(6).addOffering(new CourseOffering(1, 150));
		courseList.get(6).addOffering(new CourseOffering(2, 150));
		studentList.get(0).addCourse(courseList.get(6), 1);

		return courseList;
	}
}
