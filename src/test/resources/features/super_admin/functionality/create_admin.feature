Feature: Super admin page/ Admin section functionality
  This feature will cover admin section of super admin page, especially: creating new admins,
  adding new created admins to database, checking functionality of different buttons on the given page

  Background: Login as super admin and click on create button
    Given Fill super@gmail.com and Sa1234567+ fields
    And Click on 'login' button
    Then Click on 'create' button and open popup

  @TC1.6.2 @Regression @Smoke
  Scenario: Check functionality of 'X' button
    And Fill in all required fields
    When Click on 'X' button
    And Popup is closed
    And Click on 'create' button and open popup
    Then Check all input fields are empty in create popup
    And Save value from email input field
    And Check admin is not added in the DB

  @TC1.6.3 @Regression @Smoke
  Scenario: Check functionality of 'Generate password' button
    And Click on 'Generate password' button
    Then 'Password' field is filled with autogenerated password

  @TC1.6.4 @Regression @Smoke
  Scenario: Check functionality to regenerate password
    And Click on 'Generate password' button
    And Save value from password input field
    And Click on 'Generate password' button
    And Check the generated password has been changed
    Then Check 'Generate password' button is active

  @TC1.6.5 @Regression @Smoke
  Scenario: Check validations for Admin name, Admin surname and Admin email input fields
    Given Fill in input fields more than 50 symbols
    And Click on 'Save' button
    Then Check error messages of more symbols filled input fields

  @TC1.6.6 @Regression @Smoke
  Scenario Outline: Check email structure when creating new admin
    And Fill email <invalidEmail>
    And Click on 'Save' button
    Then Check invalid email error message

    Examples:
      | invalidEmail        |
      | !!invalid@gmail.com |
      | invalid{@gmail.com  |
      | invalidgmail.com    |
      | invalid@gmailcom    |
      | invalid@gmail.c1om  |
      | invalid@gmail.COM   |
      | invalid@gmail-.com  |
      | invalid@gm--ail.com |
      | invalid@gm.ail.com  |
      | i@gmail.com         |

  @TC1.6.7 @Regression @Smoke
  Scenario: Check mandatoriness of all input fields
    And Click on 'Save' button
    Then Check error messages of blank input fields

  @TC1.6.8 @Regression @Smoke
  Scenario: Check structure of autogenerated password
    And Click on 'Generate password' button
    Then Check the structure of generated password

  @TC1.6.9 @Regression @Smoke
  Scenario: Check uniqueness of the Admin email
    Given Fill name Valodik
    Given Fill surname Valodikyan
    When Fill existed email
    And Click on 'Generate password' button
    And Click on 'Save' button
    Then Check error message of existed email

  @TC1.6.10 @Regression @Smoke
  Scenario: Check possibility to input in password field
    Then Check the user is not able to input any data in the password field

  @TC1.6.11 @Regression @Smoke
  Scenario: Check functionality to create new admin using valid credentials
    And Fill in all required fields
    And Click on 'Generate password' button
    And Save values from name, surname and email fields
    And Click on 'Save' button
    And Popup is closed
    And Check admin is added in the DB
    Then Check new Admin is displayed on the Admins section

  @TC1.6.12 @Regression @Smoke
  Scenario: Check possibility of creating new admin with an existing 'Admin name' and 'Admin Surname'
    Given Fill existed name and surname
    And Fill non-existed email
    And Click on 'Generate password' button
    And Save values from name, surname and email fields
    And Click on 'Save' button
    Then Check admin is added in the DB
    Then Check new Admin is displayed on the Admins section

  @TC1.6.13 @Regression @Smoke
  Scenario: Check how password is kept in DB
    And Fill in all required fields
    And Click on 'Generate password' button
    And Save value from password input field
    And Save value from email input field
    And Click on 'Save' button
    Then Check the admin password is hashed in the DB
