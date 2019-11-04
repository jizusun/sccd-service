package snow.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class CaseLoginPage {
	WebDriver driver;
	@FindBy(id="j_password")
	private WebElement userPassword;
	@FindBy(id="logOnFormSubmit")
	private WebElement login;
	// @FindBy(id="__jsview0--shell-title")
	// private WebElement launchpadLabel;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
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
