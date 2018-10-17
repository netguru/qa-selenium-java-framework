package managers;

public interface Context {
    DriverManager driverManager = new DriverManager();;
    PageObjectManager pages = new PageObjectManager(driverManager.getDriver());
    ScenarioContext scenarioContext = new ScenarioContext();

}
