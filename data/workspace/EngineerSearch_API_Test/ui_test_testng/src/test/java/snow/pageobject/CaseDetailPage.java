package snow.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class CaseDetailPage {
	WebDriver driver;
    
    @FindBy(xpath ="//*[contains(text(),'Create SAE Request')]")
    private WebElement caseDetail;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
    
    public String caseDetailDisplay(WebDriver driver) {
        setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", caseDetail);
		return caseDetail.getText();
    }
}
