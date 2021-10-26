package lps.test.edufysos.create;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;
import lps.test.edufysos.verifyExists.ExistStudentInMoodleTest;

public class CreateStudentTest {
	
	private WebDriver driverRosario, driverMoodle;
	private static CSsPropertyValues propCS = new CSsPropertyValues();	
	
	private LoginTest lt = new LoginTest();
	private User user = new User("Zé", "Bonitinho","zebonitinho", "Luc@5L@bre!", "ze@bonitinho.com");
	
	public User getUser() {
		return user;
	}
	
	public void loginAdminRosario() {
		this.driverRosario = CreateWebDriver.getDriverRosario();
		lt.loginRosario(this.driverRosario);
	}
	
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
	public void createStudentTest() {
		this.loginAdminRosario();
		this.createStudent();
	}
	
	public void createStudent() {
		
		// Click on Students
		driverRosario.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[2]/a")).click();
		this.waitSeconds(1000);
		
		// Click on Add a Student
		driverRosario.findElement(By.xpath("//*[@id=\"menu_Students\"]/li[2]/a")).click();
		this.waitSeconds(1000);
		
		// Click on checkbox "Create Student in Moodle"
		driverRosario.findElement(By.name("moodle_create_student")).click();
		this.waitSeconds(1000);

		// Filling form
		WebElement firstName = driverRosario.findElement(By.id("studentsFIRST_NAME"));
		firstName.sendKeys(user.getFirstName());
		this.waitSeconds(1000);
		
		WebElement lastName = driverRosario.findElement(By.id("studentsLAST_NAME"));
		lastName.sendKeys(user.getLastName());

		WebElement username = driverRosario.findElement(By.id("studentsUSERNAME"));
		username.sendKeys(user.getUsername());
		
		WebElement password = driverRosario.findElement(By.id("studentsPASSWORD"));
		password.sendKeys(user.getPassword());
		
		WebElement email = driverRosario.findElement(By.xpath("//*[@id=\"studentsCUSTOM_200000012\"]"));
		email.sendKeys(user.getEmail());
		this.waitSeconds(1000);
		
		// Save
		driverRosario.findElement(By.xpath("//*[@id='student']/table[1]/tbody/tr/td/input")).click();
		this.waitSeconds(1000);
		
		// check if the student already exists
		Boolean error = driverRosario.findElements(By.xpath("//b[text()='Error']")).size() > 0;
		if (error) {
			System.out.println("\nA user with that username already exists. Choose a different username and try again.");
			assertTrue(error);
			
			//driverRosario.close();
		}
		
		// if the student not exist
		else {
			// click on X button
			driverRosario.findElement(By.xpath("//img[@alt='X']")).sendKeys(Keys.RETURN);
			this.waitSeconds(1000);

			// click on Submit
			driverRosario.findElement(By.xpath("//input[@value='Submit']")).click();
			this.waitSeconds(1000);

			// Test if the student exist
			String nome = this.user.getFullName();
			Boolean exist = driverRosario.findElements(By.xpath("//*[contains(text(), '"+ nome +"')]")).size() > 0;
			this.waitSeconds(1000);
			assertTrue(exist);

			//driverRosario.close();
		}
	}
	
}
