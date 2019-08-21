package managers;

public class Context {
    public static DriverManager driverManager;
    public PageObjectManager pages;
    public ZAPManager zapManager;

    public Context() {
        driverManager = new DriverManager();
        pages = new PageObjectManager(driverManager.getDriver());
        zapManager = new ZAPManager();
    }

}
