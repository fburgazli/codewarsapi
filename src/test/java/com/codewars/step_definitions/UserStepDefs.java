package com.codewars.step_definitions;

import com.codewars.pojos.User;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class UserStepDefs {

    ValidatableResponse response;
    User user;



    @Given("user sends a GET request with path param {string}")
    public void user_sends_a_GET_request_with_path_param(String user) {
        response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("user", user)
                .when()
                .get("/api/v1/users/{user}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @When("user gets the response")
    public void user_gets_the_response() {

        user = response.extract().as(User.class);
    }
    @And("user validates schema")
    public void userValidatesSchema() {
        //validate schema
        response.body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/userSchema.json")));
    }

    @Then("user verifies all fields matching")
    public void user_verifies_all_fields_matching() {
        Assert.assertEquals(user.getUsername(),"fburgazli");
        Assert.assertEquals(user.getName(),"Fikret");
        //System.out.println("user = " + user);
       //System.out.println("user.getRanks().getLanguages() = " + user.getRanks().getLanguages().getJava());

        Assert.assertTrue(user.getSkills().contains("rest assured"));
    }



}
