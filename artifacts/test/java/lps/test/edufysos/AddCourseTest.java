package lps.test.edufysos;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;

public class AddCourseTest {
	
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

	public Subject addCourse() {
		return new Subject (new Course("Computer Science"), "Object Oriented Programming");
		
		//TODO create selenium code
	}
	
	
	public boolean courseExistInRosario(Subject c1) {
		//TODO create selenium code
		return true;		
	}

	
	@Test
	public void addCourseTest() {
		Subject c1 = this.addCourse();
		Assertions.assertTrue(this.courseExistInRosario(c1));
	
	}	

}
