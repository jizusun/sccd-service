package engineersearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class SAPNotesResultPage {
	WebDriver driver;
	/**?
	 * Becuase of SAP KBA result page is same as SAP Notes result page,so SAP KBA and SAP Notes use the same result page
	 */
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---notes--idNotesResultList']/ul/li[1]/div[2]/div/div[1]/div/a")
	private WebElement sapNotesResult0;
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---kbas--idKBAsResultList']/ul/li[1]/div[2]/div/div[1]/div/a")
	private WebElement sapKBAResult0;


	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//select one sap notes and click it
	public void clickSAPNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapNotesResult0);
		sapNotesResult0.click();
	}
	
	public void clickSAPKBANotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sapKBAResult0);
		sapKBAResult0.click();
	}
}
