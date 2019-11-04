*** Settings ***
Suite Teardown    Log out
Test Teardown
Resource          Basic_actions.txt
Library           SeleniumLibrary

*** Test Cases ***
Login
    Login in Launchpad

Navigate to SAE
    Run Keyword If    '${ENV_USER}'=='TEST'    Wait until element clickable and click    (//span[text()="Report an Incident"])[1]
    ...    ELSE    Wait until element clickable and click    (//span[text()="Find a Solution"])[1]
    Should display step1
    Click Schedule an Expert
    Wait until element clickable and click    css=div[id*=bookNow-content]
    [Teardown]

Describe your question
    Describe the question

Categorize the system
    Categorize

Select expert area
    Select Expert Area

Select time
    Select Date
    Select time

Input description during create
    Input title
    Input description    CREATE

Review and submit
    Click element in SAE creation page    css=button[id*=reviewBtn]
    Click element in SAE creation page    //bdi[text()="Submit"]
    Click element in SAE creation page    (//div[contains(@id,"confirm")]//button)[1]
    Wait until sae booking successful

Edit SaE booking
    Navigate to SaE edit page
    Input description    EDIT
    Comment    Wait Until Element Is Visible    css=iframe[id*=ReplyText-textarea_ifr]
    Comment    Select Frame    css=iframe[id*=ReplyText-textarea_ifr]
    Comment    Wait until element enabled and type text    css=body[id*=tinymce]    Edit SaE booking through robot framework UI test
    Comment    Unselect Frame
    Add attachment    SAE
    Wait until element clickable and click    css=button[id*=saveBtn]
    Wait until element clickable and click    (//div[contains(@id,"detailPage")]//button[contains(@id,"cancelBtn")])
    Wait until element clickable and click    (//button[contains(@id,"mbox-btn")])[1]

Log out
    Log out

*** Keywords ***
Click Schedule an Expert
    Select frame    __frame0
    Click element after visible and enabled    css=a[href="/#/sae"]
    Unselect Frame

Describe the question
    Wait until element enabled and type text    css=input[id*=yourQuestionInput]    This is robotframework test.Please ignore
    Click element after visible and enabled    (//button[contains(@class,"NextButton")])[1]

Categorize
    Click element after visible and enabled    css=span[id*=SystemInput-vhi]
    Sleep    5s
    Click element after visible and enabled    SaESystemDialog--SearchFilter-text
    Sleep    5s
    Click element after visible and enabled    (//div[@id="SaESystemDialog--SystemVHIconTabBar-content"]//button)[1]
    Sleep    5s
    Click element after visible and enabled    (//div[contains(@id,"SaESystemDialog")]//button)[1]
    Click element after visible and enabled    (//div[contains(@id,"SearchSystems")]//li[contains(@class,"sapMLIBActionable")])[1]
    Click element after visible and enabled    (//button[contains(@class,"NextButton")])[1]

Select Expert Area
    Click element after visible and enabled    css=span[id*=AreaInput-vhi]
    Click element after visible and enabled    //div[text()="Mobile Technology"]/ancestor::li
    Click element after visible and enabled    //div[text()="SAP Afaria"]/ancestor::li
    Click element after visible and enabled    (//button[contains(@class,"NextButton")])[1]

Select Date
    Click element after visible and enabled    (//div[contains(@id,"CalendarGrid")]//li[@aria-selected="false"])[1]

Click element in SAE creation page
    [Arguments]    ${locator}    ${time_out}=30s
    Sleep    3s
    Execute Javascript    document.querySelector('[id*="bookcreation--bookCreationWizard-step-container"]').scrollTop = 9999
    Wait Until Element Is Visible    ${locator}    ${time_out}
    Wait Until Element Is Enabled    ${locator}    ${time_out}
    Click Element    ${locator}

Select time
    Sleep    3s
    Execute Javascript    document.querySelector('[id*="bookcreation--bookCreationWizard-step-container"]').scrollTop = 1000
    Click Element    xpath=(//table[contains(@id,"timeSlotTable")]//tr)[2]//td[2]
    Click element in SAE creation page    (//button[contains(@class,"NextButton")])[1]

Input title
    Sleep    3s
    Execute Javascript    document.querySelector('[id*="bookcreation--bookCreationWizard-step-container"]').scrollTop = 9999
    Input Text    css=input[id*=ProblemName-inner]    This is robot framework SAE test

Input description
    [Arguments]    ${inPage}    # If in edit page , then 'EDIT' if in SaE create page , then 'CREATE'
    Run Keyword If    '${inPage}'=='CREATE'    Select Frame    css=iframe[id*=ProblemDescriptionText-textarea_ifr]
    ...    ELSE    Select Frame    css=iframe[id*=ReplyText-textarea_ifr]
    Run Keyword If    '${inPage}'=='CREATE'    Wait until element enabled and type text    tinymce    This is a robot description of automation test .Please leave it open and do not change it . Thanks a lot for your help . Sorry for any inconvenience.
    ...    ELSE    Wait until element enabled and type text    tinymce    Edit SaE incident with robotframework
    Press Key    tinymce    \\13
    Press Key    tinymce    \\13
    Unselect frame
    Sleep    5s

Wait until sae booking successful
    Wait Until Element Is Visible    css=div[id*=bookconfirmation] button[class*=sapUISmallMarginEnd]    timeout=30s

Navigate to SaE edit page
    Click element in SAE creation page    css=div[id*=bookconfirmation] button[class*=sapUISmallMarginEnd]
    Sleep    5s
    Wait Until Element Is Visible    css=button[id*=editBtn]
    Wait Until Element Is Enabled    css=button[id*=editBtn]
    Click Button    css=button[id*=editBtn]
