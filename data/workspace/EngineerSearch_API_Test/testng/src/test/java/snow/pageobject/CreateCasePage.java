package snow.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateCasePage {
	WebDriver driver;
    @FindBy(id="__xmlview1--customerNumberInput-vhi")
    private WebElement customerSelection;
	@FindBy(id ="__field0-I")
	private WebElement searchCustomerCenter;
    @FindBy(xpath ="//*/div[@id='contactViewDynamic--IncidentCreationCustomerDialogList']/ul/li[2]/div/div[1]/div[2]")
    private WebElement oneCustomer;
    @FindBy(id="__xmlview1--inputSystemSelection-vhi")
    private WebElement systemSelection;
    @FindBy(id="__field2-I")
    private WebElement systemSearchCenter;
    @FindBy(id="__field2-search")
    private WebElement systemSearchBtn;
    @FindBy(xpath="//*/div[@id='__bar1-content']/div[2]")
    private WebElement oneSystem;
    @FindBy(id="__xmlview1--subjectInput-inner")
    private WebElement subject;
    @FindBy(id="__xmlview1--descriptionRichEditor-textarea_ifr")
    private WebElement description;

    @FindBy(id="__xmlview1--compInput-vhi")
    private WebElement compInput;
    @FindBy(id="contactViewDynamic--componentSearchField-I")
    private WebElement componentSearchCenter;
    @FindBy(xpath ="//*/div[@id='contactViewDynamic--componentSearchList-listUl']/li")
    private WebElement oneComponent;
    
    @FindBy(id="__xmlview1--sendBtn-content")
    private WebElement submit;
    
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
    // public void clickCustomerSelection(WebDriver driver) {
	// 	setDriver(driver);
	// 	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", customerSelection);
	// 	customerSelection.click();
	// }

	public void inputCustomer(WebDriver driver,String customerNumber) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchCustomerCenter);
		searchCustomerCenter.sendKeys(customerNumber);
	}

	public void selectCustomer(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", oneCustomer);
		oneCustomer.click();
    }

    public void clickSystemSelection(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", systemSelection);
		systemSelection.click();
    }
    
    public void inputSystem(WebDriver driver,String systemName) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", systemSearchCenter);
		systemSearchCenter.sendKeys(systemName);
    }
    
    public void triggerSystemSearch(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", systemSearchBtn);
		systemSearchBtn.click();
    }

    public void selectSystem(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", oneSystem);
		oneSystem.click();
    }
    
    public void inputSubject(WebDriver driver,String subjectContext) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", subject);
		subject.sendKeys(subjectContext);
    }

    public void inputDescription(WebDriver driver,String desContext) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", description);
		description.sendKeys(desContext);
    }

    public void clickComponentSelection(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", compInput);
		compInput.click();
    }

    public void inputComponentName(WebDriver driver,String compName) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", componentSearchCenter);
		componentSearchCenter.sendKeys(compName);
    }

    public void selectComponent(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", oneComponent);
		oneComponent.click();
    }

    public void submitCaseInfo(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", submit);
		submit.click();
    }
    
    
}
