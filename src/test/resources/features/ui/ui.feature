Feature: Ui elements testing

  Scenario: Check UI of Login page - TC 1.7.1
    Then The user sees email, password and login button are present


  Scenario: Check UI of Admin page - TC 1.7.8
    When Enter email heghine96@gmail.com in login page
    And Enter password 1 in login page
    And Click on 'login' button
    When Select teachers section
    Then See all elements are present