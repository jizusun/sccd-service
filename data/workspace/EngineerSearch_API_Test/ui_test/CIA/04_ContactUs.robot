*** Settings ***
Resource          Basic_actions.txt

*** Test Cases ***
Login
    Login in Launchpad

Navigate to Contact us
    Navigate to Contact Us chat

Fill out contact us form
    Input Subject and Description
    Select category
    Select salutation
    Select communication method
    Verify submit button enabled

Log out
    Log out

*** Keywords ***
Input Subject and Description
    Sleep    3s
    Wait until element enabled and type text    css=input[id*=subjectInput-inner]    This is a robot test.Subject . \ Please ignore
    Wait until element enabled and type text    css=textarea[id*=discriptionInput-inner]    This is a robot test . \ Description . Please ignore

Select category
    Wait until element clickable and click    css=div[id*=categoryLevel1Select]
    Wait until element clickable and click    css=li[id*=categoryLevel1Select-1]

Navigate to Contact Us chat
    Sleep    5s
    Click element after visible and enabled    css=button[id*="ContactUs"]
    Sleep    5s
    ${chatAvailable}    Run Keyword And Return Status    Element Should Be Enabled    css=button[id*=contactAction-Chat]
    Set Global Variable    ${chatButton}    ${chatAvailable}
    Run Keyword If    '${chatAvailable}'=='True'    Wait until element clickable and click    css=button[id*=contactAction-Chat]
    ...    ELSE    Wait until element clickable and click    css=button[id*=contactAction-Email]
    Sleep    5s
    Wait Until Element Is Visible    css=span[id*=contactUsMainPage-title-inner]
    Element Text Should Be    css=span[id*=contactUsMainPage-title-inner]    Contact Us

Verify submit button enabled
    Run Keyword If    '${chatButton}'=='True'    Element Should Be Enabled    css=button[id*=chatButton]
    ...    ELSE    Verify send email button disabled after click

Select communication method
    Wait until element clickable and click    css=div[id*=communicationMethodSelect]
    Wait until element clickable and click    css=li[id*=communicationMethodSelect-1]

Should display step1
    Select Frame    __frame0
    Wait Until Element Is Visible    step1
    Unselect Frame

Verify send email button disabled after click
    Sleep    2s
    Click element after visible and enabled    css=button[id*=submitButton]
    Sleep    10s
    Element Should Be Disabled    css=button[id*=submitButton]

Select salutation
    Click element after visible and enabled    css=span[id*=salutationSelect-arrow]
    Click element after visible and enabled    css=li[id*=salutationSelect-1]
