package lps.test.edufysos.delete;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;

public class DeleteClassTest {
	
	private LoginTest lt = new LoginTest();
	//private Course co1, co2;
	//private Subject s1;
	private CreateClassTest cct = new CreateClassTest();
	
	protected static CSsPropertyValues propCS = new CSsPropertyValues();

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	
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
	
	public void waitSecconds(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteClass() {
		//this.setSubjectCourse();
		this.loginAdminRosario();
		
		// directs to Courses url
		driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Scheduling/Courses.php");
		
		List<WebElement> subjects = driverRosario.findElements(By.xpath("//a[text()='" + this.cct.getSubject().getName() + "']"));
	
		for (int i = 0; i < subjects.size(); i++) {
			
			// click on Subject name created early
			WebElement subject = driverRosario.findElement(By.xpath("//a[text()='" + this.cct.getSubject().getName() + "']"));
			subject.click();
			this.waitSecconds(1000);
			
			// click on the first Course
			driverRosario.findElement(By.xpath("//a[text()='" + this.cct.getCourse().getNome() + "']")).click();
			this.waitSecconds(1000);
			
			// click on first course period
			driverRosario.findElement(By.xpath("//div[@class='list-outer course-periods']//child::tbody//a")).sendKeys(Keys.RETURN);
			this.waitSecconds(1000);
						
			
			//click on Delete button
			driverRosario.findElement(By.xpath("//input[@value='Delete']")).click();
			this.waitSecconds(1000);
			
			// click on OK button
			driverRosario.findElement(By.xpath("//input[@value='OK']")).click();
			this.waitSecconds(1000);
			
			// click on Delete button to delete a course
			driverRosario.findElement(By.xpath("//input[@value='Delete']")).click();
			this.waitSecconds(1000);
			
			// click on OK button
			driverRosario.findElement(By.xpath("//input[@value='OK']")).click();
			this.waitSecconds(1000);
			
			// click on Delete button to delete a Subject
			driverRosario.findElement(By.xpath("//input[@value='Delete']")).click();
			this.waitSecconds(1000);
			
			// click on OK button
			driverRosario.findElement(By.xpath("//input[@value='OK']")).click();
			this.waitSecconds(1000);

		} 
		
		// //table[@class='list widefat']/child//a
		
		
		
		
	}

}
