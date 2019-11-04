package engineersearch.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineersearch.pageobject.SearchCenterPage;

// import common.pageobject.LoginPage;
// import common.pageobject.SNOWLoginPage;
// import common.pageobject.ESLoginPage;
// import common.pageobject.LPLoginPage;


@Component
public class ESSearchKeyword {
	@Autowired
    SearchCenterPage searchCenterPage;

    public void triggerSearch(WebDriver driver,String searchTerm) {
		
		searchCenterPage.setDriver(driver);
		searchCenterPage.inputSearchTerm(searchTerm);
		searchCenterPage.clickSearchButton();
	}
}
