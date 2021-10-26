package lps.test.edufysos.enroll;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import lps.test.edufysos.AddCourseTest;
import lps.test.edufysos.create.CreateClassTest;
import lps.test.edufysos.create.CreateStudentTest;
import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.Class;
import lps.test.edufysos.util.Course;
import lps.test.edufysos.util.Subject;
import lps.test.edufysos.util.User;


public class EnrollStudentTest {

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	//private Class cl1;
	private LoginTest lt = new LoginTest();
	private CreateTeacherTest ctt = new CreateTeacherTest();
	//private AddCourseTest cct = new AddCourseTest();
	private CreateClassTest cclt = new CreateClassTest();
	private CreateStudentTest cst = new CreateStudentTest();
	
	
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
	
	
	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}
	
	@Test
	public void enrollStudentTest() {
		// login in Rosario
		this.loginAdminRosario();
		this.waitSeconds(1000);
		
		// directs to Student Schedule page
		this.driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Scheduling/Schedule.php&modfunc=&search_modfunc=list&next_modname=Scheduling/Schedule.php&advanced=&");
		this.waitSeconds(1000);
		
		// find the student
		this.driverRosario.findElement(By.xpath("//a[text()='"+this.cst.getUser().getFullName()+"']")).click();
		this.waitSeconds(1000);
		
		// add a course
		this.driverRosario.findElement(By.xpath("//b[text()='Add a Course']")).click();
		this.waitSeconds(1000);
		
		// after the click, a new window will open
		// we need save the currently driver in a List
		ArrayList<String> Available_tabs = new ArrayList<String>(driverRosario.getWindowHandles());
		
		// Available_tabs.get(0)  -> old window
		// Available_tabs.get(1)  -> new window
		// switch the old window to the new window
		this.driverRosario.switchTo().window(Available_tabs.get(1));
		this.waitSeconds(1000);
		
		// select the subject name "AAA"
		this.driverRosario.findElement(By.xpath("//a[text()='"+this.cclt.getSubject().getName()+"']")).click();
		this.waitSeconds(1000);
		
		//String name = this.cclt.getCourse().getNome();
		this.driverRosario.findElement(By.xpath("//a[text()='"+this.cclt.getCourse().getNome()+"']")).click();
		this.waitSeconds(1000);
		
		// change the month to January
		Select year = new Select(this.driverRosario.findElement(By.name("month_date")));
		year.selectByValue("01");
		this.waitSeconds(1000);
		
		// click on the course period created
		String coursePeriod = cclt.getCourse().getShortName() + " - "+ ctt.getTeacher().getFullName();
		this.driverRosario.findElement(By.xpath("//a[text()='"+ coursePeriod + "']")).click();
		this.waitSeconds(1000);
		
		this.driverRosario.switchTo().window(Available_tabs.get(0));
		System.out.println(driverRosario.getCurrentUrl());
		
		this.driverRosario.findElement(By.xpath("//input[@value='Save']")).click();
		this.waitSeconds(1000);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*

	@BeforeEach
	public void setUp() {

		driverRosario = new ChromeDriver();
 		driverRosario.get(CSsPropertyValues.getUrlRosario());
 		
 		driverMoodle = new ChromeDriver();
 		driverMoodle.get(CSsPropertyValues.getUrlMoodle()+"login/index.php"); 
 		
 		lt.loginRosario(driverRosario);

	}
	
	
	public void enrollStudentIntoAClass(Class cl1) {
		//TODO create selenium code
	}
	
	public void requestToEnroll(User u, Class cl) {
		//TODO create selenium code
	}
	
	public List studentSchedule(User u) {
		return new ArrayList();
	}
	
	
	public void enrollStudentIntoAClass(User s1, Class cl1) {
		driverRosario.get(CSsPropertyValues.getUrlRosario()+"Modules.php?modname=Scheduling/Requests.php&student_id="+s1.getId());
		
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

	public User createTeacher() {
		return ctt.createTeacher();
	}
	
	public Subject addCourse() {
		return cct.addCourse();
	}
	
	public Class createClass(User t1, Subject c1) {
		return cclt.createClass(t1, c1);
	}
	
	public User createStudent() {
		return cst.createStudent();
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
	
	
	@Test
	public void enrollStudentIntoAClassTest() {
		User t1 = this.createTeacher();
		Subject c1 = this.addCourse();
		Class cl1 = this.createClass(t1, c1);
		User s1 = this.createStudent();
		Assertions.assertTrue(login(s1));
		this.requestToEnroll(s1, cl1);
		this.enrollStudentIntoAClass(s1, cl1);	
		Assertions.assertTrue(this.studentIsEnrolled(s1, cl1));
		Assertions.assertTrue(this.studentCanAccessClassInMoodle(s1, cl1));
	
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
		
	


	private User createNotRegisteredStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@AfterEach
	public void quitDriver() {
		driverRosario.quit();
		driverMoodle.quit();
	}
	
	*/
}
