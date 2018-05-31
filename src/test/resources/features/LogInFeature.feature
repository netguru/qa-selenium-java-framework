Feature: Login

Scenario Outline: Successful login as <user_type>
    When User provides correct email: "<email>" and password
    And User selects Log in button
    Then User is taken to the dashboard page
    Examples:
        |   user_type                   |   email                         |
        |   admin                       |   admin@netguru.pl              |
        |   consumer with payment       |   consumer_unpaid@netguru.pl    |
        |   consumer without payment    |   consumer_paid@netguru.pl      |
        |   consumer with discounts     |   consumer_special@netguru.pl   |
        |   provider                    |   provider@netguru.pl           |

Scenario: Successful logout
    Given User is logged in
    When User selects Logout button
    Then User is redirected to home page
    And User cannot access the dashboard

Scenario: Cannot login with empty credentials
    When User does not provide the credentials
    And User selects Log in button
    Then User cannot login
    And "Invalid login data." alert shows up

Scenario: Cannot login with invalid email
    When User provides invalid email and good password
    And User selects Log in button
    Then User cannot login
    And "Invalid email or password." alert shows up

Scenario: Cannot login with wrong email
    When User provides wrong email and good password
    And User selects Log in button
    Then User cannot login
    And "Invalid email or password." alert shows up

Scenario: Cannot login with wrong password
    When User provides good email and wrong password
    And User selects Log in button
    Then User cannot login
    And "Invalid email or password." alert shows up

Scenario: Selecting "Forgot Password?" button
    When User selects "Forgot Password?" button
    Then User is redirected to Forgot Password page

Scenario: Selecting "Sign up now" button
    When User selects "Sign up now" button
    Then User is redirected to Register page