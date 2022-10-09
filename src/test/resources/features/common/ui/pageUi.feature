Feature:UI of pages

  @TC1.7.1
  Scenario: Check UI of Login page
    Then Check all elements are present in login page

  Scenario:Check UI of super admin page - TC 1.7.7
    Given Enter email super@gmail.com in login page
    And Enter password Sa1234567+ in login page
    And Click on 'login' button
    Then Check all elements are present in super admin page
