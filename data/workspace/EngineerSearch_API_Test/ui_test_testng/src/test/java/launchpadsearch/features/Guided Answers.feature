Feature: Guided Answers

@LPS
Scenario:Guided Answers
Given lp search term "crm" and click search button
When lp select "Guided Answers" reporitory
#Then the "SAP Notes" result list should display
When click one notes in lp "Guided Answers" result list
Then it will skip to lp "Guided Answers" detail page