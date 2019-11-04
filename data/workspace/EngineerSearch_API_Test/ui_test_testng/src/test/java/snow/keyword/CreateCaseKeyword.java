package snow.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import snow.pageobject.CreateCasePage;



@Component
public class CreateCaseKeyword {
	@Autowired
	CreateCasePage createCasePage;
	
	public void inputCustomerNumber(WebDriver driver,String customerNumber) {
		
		createCasePage.setDriver(driver);
        // createCasePage.clickCustomerSelection(driver);
        createCasePage.inputCustomer(driver, customerNumber);
        createCasePage.selectCustomer(driver);
    }
    
    public void inputSystemName(WebDriver driver,String systemName) {
		
		createCasePage.setDriver(driver);
        createCasePage.clickSystemSelection(driver);
        createCasePage.inputSystem(driver, systemName);
        createCasePage.triggerSystemSearch(driver);
        createCasePage.selectSystem(driver);
    }
    
    public void inputSubject(WebDriver driver,String subjectContext) {
		
		createCasePage.setDriver(driver);
        createCasePage.inputSubject(driver, subjectContext);
    }
    
    public void inputDescription(WebDriver driver,String desContext) {
		
		createCasePage.setDriver(driver);
        createCasePage.inputDescription(driver, desContext);
    }
    

    public void inputComponent(WebDriver driver,String compName) {
		
		createCasePage.setDriver(driver);
        createCasePage.clickComponentSelection(driver);
        createCasePage.inputComponentName(driver, compName);
        createCasePage.selectComponent(driver);
    }

    

    public void clickSubmitBtn(WebDriver driver) {
		
		createCasePage.setDriver(driver);
        createCasePage.submitCaseInfo(driver);
    }

}
