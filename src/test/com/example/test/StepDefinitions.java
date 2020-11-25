package com.example.test;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private static final String APPLICATION_JSON = "application/json";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request;


    @When("I send a GET request to root")
    public void i_send_a_GET_request_to_root() {
        request = new HttpGet("http://localhost:2222");
    }

    @Then("I should get the list of elements")
    public void i_should_get_the_list_of_elements(String expected_response) throws IOException {
        HttpResponse httpResponse = httpClient.execute(request);

        Scanner scanner = new Scanner(httpResponse.getEntity().getContent(), "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();

        assertEquals(expected_response.replaceAll("\\s+",""),responseString);   //delete whitespaces
    }
}
