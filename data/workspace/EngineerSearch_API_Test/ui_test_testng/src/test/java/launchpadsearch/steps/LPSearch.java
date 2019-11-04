package launchpadsearch.steps;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import common.cucumberunner.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import launchpadsearch.keyword.LPResultKeyword;
import launchpadsearch.keyword.LPSearchKeyword;
import launchpadsearch.pageobject.LPRepositoryPage;


public class LPSearch extends CucumberRunner {

    @Autowired
    LPSearchKeyword lpSearchKeyword;
    @Autowired
    LPRepositoryPage lpRepositoryPage;
    @Autowired
    LPResultKeyword lpResultKeyword;

	@Given("^lp search term \"(.*)\" and click search button$")
	public void lp_search_term_and_click_search_button(String searchTerm) throws Throwable {	
        TimeUnit.SECONDS.sleep(60);
        lpSearchKeyword.triggerSearch(driver, searchTerm);
	}

    @When("^lp select \"(.*)\" reporitory$")
    public void lp_select_repository(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(10);
        lpRepositoryPage.selectRepository(driver,repoName);
    }   

    @When("^click one notes in lp \"(.*)\" result list")
    public void lp_click_one_note_in_result_list(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(30);
        lpResultKeyword.clickLPNote(driver,repoName);
    }

    @Then("^it will skip to lp \"(.*)\" detail page")
    public void lp_skip_to_notes_detail(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(20);
        // assertTrue(repoName + " notes successfully displayed", notesDetailPage.notesDisplay(driver, repoName));
        driver.switchTo().window("");//go back to the main window
        TimeUnit.SECONDS.sleep(20);
    }
}
