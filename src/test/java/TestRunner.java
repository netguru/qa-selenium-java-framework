import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue={"steps"},
    tags = { "~@ignore" }
)

public class TestRunner extends AbstractTestNGCucumberTests {

}