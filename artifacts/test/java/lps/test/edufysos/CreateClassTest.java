package lps.test.edufysos;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Class;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;

public class CreateClassTest {
	
	private User u1;
	private Subject c1;
	private User t1;
	private LoginTest lt = new LoginTest();
	private CreateTeacherTest ctt = new CreateTeacherTest();
	private AddCourseTest cct = new AddCourseTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();

	
	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	

	@BeforeAll
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", propCS.urlChromeDriver);
	}	
	
	@BeforeEach
	public void setUp() {
		u1 = lt.login();
		c1 = cct.addCourse();
		t1 = ctt.createTeacher();
	}

	
	public Class createClass(User t1, Subject c1) {
		return new Class(c1, "OOP 2021.1", t1);
		
		//TODO create selenium code
	}
		
	
	
	public boolean classExistInRosario(Class cl1) {
		//TODO create selenium code
		return true;		
	}

	public boolean teacherExistInMoodle(User t1) {
		//TODO create selenium code
		return true;		
	}

	public boolean categoryExistInMoodle(Subject c1) {
		//TODO create selenium code
		return true;		
	}
	
	public boolean teacherCanLoginClassInMoodle(Class cl1, User t1) {
		//TODO create selenium code
		return true;		
	}
	
	public boolean classExistInMoodle(Class cl1) {
		//TODO create selenium code
		return true;		
	}		
	
	@Test
	public void createClassTest() {
		Class cl1 = this.createClass(t1, c1);
		Assertions.assertTrue(this.classExistInRosario(cl1));
		Assertions.assertTrue(this.teacherExistInMoodle(t1));
		Assertions.assertTrue(this.categoryExistInMoodle(c1));
		Assertions.assertTrue(this.classExistInMoodle(cl1));
		Assertions.assertTrue(this.teacherCanLoginClassInMoodle(cl1, t1));		
	}	

}
