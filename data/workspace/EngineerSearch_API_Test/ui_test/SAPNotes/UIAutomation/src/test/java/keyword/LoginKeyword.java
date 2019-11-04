package keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import pageobject.LoginPage;

@Component
public class LoginKeyword {
	@Autowired
	LoginPage loginPage;
//	LoginPage loginPage = new LoginPage();
	
	public void login(WebDriver driver,String name,String passWord) {
		
		loginPage.setDriver(driver);
		loginPage.setUserName(name);
		loginPage.setPassWord(passWord);
		loginPage.clickLoginButton();
	}
	
	public String enterLaunchpad() {
		return loginPage.launchpadLabelDisplay();
	}

	public String testSpring() {
		return "Kumara + spring";
	}
}
