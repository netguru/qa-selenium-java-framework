package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
        , glue = {"steps"}
        , snippets = SnippetType.CAMELCASE
        , tags = {"@Login"}
        , plugin = {"pretty"}
)

public class LoginTestRunner extends AbstractTestNGCucumberTests {

}