package lps.test.edufysos;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Class;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;

public class EnrollStudentExceptionalTest {

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	private User u1, t1;
	private Subject c1;
	private Class cl1;
	private LoginTest lt = new LoginTest();
	private CreateTeacherTest ctt = new CreateTeacherTest();
	private AddCourseTest cct = new AddCourseTest();
	private CreateClassTest cclt = new CreateClassTest();

	
	@BeforeAll
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", propCS.urlChromeDriver);
	}

	@BeforeEach
	public void setUp() {

		driverRosario = new ChromeDriver();
 		driverRosario.get(propCS.getUrlRosario());
 		
 		driverMoodle = new ChromeDriver();
 		driverMoodle.get(propCS.getUrlMoodle()+"login/index.php"); 
 		
 		u1 = lt.login();

	}
	
	
	
	public void enrollStudentIntoAClass(User s1, Class cl1) {
		driverRosario.get(propCS.getUrlRosario()+"Modules.php?modname=Scheduling/Requests.php&student_id="+s1.getId());
		
		Select selectCourse = new Select(driverRosario.findElement(By.xpath("//*[@id=\"body\"]/form/div[1]/div/table[2]/tbody/tr/td/span/select")));
		selectCourse.selectByVisibleText(cl1.getSubject().getCourse().getNome());
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driverRosario)
			    .withTimeout(Duration.ofSeconds(30))
			    .pollingEvery(Duration.ofSeconds(5))
			    .ignoring(NoSuchElementException.class);
		
		WebElement link = wait.until(new Function<WebDriver, WebElement>() 
		{
		    public WebElement apply(WebDriver driver) {
		    return driver.findElement(By.xpath("//*[@id=\"courses_div\"]/a[3]"));
		}
		});		
		Actions action = new Actions(driverRosario);
		action.moveToElement(link).click(link).perform();
		
		driverRosario.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driverRosario.get(propCS.getUrlRosario()+"Modules.php?modname=Scheduling/Schedule.php");		
		driverRosario.get(propCS.getUrlRosario()+"Modules.php?modname=Scheduling/Schedule.php");	

		link = driverRosario.findElement(By.xpath("//*[@id=\"body\"]/div/div/table/tbody/tr/td[1]/a"));	
		action.moveToElement(link).click(link).perform();
		
	
		Set<String> ids = driverRosario.getWindowHandles();
	    Iterator<String> itIds = ids.iterator();
	    String parent=itIds.next();
	    String child=itIds.next();
	    driverRosario.switchTo().window(child);
	    
		link = driverRosario.findElement(By.xpath("//*[@id=\"body\"]/div[3]/div/div/table/tbody/tr/td[1]/a"));
		action.moveToElement(link).click(link).perform();	 
		
		
	}
	
	public boolean studentIsEnrolled(User u, Class cl) {
		return true;
		
		//TODO create selenium code
	}
	
	public void requestToEnroll(User u, Class cl) {
		//TODO create selenium code
	}
	
	public User createTeacher() {
		return ctt.createTeacher();
	}
	
	public Subject addCourse() {
		return cct.addCourse();
	}
	
	public Class createClass(User t1, Subject c1) {
		return cclt.createClass(t1, c1);
	}
	
	
	public boolean login(User s1) {
		WebElement user = driverRosario.findElement(By.id("USERNAME"));
		user.sendKeys(s1.getUsername());
		WebElement pass = driverRosario.findElement(By.id("PASSWORD"));
		pass.sendKeys(s1.getPassword());		
		WebElement buttonLogin = driverRosario.findElement(By.xpath("//*[@id=\"loginform\"]/p/input"));
		buttonLogin.click();
		WebElement portal = driverRosario.findElement(By.xpath("//*[@id=\"menu-top\"]/a"));
		return portal != null;
	}	

	private User createNotRegisteredStudent() {
		return new User("Student not registered", "studentnrn", "987654", "S");
		
		//TODO create selenium code
	}	
	
	public boolean studentCanAccessClassInMoodle(User s, Class cl) {
		try {
			WebElement userMoodle = driverMoodle.findElement(By.id("username"));
			userMoodle.sendKeys(s.getUsername());
			WebElement passMoodle = driverMoodle.findElement(By.id("password"));
			passMoodle.sendKeys(s.getPassword());		
			WebElement botaoLoginMoodle = driverMoodle.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
			botaoLoginMoodle.click();	
			driverMoodle.get(propCS.getUrlMoodle()+"course/view.php?id="+cl.getId());
			return true;
		}
		catch(Exception e) {
			return false;
		}
			
	}	
	
	@Test
	public void enrollStudentIntoAClassExceptionalTest() {
		
		User t1 = this.createTeacher();
		Subject c1 = this.addCourse();
		Class cl1 = this.createClass(t1, c1);
		User s1 = this.createNotRegisteredStudent();
		Assertions.assertFalse(login(s1));
		this.requestToEnroll(s1, cl1);	
		Assertions.assertFalse(this.studentIsEnrolled(s1, cl1));
		Assertions.assertFalse(this.studentCanAccessClassInMoodle(s1, cl1));		
	}

	@AfterEach
	public void quitDriver() {
		driverRosario.quit();
		driverMoodle.quit();
	}
}
