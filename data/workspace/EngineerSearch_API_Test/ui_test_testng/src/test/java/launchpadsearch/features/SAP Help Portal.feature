Feature: SAP Help Portal

@LPS
Scenario:SAP Help Portal
Given lp search term "crm" and click search button
When lp select "SAP Help Portal" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SAP help Portal" result list
Then it will skip to lp "SAP Help Portal" detail page