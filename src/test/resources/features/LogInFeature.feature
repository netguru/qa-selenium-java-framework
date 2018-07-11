@Login
Feature: Login

Background: User navigates to login page
    Given The User is on login page
    Then They should see the Log in button

@Smoke
Scenario Outline: Successful login as <user_type>
    When User logs in as: "<user_type>"
    Then User is logged in and taken to the dashboard page
    Examples:
        |   user_type           |
        |   ADMIN               |
        |   PROVIDER            |
        |   CONSUMER_PAID       |
        |   CONSUMER_UNPAID     |
        |   CONSUMER_SPECIAL    |

Scenario: Successful logout
    Given User is logged in as "CONSUMER_UNPAID"
    When User selects Logout button
    Then User is redirected to "Home page"
    And User cannot access the dashboard

Scenario: Cannot login with empty credentials
    When User does not provide the credentials
    And User selects Log in button
    Then User cannot login
    And "Invalid login data." message shows up

@Smoke
Scenario Outline: Cannot login with wrong credentials
    When User provides email: "<email>" and password: "<password>"
    And User selects Log in button
    Then User cannot login
    And "<alert>" message shows up
    Examples:
        |   email               |   password        |   alert                       |
        |   invalid_email       |   password        |   Invalid email or password.  |
        |   some@wrong.email    |   password        |   Invalid email or password.  |
        |   provider@netguru.pl |   wrong_password  |   Invalid login data.         |

Scenario: Selecting Forgot Password button
    When User selects Forgot Password button
    Then User is redirected to "Forgot Password page"

Scenario: Selecting Sign up now button
    When User selects Sign up now button
    Then User is redirected to "Register page"