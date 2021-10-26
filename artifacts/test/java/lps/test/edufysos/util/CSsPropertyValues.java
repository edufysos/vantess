package lps.test.edufysos.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CSsPropertyValues {


	public static String urlChromeDriver;
	
	protected static String urlRosario;
	protected static String urlMoodle;
	protected static String passRosario;
	protected static String passMoodle;	
	protected static String userRosario;
	protected static String userMoodle;	
	
	public CSsPropertyValues(){
		this.configPropertiesValues();
	}


	public void configPropertiesValues() {
		Properties prop = new Properties();
		String propFileName = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				System.err.print("property file '" + propFileName + "' not found in the classpath");
			}
		} else {
			System.err.print("property file '" + propFileName + "' not found in the classpath");
		}		
		
		urlChromeDriver = prop.getProperty("urlChromeDriver");
		
		urlRosario = prop.getProperty("urlAOS");
		urlMoodle = prop.getProperty("urlLMS");
		passRosario = prop.getProperty("passAOS");
		passMoodle = prop.getProperty("passLMS");
		userRosario = prop.getProperty("userAOS");
		userMoodle = prop.getProperty("userLMS");			
	}

	public static String urlChromeDriver() {
		return urlChromeDriver;
	}	
	
	public static String getUrlRosario() {
		return urlRosario;
	}


	public static String getUrlMoodle() {
		return urlMoodle;
	}


	public static String getPassRosario() {
		return passRosario;
	}


	public static String getPassMoodle() {
		return passMoodle;
	}

	public static String getUserRosario() {
		return userRosario;
	}

	public static String getUserMoodle() {
		return userMoodle;
	}


}
