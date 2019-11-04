*** Settings ***
Suite Teardown    Close Browser
Test Teardown
Library           SeleniumLibrary
Resource          Basic_actions.txt
Library           OperatingSystem
Library           String

*** Variables ***

*** Test Cases ***
Login
    Login in Launchpad
    Comment    Login create incident

Navigate to create incident
    Run Keyword If    '${ENV_USER}'=='TEST'    Click tile    Report an Incident
    ...    ELSE    Click tile    Find a Solution
    Comment    Should display step1
    Comment    Search in top note    Hana studio
    Comment    Should display create incident button and click

Create incident
    Select customer
    Wait until recent and favorite system visible
    ${IF_GIGYA_SYSTEM}=    Select system
    Input title and description
    Select component    ${IF_GIGYA_SYSTEM}
    Add attachment    CREATE
    Click submit button during create
    Check incident created

Edit incident
    Click edit button
    Input Description
    Add attachment    EDIT
    Click submit button during edit
    Check incident created

Navigate to incident detail from notification
    Navigate to homepage
    Click tile    System Data
    Click an incident from notifiation
    Check incident displayed

View incident in inbox
    Navigate to homepage
    Click tile    Open Incidents
    Change personlization and veirfy column counts    (//div[contains(@id,"incTable-PersoDialog-cb")and contains(@id,"CbBg")])[1]    14
    Change personlization and veirfy column counts    css=div[id*=incTable-PersoDialog_SelectAll-CbBg]    15

Log out
    Log out

*** Keywords ***
Search in top note
    [Arguments]    ${searchText}
    Press key    search--searchCenterInputfield-I    ${searchText}
    Sleep    2s
    Wait Until Element Is Visible    __item0-search--searchCenterInputfield-0
    Sleep    2s
    Click Element    search--searchCenterSearchButton-img
    Wait Until Element Is Visible    css=div[id*=actionsToolbar] button:first-child    timeout=20s
    Sleep    5s

Should display create incident button and click
    Element Should Be Visible    css=div[id*=actionsToolbar] button:first-child
    Click Element    css=div[id*=actionsToolbar] button:nth-child(3)

Wait until recent and favorite system visible
    Unselect Frame
    Wait Until Element Is Visible    css=div[id*=sysRecVb]
    Wait Until Element Is Visible    css=div[id*=sysFavVb]

Select customer
    Wait Until Element Is Visible    css=input[id*=customerNumberInput-inner]    timeout=90s
    Click Element    css=span[id*=customerNumberInput-vhi]
    Click element after visible and enabled    //section[contains(@id,"dialog-cont")]//li[contains(@id,"clone")][1]
    ${S-USER}=    Run Keyword And Return Status    Element Should Be Visible    css=div[id*=suseridInput]
    Run Keyword If    '${S-USER}'=='True'    Select s-user

Select system
    Sleep    3s
    Click element in incident create page    css=button[id*=sysSearchBtn]
    Sleep    8s
    Execute Javascript    document.getElementById("__splitter0-content-1").scrollTop = 800
    Wait Until Element Is Not Visible    css=div[id*=sys_results_panel-busyIndicator]    timeout=90s
    Click Element    css=tbody[id*=sysSearchTable-tblBody] tr:first-child
    Sleep    5s
    ${if_GIGYA_System}=    Run Keyword And Return status    Element Should Be Visible    css=textarea[id*=apiKeyInput]
    Run Keyword If    ${if_GIGYA_System}    Input API Key
    [Return]    ${if_GIGYA_System}

Input title and description
    Sleep    10s
    Input Text    css=input[id*=subjectInput-inner]    This is a robot title
    Select Frame    css=iframe[id*=descriptionRichEditor-textarea_ifr]
    Comment    Select Frame    css=iframe[id*=descriptionTextEditor-textarea_ifr]
    Wait until element enabled and type text    css=body[id*=tinymce]    This is a robot description of automation test .Please leave it open and do not change it . Thanks a lot for your help . Sorry for any inconvenience.
    Unselect frame

Select component
    [Arguments]    ${GIGYA_System}=False
    ${SF_System}=    Run Keyword And Return Status    Page Should Contain Element    css=div[id*=subProSel]
    Sleep    3s
    Execute Javascript    document.getElementById("__splitter0-content-1").scrollTop = 9999
    Sleep    2s
    Run keyword If    '${SF_System}'=='True'    Select SFSF component
    ...    ELSE IF    '${GIGYA_System}'=='True'    Select GIGYA component
    ...    ELSE    Select onPrem component

Click submit button during create
    Wait until element clickable and click    (//button[contains(@id,"sendBtn")])

Select s-user
    Click Element    css=span[id*=suseridInput]
    Wait until element clickable and click    //section[contains(@id,"dialog-cont")]//li[contains(@id,"clone")][1]

Input API Key
    Wait until element enabled and type text    css=textarea[id*=apiKeyInput]    3_4RHWD-b2dPM6vkQkMAuQRmdVhiiVtfH4UjeTrwdzI8fzyOYh3_5daUM00ZxgATnzmlgRw0DusGdl2fpGfo0gds7eHEbtBxNoRA3_tidyhrFrDdwSYjQjhi-tidyhrFrDdw

Select SFSF component
    Click element after visible and enabled    css=span[id*=subProSel-vhi]
    Click element after visible and enabled    contactViewDynamic--AllCloudFilter
    Click element after visible and enabled    css=li[id*=subProductAllList-0]

Select GIGYA component
    Click element after visible and enabled    css=span[id*=compInput-vhi]
    Click element after visible and enabled    contactViewDynamic--GigyaFilter
    Wait until element enabled and type text    contactViewDynamic--componentSearchField-I    xx-ser-sapsmp-test
    Sleep    10s
    Click element after visible and enabled    css=div[id=contactViewDynamic--componentSearchList] li:first-child

Select onPrem component
    Click element after visible and enabled    css=span[id*=compInput-vhi]
    Click element after visible and enabled    contactViewDynamic--AllFilter-text
    Wait until element enabled and type text    contactViewDynamic--componentSearchField-I    xx-ser-sapsmp-usr
    Sleep    5s
    Click element after visible and enabled    css=div[id=contactViewDynamic--componentSearchList] li:first-child

Check incident created
    Wait Until Element Is Visible    css=span[id*=osStatus-text]    timeout=90s
    ${currentURL}=    Get Location
    ${IncidentNo}=    Fetch From Right    ${currentURL}    /
    Set Global Variable    ${IncidentNumber}    ${IncidentNo}

Login create incident
    Set launchpad environment
    Open Browser    https://supportshell-waeb84638.dispatcher.int.sap.hana.ondemand.com/#/incident/pointer/002075125200004258292018    chrome
    Set Window Size    1600    900
    Comment    Maximize Browser Window
    Wait Until Element Is Visible    id=j_username
    Input Text    id=j_username    @{ENVIRONMENT}[1]
    Input Text    id=j_password    @{ENVIRONMENT}[2]
    Click Button    id=logOnFormSubmit

Input Description
    Sleep    10s
    ${tinyExist}=    Run Keyword And Return Status    Element Should Be Enabled    css=iframe[id*=replyRichEditor-textarea_ifr]
    Run Keyword If    '${tinyExist}'=='True'    Input text in tinyMCE    css=iframe[id*=replyRichEditor-textarea_ifr]    Edit incident created by robotframework
    ...    ELSE    Input text in textarea    css=textarea[id*=replyInput]    Edit incident created by robotframework
    Sleep    10s

Click submit button during edit
    Comment    ${tinyExist}=    Run Keyword And Return Status    Element Should Be Enabled    css=iframe[id*=replyRichEditor-textarea_ifr]
    Comment    Run Keyword If    '${tinyExist}'=='True'    Wait until element clickable and click    (//button[contains(@id,"sendBtn")])[2]
    ...    ELSE
    Wait until element clickable and click    (//button[contains(@id,"sendBtn")])[2]

Navigate to homepage
    Wait until element clickable and click    css=div[id*=headItem-Home] span

Click tile
    [Arguments]    ${tile_name}
    Wait until element clickable and click    (//span[text()="${tile_name}"])[1]

Click an incident from notifiation
    Wait until element clickable and click    css=div[id*=headItem-Notify] span
    Wait until element clickable and click    css=div[id*=popover] ul[id*=listUl] li:nth-child(1)

Check incident displayed
    Wait Until Element Is Visible    (//span[contains(@id,"osStatus-text")])[2]    timeout=90s
    Wait Until Element Is Visible    (//button[contains(@id,"replyBtn")])[2]    timeout=90s
    Element Should Not Be Visible    css=button[id*=saeBtn ]
    Element Should Not Be Visible    css=button[id*=overruleEUDP]

Change personlization and veirfy column counts
    [Arguments]    ${checkboxLocator}    ${expectCount}
    Wait until element clickable and click    css=button[id*=list_personalize]    90s
    Wait until element clickable and click    (//div[contains(@id,"incTable-PersoDialog-cb")and contains(@id,"CbBg")])[1]
    Wait until element clickable and click    css=button[id*=incTable-PersoDialog-buttonOk]
    ${colCount}=    Get Element Count    //tr[contains(@id,"incTable-tblHeader")]//th
    Should Be Equal As Integers    ${expectCount}    ${colCount}
