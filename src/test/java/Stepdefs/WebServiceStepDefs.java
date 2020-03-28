package Stepdefs;


import Utility.BaseStepDefs;
import Utility.FilmsListUtility;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;


public class WebServiceStepDefs extends BaseStepDefs {

    private String baseURI = "http://localhost:5000";

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

    @And("^User receives a (\\d+) response$")
    public void checkRequestResponse(String response) {
        Assert.assertEquals(lastRequestResponse.statusCode(), Integer.parseInt(response));
    }

    @Then("^User can see the list of requested films$")
    public void printFilmsList() {
        Response jsonResponse = lastRequestResponse.then().contentType(ContentType.JSON).extract().response();
        ArrayList<String> filmsList = FilmsListUtility.getInstance().getFilmsListFromResponseJson(jsonResponse);
        System.out.println("Films list: " + filmsList.toString());
    }
}
