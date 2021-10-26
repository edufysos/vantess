package lps.test.edufysos.create;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lps.test.edufysos.util.CSsPropertyValues;


public class CreateWebDriver {
	
	private static WebDriver wdRosario, wdMoodle;
	
	
	public static WebDriver getDriverRosario() {
		if(wdRosario == null) {
			wdRosario = new ChromeDriver();
			wdRosario.get(CSsPropertyValues.getUrlRosario());
		}
		return wdRosario;
	}
	
	public static WebDriver getDriverMoodle() {
		if(wdMoodle == null) {
			wdMoodle = new ChromeDriver();
			wdMoodle.get(CSsPropertyValues.getUrlMoodle());
		}
		return wdMoodle;
	}
}
