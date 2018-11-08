Feature: Footer

  Scenario: Footer is not displayed on search page
    Given User is on "search" page
    Then Footer is not displayed

  Scenario Outline: <button> button in footer redirects to login page, if user is not logged in
    When User selects <button> button in footer
    Then User is redirected to "login" page
    Examples:
      | button                 |
      | Register your business |
      | Become a Detective     |

  Scenario Outline: User selects Become a Detective button in footer and logs in as <user>
    When User selects Become a Detective button in footer
    And User logs in as <user>
    Then Snackbar message shows up: "<message>"
    And User is redirected to "Edit profile - Basic information" page
    Examples:
      | user  | message                        |
      | BO    | You can not become a detective |
      | FD    | You are already a detective    |
      | BASIC | Avatar can't be blank          |

  Scenario: User selects Become a Detective button in footer and logs in as BASIC_WITH_AVATAR
    When User selects Become a Detective button in footer
    And User logs in as  BASIC_WITH_AVATAR
    Then User is redirected to "Edit profile - Become a Detective" page

  Scenario Outline: User selects Register your business button in footer and logs in as <user>
    When User selects Register your business button in footer
    And User logs in as <user>
    Then User is redirected to "Add restaurant - step 1" page
    Examples:
      | user  |
      | BASIC |
      | BO    |
      | FD    |

  Scenario: Become a Detective button is displayed after logging in as BASIC
    Given User is on "login" page
    When User logs in as BASIC
    Then Become a Detective is displayed in footer

  Scenario Outline: Become a Detective button is not displayed after logging in as <user>
    Given User is on "login" page
    When User logs in as <user>
    Then Become a Detective is not displayed in footer
      | user  |
      | BO    |
      | FD    |
