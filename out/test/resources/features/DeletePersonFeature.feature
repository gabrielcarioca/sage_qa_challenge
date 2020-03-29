@PersonListFeature
Feature: Deletes a person in the list

Background: Start with at least one person in the list
    Given There is at least on person in list
    And User goes to home page

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