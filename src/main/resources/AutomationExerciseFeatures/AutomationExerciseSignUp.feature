@Smoke
Feature: Sign up

  Scenario: User signed up with email

    Given   User navigate to sign up screen
    And     User insert signup name and email
    When    User click on sign up button
    And     User insert personal details
    Then    Click on create account button and Sign up successful message is displayed
    And     Main screen with logged in account