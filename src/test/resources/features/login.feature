Feature: User login

  Tests run against https://the-internet.herokuapp.com/login —
  a public sandbox built for automation testing. The valid credentials
  for that site are tomsmith / SuperSecretPassword!.

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
    Then the dashboard is displayed

  @negative
  Scenario Outline: Failed login with invalid credentials
    When the user logs in with username "<user>" and password "<pwd>"
    Then a login error is shown

    Examples:
      | user        | pwd                    |
      | wrong       | SuperSecretPassword!   |
      | tomsmith    | wrong                  |
      | nobody      | nothing                |
