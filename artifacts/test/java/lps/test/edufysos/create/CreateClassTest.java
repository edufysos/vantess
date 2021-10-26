package lps.test.edufysos.create;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;
import lps.test.edufysos.verifyExists.ExistClassMoodleTest;


public class CreateClassTest {
	
	private LoginTest lt = new LoginTest();
	private Course co1 = new Course("Matemática", "MAT");
	private Course co2 = new Course("História", "HIS");
	private Subject s1 = new Subject("Escolinha Professor Raimundo", "EPR");
	private CreateTeacherTest teacher = new CreateTeacherTest();
	protected static CSsPropertyValues propCS = new CSsPropertyValues();

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	
	public CreateClassTest() {
		this.s1.addCourse(co1);
		this.s1.addCourse(co2);
	}

	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}	
	
	public Subject getSubject() {
		return this.s1;
	}
	
	public Course getCourse() {
		return this.co1;
	}
	
	public void loginAdminRosario() {
		driverRosario = CreateWebDriver.getDriverRosario();
		lt.loginRosario(driverRosario);
	}
	
	public void waitSeconds(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createClassInRosarioTest() {

		// login Rosario
		this.loginAdminRosario();
		this.waitSeconds(1000);
		
		// click on Scheduling menu
		driverRosario.findElement(By.xpath("//li[@class='menu-module scheduling']")).click();
		this.waitSeconds(1000);
		
		// click on Courses
		driverRosario.findElement(By.xpath("//a[text()='Courses']")).sendKeys(Keys.RETURN);
		this.waitSeconds(1000);
		
		// click on + button
		driverRosario.findElement(By.xpath("//img[@alt='Add']")).click();
		this.waitSeconds(1000);
		
		// send a Title to the Course
		driverRosario.findElement(By.xpath("//input[@id='tablesCOURSE_SUBJECTSnewTITLE']")).sendKeys(this.s1.getName());
		this.waitSeconds(1000);
		
		// click on Save button
		driverRosario.findElement(By.xpath("//input[@value='Save']")).click();
		this.waitSeconds(1000);
		
		// click on + button in Course
		driverRosario.findElement(By.xpath("//div[@class='list-outer courses']//child::img")).click();
		this.waitSeconds(1000);
		
		// send title Course
		driverRosario.findElement(By.xpath("//input[@id='tablesCOURSESnewTITLE']")).sendKeys(this.co1.getNome());
		this.waitSeconds(1000);
		
		// send short name of course
		driverRosario.findElement(By.xpath("//input[@id='tablesCOURSESnewSHORT_NAME']")).sendKeys(this.co1.getShortName());
		this.waitSeconds(1000);
		
		// send the credits of the course
		driverRosario.findElement(By.xpath("//*[@id='tablesCOURSESnewCREDIT_HOURS']")).sendKeys("4");
		this.waitSeconds(1000);
		
		// click on Save button
		driverRosario.findElement(By.xpath("//input[@value='Save']")).click();
		this.waitSeconds(1000);
		
		// update the url
		driverRosario.get(driverRosario.getCurrentUrl().replace("&course_id=new", ""));
		this.waitSeconds(1000);
		
		// click on first course created
		driverRosario.findElement(By.xpath("//a[text()='" + this.getCourse().getNome() + "']")).click();
		this.waitSeconds(1000);
		
		// directs the page to new period
		driverRosario.get(driverRosario.getCurrentUrl() + "&course_period_id=new");
		this.waitSeconds(1000);
		
		// click on check button to Create Course Period in Moodle 
		driverRosario.findElement(By.name("moodle_create_course_period")).click();
		this.waitSeconds(1000);
		
		// send the short name
		driverRosario.findElement(By.id("tablesCOURSE_PERIODSnewSHORT_NAME")).sendKeys(this.co1.getShortName());
		this.waitSeconds(1000);
		
		// choose the teacher
		String teacherTest = this.teacher.getTeacher().getFullName();
		Select tea = new Select(driverRosario.findElement(By.name("tables[COURSE_PERIODS][new][TEACHER_ID]")));
		tea.selectByVisibleText(teacherTest);
		this.waitSeconds(1000);
		
		// send the seats quantity
		// ALTERNATIVA DE xpath: //span[contains(text(),'Seats')]//ancestor::td/input
		// ALTERNATIVA DE xpath: //span[contains(text(),'Seats')]/../../input
		// ALTERNATIVA DE xpath: //input[contains(@name,'TOTAL_SEATS')]
		driverRosario.findElement(By.xpath("//input[contains(@name,'TOTAL_SEATS')]")).sendKeys("10");
		this.waitSeconds(1000);
		
		// click on "Save" button
		driverRosario.findElement(By.xpath("//input[@value='Save']")).sendKeys(Keys.RETURN);
		this.waitSeconds(1000);
		
		// check if the course was created
		ExistClassMoodleTest existClass = new ExistClassMoodleTest();
		existClass.existClassInMoodleTest();
	}
	
	
	
	
	
	
	
	
	
	
	/*
	public void setUp() {
		driverMoodle = CreateWebDriver.getDriverMoodle();
		lt.loginMoodle(driverMoodle);
		//c1 = cct.addCourse();
		//t1 = ctt.createTeacher();
	}
	
	public Class createClass(User t1, Subject c1) {
		return new Class(c1, "OOP 2021.1", t1);
		
		//TODO create selenium code
	}
	
	public boolean classExistInRosario(Class cl1) {
		//TODO create selenium code
		return true;		
	}

	public boolean teacherExistInMoodle(User t1) {
		//TODO create selenium code
		return true;		
	}

	public boolean categoryExistInMoodle(Subject c1) {
		//TODO create selenium code
		return true;		
	}
	
	public boolean teacherCanLoginClassInMoodle(Class cl1, User t1) {
		//TODO create selenium code
		return true;		
	}
	
	public boolean classExistInMoodle(Class cl1) {
		//TODO create selenium code
		return true;		
	}	
		
	
	@Test
	public void createClassTest() {
		Class cl1 = this.createClass(t1, c1);
		Assertions.assertTrue(this.classExistInRosario(cl1));
		Assertions.assertTrue(this.teacherExistInMoodle(t1));
		Assertions.assertTrue(this.categoryExistInMoodle(c1));
		Assertions.assertTrue(this.classExistInMoodle(cl1));
		Assertions.assertTrue(this.teacherCanLoginClassInMoodle(cl1, t1));		
	}	
	*/
	
}
