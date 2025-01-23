@regression @akakce
Feature: Akakçe Mobile Application Test

  @mobile
  Scenario: Search and find the desired product in Akakçe mobile app
    Given I open the Akakçe mobile app and continue without signing in
    When I search for "Laptop" using the search bar
    And I filter the results to show "Computer, Hardware" products
    And I sort the results by "Highest Price"
    And I tap on the 10th product in the results
    And I open the product's details page
    Then I should see the Go to Seller button
