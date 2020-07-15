#Author: mentor@testingementor.com
Feature: Create a blob

  @SmokeTestRest
  Scenario: Create a blob
    Given the API for jsonbolb 
    When I post the request
    Then the response status code is "201"
