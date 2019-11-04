*** Settings ***
Library           SeleniumLibrary
Resource          Utils.txt

*** Test Cases ***
Login
    Login BC

SearchIncident
    Comment    Input incident number
    Unselect Frame
    Select Frame    css=iframe[id*=CRMApplicationFrame]
    Select Frame    css=frame[id*=WorkAreaFrame1]
    Input Text    css=input[id*=inputID]    002075125200003242562018
    Comment    Click display button to search incident
    Click Element    css=a[id*=Searchbtn] b

Saved Searches
    Comment    Select saved search "My Components without Processor"
    Unselect Frame
    Select Frame    //iframe[@name='CRMApplicationFrame']
    Comment    Select Frame    css=frame[id=WorkAreaFrame1]
    Click Element    css=a[title*="My Components without Processor"]

Close window
    Close Window
