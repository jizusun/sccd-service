Feature: SAP Help Portal

@ES
Scenario:SAP Help Portal
Given search term "hana" and click search button
When select "SAP Help Portal" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "SAP Help Portal" result list
Then it will skip to "SAP Help Portal" detail page