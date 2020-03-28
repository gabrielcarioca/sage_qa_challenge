@PersonListFeature
Feature: Manage Person List

Background:
    Given User goes to home page

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

@CancelDeletingPersonFeature
Scenario: User cancel deleting person
    When User clicks in the delete button for first person in the list
    Then User can see a modal to confirm if he/she wants to delete the person
    And User clicks in CANCEL to not delete the person
    Then First person was not deleted from the list

@DeletePersonFeature
Scenario: Validate if user is able to delete first person
    When User clicks in the delete button for first person in the list
    Then User can see a modal to confirm if he/she wants to delete the person
    And User clicks in OK to confirm deleting person
    Then User can see a success pop-up
    And User dismiss the success pop-up
    Then First person was deleted from the list