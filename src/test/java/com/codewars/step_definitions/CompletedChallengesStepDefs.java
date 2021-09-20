package com.codewars.step_definitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CompletedChallengesStepDefs {


    ValidatableResponse response;

    @Given("user sends a GET request with path param {string} and query param {string} {int}")
    public void user_sends_a_GET_request_with_path_param_and_query_param(String user, String queryParam, Integer pageNo) {
        response = given().accept(ContentType.JSON)
                .and()
                .pathParam("user", user)
                .and()
                .queryParam(queryParam, pageNo)
                .when()
                .get("/api/v1/users/{user}/code-challenges/completed")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Then("user validates schema matches with {string}")
    public void user_validates_schema_matches_with(String path) {
        response.body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
    }

    @Then("user verifies total pages,total items and data")
    public void user_verifies_total_pages_total_items_and_data() {
        JsonPath jsonPath = response.extract().jsonPath();
        Assert.assertEquals(jsonPath.getInt("totalPages"), 1);
        Assert.assertEquals(jsonPath.getInt("totalItems"), 1);
        Assert.assertEquals(jsonPath.getString("data[0].name"), "Multiply");
        Assert.assertEquals(jsonPath.getString("data[0].completedLanguages[0]"),"java");
        MatcherAssert.assertThat(jsonPath.getString("data[0].completedAt"), notNullValue());
    }
}
