*** Settings ***
Suite Teardown    Close Browser
Library           SeleniumLibrary
Library           OperatingSystem
Resource          Basic_actions.txt

*** Variables ***
${NoExpertAvailable}    ${EMPTY}

*** Test Cases ***
Login
    Login in Launchpad

Navigate To Expert chat
    Navigate to expert chat

Step1
    Wait until element clickable and click    css=span[id*=AreaInput]
    Wait until element clickable and click    //div[@id="ExpertChatExpertAreaDialog--FunctionalAreas"]//li[1]
    Wait until element clickable and click    //div[@id="ExpertChatExpertAreaDialog--ExpertAreas"]//li[1]
    Should see no expert available message strip displays

Step2
    Wait until element clickable and click    (//button[contains(@class,"NextButton")])[1]
    Wait until element clickable and click    css=span[id*=SystemInput]
    Wait until element clickable and click    css=li[id*=RecentSystems-0]
    Wait until element clickable and click    (//button[contains(@class,"NextButton")])[1]

Step3
    Wait until element enabled and type text    css=input[id*=ProblemName]    UITA Test
    Input description
    Click submit button

Log out
    Log out

*** Keywords ***
Type detail in tinyMCE
    Select Frame    css=iframe[id*=ProblemDescriptionText]
    Input Text    css=body[id*=tinymce]    UITA Description
    Unselect Frame

Type detail in textarea
    Input Text    css=textarea[id*=ProblemDescriptionText]    UITA Description

Click submit button
    ${unknown_status}=    Run Keyword And Return Status    Element Should Be Visible    css=button[id*=createIncidentButton]
    Run Keyword If    '${NoExpertAvailable}'=='True'    Click create incident button
    ...    ELSE IF    '${unknown_status}'=='True'    Click create incident button
    ...    ELSE    Checkstart chat button enabled

Input description
    ${mceAvailable}=    Run Keyword And Return Status    Element Should Be Visible    css=iframe[id*=ProblemDescriptionText]
    Run Keyword If    '${mceAvailable}'=='True'    Type detail in tinyMCE
    ...    ELSE    Type detail in textarea
    Sleep    5s

Select application area
    Execute Javascript    function getElementByXpath(path) {return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; };    getElementByXpath('//div[text()="SAP BusinessObjects Business Intelligence"]/ancestor::li').focus();

Navigate to expert chat
    Run Keyword If    '${ENV_USER}'=='TEST'    Wait until element clickable and click    (//span[text()="Report an Incident"])[2]
    ...    ELSE    Wait until element clickable and click    (//span[text()="Find a Solution"])[1]
    Should display step1
    Select frame    __frame0
    Wait until element clickable and click    css=a[href="/#/expertchat/entry"]
    Unselect Frame
    Wait until element clickable and click    css=ul[role=listbox] li:first-child
    ${dataPrivacyDialog}=    Run Keyword And Return Status    Element Should Be Visible    //bdi[text()="Agree"]
    Run Keyword If    '${dataPrivacyDialog}' == 'True'    Wait until element clickable and click    //bdi[text()="Agree"]

Should see no expert available message strip displays
    Sleep    4s
    ${NoExpert}=    Run Keyword And Return Status    Element Should Be Visible    css=div[id*=noExpertMessageStrip]
    Set Suite Variable    ${NoExpertAvailable}    ${NoExpert}

Click create incident button
    Execute JavaScript    window.scrollTo(0, document.body.scrollHeight)
    Wait Until Element Is Visible    css=button[id*=createIncidentButton]    timeout=10s
    Wait Until Element Is Enabled    css=button[id*=createIncidentButton]    timeout=10s
    Sleep    5s
    Click Element    css=button[id*=createIncidentButton]
    Wait Until Page Contains Element    css=span[id*=detailObjHeader-innerTitle]    timeout=1min
    Sleep    5s
    Check incident created

Checkstart chat button enabled
    Sleep    5s
    Element Should Be Enabled    css=button[id*=startChatButton]

Check incident created
    Wait Until Element Is Visible    css=span[id*=osStatus-text]    timeout=90s
    ${currentURL}=    Get Location
    ${IncidentNo}=    Fetch From Right    ${currentURL}    /
    Set Global Variable    ${ExpertIncidentNumber}    ${IncidentNo}
