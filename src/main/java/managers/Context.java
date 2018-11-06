package managers;

public class Context {
    public static DriverManager driverManager;
    public PageObjectManager pages;
    public ScenarioContext scenarioContext;

  public Context() {
    driverManager = new DriverManager();
    pages = new PageObjectManager(driverManager.getDriver());
    scenarioContext = new ScenarioContext();
  }

}
