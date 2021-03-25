package lps.test.edufysos;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class CreateStudentTest {
	
	private User u1;
	private LoginTest lt = new LoginTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	


	@BeforeAll
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", propCS.urlChromeDriver);
	}
	
	@BeforeEach
	public void loginAdmin() {
		u1 = lt.login();
	}
	
	public User createStudent() {
		return new User("Student", "student", "654321", "S");
		
		//TODO create selenium code
	}	
	
	public boolean studentExistInRosario(User s1) {
		//TODO create selenium code
		return true;		
	}
	
	public boolean studentExistInMoodle(User s1) {
		//TODO create selenium code
		return true;
	}
	
	@Test
	public void createStudentTest() {

		User s1 = this.createStudent();

		Assertions.assertTrue(this.studentExistInRosario(s1));
		Assertions.assertTrue(this.studentExistInMoodle(s1));
	
	}	

}
