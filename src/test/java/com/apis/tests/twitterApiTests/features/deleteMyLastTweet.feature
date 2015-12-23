Feature: DeleteMyLastTweet
  In order to know erase my traces
  As a twitter user
  I want to be able to delete my tweets

  Scenario Outline: DeleteMyLastTweet
    Given I tweet a <message>
    When I send a request to delete the message
    Then the <message> should be deleted
    Examples:
      | message                                         |
      | this message will be deleted in 0,0001 seconds. |