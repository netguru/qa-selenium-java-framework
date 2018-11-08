package ngelements;

import managers.Context;
import org.openqa.selenium.interactions.Actions;

public class NGButton extends NGHtmlElement {

    @Override
    public void click() {
        this.getWrappedElement().click();
        log.info("NGButton: " + this.getName() + " was clicked");
    }

    public void moveToElementAndClick() {
        Actions actions = new Actions(Context.driverManager.getDriver());
        actions.moveToElement(this.getWrappedElement()).click().perform();
        log.info("NGButton: " + this.getName() + " was clicked");
    }
}
