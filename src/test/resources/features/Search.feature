@Regression
Feature: Search

  Background:
    Given The User is on login page
    Then They should see the Log in button

  Scenario: Student finds a tutor
    When User logs in as: "CONSUMER_PAID"
    And User searches a profile in "Zurich"
    And Opens first profile
    Then Tutor profile is displayed

  Scenario: Student searches for tutors in distance of 50 km
    When User logs in as: "CONSUMER_PAID"
    And User sets the distance of 50 km
    And User searches a profile in "Zurich"
    Then Search results match distance of 50 km

  Scenario: Tutor finds a job
    When User logs in as: "PROVIDER"
    And User searches a profile in "Zurich"
    And Opens first profile
    Then Student profile is displayed