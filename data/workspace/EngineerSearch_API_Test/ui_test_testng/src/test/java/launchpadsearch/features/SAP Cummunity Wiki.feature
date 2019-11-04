Feature: SAP Community Wiki

@LPS
Scenario:SAP Community Wiki
Given lp search term "crm" and click search button
When lp select "SAP Community Wiki" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Community Wiki" result list
Then it will skip to lp "SAP Community Wiki" detail page