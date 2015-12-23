Feature: GetRetweets
  In order to know if someone is popular
  As a twitter user
  I want to be able to see who's been retweeting a status

  Scenario: NewTweet
    Given I request the last retweets of status ID 677700362049056768
    Then the response should contain at least 10 retweets