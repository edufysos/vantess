package lps.test.edufysos.verifyExists;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;

public class ExistClassMoodleTest {
	
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
	
	public void verifyExistClass() {
		this.existClassInMoodleTest();
	}
	
	@Test
	public void existClassInMoodleTest() {
		this.loginAdminMoodle();
		this.waitSeconds(1000);

		driverMoodle.get("http://129.213.75.60/moodle/course/management.php");
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//a[text()='" + this.cct.getSubject().getName() + "']")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//a[text()='"+this.cct.getCourse().getNome()+"']")).click();
		this.waitSeconds(1000);
		
		String course = this.cct.getCourse().getShortName()+" - "+this.ctt.getTeacher().getFullName();
		this.waitSeconds(1000);
		Boolean exist = driverMoodle.findElements(By.xpath("//a[text()='2020-2021 - "+course+"']")).size() > 0;
		assertTrue(exist);
		
	}

}
