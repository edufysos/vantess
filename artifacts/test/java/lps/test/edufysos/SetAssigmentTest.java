package lps.test.edufysos;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class SetAssigmentTest {

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;

	@BeforeClass
	public static void configuraDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\Libs\\chromedriver\\88\\chromedriver.exe");
	}

	@Before
	public void createDriver() {
		
 		driverMoodle = new ChromeDriver();
 		driverMoodle.get("http://129.213.75.60/moodle/login/index.php"); 
	}

	@Test
	public void enrollStudentTest() {
		/*WebElement username = driverRosario.findElement(By.id("USERNAME"));
		username.sendKeys("admin");
		WebElement pass = driverRosario.findElement(By.id("PASSWORD"));
		pass.sendKeys("ros@r10");		
		WebElement botaoLogin = driverRosario.findElement(By.xpath("//*[@id=\"loginform\"]/p/input"));
		botaoLogin.click();
		
		Select year = new Select(driverRosario.findElement(By.xpath("//*[@id=\"syear\"]")));
		year.selectByValue("2020");
		
		driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Scheduling/Requests.php&student_id=2");
		
		Select selectCourse = new Select(driverRosario.findElement(By.xpath("//*[@id=\"body\"]/form/div[1]/div/table[2]/tbody/tr/td/span/select")));
		selectCourse.selectByVisibleText("Computer Science");
		
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
		driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Scheduling/Schedule.php");		
		driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Scheduling/Schedule.php");	

		link = driverRosario.findElement(By.xpath("//*[@id=\"body\"]/div/div/table/tbody/tr/td[1]/a"));	
		action.moveToElement(link).click(link).perform();
		
	
		Set<String> ids = driverRosario.getWindowHandles();
	    Iterator<String> itIds = ids.iterator();
	    String parent=itIds.next();
	    String child=itIds.next();
	    driverRosario.switchTo().window(child);
	    
		link = driverRosario.findElement(By.xpath("//*[@id=\"body\"]/div[3]/div/div/table/tbody/tr/td[1]/a"));
		action.moveToElement(link).click(link).perform();*/	 
		
		WebElement userMoodle = driverMoodle.findElement(By.id("username"));
		userMoodle.sendKeys("mariasilva");
		WebElement passMoodle = driverMoodle.findElement(By.id("password"));
		passMoodle.sendKeys("m@riaS1lva");		
		WebElement botaoLoginMoodle = driverMoodle.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
		botaoLoginMoodle.click();	

		driverMoodle.get("http://129.213.75.60/moodle/course/view.php?id=9");	
		driverMoodle.get("http://129.213.75.60/moodle/course/view.php?id=9&sesskey=g8jHRISQdW&edit=on");
		
	}
	
	


	@After
	public void quitDriver() {
		//driver.quit();
	}
}
