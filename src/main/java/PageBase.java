import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {
    protected WebDriver driver;
    protected static String baseUrl;
    protected String url;

    public PageBase(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isInitialized();

    public String getUrl() {
        return url;
    }
}