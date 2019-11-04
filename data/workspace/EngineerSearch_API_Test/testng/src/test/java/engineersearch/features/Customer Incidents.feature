Feature: Customer Incidents & Info Docs

@ES
Scenario:Customer Incidents & Info Docs
Given search term "hana" and click search button
When select "Customer Incidents & Info Docs" reporitory
#Then the "SAP Notes" result list should display
When click one notes in "Customer Incidents & Info Docs" result list
Then it will skip to "Customer Incidents & Info Docs" detail page