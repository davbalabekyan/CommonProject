Feature: Ui elements testing
  In this feature is tested UI of different pages, whether all elements of certain page are displayed

  @TC1.7.1
  Scenario: Check UI of Login page
    Then Check all elements are present in login page

  @TC1.7.8
  Scenario: Check UI of Admin page
    When Enter email heghine96@gmail.com in login page
    And Enter password 1 in login page
    And Click on 'login' button
    When Select teachers section
    Then See all elements are present