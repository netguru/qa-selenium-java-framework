@AddingRestaurant-Step1
Feature: Adding restaurant - step 1

  Scenario Outline: User can go to next step after filling up all required fields
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    When User submits form with <fillingSetting> data on Add Restaurant - Step 1 page
    Then User is redirected to "Add Restaurant - step 2" page
    Examples:
      | user | fillingSetting   |
      | BO   | minimum          |
      | BO   | maximum          |
      | FD   | minimum          |
      | FD   | maximum          |

#  Scenario Outline: Restaurant's draft is created and saved after going to 2nd step
#    Given User is logged in as <user>
#    And User is on "Add Restaurant - step 1" page
#    When User submits form with <fillingSetting> data on Add Restaurant - Step 1 page
#    Then Restaurant's draft is created
##    And Restaurant's data is correct in "" section on Edit
#    Examples:
#      | user | fillingSetting   |
#      | BO   | minimum          |
#      | BO   | maximum          |
#      | FD   | minimum          |
#      | FD   | maximum          |
#
#  Scenario Outline: Region field is not available by default
#    Given User is logged in as <user>
#    And User is on "Add Restaurant - step 1" page
#    Then Region field is not displayed on Add Restaurant - Step 1 page
#    Examples:
#      | user |
#      | BO   |
#      | FD   |
#
#  Scenario Outline: Region field is available after selecting country with regions
#    Given User is logged in as <user>
#    And User is on "Add Restaurant - step 1" page
#    When User selects a country with regions on Add Restaurant - Step 1 page
#    Then Region field is displayed on Add Restaurant - Step 1 page
#    Examples:
#      | user |
#      | BO   |
#      | FD   |

