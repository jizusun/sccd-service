package launchpadsearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPRepositoryPage {
	WebDriver driver;
	@FindBy(id="tabnotesv2")
    private WebElement sapNotes;
    @FindBy(id="tabscnforums")
    private WebElement sapCummunity;
    @FindBy(id="tabscnwiki")
    private WebElement sapCummunityWk;
    @FindBy(id="tabcq5portal")
    private WebElement sapSupportPortal;
    @FindBy(id="tabsaphelp")
    private WebElement sapHelpPortal;
    @FindBy(id="tablithiumforums")
    private WebElement sucFactorsCmnt;
    @FindBy(id="tabguidedanswers")
    private WebElement guidedAnswer;
    @FindBy(id="tabcallidus")
    private WebElement sapSalesCloud;
    @FindBy(id="tablitmos")
    private WebElement sapLitmos;
    @FindBy(id="tabqualtrics")
	private WebElement sapQualtrics;
	// @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[2]")
    // private WebElement sapKBA;
    // @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[3]")
	// private WebElement customerIncidents;
	// @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[4]")
    // private WebElement internalIncidents;
    // @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[5]")
	// private WebElement sapCommunity;
	// @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[6]")
    // private WebElement sapSupportPortal;
    // @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[7]")
	// private WebElement sapHelpPortal;
	// @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[8]")
	// private WebElement guidedAnswer;
    // @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[9]")
	// private WebElement bydc4cIncidents;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//click search button to trigger search
	public void selectRepository(WebDriver driver,String repoName) {
        setDriver(driver);
        switch(repoName) {
            case "SAP Notes & KBAs" :
                 //Scroll the page till the element is found
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapNotes);
                sapNotes.click();
                break;
            case "SAP Community" :
                sapCummunity.click();
                break;
            case "SAP Community Wiki" :
                sapCummunityWk.click();
                break;
            case "SAP Support Portal" :
                sapSupportPortal.click();
                break;
            case "SAP Help Portal" :
                sapHelpPortal.click();
                break;
            case "SuccessFactors Community" :
                sucFactorsCmnt.click();
                break;
            case "Guided Answers" :
                guidedAnswer.click();
                break;
            case "SAP Sales Cloud" :
                sapSalesCloud.click();
                break;
            case "SAP Litmos Training Cloud" :
                sapLitmos.click();
                break;
            case "SAP Qualtrics Support Pages" :
                sapQualtrics.click();
                break;
            default :
                sapNotes.click();
                break;
        }
    }
	
}
