Feature: Login Functionality

  Background: Login
    Given Enter email super@gmail.com
    And Enter password Sa1234567+
    And Click on 'login' button

  Scenario: Check super admin logs successfully - TC 1.7.2
    Then The user is on super admin page
