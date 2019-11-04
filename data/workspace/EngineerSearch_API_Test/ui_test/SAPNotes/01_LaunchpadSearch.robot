*** Settings ***
Resource          Basic_actions.txt

*** Test Cases ***
Login
    Login in Launchpad
    Comment    Wait Until Element Is Visible    search--searchCenterInputfield-I    timeout=300s
    Comment    Input Text    search--searchCenterInputfield-I    hana

SAP Notes & KBAs
    Input search term and trigger search    hana    #trigger \ search
    Switch Repository    id=tabnotesv2    #Select SAP Notes & KBAs repository
    Function display    id=__xmlview2--search--Facetselectlist    #Check Repository display
    Function display    id=__xmlview2--search--facetv2--idfacetComponentPanel    #Check Filters display
    Switch view    xpath=//ul[@id='__xmlview2--search--selectViewType']/li[1]    #Switch to List View
    Search term correctly display    xpath=//*[@id='notesv2--search--idIconTabFilterNotesList2-listUl']/li[1]/div[2]/div/div[2]    HANA    #Check the result successfully display
    Click one note in result list    xpath=//*/li[1]/div[2]/div/div[1]/div/a
    Function display    id=__jsview0--shell-title    #Check note successfully display
    Wait until element clickable and click    id=__jsview0--headItem-Back    30s    #Back to launchpad search
    Switch view    xpath=//ul[@id='__xmlview2--search--selectViewType']/li[2]    #Switch to Table View
    Function display    id=notesv2--search--idNotesResultTable    #Check the result not domp
    Input search term and trigger search    hanaaaaaa    #trigger \ search with \ result count is 0
    Function display    id=notesv2--search--noDataViewofNotesv2--noDataFoundofSearch    #Check the result not domp
    Switch view    xpath=//ul[@id='__xmlview2--search--selectViewType']/li[1]    #Switch to List View
    Function display    id=notesv2--search--noDataViewofNotesv2--noDataFoundofSearch    #Check the result not domp

SAP Community
    Input search term and trigger search    hana    #trigger \ search
    Switch Repository    id=tabscnforums    #Select SAP Community repository
    Comment    Function display    id=__xmlview4--search--facetScnforums--search--facetProductScnforums-listUl    #Check Filters display
    Search term correctly display    xpath=//*/li[1]/div[2]/div[2]    HANA    #Check the result successfully display
    Click one note in result list    xpath=//*/li[1]/div[2]/div[1]/a
    Wait until element clickable and click    xpath=//*[@id='confirmationDialog-footer']/button[1]    #Click pop up 'OK' button
    Select Window    NEW    # Select latest opened window
    Comment    Function display    id=comm-shdr-breadcrumb-community
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

SAP Community Wiki
    Select Window    MAIN    # Back to Launchpad Search page
    Input search term and trigger search    hana    #trigger \ search
    Switch Repository    id=tabscnwiki    #Select SAP Community repository
    Search term correctly display    xpath=//*/li[1]/div[2]/div[2]    HANA    #Check the result successfully display
    Click one note in result list    xpath=//*/li[1]/div[2]/div[1]/a
    Wait until element clickable and click    xpath=//*[@id='confirmationDialog-footer']/button[1]    #Click pop up 'OK' button
    Select Window    NEW    # Select latest opened window
    Function display    class=community-logo
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

SAP Support Portal
    Select Window    MAIN    # Back to Launchpad Search page
    Input search term and trigger search    hana    #trigger \ search
    Switch Repository    id=tabcq5portal    #Select SAP Community repository
    Comment    Function display    id=__xmlview4--search--facetcq5portal--search--facetPanelLanguage    #Check Filters display
    Search term correctly display    xpath=//*/li[1]/div[2]/div[2]    HANA    #Check the result successfully display
    Click one note in result list    xpath=//*/li[1]/div[2]/div[1]/a
    Comment    Wait until element clickable and click    xpath=//*[@id='confirmationDialog-footer']/button[1]    #Click pop up 'OK' button
    Select Window    NEW    # Select latest opened window
    Comment    Function display    id=toolbar
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

SAP Help Portal
    Select Window    MAIN    # Back to Launchpad Search page
    Input search term and trigger search    hana    #trigger search
    Switch Repository    id=tabsaphelp    #Select SAP Help Portal repository
    Search term correctly display    xpath=//*[@id='saphelp--search--saphelpList-listUl']/li[1]/div[2]/div[2]    HANA    #Check the result soccessfully display
    Click one note in result list    xpath=//*[@id='saphelp--search--saphelpList-listUl']/li[1]/div[2]/div[1]/a    #Click first note
    Select Window    NEW    # Select latest opened window
    Function display    id=cp-hero    #Check note successfully display
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

SuccessFactors Community
    Select Window    MAIN    # Back to Launchpad Search page
    Input search term and trigger search    hana    #trigger \ search
    Switch Repository    id=tablithiumforums    #Select SuccessFactors Community repository
    Comment    Search term correctly display    xpath=//*[@id='lithiumforums--search--idIconTabFilterSFCNList-listUl']/li[0]/div[2]/div[1]/a    Hana    #Check the result soccessfully display
    Comment    Click Element    id=__xmlview2--dynamicPageId-content    #Click a blank place
    Click one note in result list    xpath=//*[@id='lithiumforums--search--idIconTabFilterSFCNList-listUl']/li[1]/div[2]/div[1]/a    #Click first note
    Wait until element clickable and click    xpath=//*[@id='confirmationDialog-footer']/button[1]    #Click pop up 'OK' button
    Select Window    NEW    # Select latest opened window
    Function display    id=logo    #Check note successfully display
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

Guided Answers
    Select Window    MAIN    # Back to Launchpad Search page
    Input search term and trigger search    hana    #trigger search
    Switch Repository    id=tabguidedanswers    #Select GuidedAnswers repository
    Comment    Search term correctly display    xpath=//*[@id=‘guidedanswers--list_guidedanswers-listUl']/li[1]/div[2]/div[2]    HANA    #Check the result soccessfully display
    Click one note in result list    xpath=//*/li[1]/div[2]/div[1]/a    #Click first note
    Select Window    NEW    # Select latest opened window
    Function display    id=__xmlview1--idTaskSteps-scroll    #Check note successfully display
    Close Window    # Close current window(close notes detail page)
    Comment    Select Window    MAIN    # Back to Launchpad Search page

*** Keywords ***
Input search term and trigger search
    [Arguments]    ${search_term}
    Wait Until Element Is Visible    id=search--searchCenterInputfield-I    timeout=180s
    Input Text    id=search--searchCenterInputfield-I    ${search_term}
    Click Element    id=search--searchCenterSearchButton

Function display
    [Arguments]    ${locator}
    Wait Until Element Is Visible    ${locator}    timeout=90s
    Element Should Be Visible    ${locator}

Search term correctly display
    [Arguments]    ${locator}    ${text}
    Element Should Contain    ${locator}    ${text}

Click one note in result list
    [Arguments]    ${locator}
    Wait Until Element Is Visible    ${locator}    timeout=30s
    Click Link    ${locator}
    Sleep    30s

Switch view
    [Arguments]    ${locator}
    Wait Until Element Is Visible    ${locator}    timeout=90s
    Click Element    ${locator}
    Sleep    10s

Switch Repository
    [Arguments]    ${locator}
    Wait until element clickable and click    ${locator}
    Sleep    10s
