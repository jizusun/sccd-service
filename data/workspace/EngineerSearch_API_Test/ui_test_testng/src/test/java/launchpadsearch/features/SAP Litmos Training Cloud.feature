Feature: SAP Litmos Training Cloud

@LPS
Scenario:SAP Litmos Training Cloud
Given lp search term "crm" and click search button
When lp select "SAP Litmos Training Cloud" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Litmos Training Cloud" result list
Then it will skip to lp "SAP Litmos Training Cloud" detail page