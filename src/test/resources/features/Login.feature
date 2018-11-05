Feature: Login

  Background:
    Given User is on "login" page

  Scenario: "<user>" can log in
    When User logs in as "<user>"
    Then "<user>" is logged in
    And User is redirected to "home" page
    Examples:
      | user  |
      | BASIC |
      | BO    |
      | FD    |

  Scenario: Admin cannot log in
    When User logs in as "ADMIN"
    Then User is not logged in
    And Snackbar message shows up: "Login failed, bad credentials?"

  Scenario: User cannot log in with wrong password
    When User logs in with wrong password
    Then User is not logged in
    And Snackbar message shows up: "Login failed, bad credentials?"

  Scenario: User cannot log in with invalid email
    When User logs in with invalid email
    Then User is not logged in
    And Email validation message shows up: "Email is invalid"

  Scenario: User cannot log in with empty credentials
    When User logs in with empty credentials
    Then Email validation message shows up: "This field is required"
    And Password validation message shows up: "This field is required"

  Scenario: Forgot password button redirects to reset password page
    When User user selects "Forgot your password?" button
    Then User is redirected to "reset password" page

  Scenario: Forgot password button redirects to reset password page
    When User user selects "Create an account" button
    Then User is redirected to "register" page