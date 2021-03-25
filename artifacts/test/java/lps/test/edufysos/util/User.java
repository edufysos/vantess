package lps.test.edufysos.util;

public class User {
	
	private int id;
	private String name;
	private String username;
	private String password;
	private String profile;
	
	public User(String name, String username, String password, String profile) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.profile = profile;
	}	

	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
