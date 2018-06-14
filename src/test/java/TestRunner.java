import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
<<<<<<< HEAD
        , glue={"steps"}
        , snippets = SnippetType.CAMELCASE
        , monochrome = true
        , tags = {}
=======
        , glue = {"steps"}
        , snippets = SnippetType.CAMELCASE
>>>>>>> master
)

public class TestRunner extends AbstractTestNGCucumberTests {

}