Feature: Possitive Adding item to shopping card

  Scenario: The item has been successfully added to shopping card
    Given Navigate to the page with items
    And save current value of 'OldItemCount'
    When button of selected item will be clicked
    Then item should be add to card, and 'OldItemCount' value should increase