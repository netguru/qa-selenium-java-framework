@Header
Feature: Header

#  Scenario: Add Restaurant button in header redirects to login page, if user is not logged in
#    When User selects Add Restaurant button in header
#    Then User is redirected to "login" page
#
#  Scenario Outline: User selects Add Restaurant button in header and logs in as <user>
#    When User selects Add Restaurant button in header
#    And User logs in as <user>
#    Then User is redirected to "add restaurant - step 1" page
#    Examples:
#      | user  |
#      | BO    |
#      | FD    |
#      | BASIC |

  Scenario Outline: Add Restaurant button is displayed after logging in as <user>
    Given User is on "login" page
    When User logs in as <user>
    Then Add Restaurant button in header is displayed
    Examples:
      | user                          |
      | BASIC                         |
      | BO_NO_RESTAURANTS             |
      | FD_NO_RESTAURANTS_AND_REVIEWS |

  Scenario Outline: Add Restaurant button is not displayed after logging in as <user>
    Given User is on "login" page
    When User logs in as <user>
    Then Add Restaurant button in header is not displayed
    Examples:
      | user |
      | BO   |
      | FD   |