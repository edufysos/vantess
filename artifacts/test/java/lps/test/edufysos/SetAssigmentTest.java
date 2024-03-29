package lps.test.edufysos;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.Assignment;
import lps.test.edufysos.util.CSsPropertyValues;


public class SetAssigmentTest {

	private WebDriver driverMoodle;
	//private static CSsPropertyValues propCS = new CSsPropertyValues();
	
	private CreateTeacherTest ctt = new CreateTeacherTest();
	private CreateClassTest cct = new CreateClassTest();
	private Assignment assignment = new Assignment("Assignment name test", "Description test");
	private LoginTest lt = new LoginTest();

	@BeforeClass
	public static void configuraDriver() {
		System.setProperty("webdriver.chrome.driver",  "D:\\programas\\java\\webdriver\\chrome\\chromedriver-v94.exe");
	}
	
	public void loginAdminMoodle() {
		driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(driverMoodle, this.ctt.getTeacher());
	}
	
	public void waitSeconds(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void enrollStudentTest() {
		
		// login in Moodle with teachers login
		this.loginAdminMoodle();
		this.waitSeconds(1000);
		
		// click on the created course
		String course = this.cct.getCourse().getShortName()+" - "+this.ctt.getTeacher().getFullName();
		driverMoodle.findElement(By.xpath("//span[contains(text(),'"+course+"')]")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//i[@title='Actions menu']")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//a[contains(text(),'Turn editing on')]")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//span[text()='Add an activity or resource']")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//span[text()='Assignment']/../input")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//input[@value='Add']")).click();
		this.waitSeconds(1000);
		
		// ALTERNATIVA DE xpath: //label[contains(text(),'Assignment name')]/parent::div/following-sibling::div/input
		driverMoodle.findElement(By.id("id_name")).sendKeys(this.assignment.getName());
		this.waitSeconds(1000);
		driverMoodle.findElement(By.xpath("//div[@role='textbox']")).sendKeys(this.assignment.getDescription());
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//input[@value='Save and return to course']")).click();
		this.waitSeconds(1000);
		
		Boolean existAssignment = driverMoodle.findElements(By.xpath("//span[text()='"+this.assignment.getName()+"']")).size() > 0;
		assertTrue(existAssignment);
		
	}
	
	
	@After
	public void quitDriver() {
		//driver.quit();
	}
}
