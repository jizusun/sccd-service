Feature: Guided Answer

@ES
Scenario:Guided Answer
Given search term "hana" and click search button
When select "Guided Answer" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "Guided Answer" result list
Then it will skip to "Guided Answer" detail page