Feature: Codewar user information

  Scenario: GET user information and verify it
    Given user sends a GET request with path param "fburgazli"
    When user gets the response
    And user validates schema
    Then user verifies all fields matching