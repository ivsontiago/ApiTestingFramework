Feature: SearchAlbums
  In order to access the albums available in Spotify
  As a Spotify user
  I want check whether an album is available or not

  Scenario Outline: SearchAlbums
    Given I search for an album on Spotify using <albumName>
    Then the album should <expectedResult>
    Examples:
      | albumName                | expectedResult |
      | Zungguzungguguzungguzeng | exist          |
      | Alebayemarentemanbayeay  | not exist      |