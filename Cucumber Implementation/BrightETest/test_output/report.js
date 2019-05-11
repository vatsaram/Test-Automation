$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/testScenario.feature");
formatter.feature({
  "name": "Validate loan amount eligibity",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify valid loan amount being displayed on entry of all input parameters",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "The user has provided the inputs",
  "rows": [
    {
      "cells": [
        "name",
        "mobile",
        "email",
        "baseIncome",
        "partnerIncome",
        "otherIncome",
        "mortgage",
        "otherLoans",
        "creditCard",
        "livingCost"
      ]
    },
    {
      "cells": [
        "My name",
        "854985943",
        "abc@bac.com",
        "2500",
        "1700",
        "1000",
        "1400",
        "1000",
        "1000",
        "900"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CalculateLoanStepDefn.readAllInputs(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Assert the amount user is eligible for is displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "CalculateLoanStepDefn.validAmtDisplayed()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Assert maximum repayment amount is displayed to the user",
  "keyword": "And "
});
formatter.match({
  "location": "CalculateLoanStepDefn.validRepayAmtDisplayed()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify user with insufficient income is not provided with a loan",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "The user has provided the inputs",
  "rows": [
    {
      "cells": [
        "name",
        "mobile",
        "email",
        "baseIncome",
        "partnerIncome",
        "otherIncome",
        "mortgage",
        "otherLoans",
        "creditCard",
        "livingCost"
      ]
    },
    {
      "cells": [
        "No Name",
        "484985943",
        "abc@bac.com",
        "2500",
        "000",
        "0000",
        "1300",
        "1000",
        "1000",
        "900"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "CalculateLoanStepDefn.readAllInputs(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Assert that application provides a notice that user is not eligible for loan",
  "keyword": "Then "
});
formatter.match({
  "location": "CalculateLoanStepDefn.userIneligible()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});