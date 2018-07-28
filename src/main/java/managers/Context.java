package managers;

public class Context {
    public static DriverManager driverManager;
    public PageObjectManager pages;
    public SectionManager sections;
    public ScenarioContext scenarioContext;

    public Context() {
        driverManager = new DriverManager();
        pages = new PageObjectManager(driverManager.getDriver());
        sections = new SectionManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public static DriverManager getDriverManager() {
        return driverManager;
    }
}
