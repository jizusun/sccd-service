Feature: ByD/C4C Incidents

@ES
Scenario:ByD/C4C Incidents
Given search term "hana" and click search button
When select "ByD/C4C Incidents" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "ByD/C4C Incidents" result list
Then it will skip to "ByD/C4C Incidents" detail page