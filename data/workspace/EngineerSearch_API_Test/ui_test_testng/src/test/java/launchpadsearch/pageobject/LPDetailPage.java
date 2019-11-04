package launchpadsearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPDetailPage {
	WebDriver driver;
	/**?
	 * Onex page
	 */
	@FindBy(xpath ="//*/div[@id='scnforums--search--idIconTabFilterSCNFMList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapCommunity0;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void clickCommunityNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapCommunity0);
		sapCommunity0.click();
    }
	
}
