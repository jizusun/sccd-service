package notes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchPage {
	WebDriver driver;
	@FindBy(id="search--searchCenterInputfield-I")
	private WebElement search;
	@FindBy(id="search--searchCenterSearchButton-img")
	private WebElement searchButton;
	@FindBy(id="__xmlview2--search--DynamicSideContent-MCGridCell")
	private WebElement resultDisplay;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//enter search term
	public void enterSearchTerm(String searchTerm) {
		search.sendKeys(searchTerm);
	}
	
	//click search button to search	
	public void clickSearchButton() {
		searchButton.click();
	}

	public boolean resultListDisplay() {
		return resultDisplay.isDisplayed();
	}
}
