package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/Feature",
    glue = {"stepDefinitions"},
    tags = "@Activity12",
    plugin = { "pretty" },
    monochrome = true
)

public class Activity1 {
    //empty
}
