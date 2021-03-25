package lps.test.edufysos;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class CreateTeacherTest {
	
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
	
	public User createTeacher() {
		return new User("Teacher", "teacher", "123456", "T");
		
		//TODO create selenium code
	}	
	
	public boolean teacherExistInRosario(User t1) {
		//TODO create selenium code
		return true;		
	}
	
	
	@Test
	public void createTeacherTest() {

		User t1 = this.createTeacher();

		Assertions.assertTrue(this.teacherExistInRosario(t1));
	}	

}
