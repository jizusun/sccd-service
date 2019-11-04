package snow.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import snow.pageobject.CaseLoginPage;



@Component
public class CaseLoginKeyword {
	@Autowired
	CaseLoginPage caseLoginPage;

//	LoginPage loginPage = new LoginPage();
	
	public void login(WebDriver driver,String passWord) {
		
		caseLoginPage.setDriver(driver);
		caseLoginPage.setPassWord(passWord);
		caseLoginPage.clickLoginButton();
	}
}
