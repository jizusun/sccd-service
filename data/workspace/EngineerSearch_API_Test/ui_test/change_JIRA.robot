*** Settings ***
Library           SeleniumLibrary

*** Variables ***
@{JIRA}           KNGMHM02-7530    KNGMHM02-7531    KNGMHM02-9279    KNGMHM02-7415    KNGMHM02-9957    KNGMHM02-7414    KNGMHM02-5901
...               KNGMHM02-5900    KNGMHM02-12380    KNGMHM02-10870    KNGMHM02-13588    KNGMHM02-14799    KNGMHM02-10992    KNGMHM02-11960
...               KNGMHM02-6570    KNGMHM02-6576    KNGMHM02-6697    KNGMHM02-12822    KNGMHM02-6926    KNGMHM02-5959    KNGMHM02-6927
...               KNGMHM02-9080    KNGMHM02-12564    KNGMHM02-13773    KNGMHM02-13774    KNGMHM02-13772    KNGMHM02-6141    KNGMHM02-13099
...               KNGMHM02-10823    KNGMHM02-10944    KNGMHM02-6701    KNGMHM02-8689    KNGMHM02-12448    KNGMHM02-10816    KNGMHM02-6937
...               KNGMHM02-13090    KNGMHM02-13520    KNGMHM02-6395    KNGMHM02-7242    KNGMHM02-6151    KNGMHM02-5982    KNGMHM02-6158
...               KNGMHM02-8214    KNGMHM02-9424    KNGMHM02-6155    KNGMHM02-8455    KNGMHM02-7124    KNGMHM02-8454    KNGMHM02-11347
...               KNGMHM02-6704    KNGMHM02-6825    KNGMHM02-6824    KNGMHM02-6829    KNGMHM02-6706    KNGMHM02-6827    KNGMHM02-6828
...               KNGMHM02-12300    KNGMHM02-13995    KNGMHM02-5190    KNGMHM02-13754    KNGMHM02-10002    KNGMHM02-7495    KNGMHM02-7133
...               KNGMHM02-6162    KNGMHM02-7130    KNGMHM02-8462    KNGMHM02-6163    KNGMHM02-6284    KNGMHM02-8461    KNGMHM02-7136
...               KNGMHM02-9557    KNGMHM02-5993    KNGMHM02-9556    KNGMHM02-3693    KNGMHM02-7019    KNGMHM02-11698    KNGMHM02-7139
...               KNGMHM02-6834    KNGMHM02-7923    KNGMHM02-6719    KNGMHM02-12895    KNGMHM02-10473    KNGMHM02-12894    KNGMHM02-7141
...               KNGMHM02-7263    KNGMHM02-6058    KNGMHM02-11329    KNGMHM02-12418    KNGMHM02-6730    KNGMHM02-11328    KNGMHM02-6057
...               KNGMHM02-7146    KNGMHM02-7388    KNGMHM02-9323    KNGMHM02-8119    KNGMHM02-11324    KNGMHM02-8118    KNGMHM02-11327
...               KNGMHM02-8117    KNGMHM02-12536    KNGMHM02-12657    KNGMHM02-5998    KNGMHM02-5999    KNGMHM02-6728    KNGMHM02-11552
...               KNGMHM02-6180    KNGMHM02-11311    KNGMHM02-12760    KNGMHM02-10460    KNGMHM02-6740    KNGMHM02-7037    KNGMHM02-9336
...               KNGMHM02-6189    KNGMHM02-6502    KNGMHM02-7712    KNGMHM02-6866    KNGMHM02-10466    KNGMHM02-12889    KNGMHM02-6743
...               KNGMHM02-7711    KNGMHM02-9217    KNGMHM02-12646    KNGMHM02-7946    KNGMHM02-7707    KNGMHM02-7708    KNGMHM02-7166
...               KNGMHM02-7042    KNGMHM02-6196    KNGMHM02-11780    KNGMHM02-6872    KNGMHM02-7841    KNGMHM02-6873    KNGMHM02-8137
...               KNGMHM02-8136    KNGMHM02-10219    KNGMHM02-12759    KNGMHM02-7966    KNGMHM02-8934    KNGMHM02-12756    KNGMHM02-10334
...               KNGMHM02-7964    KNGMHM02-6190    KNGMHM02-12070    KNGMHM02-11530    KNGMHM02-8142    KNGMHM02-9110    KNGMHM02-9115
...               KNGMHM02-7970    KNGMHM02-8267    KNGMHM02-9114    KNGMHM02-6887    KNGMHM02-8701    KNGMHM02-12864    KNGMHM02-13711
...               KNGMHM02-9909    KNGMHM02-9908    KNGMHM02-8939    KNGMHM02-12181    KNGMHM02-11882    KNGMHM02-12730    KNGMHM02-8151
...               KNGMHM02-11881    KNGMHM02-10432    KNGMHM02-8156    KNGMHM02-9365    KNGMHM02-7065    KNGMHM02-9484    KNGMHM02-13022
...               KNGMHM02-6894    KNGMHM02-6773    KNGMHM02-9248    KNGMHM02-10437    KNGMHM02-12978    KNGMHM02-6772    KNGMHM02-6777
...               KNGMHM02-7745    KNGMHM02-10434    KNGMHM02-8714    KNGMHM02-6657    KNGMHM02-8713    KNGMHM02-10433    KNGMHM02-6775
...               KNGMHM02-10436    KNGMHM02-10435    KNGMHM02-6526    KNGMHM02-13137    KNGMHM02-11075    KNGMHM02-8044    KNGMHM02-9375
...               KNGMHM02-10669    KNGMHM02-8047    KNGMHM02-12726    KNGMHM02-12847    KNGMHM02-7514    KNGMHM02-12600    KNGMHM02-12724
...               KNGMHM02-174    KNGMHM02-8715    KNGMHM02-10771    KNGMHM02-12156    KNGMHM02-12159    KNGMHM02-9266    KNGMHM02-8296
...               KNGMHM02-8611    KNGMHM02-8974    KNGMHM02-10778    KNGMHM02-10418    KNGMHM02-6310    KNGMHM02-8179    KNGMHM02-8856
...               KNGMHM02-11984    KNGMHM02-8854    KNGMHM02-6428    KNGMHM02-7517    KNGMHM02-13480    KNGMHM02-13236    KNGMHM02-12024
...               KNGMHM02-9399    KNGMHM02-9398    KNGMHM02-8066    KNGMHM02-11055    KNGMHM02-12386    KNGMHM02-9396    KNGMHM02-9953

