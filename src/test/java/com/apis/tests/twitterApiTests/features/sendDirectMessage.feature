Feature: SendDirectMessage
  In order to be able to bond with my acquaintances
  As a twitter account owner
  I want to be able do send direct messages

  Scenario Outline: SendDirectMessage
    Given I send a <message> to <userName> <userId>
    Then the <message> should be successfully sent
    Examples:
      | message | userName   | userId     |
      | Hello!  | ivsontiago | 3401005576 |