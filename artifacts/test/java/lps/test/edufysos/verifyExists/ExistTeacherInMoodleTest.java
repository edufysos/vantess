package lps.test.edufysos.verifyExists;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class ExistTeacherInMoodleTest {
	
	private LoginTest lt = new LoginTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	protected WebDriver driverRosario, driverMoodle;	
	CreateTeacherTest teacherTest = new CreateTeacherTest();
	User teacher = teacherTest.getTeacher();
	
	public void loginAdminMoodle() {
		driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(driverMoodle);
	}
	
	public void waitSeconds(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}
	
	@Test
	public void teacherExistInMoodle() {
		// Login in Moodle
		this.loginAdminMoodle();
		
		// Verify whether the teacher was created in Moodle
		driverMoodle.findElement(By.linkText("Site administration")).click();
		driverMoodle.findElement(By.linkText("Users")).click();
		driverMoodle.findElement(By.linkText("Browse list of users")).click();
		WebElement newTeacher = driverMoodle.findElement(By.linkText(teacher.getFullName()));
		assertTrue(newTeacher.isEnabled());
	}

}
