@DeleteLastTweet
Feature: RetweetAStatus
  In order to be able to retweet a message that I like
  As a twitter account owner
  I want to be able to retweet any given message from another user

  Scenario Outline: RetweetAStatus
    Given I send a request to retweet a <messageId>
    When I refresh my timeline
    Then the <messageId> should appear on my timeline
    Examples:
      | messageId          |
      | 677700362049056768 |