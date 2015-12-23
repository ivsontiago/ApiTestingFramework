Feature: SearchAlbums
  In order to know if I can listen to my favourite songs
  As a Spotify user
  I want check whether a song is available or not

  Scenario Outline: SearchAlbums
    Given I search for a track on Spotify using <trackName>
    Then the track should <expectedResult>
    Examples:
      | trackName                                         | expectedResult |
      | You Only Live Once                                | exist          |
      | Some random song name played by an unknown artist | not exist      |