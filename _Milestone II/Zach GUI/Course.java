
public class Course {
	private String courseName;
	private int courseNumber;
	private int sectionNumber;
	
	public Course(String name, int number, int section) {
		courseName = name;
		courseNumber = number;
		sectionNumber = section;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getCourseNumber() {
		return courseNumber;
	}
	
	@Override
	public String toString() {
		return courseName+" "+courseNumber+"\t"+sectionNumber+"\n";
	}
}
