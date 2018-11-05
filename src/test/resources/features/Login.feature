Feature: Login

  Background:
    Given User is on "login" page

#  Scenario Outline: <user> can log in
#    When User logs in as <user>
#    Then User is logged in
#    And User is redirected to "home" page
#    Examples:
#      | user  |
#      | BASIC |
#      | BO    |
#      | FD    |
#
#  Scenario Outline: <user> can log out
#    Given User is logged in as <user>
#    When User logs out
#    Then User is not logged in
#    Examples:
#      | user  |
#      | BASIC |
#      | BO    |
#      | FD    |

  Scenario: Admin cannot log in
    When User logs in as ADMIN
    Then Snackbar message shows up: "Login failed, bad credentials?"
    And User is not logged in

  Scenario: User cannot log in with wrong password
    When User logs in with wrong password
    Then Snackbar message shows up: "Login failed, bad credentials?"
    And User is not logged in

#  Scenario: User cannot log in with empty password
#    When User logs in with empty password
#    Then User is not logged in
#    And Password error message shows up on login page: "This field is required"
#
#  Scenario: User cannot log in with invalid email
#    When User logs in with invalid email
#    Then User is not logged in
#    And Email error message shows up on login page: "Email is invalid"
#
#  Scenario: User cannot log in with empty credentials
#    When User logs in with empty credentials
#    Then User is not logged in
#    And Email error message shows up on login page: "This field is required"
#    And Password error message shows up on login page: "This field is required"
#
#  Scenario: Forgot password button redirects to reset password page
#    When User selects "Forgot your password?" button
#    Then User is redirected to "reset password" page
#
#  Scenario: Forgot password button redirects to reset password page
#    When User selects "Create an account" button
#    Then User is redirected to "register" page