import java.util.ArrayList;

public class Student {
	private String studentName;
	private int studentId;
	private ArrayList<Course> studentCourseList;
	
	public Student(String name, int id) {
		setStudentName(name);
		setStudentId(id);
		studentCourseList = new ArrayList<Course>();
	}
	
	public void addCourse(String name, int number, int section) {
		studentCourseList.add(new Course(name, number, section));
	}
	
	public void removeCourse(String name, int number) {
		for(int i = 0; i<studentCourseList.size();i++) {
			if(name.equals(studentCourseList.get(i).getCourseName())
					&& number == studentCourseList.get(i).getCourseNumber()) {
				studentCourseList.remove(i);
			}
		}
	}
	
	public String displayCourses() {
		String courseList="Course Name\tSection\n";
		for(Course c:studentCourseList) {
			courseList += c;
		}
		return courseList;
	}
	
	public boolean checkEnrollment() {
		if(studentCourseList.size()>=6) {
			return false;
		}
		return true;
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
}
