package launchpadsearch.keyword;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import launchpadsearch.pageobject.LPOnedxPage;
import launchpadsearch.pageobject.LPOtherPage;
import launchpadsearch.pageobject.LPSAPNotesPage;


@Component
public class LPResultKeyword {
    @Autowired
    LPSAPNotesPage lpSAPNotesPage;
    @Autowired
    LPOnedxPage lpOnedxPage;
    @Autowired
    LPOtherPage lpOtherPage;
    
    public void clickLPNote(WebDriver driver,String repoName) {
        switch(repoName) {
            case "SAP Notes & KBAs" :
                lpSAPNotesPage.clickSAPNotes(driver);
                break;
            case "SAP Community" :
                lpOnedxPage.clickCommunityNotes(driver);
                clickConfirmDialogOKBtn(driver);
                break;
            case "SAP Community Wiki" :
                lpOnedxPage.clickCmntWikiNotes(driver);
                clickConfirmDialogOKBtn(driver);
                break;
            case "SAP Support Portal" :
                lpOnedxPage.clickSupPortalNotes(driver);
                break;
            case "SAP Help Portal" :
                lpOnedxPage.clickHelpPortalNotes(driver);
                break;           
            case "SuccessFactors Community" :
                lpOnedxPage.clickSucFactorCmntNotes(driver);
                clickConfirmDialogOKBtn(driver);
                break;
            case "Guided Answers" :
                lpOnedxPage.clickGuidedAnswerNotes(driver);
                break;
            case "SAP Sales Cloud" :
                lpOtherPage.clickCallidusNotes(driver);
                break;
            case "SAP Litmos Training Cloud" :
                lpOtherPage.clickLitmosNotes(driver);
                break;
            case "SAP Qualtrics Support Pages" :
                lpOtherPage.clickQualtricsNotes(driver);
                break;
            default :
                break;
        }
    }

    public void clickConfirmDialogOKBtn(WebDriver driver) {
        lpOnedxPage.clickDialogOKBtn(driver);
    }
}
