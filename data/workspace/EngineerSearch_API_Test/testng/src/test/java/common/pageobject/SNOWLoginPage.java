package common.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class SNOWLoginPage {
	WebDriver driver;
	@FindBy(id="user_name")
	private WebElement userName;

	@FindBy(id="user_password")
	private WebElement userPassword;

	@FindBy(id="sysverb_login")
	private WebElement login;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//input username
	public void setUserName(String name) {
		userName.sendKeys(name);
	}
	
	//input password
	public void setPassWord(String passWorld) {
		userPassword.sendKeys(passWorld);
	}
	
	//click log on button
	public void clickLoginButton() {
		login.click();
	}
	
}
