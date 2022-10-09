Feature: Check UI of different pages

  Scenario Outline: Check mandatory of all input fields and error messages - TC 1.7.3, TC 1.7.4,
    When Leave blank or incorrect <email> and-or <password> field
    And Click on 'login' button
    Then See error message

    Examples:
      | email         | password      |
      | wrongUsername | wrongPassword |
      |               | blankUsername |
      | blankPassword |               |
      |               |               |

  Scenario: Check ability to login using last generated password - TC 1.7.6
    Given Enter email Super@gmail.com in login page
    And Enter password Sa1234567+ in login page
    And Click on 'login' button
    And Click on 'create' button and open popup
    And Fill in all required fields
    And Click on 'Generate password' button
    And Click on 'Save' button
    Then Sign in as admin with generated password


