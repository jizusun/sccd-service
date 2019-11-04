Feature: SAP Notes & KBA

@LPS
Scenario:SAP Notes & KBA
Given lp search term "crm" and click search button
When lp select "SAP Notes & KBA" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Notes & KBA" result list
Then it will skip to lp "SAP Notes & KBA" detail page