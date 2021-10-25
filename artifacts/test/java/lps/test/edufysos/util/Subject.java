package lps.test.edufysos.util;

import java.util.ArrayList;

public class Subject { 
	
	private String name;
	private String shortName;
	private ArrayList<Course> courseList;
	
	public Subject(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
		this.courseList = new ArrayList<Course>();
	}
	
	public void addCourse(Course course) {
		this.courseList.add(course);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	public ArrayList<Course> getCourses() {
		return this.courseList;
	}
	
	
	
	/*
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
	*/

}
