package launchpadsearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPOnedxPage {
	WebDriver driver;
	/**?
	 * Onex page
	 */
	@FindBy(xpath ="//*/div[@id='scnforums--search--idIconTabFilterSCNFMList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapCommunity0;
    @FindBy(xpath ="//*/div[@id='scnwiki--search--idIconTabFilterWiKiList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapCommunityWiki0;
    @FindBy(xpath ="//*/div[@id='cq5portal--idIconTabFilterSPList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapSupportPortal0;
    @FindBy(xpath ="//*/div[@id='saphelp--search--saphelpList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sapHelpPortal0;
    @FindBy(xpath ="//*/div[@id='lithiumforums--search--idIconTabFilterSFCNList']/ul/li[1]/div[2]/div[1]/a")
    private WebElement sucessFactorsCmnt0;
    @FindBy(xpath ="//*/div[@id='guidedanswers--list_guidedanswers']/ul/li[1]/div[2]/div[1]/a")
	private WebElement guidedAnswer0;
	@FindBy(xpath ="//*/div[@id='confirmationDialog-footer']/button[1]")
	private WebElement okBtn;


	
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
    
    public void clickCmntWikiNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapCommunityWiki0);
		sapCommunityWiki0.click();
    }
    
    public void clickSupPortalNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapSupportPortal0);
		sapSupportPortal0.click();
    }

    public void clickHelpPortalNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapHelpPortal0);
		sapHelpPortal0.click();
    }
    public void clickSucFactorCmntNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sucessFactorsCmnt0);
		sucessFactorsCmnt0.click();
    }
    
    public void clickGuidedAnswerNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", guidedAnswer0);
		guidedAnswer0.click();
	}

	public void clickDialogOKBtn(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", okBtn);
		okBtn.click();
	}
	
}
