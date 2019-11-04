package steps;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import common.CucumberRunner;
import keyword.LoginKeyword;

public class test extends CucumberRunner{
	@Autowired
	LoginKeyword loginKeyword;	
	
	@Given("^Open test and Enter url$")
	public void open_Application_and_Enter_url() throws Throwable {
		url = "https://www.baidu.com";
		System.out.println("Kumara Url test ... " + url );
		driver.get(url);	  
		TimeUnit.SECONDS.sleep(10);
	}
 
	@When("^enter userName  to login$")
	public void enter_username() throws Throwable {
//		loginKeyword.login(driver,"S0006211344","Sa-Bine7");
//		driver.findElement(By.id("j_username")).sendKeys("S0006211344");
//		driver.findElement(By.id("j_password")).sendKeys("Sa-Bine7");
//		driver.findElement(By.id("logOnFormSubmit")).click();
		System.out.println("Kumara test");
	}
 
	@Then("^verify L$")
	public void verify_Msg() throws Throwable {
//		Assert.assertEquals("ONE Support Launchpad",loginKeyword.enterLaunchpad());
		Assert.assertTrue(false, "test fail");
		Assert.assertTrue(false, "test fail");
		System.out.println("Kumara test");
	}
}
