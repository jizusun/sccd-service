Feature: SAP Community

@ES
Scenario:SAP Community
Given search term "hana" and click search button
When select "SAP Community" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "SAP Community" result list
Then it will skip to "SAP Community" detail page