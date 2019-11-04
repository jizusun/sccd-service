Feature: SuccessFactors Community

@LPS
Scenario:SuccessFactors Community
Given lp search term "crm" and click search button
When lp select "SuccessFactors Community" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "SuccessFactors Community" result list
Then it will skip to lp "SuccessFactors Community" detail page