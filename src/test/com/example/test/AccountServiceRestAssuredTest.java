package com.example.test;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AccountServiceRestAssuredTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://account-service";
        RestAssured.port = 2222;
    }

    @Test
    public void accountInitialElementsExist03() {
        get("/").then().assertThat()
                .body("number", hasItems("111111", "222222", "333333", "444444", "555555", "666666", "777777"))
                .body("id", hasItems(1, 2, 3, 4, 5, 6, 7))
                .body("customerId", hasItems(1, 2, 3, 4));
    }

    @Test
    public void accountRetrieveExistingElementOnRootEndpoint04() {
        get("/1").then().assertThat()
                .body("number", equalTo("111111"))
                .body("number", not(hasItems("222222", "333333", "444444", "555555", "666666", "777777")))
                .body("id", equalTo(1))
                .body("id", not(hasItems(2, 3, 4, 5, 6, 7)))
                .body("customerId", equalTo(1))
                .body("customerId", not(hasItems(2, 3, 4)));
    }

}