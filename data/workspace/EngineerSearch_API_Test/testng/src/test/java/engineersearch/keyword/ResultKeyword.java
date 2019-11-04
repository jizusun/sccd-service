package engineersearch.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineersearch.pageobject.IncidentsResultPage;
import engineersearch.pageobject.OnedxResultPage;
import engineersearch.pageobject.SAPNotesResultPage;
import engineersearch.pageobject.SearchCenterPage;


@Component
public class ResultKeyword {
	@Autowired
    SearchCenterPage searchCenterPage;
    @Autowired
    SAPNotesResultPage sapNotesResultPage;
    @Autowired
    IncidentsResultPage incidentsResultPage;
    @Autowired
    OnedxResultPage onedxResultPage;

    public void triggerSearch(WebDriver driver,String searchTerm) {
		
		searchCenterPage.setDriver(driver);
		searchCenterPage.inputSearchTerm(searchTerm);
		searchCenterPage.clickSearchButton();
    }
    
    public void clickOneNote(WebDriver driver,String repoName) {
        switch(repoName) {
            case "SAP Notes" :
                sapNotesResultPage.clickSAPNotes(driver);
                break;
            case "SAP Knowledge Base Articles" :
                sapNotesResultPage.clickSAPKBANotes(driver);
                break;
            case "Customer Incidents & Info Docs" :
                incidentsResultPage.clickInfoDocsNotes(driver);
                break;
            case "Internal Incidents" :
                incidentsResultPage.clickIntlIncdtsNotes(driver);
                break;
            case "SAP Community" :
                onedxResultPage.clickCommunityNotes(driver);
                break;
            case "SAP Support Portal" :
                onedxResultPage.clickSupPortalNotes(driver);
                break;
            case "SAP Help Portal" :
                onedxResultPage.clickHelpPortalNotes(driver);
                break;
            case "Guided Answer" :
                onedxResultPage.clickGuidedAnswerNotes(driver);
                break;
            case "ByD/C4C Incidents" :
                incidentsResultPage.clickbydc4cIncdtsNotes(driver);
                break;
            default :
                break;
        }
    }
}
