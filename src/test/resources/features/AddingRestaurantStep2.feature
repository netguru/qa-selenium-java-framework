@AddingRestaurantStep2
Feature: Adding restaurant - step 2


  Scenario Outline: User can go to next step after filling up all required fields
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 2" page
    When User submits form with required data on Add restaurant - step 2
    Then User is redirected to "Add Restaurant - step 3" page
    Examples:
      | user |
      | BO   |
      | FD   |

  Scenario Outline: User can go to next step after filling up all fields
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 2" page
    When User submits form with all data on Add restaurant - step 2
    Then User is redirected to "Add Restaurant - step 3" page
    Examples:
      | user |
      | BO   |
      | FD   |

  Scenario Outline: Verify validation of fields
    Given User is logged in as BO
    And User is on "Add Restaurant - step 2" page
    When User submits form with all correct data except "<field>" on Add restaurant - step 2
    Then User sees the error message of "<field>" on Add restaurant - step 2
    Examples:
      | field     |
      | email     |
      | phone     |
      | website   |
      | facebook  |
      | instagram |

  Scenario Outline: Passing correct numbers in form by country standard
    Given User is logged in as BO
    And User is on "Add Restaurant - step 2" page
    When User submits form with phone number "<number>" in "<country>" on Add restaurant - step 2
    Then User is redirected to "Add Restaurant - step 3" page
    Examples:
      | number        | country    |
      | 1 32 43 54 65 | france     |
      | 79 5345       | luxembourg |


  Scenario Outline: Passing incorrect numbers in form by country standard
    Given User is logged in as BO
    And User is on "Add Restaurant - step 2" page
    When User submits form with phone number "<number>" in "<country>" on Add restaurant - step 2
    Then User sees the error message of "phone" on Add restaurant - step 2
    Examples:
      | number         | country    |
      | 1 32 43 54 651 | france     |
      | 1 32 43 54 6   | france     |
      | 79 5           | luxembourg |
