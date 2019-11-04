package launchpadsearch.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import launchpadsearch.pageobject.LPSearchCenterPage;



@Component
public class LPSearchKeyword {
	@Autowired
    LPSearchCenterPage lpSearchCenterPage;

    public void triggerSearch(WebDriver driver,String searchTerm) {
		
		lpSearchCenterPage.setDriver(driver);
		lpSearchCenterPage.inputSearchTerm(searchTerm);
		lpSearchCenterPage.clickSearchButton();
	}
}