package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
        , glue = {"steps"}
        , snippets = SnippetType.CAMELCASE
        , plugin = {"pretty", "html:target/cucumber-reports/html", "json:target/cucumber-reports/json/cucumber.json" }
)
public class JUnitRunner {
}
