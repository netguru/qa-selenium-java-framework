Feature: Login
  Scenario Outline: Successful login as <user>
    Given The user is on login page
    When User logs in as "<user>"
    Then User is logged in
    Examples:
      |user|
      |PROVIDER|

