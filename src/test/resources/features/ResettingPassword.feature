Feature: Resetting password

  Background:
    Given User is on "reset password" page

  Scenario: User cannot submit invalid email
    When User submits invalid email in reset password form
    Then Email error message shows up on reset password page: "Email is invalid"
    And Reset password email is not sent

  Scenario: User cannot submit empty email
    When User submits empty email in reset password form
    Then Email error message shows up on reset password page: "This field is required"
    And Reset password email is not sent

  Scenario: User can submit a valid email
    When User submits a valid email in reset password form
    Then Snackbar message shows up: "Please check your email to see instructions on how to reset your password"
    And User is redirected to "home" page
