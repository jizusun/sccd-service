Feature: Internal Incidents

@ES
Scenario:Internal Incidents
Given search term "hana" and click search button
When select "Internal Incidents" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "Internal Incidents" result list
Then it will skip to "Internal Incidents" detail page