package engineersearch.steps;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import common.cucumberunner.CucumberRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engineersearch.keyword.ResultKeyword;
import engineersearch.keyword.ESSearchKeyword;
import engineersearch.pageobject.NotesDetailPage;
import engineersearch.pageobject.RepositoryPage;

public class SAPNotes extends CucumberRunner {
    @Autowired
    ESSearchKeyword searchKeyword;
    @Autowired
    ResultKeyword resultKeyword;
    @Autowired
    RepositoryPage repositoryPage;
    @Autowired
    NotesDetailPage notesDetailPage;

	@Given("^search term \"(.*)\" and click search button$")
	public void search_term_and_click_search_button(String searchTerm) throws Throwable {	
        TimeUnit.SECONDS.sleep(10);
        searchKeyword.triggerSearch(driver, searchTerm);
	}

    @When("^select \"(.*)\" reporitory$")
    public void select_repository(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(10);
        repositoryPage.selectRepository(driver,repoName);
    }   

    @When("^click one notes in \"(.*)\" result list")
    public void click_one_note_in_result_list(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(60);
        resultKeyword.clickOneNote(driver,repoName);
    }

    @Then("^it will skip to \"(.*)\" detail page")
    public void skip_to_notes_detail(String repoName) throws Throwable {
        TimeUnit.SECONDS.sleep(20);
        assertTrue(repoName + " notes successfully displayed", notesDetailPage.notesDisplay(driver, repoName));
        driver.switchTo().window("");//go back to the main window
        TimeUnit.SECONDS.sleep(20);
    }

    //close the child window
    public void closeChildWindow() {
        String homeWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        //Use Iterator to iterate over windows
        Iterator<String> windowIterator =  allWindows.iterator();

        //Verify next window is available
        while(windowIterator.hasNext())
        {
            //Store the Recruiter window id
            String childWindow = windowIterator.next();
            if (homeWindow.equals(childWindow))
            {
                driver.switchTo().window(childWindow);
                driver.close();
            }
        }
        //Here we will compare if parent window is not equal to child window 
        // if (homeWindow.equals(childWindow))
        // {
        //     driver.switchTo().window(childWindow);
        //     driver.close();
        // }
        
    }
}
