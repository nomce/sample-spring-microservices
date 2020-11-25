package com.example.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith( Cucumber.class )
@CucumberOptions(publish = true,
        features = "src/test/resources/features")
public class AccountServiceCucumberTest {
}