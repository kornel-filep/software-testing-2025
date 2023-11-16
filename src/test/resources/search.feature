Feature: Search on the communities page

  Background:
    Given the 'Main' site is opened

  Scenario Outline: Search for the <cardName> community
    Given the 'Communities' button is clicked
    When I search for '<cardName>'
    Then I see <cardCount> community card
    And All cards contain the '<cardName>' word

    Examples:
      | cardName  | cardCount |
      | Idea Pool | 1         |
      | Budapest  | 4         |
      | Debrecen  | 2         |


  Scenario: Filter for location
    Given the 'Communities' button is clicked
    When I narrow the location to 'Budapest'
    And I click the highlited checkbox
    Then I see 7 community card
