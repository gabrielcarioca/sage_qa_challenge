@PersonListFeature
Feature: Add a person to the list

Background: Start with empty people list
    Given Person list is empty
    And User goes to home page

@AddPersonFeature
Scenario: Validate if user is able to add a person
    When User clicks in new button
    And User fills person data
    And User goes to address data page
    And User fills address data
    And User saves the new person
    Then User can see a success pop-up
    And User dismiss the success pop-up
    Then User can see the added person in the list
    And User opens the modal for added person
    Then User can see all previous added information already filled in the modal