Feature: User login

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with username "admin" and password "admin"
    Then the dashboard is displayed

  @negative
  Scenario Outline: Failed login with invalid credentials
    When the user logs in with username "<user>" and password "<pwd>"
    Then a login error is shown

    Examples:
      | user        | pwd       |
      | wrong       | admin     |
      | admin       | wrong     |
      |             |           |
