Feature: Case integration test


Scenario: Case integration lp to snow
Given login to "LP"
When create case
Then should jump to snow portal

@snow
Scenario: Case show in snow
Given login to "SNOW"
When search case and select case
Then should jump to case detail
