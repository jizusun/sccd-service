package engineersearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class OnedxResultPage {
	WebDriver driver;
	/**?
	 * Becuase of SAP KBA result page is same as SAP Notes result page,so SAP KBA and SAP Notes use the same result page
	 */
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---community--idCommunityList']/ul/li[1]/div[2]/a")
	private WebElement community;
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---supportportal--idSupportPortal']/ul/li[1]/div[2]/a")
    private WebElement supportPortal;
    @FindBy(xpath ="//*/div[@id='EngineerSearchContainer---helpportal--idHelpPortal']/ul/li[1]/div[2]/a")
    private WebElement helpPortal;
    @FindBy(xpath ="//*/div[@id='EngineerSearchContainer---guidedanswer--idGuidedAnswerList']/ul/li[1]/div[2]/a")
	private WebElement guidedAnswer;


	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickCommunityNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", community);
		community.click();
	}

	public void clickSupPortalNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", supportPortal);
		supportPortal.click();
    }
 
    public void clickHelpPortalNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", helpPortal);
		helpPortal.click();
    }
    public void clickGuidedAnswerNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", guidedAnswer);
		guidedAnswer.click();
	}
}
