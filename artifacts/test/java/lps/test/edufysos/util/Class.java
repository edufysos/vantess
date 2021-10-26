package lps.test.edufysos.util;

public class Class {
	
	private int id;
	private Subject subject;	
	private String period;
	private User teacher;
	private String subjectName;
	private String courseName;
	
	
	public Class(Subject subject, String period, User teacher) {
		super();
		this.subject = subject;
		this.period = period;
		this.teacher = teacher;
	}
	
	public Subject getSubject() {
		return this.subject;
	}

	public int getId() {
		return this.id;
	}
	
}
