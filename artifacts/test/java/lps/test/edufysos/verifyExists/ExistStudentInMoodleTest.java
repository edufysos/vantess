package lps.test.edufysos.verifyExists;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateStudentTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;

public class ExistStudentInMoodleTest {
	
	private WebDriver driverMoodle;
	private static CSsPropertyValues propCS = new CSsPropertyValues();
	
	private LoginTest lt = new LoginTest();
	CreateStudentTest studentTest = new CreateStudentTest();
	
	public void loginAdminMoodle() {
		this.driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(this.driverMoodle);
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
	public void studentExistInMoodleTest() {
		
		this.loginAdminMoodle();
		
		// Click on "Site administration"
		String url = driverMoodle.getCurrentUrl();
		driverMoodle.findElement(By.linkText("Site administration")).click();
		this.waitSeconds(1000);
		
		// Click on "Users"
		driverMoodle.findElement(By.linkText("Users")).click();
		this.waitSeconds(1000);
		
		// Click on "Browse list of users"
		driverMoodle.findElement(By.linkText("Browse list of users")).click();
		this.waitSeconds(1000);
		
		// Test if the student exist
		String nome = this.studentTest.getUser().getFullName();
		Boolean exist = driverMoodle.findElements(By.xpath("//*[contains(text(), '"+ nome +"')]")).size() > 0;
		assertTrue(exist);
		
	}

}
