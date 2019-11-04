Feature: SAP Support Portal

@LPS
Scenario:SAP Support Portal
Given lp search term "crm" and click search button
When lp select "SAP Support Portal" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Support Portal" result list
Then it will skip to lp "SAP Support Portal" detail page