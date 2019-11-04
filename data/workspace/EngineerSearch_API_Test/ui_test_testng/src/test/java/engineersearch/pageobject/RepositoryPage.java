package engineersearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class RepositoryPage {
	WebDriver driver;
	@FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[1]")
	private WebElement sapNotes;
	@FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[2]")
    private WebElement sapKBA;
    @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[3]")
	private WebElement customerIncidents;
	@FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[4]")
    private WebElement internalIncidents;
    @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[5]")
	private WebElement sapCommunity;
	@FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[6]")
    private WebElement sapSupportPortal;
    @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[7]")
	private WebElement sapHelpPortal;
	@FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[8]")
	private WebElement guidedAnswer;
    @FindBy(xpath="//*[@id='__xmlview2--searchInRepositoryList-listUl']/li[9]")
	private WebElement bydc4cIncidents;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//click search button to trigger search
	public void selectRepository(WebDriver driver,String repoName) {
        setDriver(driver);
        switch(repoName) {
            case "SAP Notes" :
                 //Scroll the page till the element is found
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapNotes);
                sapNotes.click();
                break;
            case "SAP Knowledge Base Articles" :
                sapKBA.click();
                break;
            case "Customer Incidents & Info Docs" :
                customerIncidents.click();
                break;
            case "Internal Incidents" :
                internalIncidents.click();
                break;
            case "SAP Community" :
                sapCommunity.click();
                break;
            case "SAP Support Portal" :
                sapSupportPortal.click();
                break;
            case "SAP Help Portal" :
                sapHelpPortal.click();
                break;
            case "Guided Answer" :
                guidedAnswer.click();
                break;
            case "ByD/C4C Incidents" :
                bydc4cIncidents.click();
                break;
            default :
                sapNotes.click();
                break;
        }
    }
	
}
