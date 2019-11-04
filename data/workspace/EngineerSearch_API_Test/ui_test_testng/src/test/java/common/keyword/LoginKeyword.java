package common.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.pageobject.LoginPage;
import common.pageobject.SNOWLoginPage;
import common.pageobject.ESLoginPage;
import common.pageobject.LPLoginPage;


@Component
public class LoginKeyword {
	@Autowired
	LoginPage loginPage;
	@Autowired
	LPLoginPage lpLoginPage;
	@Autowired
	SNOWLoginPage snowLoginPage;
	@Autowired
	ESLoginPage esLoginPage;
//	LoginPage loginPage = new LoginPage();
	
	public void login(WebDriver driver,String name,String passWord) {
		
		loginPage.setDriver(driver);
		loginPage.setUserName(name);
		loginPage.setPassWord(passWord);
		loginPage.clickLoginButton();
	}

	public void loginLP(WebDriver driver,String name,String passWord) {
		
		lpLoginPage.setDriver(driver);
		lpLoginPage.setUserName(name);
		lpLoginPage.setPassWord(passWord);
		lpLoginPage.clickLoginButton();
	}

	public void loginSNOW(WebDriver driver,String name,String passWord) {
		
		snowLoginPage.setDriver(driver);
		snowLoginPage.setUserName(name);
		snowLoginPage.setPassWord(passWord);
		snowLoginPage.clickLoginButton();
	}

	public void loginES(WebDriver driver,String name,String passWord) {
		
		esLoginPage.setDriver(driver);
		esLoginPage.setUserName(name);
		esLoginPage.setPassWord(passWord);
		esLoginPage.clickLoginButton();
	}
}
