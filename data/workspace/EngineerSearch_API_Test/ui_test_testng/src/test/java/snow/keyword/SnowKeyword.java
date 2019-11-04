package snow.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import snow.pageobject.SnowPage;



@Component
public class SnowKeyword {
	@Autowired
	SnowPage snowPage;

//	LoginPage loginPage = new LoginPage();
	
	public void searchCase(WebDriver driver,String caseContext) {
		
		snowPage.setDriver(driver);
		snowPage.clickSearchBtn(driver);
		snowPage.inputCaseAndSearch(driver, caseContext);
    }
    
    public String searchResult(WebDriver driver) {
        snowPage.setDriver(driver);
        return snowPage.resultDisplay(driver);
    }

    public void selectCase(WebDriver driver) {
        snowPage.setDriver(driver);
        snowPage.selectCase(driver);
    }

    public String caseDetailDisplay(WebDriver driver) {
        snowPage.setDriver(driver);
        return snowPage.caseDetail(driver);
    }
}
