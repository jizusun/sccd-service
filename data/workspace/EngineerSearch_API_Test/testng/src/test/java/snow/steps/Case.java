package snow.steps;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import common.cucumberunner.CucumberRunner;
// import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import snow.keyword.CreateCaseKeyword;
import snow.keyword.SnowKeyword;
import snow.keyword.CaseLoginKeyword;

public class Case extends CucumberRunner {
	
	@Autowired
	CreateCaseKeyword createCaseKeyword;
	@Autowired
	CaseLoginKeyword caseLoginKeyword;
	@Autowired
	SnowKeyword snowKeyword;
	private String incidentFormURL = "https://supportshell-dgq5qdz5dm.dispatcher.int.sap.eu2.hana.ondemand.com/#/incident/create";
	@When("^create case$")
	public void create_case() throws Throwable {
		//enter create incident page
		driver.get(incidentFormURL);
		TimeUnit.SECONDS.sleep(60);
		createCaseKeyword.inputCustomerNumber(driver, "1208936");
		TimeUnit.SECONDS.sleep(10);
		createCaseKeyword.inputSystemName(driver, "CLO - FSM_EU");
		TimeUnit.SECONDS.sleep(10);
		createCaseKeyword.inputSubject(driver, "Case integration Auto test - TestNG");
		TimeUnit.SECONDS.sleep(10);
		createCaseKeyword.inputDescription(driver, "case integration auto test");
		TimeUnit.SECONDS.sleep(10);
		createCaseKeyword.inputComponent(driver, "CEC-SRV");
		TimeUnit.SECONDS.sleep(10);
		createCaseKeyword.clickSubmitBtn(driver);
		TimeUnit.SECONDS.sleep(90);
	}
	@Then("^should jump to snow portal$")
	public void should_jump_to_snow_portal() throws Throwable {
		caseLoginKeyword.login(driver, "Sapsap123!");		
	}

	@When("^search case and select case$")
	public void search_case_and_select_case() throws Throwable {
		TimeUnit.SECONDS.sleep(60);
		snowKeyword.searchCase(driver, "integration test");
		TimeUnit.SECONDS.sleep(30);
		Assert.assertEquals("integration test", snowKeyword.searchResult(driver));
		snowKeyword.selectCase(driver);
		TimeUnit.SECONDS.sleep(60);
	}

	@Then("^should jump to case detail$")
	public void should_jump_to_case_detail() throws Throwable {
		// Assert.assertEquals("Create SAE Request", snowKeyword.caseDetailDisplay(driver));		
	}
}
