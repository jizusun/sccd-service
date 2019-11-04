*** Settings ***
Library           SeleniumLibrary
Resource          Basic_actions.txt

*** Test Cases ***
Login
    Login in BC

Check replication
    Search Incident in Home page

Log out
    Log out

*** Keywords ***
Search Incident in Home page
    Unselect Frame
    Sleep    3s
    Select Frame    css=iframe[id=CRMApplicationFrame]
    Sleep    2s
    Select Frame    id=WorkAreaFrame1
    Sleep    2s
    Wait until element enabled and type text    css=input[tempname*=searchnode_incidentnumber]    ${ExpertIncidentNumber}
    ${searchResult}=    Run Keyword And Return Status    Wait until element clickable and click    css=a[id*=Searchbtn]
    : FOR    ${i}    IN RANGE    10
    \    Exit For Loop If    '${searchResult}'=='False'
    \    ${searchResult}=    Switch frames when search

Switch frames when search
    Unselect Frame
    Sleep    3s
    Select Frame    css=iframe[id=CRMApplicationFrame]
    Sleep    2s
    Select Frame    id=WorkAreaFrame1
    Sleep    2s
    ${WorkAreaframe1}=    Run Keyword And Return Status    Element Should Be Visible    css=a[id*=Searchbtn]
    ${Can_click_search}=    Run Keyword If    '${WorkAreaframe1}'=='True'    Click search button
    ...    ELSE    Run Keyword And Return    Search incident in frame2
    [Return]    ${Can_click_search}

Search incident in frame2
    Unselect Frame
    Sleep    3s
    Select Frame    id=CRMApplicationFrame
    Sleep    2s
    Select Frame    id=WorkAreaFrame2
    Sleep    2s
    ${Click_search}=    Run Keyword And Return Status    Wait until element clickable and click    css=a[id*=Searchbtn]    time_out=20s
    [Return]    ${Click_search}

Click search button
    ${Click_search}=    Run Keyword And Return Status    Wait until element clickable and click    css=a[id*=Searchbtn]    time_out=20s
    [Return]    ${Click_search}
