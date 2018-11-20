package ngelements;

import managers.Context;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NGCheckbox extends NGHtmlElement {

    @FindBy(xpath = "self::*/../following-sibling::span")
    private NGTextBlock validator;

    public void select() {
        if (!this.isSelected()) {
            this.getWrappedElement().click();
            log.info("Element: " + getName() + " is checked");
        } else {
            log.info("Element: " + getName() + " was already checked");
        }
    }

    public void deselect() {
        if (this.isSelected()) {
            this.getWrappedElement().click();
            log.info("Element: " + getName() + " is unchecked");
        } else {
            log.info("Element: " + getName() + " was already unchecked");
        }
    }

    public void set(boolean value) {
        if (value) {
            this.select();
        } else {
            this.deselect();
        }
    }

    public NGTextBlock getValidator() {
        return validator;
    }
}
