import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {
	
	ArrayList <Course> courseList;

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	public ArrayList<Course> readFromDataBase() {
		// TODO Auto-generated method stub
		Student s = new Student("Salma", 100);
		Student s1 = new Student("Bryce", 101);
		Student s2 = new Student("Jens", 102);
		Student s3 = new Student("Billy", 103);
		Student s4 = new Student("Bob", 104);
		Student s5 = new Student("Joe", 105);
		Student s6 = new Student("Harry", 106);
		Student s7 = new Student("Sally", 107);
		Student s8 = new Student("Mabel", 108);
		Student s9 = new Student("Sofie", 109);
		courseList.add(new Course ("ENGG", 233));
		courseList.get(0).addOffering(new CourseOffering(1, 100));
		courseList.get(0).addOffering(new CourseOffering(2, 100));
		courseList.get(0).addOffering(new CourseOffering(3, 100));
		courseList.get(0).addOffering(new CourseOffering(4, 100));	
		courseList.get(0).getCourseOfferingAt(0).addStudent(s);
		courseList.get(0).getCourseOfferingAt(0).addStudent(s1);
		courseList.get(0).getCourseOfferingAt(1).addStudent(s2);
		courseList.get(0).getCourseOfferingAt(1).addStudent(s3);
		courseList.get(0).getCourseOfferingAt(2).addStudent(s4);
		courseList.get(0).getCourseOfferingAt(2).addStudent(s5);
		courseList.get(0).getCourseOfferingAt(3).addStudent(s6);
		courseList.get(0).getCourseOfferingAt(3).addStudent(s7);
		
		courseList.add(new Course ("ENSF", 337));
		courseList.get(1).addOffering(new CourseOffering(1, 150));
		courseList.get(1).getCourseOfferingAt(0).addStudent(s);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s1);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s2);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s3);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s5);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s6);
		courseList.get(1).getCourseOfferingAt(0).addStudent(s7);
		courseList.get(1).addPreReq(courseList.get(0));
		
		courseList.add(new Course ("ENSF", 409));
		courseList.get(2).addOffering(new CourseOffering(1, 150));
		courseList.get(2).addPreReq(courseList.get(0));
		courseList.get(2).getCourseOfferingAt(0).addStudent(s);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s1);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s2);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s3);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s5);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s6);
		courseList.get(2).getCourseOfferingAt(0).addStudent(s7);
		courseList.get(2).addPreReq(courseList.get(1));
		
		courseList.add(new Course ("ENGG", 200));
		courseList.get(3).addOffering(new CourseOffering(1, 200));
		courseList.get(3).addOffering(new CourseOffering(2, 200));
		courseList.get(3).getCourseOfferingAt(0).addStudent(s8);
		courseList.get(3).getCourseOfferingAt(0).addStudent(s9);
		courseList.get(3).getCourseOfferingAt(1).addStudent(s2);
		courseList.get(3).getCourseOfferingAt(1).addStudent(s3);
		courseList.get(3).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(3).getCourseOfferingAt(0).addStudent(s5);
		courseList.get(3).getCourseOfferingAt(1).addStudent(s6);
		courseList.get(3).getCourseOfferingAt(1).addStudent(s7);
		
		courseList.add(new Course ("ENGG", 201));
		courseList.get(4).addOffering(new CourseOffering(1, 200));
		courseList.get(4).addOffering(new CourseOffering(2, 200));
		courseList.get(4).getCourseOfferingAt(0).addStudent(s8);
		courseList.get(4).getCourseOfferingAt(0).addStudent(s9);
		courseList.get(4).getCourseOfferingAt(1).addStudent(s2);
		courseList.get(4).getCourseOfferingAt(1).addStudent(s3);
		courseList.get(4).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(4).getCourseOfferingAt(0).addStudent(s5);
		courseList.get(4).getCourseOfferingAt(1).addStudent(s);
		courseList.get(4).getCourseOfferingAt(1).addStudent(s1);
		
		courseList.add(new Course ("MATH", 275));
		courseList.get(5).addOffering(new CourseOffering(1, 100));
		courseList.get(5).addOffering(new CourseOffering(2, 100));
		courseList.get(5).addOffering(new CourseOffering(3, 100));
		courseList.get(5).addOffering(new CourseOffering(4, 100));
		courseList.get(5).getCourseOfferingAt(0).addStudent(s8);
		courseList.get(5).getCourseOfferingAt(1).addStudent(s9);
		courseList.get(5).getCourseOfferingAt(2).addStudent(s2);
		courseList.get(5).getCourseOfferingAt(3).addStudent(s3);
		courseList.get(5).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(5).getCourseOfferingAt(1).addStudent(s5);
		courseList.get(5).getCourseOfferingAt(2).addStudent(s6);
		courseList.get(5).getCourseOfferingAt(3).addStudent(s7);
		
		courseList.add(new Course ("PHYS", 259));
		courseList.get(6).addOffering(new CourseOffering(1, 150));
		courseList.get(6).addOffering(new CourseOffering(2, 150));
		courseList.get(6).getCourseOfferingAt(0).addStudent(s);
		/*courseList.get(6).getCourseOfferingAt(0).addStudent(s1); //These are commented out showcase a course with too few students.
		courseList.get(6).getCourseOfferingAt(1).addStudent(s2);
		courseList.get(6).getCourseOfferingAt(1).addStudent(s3);
		courseList.get(6).getCourseOfferingAt(0).addStudent(s4);
		courseList.get(6).getCourseOfferingAt(0).addStudent(s5);
		courseList.get(6).getCourseOfferingAt(1).addStudent(s6);
		courseList.get(6).getCourseOfferingAt(1).addStudent(s7);*/
		return courseList;
	}
}
