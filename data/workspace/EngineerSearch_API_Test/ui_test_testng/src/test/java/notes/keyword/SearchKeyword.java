package notes.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import notes.pageobject.SearchPage;

@Component
public class SearchKeyword {
	@Autowired
	SearchPage searchPage;
	
	// trigger search
	public void triggerSearch(WebDriver driver,String searchTerm) {		
		searchPage.setDriver(driver);
		searchPage.enterSearchTerm(searchTerm);
		searchPage.clickSearchButton();
	}

	// result list display
	public boolean resultDisplay() {		
		return searchPage.resultListDisplay();		
	}

}
