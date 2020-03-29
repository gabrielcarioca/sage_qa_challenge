package Stepdefs;


import Utility.BaseStepDefs;
import Utility.WebServiceUtility;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WebServiceStepDefs extends BaseStepDefs {

    private String baseURI = "http://localhost:5000";
    private String peoplePath = "/api/people/";

    Response lastRequestResponse;
    RequestSpecification request;

    Map<String, String> fieldNamesForResponse = new HashMap<String, String>() {{
        put("email", "E-mail");
        put("name", "Nome");
        put("document", "Documento");
        put("address", "Logradouro");
    }};

    @Before(order = 1)
    public void beforeSuite() {
        System.out.println("Configuration for WebServiceStepDefs");

        RestAssured.baseURI = baseURI;
    }

    @Given("^Person list is empty$")
    public void clearAllPersonInTheList() {
        lastRequestResponse = RestAssured.when().get(peoplePath);
        Response jsonResponse = lastRequestResponse.then().contentType(ContentType.JSON).extract().response();
        List<String> personIdList = WebServiceUtility.getInstance().getPersonListIdFromResponse(jsonResponse);
        for (int i = 0; i < personIdList.size(); i++) {
            RestAssured.when()
                    .delete(peoplePath + personIdList.get(i) + "/")
                    .then()
                    .statusCode(204);
        }
    }

    @Given("^There is at least on person in list$")
    public void makeSureThereIsAtLeastOnePerson() {
        lastRequestResponse = RestAssured.when().get(peoplePath);
        Response jsonResponse = lastRequestResponse.then().contentType(ContentType.JSON).extract().response();
        List<String> personIdList = WebServiceUtility.getInstance().getPersonListIdFromResponse(jsonResponse);
        if (personIdList.size() > 0) {
            return;
        }
        String requestBody = WebServiceUtility.getInstance().createJsonForRandomPersonToAdd();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(peoplePath)
                .then()
                .statusCode(201);
    }

    @Given("^Request without ([^\"]*) is created$")
    public void createRequestWithoutField(String field) {
        String bodyJson = WebServiceUtility.getInstance().createJsonForRandomUserWithMissingField(field);
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyJson);
    }

    @When("^Post request is sent$")
    public void sendPostRequest() {
        lastRequestResponse = request.when().post(peoplePath);
    }

    @Then("^A response for ([^\"]*) required should be received$")
    public void validateResponseForFieldRequired(String field) {
        lastRequestResponse.then().statusCode(400);
        String responseMessage = lastRequestResponse.jsonPath().get("message");
        Assert.assertEquals(responseMessage
                , fieldNamesForResponse.get(field) + " é obrigatório."
        );
    }

    @Given("^Request with invalid email is created$")
    public void createRequestWithInvalidEmail() {
        String bodyJson = WebServiceUtility.getInstance().createJsonForRandomUserWithInvalidEmail();
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyJson);
    }

    @Then("^A response for invalid email should be received$")
    public void validateResponseForInvalidEmail() {
        lastRequestResponse.then().statusCode(400);
        String responseMessage = lastRequestResponse.jsonPath().get("message");
        Assert.assertEquals(responseMessage
                , fieldNamesForResponse.get("email") + " não é um endereço válido."
        );
    }

    @Given("^Request with invalid document is created$")
    public void createRequestWithInvalidDocument() {
        String bodyJson = WebServiceUtility.getInstance().createJsonForRandomUserWithInvaliDocument();
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyJson);
    }

    @Then("^A response for invalid document should be received$")
    public void validateResponseForInvalidDocument() {
        lastRequestResponse.then().statusCode(400);
        String responseMessage = lastRequestResponse.jsonPath().get("message");
        Assert.assertEquals(responseMessage
                , fieldNamesForResponse.get("document") + " é inválido."
        );
    }
}
