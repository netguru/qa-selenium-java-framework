@AddingRestaurant-Step1
  Feature: Adding restaurant - step 1

  Scenario Outline: User can go to next step after filling up fields
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

  Scenario Outline: Restaurant's draft is created and saved after going to 2nd step
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    When User submits form with <fillingSetting> data on Add Restaurant - Step 1 page
    Then Snackbar message shows up: "Restaurant was updated!"
    And Restaurant's draft is created
    And Restaurant's <fillingSetting> data is correct in Basic Information section on Edit Restaurant page
    Examples:
      | user | fillingSetting   |
      | BO   | minimum          |
      | BO   | maximum          |
      | FD   | minimum          |
      | FD   | maximum          |

  Scenario Outline: Restaurant's data is shown correctly after going back to 1st step
    Given User is logged in as <user>
    And User submitted a form with <fillingSetting> data on Add Restaurant - Step 1 page
    When Snackbar message shows up: "Restaurant was updated!"
    And User goes back to 1st step in Add Restaurant form
    Then Restaurant's <fillingSetting> data is correct on Add Restaurant - Step 1 page
    Examples:
      | user | fillingSetting   |
      | BO   | minimum          |
      | BO   | maximum          |
      | FD   | minimum          |
      | FD   | maximum          |

  Scenario Outline: Region field is not available by default
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    Then Region field is not displayed on Add Restaurant - Step 1 page
    Examples:
      | user |
      | BO   |
      | FD   |

  Scenario Outline: Region field is available after selecting country with regions
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    When User selects a country with regions on Add Restaurant - Step 1 page
    Then Region field is displayed on Add Restaurant - Step 1 page
    Examples:
      | user |
      | BO   |
      | FD   |

  Scenario Outline: User cannot go to the next step without providing <field> field
    Given User is logged in as <user>
    And User is on "add restaurant - step 1" page
    When User provides all required data on Add Restaurant - Step 1 page except <field>
    And User submits form on Add Restaurant - Step 1 page
    Then <field> error message shows up on Add Restaurant - Step 1 page: "This field is required"
    Examples:
      | user | field       |
      | BO   | name        |
      | BO   | country     |
      | BO   | post code   |
      | BO   | city        |
      | BO   | street name |
      | FD   | name        |
      | FD   | country     |
      | FD   | post code   |
      | FD   | city        |
      | FD   | street name |


  Scenario Outline: User cannot go to the next step after selecting wrong amount of items in <field>
    Given User is logged in as <user>
    And User is on "Add restaurant - step 1" page
    When User provides all required data on Add Restaurant - Step 1 page except <field>
    And User submits form with <amount> items selected in <field> on Add Restaurant - Step 1 page
    Then <field> error message shows up on Add Restaurant - Step 1 page: "<message>"
    Examples:
      | user | field           | amount | message                               |
      | BO   | types           | 0      | You have to choose from 1 to 3 items  |
      | BO   | types           | 4      | You have to choose from 1 to 3 items  |
      | BO   | cuisines        | 0      | You have to choose from 1 to 5 items  |
      | BO   | cuisines        | 6      | You have to choose from 1 to 5 items  |
      | BO   | food and drinks | 0      | You have to choose from 1 to 6 items  |
      | BO   | food and drinks | 7      | You have to choose from 1 to 6 items  |
      | BO   | perfect for     | 2      | You have to choose from 3 to 10 items |
      | BO   | perfect for     | 11     | You have to choose from 3 to 10 items |
      | FD   | types           | 0      | You have to choose from 1 to 3 items  |
      | FD   | types           | 4      | You have to choose from 1 to 3 items  |
      | FD   | cuisines        | 0      | You have to choose from 1 to 5 items  |
      | FD   | cuisines        | 6      | You have to choose from 1 to 5 items  |
      | FD   | food and drinks | 0      | You have to choose from 1 to 6 items  |
      | FD   | food and drinks | 7      | You have to choose from 1 to 6 items  |
      | FD   | perfect for     | 2      | You have to choose from 3 to 10 items |
      | FD   | perfect for     | 11     | You have to choose from 3 to 10 items |

  Scenario Outline: All errors show up after submitting an empty form
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    When User submits an empty form on Add Restaurant - Step 1 page
    Then All error messages are shown on Add Restaurant - Step 1 page
    Examples:
      | user |
      | BO   |
      | FD   |

  Scenario Outline: Other errors are still present after fixing only one
    Given User is logged in as <user>
    And User is on "Add Restaurant - step 1" page
    When User submits an empty form on Add Restaurant - Step 1 page
    And User provides name field on Add Restaurant - Step 1 page
    Then All error messages except name are shown on Add Restaurant - Step 1 page
    Examples:
      | user |
      | BO   |
      | FD   |