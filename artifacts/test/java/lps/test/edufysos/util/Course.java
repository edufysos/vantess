package lps.test.edufysos.util;

public class Course {
	
	private String name;
	private String shortName;

	public Course(String name, String shortName) {
		super();
		this.name = name;
		this.shortName = shortName;
	}
	
	public String getNome() {
		return this.name;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
}
