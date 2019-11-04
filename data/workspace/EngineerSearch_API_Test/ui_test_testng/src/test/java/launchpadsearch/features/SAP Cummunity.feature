Feature: SAP Community

@LPS
Scenario:SAP Community
Given lp search term "crm" and click search button
When lp select "SAP Community" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Community" result list
Then it will skip to lp "SAP Community" detail page