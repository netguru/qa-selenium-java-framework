package managers;

public class Context {
    public PageObjectManager pages;
    public Driver driver;
    public ScenarioContext scenarioContext;

    public Context() {
        driver = new Driver();
        pages = new PageObjectManager();
        scenarioContext = new ScenarioContext();
    }

}
