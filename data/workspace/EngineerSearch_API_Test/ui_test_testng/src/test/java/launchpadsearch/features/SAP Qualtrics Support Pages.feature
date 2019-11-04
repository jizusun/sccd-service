Feature: SAP Qualtrics Support Pages

@LPS
Scenario:SAP Qualtrics Support Pages
Given lp search term "crm" and click search button
When lp select "SAP Qualtrics Support Pages" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP Qualtrics Support Pages" result list
Then it will skip to lp "SAP Qualtrics Support Pages" detail page