Feature: Login

Background: User navigates to login page
    Given The User is on login page
    Then They should see the Log in button

Scenario Outline: Successful login as <user_type>
    When User provides email: "<email>" and password: "<password>"
    And User selects Log in button
    Then User is logged in and taken to the dashboard page
    Examples:
        |   user_type   |   email               | password    |
        |   admin       |   admin@netguru.pl    | password    |
        |   provider    |   provider@netguru.pl | password    |

Scenario: Successful logout
    Given User is logged in
    When User selects Logout button
    Then User is redirected to home page
    And User cannot access the dashboard

Scenario: Cannot login with empty credentials
    When User does not provide the credentials
    And User selects Log in button
    Then User cannot login
    And "Invalid login data." message shows up

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
    Then User is redirected to Forgot Password page

Scenario: Selecting Sign up now button
    When User selects Sign up now button
    Then User is redirected to Register page