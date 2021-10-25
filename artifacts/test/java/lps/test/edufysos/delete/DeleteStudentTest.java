package lps.test.edufysos.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateStudentTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class DeleteStudentTest {
	
	protected WebDriver driverRosario, driverMoodle;
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	User user1;
	LoginTest lt = new LoginTest();

	
	public void loginAdminRosario() {
		driverRosario = CreateWebDriver.getDriverRosario();
		lt.loginRosario(driverRosario);
	}
	
	public void loginAdminMoodle() {
		driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(driverMoodle);
	}
	
	public void waitSecconds(int sec) {
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
	
	@Before
	public void inicializaUser() {
		CreateStudentTest cs = new CreateStudentTest();
		user1 = cs.getUser();
	}
	
	@Test
	public void deleteStudentInRosarioTest() {
		this.loginAdminRosario();
		this.deleteStudentInRosario();
	}
	
	public void deleteStudentInRosario() {
		
		driverRosario.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[2]/a")).click();
		this.waitSecconds(1000);
		
		driverRosario.findElement(By.xpath("//*[@id=\"search\"]/div/input[1]")).click();
		this.waitSecconds(1000);
		
		//Click on name student
		driverRosario.findElement(By.xpath("//*[contains(text(), '"+user1.getFirstName()+" "+user1.getLastName()+"')]")).click();
		this.waitSecconds(1000);
		
		// click on 
		driverRosario.findElement(By.xpath("//*[@id=\"student\"]/table[1]/tbody/tr/td[2]/input[1]")).click();
		this.waitSecconds(1000);
		
		driverRosario.findElement(By.name("delete_ok")).click();
		this.waitSecconds(1000);
		
		driverRosario.findElement(By.xpath("//*[@id=\"search\"]/div/input[1]")).click();
		this.waitSecconds(1000);
		
		// Test if the student exists
		String nome = user1.getFirstName() + " " + user1.getLastName();
		Boolean exist = driverRosario.findElements(By.xpath("//*[contains(text(), '"+ nome +"')]")).size() > 0;
		assertFalse(exist);
	}
	
	@Test
	public void deleteStudentInMoodle() {
		this.loginAdminMoodle();
		this.deleteStudentInMoodleTest();
	}
		
	public void deleteStudentInMoodleTest() {

		// Click on "Site administration"
		driverMoodle.findElement(By.linkText("Site administration")).click();
		this.waitSecconds(1000);
		
		// Click on "Users"
		driverMoodle.findElement(By.linkText("Users")).click();
		this.waitSecconds(1000);
		
		// Click on "Browse list of users"
		driverMoodle.findElement(By.linkText("Browse list of users")).click();
		this.waitSecconds(1000);
		
		// click on trash icon
		String name = user1.getFullName();
		driverMoodle.findElement(By.xpath("//a[contains(text(),'"+name+"')]/parent::td[//a]/following-sibling::td[@class=\"cell c5\"]/a/i[@title=\"Delete\"]")).click();
		this.waitSecconds(1000);
		
		// click on Delete button
		driverMoodle.findElement(By.xpath("//button[text()=\"Delete\"]")).click();
		
		// Test if the student exists
		Boolean exist2 = driverMoodle.findElements(By.xpath("//*[contains(text(),'"+ name +"')]")).size() > 0;
		assertFalse(exist2);
		
	}

}
