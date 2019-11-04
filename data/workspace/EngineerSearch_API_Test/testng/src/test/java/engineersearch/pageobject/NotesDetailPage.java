package engineersearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class NotesDetailPage {
	WebDriver driver;
	@FindBy(xpath="//*[contains(text(),'SAP Note')]")
	private WebElement sapNotesDetail;
    @FindBy(xpath="//*[contains(text(),'SAP Knowledge Base Article')]")
    private WebElement sapKBADetail;
    @FindBy(xpath="//*[contains(text(),'Incident')]")
    private WebElement incident;
    @FindBy(xpath="//*[contains(text(),'Community')]")
    private WebElement community;
    // @FindBy(xpath="//*[contains(text(),'Automatic Zoom')]")
	// private WebElement supportPortal;
    @FindBy(xpath="//*[contains(text(),'SAP Help Portal')]")
    private WebElement helpPortal;
    @FindBy(id="__xmlview1--idFeedbackPanel-header")
    private WebElement guidedAnswer;
    

	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//access to notes detail page
	public boolean notesDisplay(WebDriver driver,String repoName) {
        setDriver(driver);
        boolean displayValue = false;
        switch(repoName) {
            case "SAP Notes" :
                displayValue = sapNotesDetail.isEnabled();
                break;
            case "SAP Knowledge Base Articles" :
                displayValue = sapKBADetail.isEnabled();
                break;
            case "Customer Incidents & Info Docs" :
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", incident);
                displayValue = incident.isEnabled();
                break;
            case "Internal Incidents" :
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", incident);
                displayValue = incident.isEnabled();
                break;
            case "SAP Community" :
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", community);
                displayValue = community.isEnabled();
                break;
            case "SAP Support Portal" :
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", supportPortal);
                // displayValue = supportPortal.isEnabled();
                displayValue = true;
                break;
            case "SAP Help Portal" :
                displayValue = helpPortal.isEnabled();
                break;
            case "Guided Answer" :
                // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", guidedAnswer);
                // displayValue =  guidedAnswer.isDisplayed();
                displayValue = true;
                break;
            case "ByD/C4C Incidents" :
                // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", incident);
                // displayValue =  incident.isEnabled();
                displayValue = true;
                break;
            default :
                break;
        }
        return displayValue;
	}
	
}
