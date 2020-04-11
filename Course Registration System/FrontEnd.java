import java.util.Scanner;
public class FrontEnd {
	
	private CourseCatalogue cat;
	private Scanner scan;
	private Student user;
	
	public FrontEnd() {
		cat = new CourseCatalogue();
		scan = new Scanner(System.in);
		user = newStudent();
	}
	
	public static void main (String [] args) {
		FrontEnd app = new FrontEnd();
		app.printMenu();
		app.menu();
	}
	
	public void printMenu() {
		System.out.println("\n1. Search course catalogue.");
		System.out.println("2. Add a course.");
		System.out.println("3. Drop a course.");
		System.out.println("4. View all courses.");
		System.out.println("5. View your courses.");
		System.out.println("6. Exit.");
	}
	
	public void menu() {
		while(true) {
			System.out.print("Make a selection: ");
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					printCourse();
					break;
				case 2:
					addCourse();
					break;
				case 3:
					user.removeCourse();
					break;
				case 4:
					System.out.println(cat);
					break;
				case 5:
					user.printCourses();
					break;
				case 6:
					System.out.println("\nGoodbye!");
					scan.close();
					System.exit(0);
				default:
					System.out.println("Invalid input. Try again.");
			}
			printMenu();
		}
	}
	
	private Student newStudent() {
		System.out.print("Enter your name: ");
		String name = scan.nextLine();
		System.out.print("Enter your ID: ");
		int id = scan.nextInt();
		return new Student(name, id);
	}
	
	private void printCourse(){
		Course c = searchCourse();
		if(c == null)
			System.out.println("Course not found!");
		else
			if(c.getClassSize() == false)
				System.out.println("\n"+c.getCourseName()+" "+c.getCourseNum()+" is not available due to low enrolment.");
			else
				System.out.println(c);
	}
	
	private void addCourse() {
		if(user.checkCourseAmount() == false)
			System.out.println("/nYou can only register in a maximum of 6 courses!");
		else {
			Course c = searchCourse();
			if(c != null)
				user.addCourse(c);
		}
	}
	
	private Course searchCourse(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter course name: ");
		String name = scan.nextLine();
		System.out.print("Please enter course number: ");
		int num = scan.nextInt();
		return cat.searchCat(name, num);
	}
}
