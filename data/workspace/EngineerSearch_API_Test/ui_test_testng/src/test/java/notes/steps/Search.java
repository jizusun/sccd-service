package notes.steps;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import common.cucumberunner.CucumberRunner;
// import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import notes.keyword.SearchKeyword;

public class Search extends CucumberRunner {
	
	@Autowired
	SearchKeyword searchKeyword;	
 
	@When("^input a text and click search button$")
	public void input_a_text_and_click_search_button() throws Throwable {
		searchKeyword.triggerSearch(driver, "hana");
		TimeUnit.SECONDS.sleep(10);
	}
 
	@Then("^the result list display$")
	public void the_result_list_display() throws Throwable {
		Assert.assertEquals(true,searchKeyword.resultDisplay());
	}
	
	
}
