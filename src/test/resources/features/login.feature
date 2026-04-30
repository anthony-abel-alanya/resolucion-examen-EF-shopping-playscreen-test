Feature: Login to Shopping Cart application
  As a registered user
  I want to log in with my credentials
  So I can access my account and make purchases

  Scenario: Successful login with valid credentials
    Given Andrea opens the shopping Cart application
    When she enters her email "user1@test.com" and password "password1"
    And she taps the Login button
    Then she should see the main screen of the application

  Scenario: Failed login with invalid credentials
    Given Andrea opens the shopping Cart application
    When she enters her email "invalid@test.com" and password "wrong-password"
    And she taps the Login button
    Then she should see an error message for invalid credentials
