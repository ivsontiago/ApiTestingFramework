package com.apis.tests.setUp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                strict = false,
                features = {"src/test/java/com/apis/tests/twitterApiTests/features",
                        "src/test/java/com/apis/tests/spotifyApiTests/features"},
                format = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
                tags = {"~@ignore"}
        )
public class runBDDTests {
}