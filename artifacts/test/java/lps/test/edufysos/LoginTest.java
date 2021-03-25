package lps.test.edufysos;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class LoginTest {

	protected WebDriver driverRosario;
	protected WebDriver driverMoodle;
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	

	@BeforeAll
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", propCS.urlChromeDriver);
	}

	public User login() {
		
		User u1 = new User(propCS.getUserRosario(), propCS.getUserRosario(), propCS.getPassRosario(), "A");
		WebElement user = driverRosario.findElement(By.id("USERNAME"));
		user.sendKeys(u1.getUsername());
		WebElement pass = driverRosario.findElement(By.id("PASSWORD"));
		pass.sendKeys(u1.getPassword());		
		WebElement buttonLogin = driverRosario.findElement(By.xpath("//*[@id=\"loginform\"]/p/input"));
		buttonLogin.click();
		
		Select year = new Select(driverRosario.findElement(By.xpath("//*[@id=\"syear\"]")));
		year.selectByValue("2020");	
		return u1;
	}

}
