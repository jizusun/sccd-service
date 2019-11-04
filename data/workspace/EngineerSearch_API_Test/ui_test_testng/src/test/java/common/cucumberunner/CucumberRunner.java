package common.cucumberunner;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
features = { "src/test/java/common/features", 
			 "src/test/java/notes/features",
			 "src/test/java/snow/features",
			 "src/test/java/engineersearch/features",
			 "src/test/java/launchpadsearch/features"}, 
glue = { "common/steps", 
		 "notes/steps",
		 "snow/steps",
		 "engineersearch/steps",
		 "launchpadsearch/steps"}, 
plugin = { "pretty",
			"html:target/cucumber",
			"json:target/cucumber/cucumber.json"},
monochrome = true,
tags = { "@notes" })
@ContextConfiguration(locations = { "classpath*:spring/spring.xml" })
public class CucumberRunner extends AbstractTestNGCucumberTests {

	public static WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.chrome.driver",
		// "src\\test\\java\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		Dimension windowSize = new Dimension(1600,900);
		driver.manage().window().setSize(windowSize);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
}
