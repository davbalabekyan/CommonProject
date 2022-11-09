Feature: UI of pages
  This feature checks all elements that must be present on certain web pages

  @TC1.7.1 @Regression
  Scenario: Check UI of Login page
    Then Check all elements are present in login page

  @TC1.7.7 @Regression
  Scenario: Check UI of super admin page
    Given Login as super admin
    Then Check all elements are present in super admin page

  @TC1.6.1 @Regression
  Scenario: Check UI of create popup in super admin dashboard
    Given Login as super admin
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup

  @TC1.8.1 @Regression
  Scenario: Check UI of 'create' popup in admin dashboard/ teacher section
    Given Login as admin
    When Select teachers section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup

  @TC1.9.1
  Scenario: Check UI of Admin Page Parents section
    Given Login as admin
    When Select parents section
    Then Check all elements are present on the chosen section

  @TC1.9.2 @TC1.9.3
  Scenario: Check functionality of "Create" button in admin dashboard/ parents section
    Given Login as admin
    When Select parents section
    And Click on 'create' button and open popup
    Then Popup is opened
    Then Check all fields are present in create popup

  @TC1.10.1 @Regression
  Scenario: Check UI of Admin page Students section
    Given Login as admin
    When Select students section
    Then Check all elements are present on the chosen section

  @TC1.10.2 @TC1.10.3 @Regression
  Scenario: Check UI of 'create' button in admin dashboard/ students section
    Given Login as admin
    When Select students section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup - students section

  @TC1.7.8 @Regression
  Scenario: Check UI of admin page
    Given Login as admin
    Then Check all elements are present on admin page

  @TC1.11.1 @TC1.11.2 @1.11.3 @Regression
  Scenario: Check UI of "Academic Classes" section and "Create" button on the "Academic Classes" section
    Given Login as admin
    When Select classes section
    Then Check all elements are present on the chosen section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup - academic classes section

  @TC2.13.1 @Regression
  Scenario: Check UI of 'Create' button in 'Academic Year' section
    Given Login as admin
    When Select years section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup years section

  @TC2.14.1 @TC2.14.2 @TC2.14.3 @Regression
  Scenario: Check UI of 'Vacations' section and UI of 'Create' button in the given section
    Given Login as admin
    When Select vacations section
    Then Check all elements are present on the chosen section
    When Click on 'create' button and open popup
    Then Popup is opened
    Then Check all fields are present in create popup years section

  @TC2.12.1 @TC2.12.2 @TC2.12.3 @Regression
  Scenario: Check UI of admin dashboard / subjects section and create subject popup
    Given Login as admin
    When Select subjects section
    Then Check all elements are present on the chosen section
    And Click on 'create' button and open popup
    Then Popup is opened
    And Check all elements are present in create popup - subjects section

  @TC2.41.1 @Regression
  Scenario: Check UI of 'Add' popup in Subjects dashboard after selecting subject
    Given Login as admin
    When Select subjects section
    And Click on the Languages item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup
    Then Check all elements are present in subject dashboard teachers section
