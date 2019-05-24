package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

// As each page class this class also extends BasePage class

public class ChallengingDomPage extends BasePage {


    // constructor - needed
    public ChallengingDomPage(WebDriver driver) {
        super(driver);
    }


    /*
    2 methods which have to be overridden because of inheritance.
    The parent class - BasePage is abstract class and has 2 abstract methods.
    Abstract methods haven't body but they have to be overridden in classes which
    inherit from parent abstract class.
     */

    @Override
    protected void load() {
        driver.get(getUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertEquals(getUrl(), driver.getCurrentUrl());
    }


    /*
    Below is locators section. Here you can find By's locators and also methods
    which return By object - for example if locator depends on text of element.
    * */
    private By alertButtonLocator = By.cssSelector(".alert");

    private By getRowAndColumnsLocator(int row, int column) {
        return By.xpath(format("//tbody/tr[%s]/td[%s]", row, column));
    }

    private By getEditButtonInSelectedRow(String name) {
        return By.xpath(format("//td[contains(.,'%s')]/following-sibling::td/a[contains(.,'edit')]", name));
    }

    /*Below there's actions section. These methods operate on elements on selected page.
    * */

    public String getValueFromSelectedField(int rowNumber, int columnNumber) {
        return driver.findElement(getRowAndColumnsLocator(rowNumber, columnNumber)).getText();
    }

    public void clickOnEditButtonNearBySelectedSixthColumnValue(String name) {
        driver.findElement(getEditButtonInSelectedRow(name)).click();
    }

    public String getAlertButtonText() {
        return driver.findElement(alertButtonLocator).getText();
    }
}
