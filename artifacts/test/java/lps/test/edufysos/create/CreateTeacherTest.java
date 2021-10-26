package lps.test.edufysos.create;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class CreateTeacherTest {
	
	private User teacher = new User("Professor", "Raimundo", "profraimundo", "Te@ch&r123", "prof@raimundo.com");
	private LoginTest lt = new LoginTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	protected WebDriver driverRosario, driverMoodle;
	
	public User getTeacher() {
		return this.teacher;
	}
	

	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}
	
	public void loginAdminRosario() {
		driverRosario = CreateWebDriver.getDriverRosario();
		lt.loginRosario(driverRosario);
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
	
	@Test
	public void createTeacherTest() {
		// Login Rosario
		this.loginAdminRosario();
		this.createTeacherInRosario(teacher);
	}	
	
	public void createTeacherInRosario(User t1) {
		
		driverRosario.findElement(By.linkText("Users")).click();
		this.waitSeconds(1000);
		
		driverRosario.findElement(By.linkText("Add a User")).click();
		this.waitSeconds(1000);
		
		driverRosario.findElement(By.name("moodle_create_user")).click();
		this.waitSeconds(1000);
		
		driverRosario.findElement(By.id("staffFIRST_NAME")).sendKeys(t1.getFirstName());
		driverRosario.findElement(By.id("staffLAST_NAME")).sendKeys(t1.getLastName());
		driverRosario.findElement(By.id("staffUSERNAME")).sendKeys(t1.getUsername());
		driverRosario.findElement(By.id("staffPASSWORD")).sendKeys(t1.getPassword());
		Select profile = new Select(driverRosario.findElement(By.id("staffPROFILE")));
		profile.selectByVisibleText("Teacher");
		driverRosario.findElement(By.id("staffEMAIL")).sendKeys(t1.getEmail());
		this.waitSeconds(1000);
		
		// Click on "Save"
		driverRosario.findElement(By.xpath("//*[@id=\"staff\"]/table[1]/tbody/tr/td/input")).click();
		this.waitSeconds(1000);
		
		// click on X button
		driverRosario.findElement(By.xpath("//img[@alt=\"X\"]")).click();
		this.waitSeconds(1000);
		
		// click on Submit
		driverRosario.findElement(By.xpath("//input[@value=\"Submit\"]")).click();
		this.waitSeconds(1000);
		
		// Test if the student exist
		String name = t1.getFirstName() + " " + t1.getLastName();
		Boolean exist = driverRosario.findElements(By.xpath("//*[contains(text(), '"+ name +"')]")).size() > 0;
		assertTrue(exist);
		
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
