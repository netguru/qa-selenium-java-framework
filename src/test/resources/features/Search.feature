@Regression @Search
Feature: Search

  Scenario: Student finds a tutor
    Given User is logged in as "CONSUMER_PAID"
    When User searches a profile in "Zurich"
    And Opens first profile
    Then Tutor profile is displayed

  Scenario: Student searches for tutors in distance of 50 km
    Given User is logged in as "CONSUMER_PAID"
    When User sets the distance of 50 km
    And User searches a profile in "Zurich"
    Then Search results match distance of 50 km

  Scenario: Tutor finds a job
    Given User is logged in as "PROVIDER"
    When User searches a profile in "Zurich"
    And Opens first profile
    Then Student profile is displayed