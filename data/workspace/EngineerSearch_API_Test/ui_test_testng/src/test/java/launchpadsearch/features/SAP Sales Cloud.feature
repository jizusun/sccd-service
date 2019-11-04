Feature: SAP Sales Cloud

@LPS
Scenario:SAP Sales Cloud
Given lp search term "crm" and click search button
When lp select "SAP Sales Cloud" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Sales Cloud" result list
Then it will skip to lp "SAP Sales Cloud" detail page