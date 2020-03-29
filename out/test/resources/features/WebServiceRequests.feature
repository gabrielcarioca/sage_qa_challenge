@WebServiceRequestFeature
Feature: Send Invalid Requests and see the response

Scenario: User send requests without mandatory fields
    Given Request without email is created
    When Post request is sent
    Then A response for email required should be received
    Given Request without name is created
    When Post request is sent
    Then A response for name required should be received
    Given Request without document is created
    When Post request is sent
    Then A response for document required should be received
    Given Request without address is created
    When Post request is sent
    Then A response for address required should be received

Scenario: User send requests with invalid email
    Given Request with invalid email is created
    When Post request is sent
    Then A response for invalid email should be received

Scenario: User send requests with invalid document
    Given Request with invalid document is created
    When Post request is sent
    Then A response for invalid document should be received


