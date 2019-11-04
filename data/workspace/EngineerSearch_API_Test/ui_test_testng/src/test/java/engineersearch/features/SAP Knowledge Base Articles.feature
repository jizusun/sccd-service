Feature: SAP Knowledge Base Articles

@ES
Scenario:SAP Knowledge Base Articles
Given search term "hana" and click search button
When select "SAP Knowledge Base Articles" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "SAP Knowledge Base Articles" result list
Then it will skip to "SAP Knowledge Base Articles" detail page