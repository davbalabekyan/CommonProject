Feature: This feature will cover academic course section of admin page, especially adding new classes for courses,
  checking validations and DB integration

  Background: Login as admin, go to academic course section and select one of the existed courses
    Given Login as admin
    When Select courses section
    And Click on the English item in the list
    And Select Classes section in the dashboard
    And Click on 'Add' button and open popup
