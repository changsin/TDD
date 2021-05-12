Feature: sample BDD feature
  Scenario: open a url and download the content
    Given Load a text file "test.txt"
    When Open the url
    Then I can download the content