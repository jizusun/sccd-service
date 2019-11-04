package snow.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class SnowPage {
	WebDriver driver;
    
    @FindBy(tagName ="sn-component-workspace-global-search-typeahead")
    private WebElement shadowRootSearch;
    @FindBy(tagName="sn-workspace-content")
    private WebElement shadowRootSearchResult;
    @FindBy(xpath="//*[contains(text(),'Create SAE Request')]")
    private WebElement caseDetail;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
    
    public void clickSearchBtn(WebDriver driver) {
        setDriver(driver);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchBtn);
		findByShadowSearchButton(driver).click();
    }

	public void inputCaseAndSearch(WebDriver driver,String caseContext) {
		setDriver(driver);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", snowSearchCenter);
        findByShadowSearchCenter(driver).sendKeys(caseContext);
        findByShadowSearchCenter(driver).sendKeys(Keys.ENTER);
	}
    
    public String resultDisplay(WebDriver driver) {
        setDriver(driver);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", firstResult);
        return findByShadowSearchResult(driver).getText();
    }

    public void selectCase(WebDriver driver) {
        setDriver(driver);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", firstResult);
        findByShadowSearchResult(driver).click();
    }

    public String caseDetail(WebDriver driver) {
        setDriver(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", caseDetail);
        return caseDetail.getText();
    }

    //Find element shadow-root
    private WebElement shadowDom;

    private WebElement expandRootElement(WebElement element,WebDriver driver) {
        WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",element);
        return ele;
    }
    // shadow-root search
    private void findByShadowRootSearch(WebDriver driver) {
        shadowDom = expandRootElement(shadowRootSearch,driver);
    }

    // shadow-root search
    private void findByShadowRootSearchResult(WebDriver driver) {
        shadowDom = expandRootElement(shadowRootSearchResult,driver);
    }

    //Find element in shadow-root
    private WebElement findByShadowSearchButton(WebDriver driver) {
        findByShadowRootSearch(driver);
        return shadowDom.findElement(By.cssSelector("div > div.sn-typeahead--control-container > button"));
    }

    private WebElement findByShadowSearchCenter(WebDriver driver) {
        findByShadowRootSearch(driver);
        return shadowDom.findElement(By.id("sncwsgs-typeahead-input"));
    }

    private WebElement findByShadowSearchResult(WebDriver driver) {
        findByShadowRootSearchResult(driver);
        WebElement root2 = shadowDom.findElement(By.cssSelector("sn-component-workspace-global-search-tab"));
        WebElement shadowDom2 = expandRootElement(root2,driver);
        return shadowDom2.findElement(By.cssSelector("div > div.tab-results-container.sn-panel--row > div.sn-panel.sn-global-search > div.sn-panel--body.tab-results > div:nth-child(1) > div > ul > li:nth-child(1) > span > span"));
    }
}
