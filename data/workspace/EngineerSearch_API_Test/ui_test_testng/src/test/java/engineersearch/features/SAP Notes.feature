Feature: SAP Notes

@ES
Scenario:SAP Notes
Given search term "hana" and click search button
When select "SAP Notes" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "SAP Notes" result list
Then it will skip to "SAP Notes" detail page