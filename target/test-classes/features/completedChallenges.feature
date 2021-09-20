Feature: List completed challenges

  @wip
  Scenario: user gets completed challenges info
    Given user sends a GET request with path param "fburgazli" and query param "page" 0
    Then user validates schema matches with "src/test/resources/completedChallengesSchema.json"
    Then  user verifies total pages,total items and data