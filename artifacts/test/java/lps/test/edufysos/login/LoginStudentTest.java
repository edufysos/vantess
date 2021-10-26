package lps.test.edufysos.login;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateStudentTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class LoginStudentTest {
	
	private LoginTest lt = new LoginTest();
	private CreateStudentTest cst = new CreateStudentTest();
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
	public void loginStudentInMoodle() {
		this.loginUserMoodle(this.cst.getUser());
		this.waitSeconds(1000);
		
		String name = this.cst.getUser().getFullName();
		Boolean exist =  driverMoodle.findElements(By.xpath("//span[text()='"+name+"']")).size() > 0;
		this.waitSeconds(1000);
		assertTrue(exist);		
	}

}
