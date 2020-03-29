@PersonListFeature
Feature: Edit a person in the list

Background: Start with at least one person in the list
    Given There is at least on person in list
    And User goes to home page

@EditPersonFeature
Scenario: User edits information for first person in list
    Given User opens the modal for added person
    And User clear all person data fields
    And User fills person data
    And User goes to address data page
    And User saves address data for person
    And User saves the edited person
    Then User can see a success pop-up
    And User dismiss the success pop-up
    Then User can see the edited person in the list
    And User opens the modal for edited person
    Then User can see all previous edited information already filled in the modal