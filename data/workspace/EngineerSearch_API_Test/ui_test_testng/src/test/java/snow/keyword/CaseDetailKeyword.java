package snow.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import snow.pageobject.CaseDetailPage;



@Component
public class CaseDetailKeyword {
	@Autowired
	CaseDetailPage caseDetailPage;

//	LoginPage loginPage = new LoginPage();
	
	public String CaseDisplay(WebDriver driver) {
		
		caseDetailPage.setDriver(driver);
		return caseDetailPage.caseDetailDisplay(driver);
    }

}
