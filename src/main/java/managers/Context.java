package managers;

public class Context {
    public static DriverManager driverManager;
    public static ScenarioContext scenarioContext;
    public PageObjectManager pages;
    public ZAPManager zapManager;

    public Context() {
        driverManager = new DriverManager();
        pages = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
        zapManager = new ZAPManager();
    }

}
