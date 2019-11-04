package common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features= {"src/test/java/features"},
		glue={"steps"},
		plugin = { 
				"pretty",
   				"html:target/cucumber",
   				"json:target/cucumber/cucumber.json",
		},
		monochrome = true
		,tags 				= {"@all","@Notes"}
		)
@ContextConfiguration(locations = { "classpath*:/spring/spring-notes.xml" })
public class CucumberRunner extends AbstractTestNGCucumberTests{
	
	public static WebDriver driver;	
	public String DEV="https://supportshell-waeb84638.dispatcher.int.sap.hana.ondemand.com/";
	public String QUAL="https://supportshell-wb0ceb360.dispatcher.int.sap.hana.ondemand.com/";
	public String TEST="https://supportshell-wd9bf76ae.dispatcher.int.sap.hana.ondemand.com/";
	public String CANARA="https://supportshell-supportportal.dispatcher.int.sap.hana.ondemand.com/";
	public String PRO="https://launchpad.support.sap.com/";
	public Map<String, String> urlMap = new HashMap<String, String>();
	public String systemUrl = "";
	public static String url="https://supportshell-waeb84638.dispatcher.int.sap.hana.ondemand.com/";
	
	@BeforeTest
	public void beforeTest() {
		//System.setProperty("webdriver.chrome.driver", "src\\test\\java\\resources\\chromedriver.exe");
		systemUrl = System.getProperty("propertyName");	
		System.out.println("kumara systemUrl ........" + systemUrl);
		urlMap.put("DEV", DEV);
		urlMap.put("QUAL", QUAL);
		urlMap.put("TEST", TEST);
		urlMap.put("CANARA", CANARA);
		urlMap.put("PRO", PRO);
		if(systemUrl == "" || systemUrl == null) {
			url = DEV;
		}else{
			url = urlMap.get(systemUrl);
		}
		System.out.println("kumara url ........" + url);	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
}
