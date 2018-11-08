Feature: Registration

  Background:
    Given User is on "register" page

  Scenario Outline: User cannot register with already taken email
    When User registers with <user> email
    Then Snackbar message shows up: "Email has already been taken"
    And User is not registered
    Examples:
      | user  |
      | BASIC |
      | BO    |
      | FD    |
      | ADMIN |

  Scenario: User cannot register with invalid email
    When User registers with invalid email
    Then User is not registered
    And Email error message shows up on register page: "Email is invalid"

  Scenario: User cannot register with empty password
    When User registers with empty password
    Then User is not registered
    And Password error message shows up on register page: "This field is required"
    And Password confirmation error message shows up on register page: "This field is required"

  Scenario: User cannot register with too short password
    When User registers with too short password
    Then User is not registered
    And Password error message shows up on register page: "This field needs to have at least 8 characters"

  Scenario: User cannot register with wrong password confirmation
    When User registers with wrong password confirmation
    Then User is not registered
    And Password confirmation error message shows up on register page: "Passwords are not equal"

  Scenario: User cannot register without accepting terms and privacy policy
    When User registers without accepting terms and privacy policy
    Then User is not registered
    And Terms and privacy policy error message shows up on register page: "This field is required"

  Scenario: User can submit new account request with valid data
    When User registers with valid credentials
    Then Confirm you email message shows up on register page