*** Test Cases ***
for loop
    open JIRA    KNGMHM02-9181
    : FOR    ${item}    IN    @{JIRA}
    \    ${url}=    Catenate    SEPARATOR=    https://sapjira.wdf.sap.corp/browse/    ${item}
    \    go to    ${url}
    \    ${hasEpic}=    Run Keyword And Return Status    Element Should Be Visible    css=[title="Epic Link"]
    \    Run Keyword If    '${hasEpic}'=='True'    Continue For Loop
    \    ${openIssue}=    Run Keyword And Return Status    Element Should Be Visible    //span[contains(text(),"Set to reopened")]
    \    ${reopenedIssue}=    Run Keyword And Return Status    Element Should Be Visible    //span[text()="Set to reopen"]
    \    Run Keyword If    '${openIssue}'=='True'    Wait until element available for click    //span[text()="Set to reopened"]
    \    Run Keyword If    '${reopenedIssue}'=='True'    Wait until element available for click    //span[contains(text(),"Set to reopen")]
    \    Sleep    5s
    \    Element Should Be Visible    //span[contains(text(),"Set to completed")]
    \    Wait until element available for click    //span[contains(text(),"Edit")]
    \    Wait untill element available for type    (//label[contains(text(),"Epic Link")]/parent::div//input)[3]    Incident Form
    \    sleep    5s
    \    Wait until element available for click    css=div[class=buttons] input[type=submit]
    \    Run Keyword If    '${openIssue}'=='True'    Wait until element available for click    //span[contains(text(),"Set to completed")]
    \    Sleep    5s
    \    Element Should Be Visible    css=[title="Epic Link"]
    Close Browser

*** Keywords ***
Wait until element available for click
    [Arguments]    ${locator}
    Wait Until Element Is Visible    ${locator}    timeout=30s
    Wait Until Element Is Enabled    ${locator}    timeout=30s
    sleep    2s
    Click Element    ${locator}
    sleep    3s

Wait untill element available for type
    [Arguments]    ${locator}    ${text}
    Wait Until Element Is Visible    ${locator}    timeout=30s
    Wait Until Element Is Enabled    ${locator}    timeout=30s
    sleep    5s
    Input Text    ${locator}    ${text}
    sleep    5s

open JIRA
    [Arguments]    ${JIRAID}
    ${url}=    Catenate    SEPARATOR=    https://sapjira.wdf.sap.corp/browse/    ${JIRAID}
    Log To Console    ${url}
    Open Browser    ${url}    chrome
    Input Text    id=j_username    I319219
    Input Text    id=j_password    Tianfud645
    Click Button    id=logOnFormSubmit
