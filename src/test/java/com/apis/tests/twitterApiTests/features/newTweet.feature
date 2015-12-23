@DeleteLastTweet
Feature: NewTweet
  In order to be able to tweet from my app
  As a twitter account owner
  I want to tweet any given message

  Scenario Outline: NewTweet
    Given I tweet a <message>
    Then the <message> should appear on my timeline
    Examples:
      | message                                         |
      | this message will be deleted in 0,0001 seconds. |