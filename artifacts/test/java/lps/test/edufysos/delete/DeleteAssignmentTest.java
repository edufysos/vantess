package lps.test.edufysos.delete;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.Assignment;

public class DeleteAssignmentTest {
	
	private WebDriver driverMoodle;
	//private static CSsPropertyValues propCS = new CSsPropertyValues();
	
	private CreateTeacherTest ctt = new CreateTeacherTest();
	private CreateClassTest cct = new CreateClassTest();
	private Assignment assignment = new Assignment("Assignment name test", "Description test");
	private LoginTest lt = new LoginTest();

	@BeforeClass
	public static void configuraDriver() {
		System.setProperty("webdriver.chrome.driver",  "D:\\programas\\java\\webdriver\\chrome\\chromedriver.exe");
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
		
		//div[@class='action-menu-item']
		driverMoodle.findElement(By.xpath("//div[@class='action-menu-item']/../div")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//span[contains(text(), 'Delete')]")).click();
		this.waitSeconds(1000);
		
		driverMoodle.findElement(By.xpath("//input[@value='Yes']")).click();
		this.waitSeconds(1000);
		
	}

}
