Feature: SearchAlbums
  In order to access the albums available in Spotify
  As a Spotify user
  I want check whether an album is available or not

  Scenario Outline: SearchAlbums
    Given I search for an artist on Spotify using <artistName>
    Then the artist should <expectedResult>
    Examples:
      | artistName        | expectedResult |
      | Yellowman         | exist          |
      | Magentaman        | not exist      |
      | Justin Timberlake | exist          |