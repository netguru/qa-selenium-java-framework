package ngelements;

import org.openqa.selenium.WebElement;

import java.util.List;

public class NGSelect extends NGHtmlElement {

    private org.openqa.selenium.support.ui.Select getSelect() {
        return new org.openqa.selenium.support.ui.Select(this.getWrappedElement());
    }

    public boolean isMultiple() {
        boolean multiple = this.getSelect().isMultiple();
        if (multiple) {
            log.debug("Select: " + getName() + " is multiple");
        } else {
            log.debug("Select: " + getName() + " isn't multiple");
        }
        return multiple;
    }

    public List<WebElement> getOptions() {
        return this.getSelect().getOptions();
    }

    public List<WebElement> getAllSelectedOptions() {
        return this.getSelect().getAllSelectedOptions();
    }

    public WebElement getFirstSelectedOption() {
        return this.getSelect().getFirstSelectedOption();
    }

    public boolean hasSelectedOption() {
        return this.getOptions().stream().anyMatch(WebElement::isSelected);
    }

    public void selectByVisibleText(String text) {
        this.getSelect().selectByVisibleText(text);
        log.info("Select by text: " + text);
    }

    public void selectByIndex(int index) {
        this.getSelect().selectByIndex(index);
        log.info("Selected by index: " + Integer.toString(index));
    }

    public void selectByValue(String value) {
        this.getSelect().selectByValue(value);
        log.info("Selected by value: " + value);
    }

    public void deselectAll() {
        this.getSelect().deselectAll();
        log.info("All values were deselected");
    }

    public void deselectByValue(String value) {
        this.getSelect().deselectByValue(value);
        log.info("Deselected by value: " + value);
    }

    public void deselectByIndex(int index) {
        this.getSelect().deselectByIndex(index);
        log.info("Deselected by index: " + Integer.toString(index));
    }

    public void deselectByVisibleText(String text) {
        this.getSelect().deselectByVisibleText(text);
        log.info("Deselected by text: " + text);
    }

}
