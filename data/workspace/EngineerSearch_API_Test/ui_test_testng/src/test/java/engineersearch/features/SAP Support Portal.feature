Feature: SAP Support Portal

@ES
Scenario:SAP Support Portal
Given search term "hana" and click search button
When select "SAP Support Portal" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "SAP Support Portal" result list
Then it will skip to "SAP Support Portal" detail page