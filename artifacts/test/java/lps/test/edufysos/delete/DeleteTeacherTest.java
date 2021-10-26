package lps.test.edufysos.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lps.test.edufysos.create.CreateTeacherTest;
import lps.test.edufysos.create.CreateWebDriver;
import lps.test.edufysos.login.LoginTest;
import lps.test.edufysos.util.CSsPropertyValues;

public class DeleteTeacherTest {
	
	protected WebDriver driverRosario, driverMoodle;
	protected static CSsPropertyValues propCS = new CSsPropertyValues();
	
	CreateTeacherTest teacher = new CreateTeacherTest();
	LoginTest lt = new LoginTest();

	
	public void loginAdminRosario() {
		driverRosario = CreateWebDriver.getDriverRosario();
		lt.loginRosario(driverRosario);
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
	
	@Test
	public void deleteTeacherTestInRosario() {
		this.loginAdminRosario();
		this.waitSecconds(1000);
		
		this.driverRosario.get("http://129.213.75.60/rosariosis/Modules.php?modname=Users/User.php&modfunc=&search_modfunc=list&next_modname=Users/User.php&advanced=&");
		this.waitSecconds(1000);
		
		this.driverRosario.findElement(By.xpath("//a[text()='"+this.teacher.getTeacher().getFirstName()+" "+this.teacher.getTeacher().getLastName()+"']")).click();
		this.waitSecconds(1000);
		
		this.driverRosario.findElement(By.xpath("//input[@value='Delete']")).click();
		this.waitSecconds(1000);
		
		this.driverRosario.findElement(By.name("delete_ok")).click();
		this.waitSecconds(1000);
		
		this.driverRosario.findElement(By.xpath("//input[@value='Submit']")).click();
		this.waitSecconds(1000);
		
		boolean exist = this.driverRosario.findElements(By.xpath("//a[text()='\"+this.teacher.getTeacher().getFirstName()+\" \"+this.teacher.getTeacher().getLastName()+\"']")).size()>0;
		assertFalse(exist);
	}

}
