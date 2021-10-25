package lps.test.edufysos.login;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;

public class LoginTeacherMoodleTest {
	
	private LoginTest lt = new LoginTest();
	private CreateClassTest cct = new CreateClassTest();
	private CreateTeacherTest ctt = new CreateTeacherTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;

	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}	
	
	private void loginUserMoodle(User user) {
		driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(driverMoodle, user);
	}
	
	public void waitSeconds(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void loginTeacherInMoodle() {

		this.loginUserMoodle(this.ctt.getTeacher());
		this.waitSeconds(1000);
		
		String course = this.cct.getCourse().getShortName()+" - "+this.ctt.getTeacher().getFullName();
		Boolean exist =  driverMoodle.findElements(By.xpath("//span[contains(text(),'"+course+"')]")).size() > 0;
		this.waitSeconds(1000);
		assertTrue(exist);
	}

}
