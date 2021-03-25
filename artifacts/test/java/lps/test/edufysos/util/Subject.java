package lps.test.edufysos.util;

public class Subject { 
	
	private Course course;
	private String name;
	
	
	public Subject(Course course, String name) {
		super();
		this.course = course;
		this.name = name;
	}
	
	public Course getCourse() {
		return this.course;
	}

}
