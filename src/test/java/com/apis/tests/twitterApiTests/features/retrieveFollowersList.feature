Feature: RetrieveFollowersList
  In order to know if I'm popular
  As a twitter user
  I want to be able to see who's following me

  Scenario: RetrieveFollowersList
    Given I request my list of followers
    Then I must have at least 1 follower