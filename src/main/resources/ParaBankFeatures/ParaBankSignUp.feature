@Smoke
Feature: Sign up

  Scenario: User sign up with email

    Given   User navigate to sign up page
    And     User fill the details in the sign up page
    When    User click on register button
    Then    Sign up successful message will be displayed