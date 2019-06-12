Feature: Validate loan amount eligibity

  Scenario: Verify valid loan amount being displayed on entry of all input parameters
    Given The user has provided the inputs
      | name    | mobile    | email       | baseIncome | partnerIncome | otherIncome | mortgage | otherLoans | creditCard | livingCost |
      | My name | 854985943 | abc@bac.com |       2500 |          1700 |        1000 |     1400 |       1000 |       1000 |        900 |
    Then Assert the amount user is eligible for is displayed
    And Assert maximum repayment amount is displayed to the user

  Scenario: Verify user with insufficient income is not provided with a loan
    Given The user has provided the inputs
      | name    | mobile    | email       | baseIncome | partnerIncome | otherIncome | mortgage | otherLoans | creditCard | livingCost |
      | No Name | 484985943 | abc@bac.com |       2500 |           000 |        0000 |     1300 |       1000 |       1000 |        900 |
    Then Assert that application provides a notice that user is not eligible for loan
