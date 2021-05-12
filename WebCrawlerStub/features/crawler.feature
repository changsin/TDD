Feature: BDD sample
  Scenario: open a url and download
    Given I open "naver.com"
    When I see x element
    Then I see top text x
