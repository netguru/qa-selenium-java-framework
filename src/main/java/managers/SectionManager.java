package managers;

import org.openqa.selenium.WebDriver;
import sections.MainHeaderSection;

public class SectionManager {

    private WebDriver driver;
    private MainHeaderSection mainHeaderSection;

    public SectionManager(WebDriver driver) {
        this.driver = driver;
    }

    public MainHeaderSection getMainHeaderSection() {
        return (mainHeaderSection == null) ? mainHeaderSection = new MainHeaderSection(driver) : mainHeaderSection;
    }
}
