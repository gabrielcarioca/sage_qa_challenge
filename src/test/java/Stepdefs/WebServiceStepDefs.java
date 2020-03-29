package Stepdefs;


import Utility.BaseStepDefs;
import Utility.ApiUtility;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class WebServiceStepDefs extends BaseStepDefs {

    private String baseURI = "http://localhost:5000";
    private String peoplePath = "/api/people/";

    Response lastRequestResponse;

    @Before(order = 1)
    public void beforeSuite() {
        System.out.println("BeforeTest");

        RestAssured.baseURI = baseURI;
    }

    @Given("^User sends request to find films with director \"([^\"]*)\" and producer \"([^\"]*)\"$")
    public void getFilmsWithDirectorAndProducer(String director, String producer) {
        // baseURI/api/films/?director=${director}&producer=${producer}
        lastRequestResponse = RestAssured.given()
                .param("director", director)
                .param("producer", producer)
                .when()
                .get("/api/films/");
    }

    @Then("^User can see the list of requested films$")
    public void printFilmsList() {
        Response jsonResponse = lastRequestResponse.then().contentType(ContentType.JSON).extract().response();
        ArrayList<String> filmsList = ApiUtility.getInstance().getFilmsListFromResponseJson(jsonResponse);
        System.out.println("Films list: " + filmsList.toString());
    }

    @Given("^Person list is empty$")
    public void clearAllPersonInTheList() {
        lastRequestResponse = RestAssured.when().get(peoplePath);
        Response jsonResponse = lastRequestResponse.then().contentType(ContentType.JSON).extract().response();
        List<String> personIdList = ApiUtility.getInstance().getPersonListIdFromResponse(jsonResponse);
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
        List<String> personIdList = ApiUtility.getInstance().getPersonListIdFromResponse(jsonResponse);
        if (personIdList.size() > 0) {
            return;
        }
        String requestBody = ApiUtility.getInstance().createJsonForRandomPersonToAdd();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(peoplePath)
                .then()
                .statusCode(201);
    }
}
