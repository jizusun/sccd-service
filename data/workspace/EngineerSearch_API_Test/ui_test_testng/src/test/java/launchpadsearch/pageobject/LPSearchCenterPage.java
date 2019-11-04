package launchpadsearch.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPSearchCenterPage {
	WebDriver driver;
	@FindBy(id="search--searchCenterInputfield-I")
	private WebElement searchCenter;
	@FindBy(id="search--searchCenterSearchButton")
	private WebElement searchBtn;

	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//input search term
	public void inputSearchTerm(String searchTerm) {
		searchCenter.clear();
		searchCenter.sendKeys(searchTerm);
	}
	
	//click search button to trigger search
	public void clickSearchButton() {
		searchBtn.click();
	}
	
}
