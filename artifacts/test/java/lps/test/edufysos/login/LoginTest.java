package lps.test.edufysos.login;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.util.CSsPropertyValues;
import lps.test.edufysos.util.User;

public class LoginTest {

	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	User userRosario = new User("","",CSsPropertyValues.getUserRosario(), CSsPropertyValues.getPassRosario(), "");
	User userMoodle = new User("","",CSsPropertyValues.getUserMoodle(), CSsPropertyValues.getPassMoodle(), "");
	
	@BeforeClass
	public static void configDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", CSsPropertyValues.urlChromeDriver);
	}
	
	@Test
	public void testLoginRosario() {
		this.loginRosario(CreateWebDriver.getDriverRosario());
	}
	
	@Test
	public void testLoginMoodle() {
		this.loginMoodle(CreateWebDriver.getDriverMoodle());
	}
	
	public void loginRosario(WebDriver wdRosario) {
		wdRosario.findElement(By.id("USERNAME")).sendKeys(userRosario.getUsername());
		wdRosario.findElement(By.id("PASSWORD")).sendKeys(userRosario.getPassword());		
		wdRosario.findElement(By.xpath("//*[@id=\"loginform\"]/p/input")).click();
		
		Select year = new Select(wdRosario.findElement(By.xpath("//*[@id=\"syear\"]")));
		year.selectByValue("2020");	
	}
	
	public void loginRosario(WebDriver wdRosario, User user) {
		this.userRosario.setUsername(user.getUsername());
		this.userRosario.setPassword(user.getPassword());
		loginMoodle(wdRosario);
	}
	
	public void loginMoodle(WebDriver wdMoodle) {
		wdMoodle.findElement(By.id("username")).sendKeys(userMoodle.getUsername(), Keys.TAB, userMoodle.getPassword(), Keys.ENTER);
	}
	
	public void loginMoodle(WebDriver wdMoodle, User user) {
		this.userMoodle.setUsername(user.getUsername());
		this.userMoodle.setPassword(user.getPassword());
		loginMoodle(wdMoodle);
	}
}
