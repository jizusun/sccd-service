package common.steps;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import common.cucumberunner.CucumberRunner;
import common.keyword.LoginKeyword;
import common.pageobject.URLConfig;
import cucumber.api.java.en.Given;

public class Login extends CucumberRunner {
	
	@Autowired
	LoginKeyword loginKeyword;	
	@Autowired
	URLConfig urlConfig;

	public static String landscape = "DEV";
	public static String application = "launchpad";
	public static String url = "https://supportshell-waeb84638.dispatcher.int.sap.hana.ondemand.com/";
	private static String username ="";
	private static String password = "";

	@Given("^login to \"(.*)\"$")
	public void login_to(String app) throws Throwable {	
		// landscape = System.getProperty("propertyName");
		landscape = System.getProperty("landscape");
		username = System.getProperty("username");
		password = System.getProperty("password");
		application = app;
		url = urlConfig.getURLConfig(landscape, application);
		if(url==null||url=="") {
			url = "https://supportshell-waeb84638.dispatcher.int.sap.hana.ondemand.com/";
		}
		//open browser with the url
		driver.get(url);	  
		TimeUnit.SECONDS.sleep(5);
		//login to relevance application according to the app
		switch(application){
			case "LP" : 
				loginToLP();
				break;
			case "SNOW" : 
				loginToSNOW();
				break;	
			case "ES" : 
				loginToES();
				break;
			default : 
				loginToLP();
				break;
		}
	}
	
	public void loginToLP() {
		loginKeyword.loginLP(driver,username,password);
	}

	public void loginToSNOW() {
		loginKeyword.loginSNOW(driver,username,password);
	}

	public void loginToES() {
		loginKeyword.loginES(driver,username,password);
	}

}
