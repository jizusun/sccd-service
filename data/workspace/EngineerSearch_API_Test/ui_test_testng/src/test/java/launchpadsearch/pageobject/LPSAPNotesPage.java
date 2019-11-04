package launchpadsearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LPSAPNotesPage {
	WebDriver driver;
	/**?
	 * SAP Notes & KBAs
	 */
	@FindBy(xpath ="//*/div[@id='notesv2--search--idIconTabFilterNotesList2']/ul/li[1]/div[2]/div/div[1]/div/a")
	private WebElement sapNotesResult0;

	
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
	
}
