package lps.test.edufysos.util;

public class User {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private int idRosario, idMoodle;
	
	public User(String firstName, String lastName, String username, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdRosario() {
		return idRosario;
	}

	public void setIdRosario(int idRosario) {
		this.idRosario = idRosario;
	}

	public int getIdMoodle() {
		return idMoodle;
	}

	public void setIdMoodle(int idMoodle) {
		this.idMoodle = idMoodle;
	}

	public String getFullName() {
		return String.format("%s %s", this.firstName, this.lastName); 
	}

}
