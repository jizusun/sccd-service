package engineersearch.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import engineersearch.pageobject.SAPNotesResultPage;
import engineersearch.pageobject.SearchCenterPage;


@Component
public class NotesDetailKeyword {
	@Autowired
    SearchCenterPage searchCenterPage;
    @Autowired
    SAPNotesResultPage sapNotesResultPage;

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
                // sapKBA.click();
                break;
            case "Customer Incidents & Info Docs" :
                // customerIncidents.click();
                break;
            case "Internal Incidents" :
                // internalIncidents.click();
                break;
            case "SAP Community" :
                // sapCommunity.click();
                break;
            case "SAP Support Portal" :
                // sapSupportPortal.click();
                break;
            case "SAP Help Portal" :
                // sapHelpPortal.click();
                break;
            case "Guided Answer" :
                // guidedAnswer.click();
                break;
            case "ByD/C4C Incidents" :
                // bydc4cIncidents.click();
                break;
            default :
                // sapNotes.click();
                break;
        }
    }
}
