package com.codewars.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/codewars/step_definitions",
        dryRun = false,
        tags = "@wip"
)
public class CukesRunner {
}
