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
            log.debug(String.format("Select: %s is multiple", getName()));
        } else {
            log.debug(String.format("Select: %s isn't multiple", getName()));
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
        log.info(String.format("Select by text: %s", text));
    }

    public void selectByIndex(int index) {
        this.getSelect().selectByIndex(index);
        log.info(String.format("Selected by index: %d", index));
    }

    public void selectByValue(String value) {
        this.getSelect().selectByValue(value);
        log.info(String.format("Selected by value: %s", value));
    }

    public void deselectAll() {
        this.getSelect().deselectAll();
        log.info("All values were deselected");
    }

    public void deselectByValue(String value) {
        this.getSelect().deselectByValue(value);
        log.info(String.format("Deselected by value: %s", value));
    }

    public void deselectByIndex(int index) {
        this.getSelect().deselectByIndex(index);
        log.info(String.format("Deselected by index: %d", index));
    }

    public void deselectByVisibleText(String text) {
        this.getSelect().deselectByVisibleText(text);
        log.info(String.format("Deselect by text: %s", text));
    }

}
