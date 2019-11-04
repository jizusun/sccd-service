package launchpadsearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPOtherPage {
	WebDriver driver;
	/**?
	 * Others page
	 */
	@FindBy(xpath ="//*/div[@id='callidus--search--callidusList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapSalesCloud0;
    @FindBy(xpath ="//*/div[@id='litmos--search--litmosList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement litmos0;
    @FindBy(xpath ="//*/div[@id='qualtrics--search--qualtricsList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement qualtrics0;

	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void clickCallidusNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapSalesCloud0);
		sapSalesCloud0.click();
    }
    
    public void clickLitmosNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", litmos0);
		litmos0.click();
    }
    
    public void clickQualtricsNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", qualtrics0);
		qualtrics0.click();
    }
	
}
