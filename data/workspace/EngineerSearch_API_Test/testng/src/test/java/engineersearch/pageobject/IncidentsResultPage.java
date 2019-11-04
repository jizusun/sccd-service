package engineersearch.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class IncidentsResultPage {
	WebDriver driver;
	/**?
	 * Becuase of SAP KBA result page is same as SAP Notes result page,so SAP KBA and SAP Notes use the same result page
	 */
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---infodocs--idInfoDocsResultList']/ul/li[1]/div[2]/div/div[1]/div/a")
	private WebElement infoDocs;
	@FindBy(xpath ="//*/div[@id='EngineerSearchContainer---incidents--idIncidentsResultList']/ul/li[1]/div[2]/div/div[1]/div/a")
    private WebElement internalIncidents;
    @FindBy(xpath ="//*/div[@id='EngineerSearchContainer---bydc4c--idBYDC4CResultList']/ul/li[1]/div[2]/div/div[1]/div/a")
	private WebElement bydc4cIncidents;


	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//select one customer incident & infodoc note and click
	public void clickInfoDocsNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", infoDocs);
		infoDocs.click();
	}
	//select one internal incident note and click
	public void clickIntlIncdtsNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", internalIncidents);
		internalIncidents.click();
    }
    //select one bydc4c incident note and click
    public void clickbydc4cIncdtsNotes(WebDriver driver) {
		setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bydc4cIncidents);
		bydc4cIncidents.click();
	}
}
