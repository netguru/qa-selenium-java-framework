package ngelements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NGRadio extends NGHtmlElement {

    public WebElement getSelectedButton() {
        return this.getButtons().stream().filter(WebElement::isSelected).findAny().orElseThrow(() ->
                new NoSuchElementException("No selected button"));
    }

    public boolean hasSelectedButton() {
        return this.getButtons().stream().anyMatch(WebElement::isSelected);
    }

    public void selectByValue(String value) {
        WebElement matchingButton = this.getButtons().stream().filter((b) ->
                value.equals(b.getAttribute("value"))).findFirst().orElseThrow(() ->
                new NoSuchElementException(String.format("Cannot locate radio button with value: %s", value)));
        this.selectButton(matchingButton);
        log.info(String.format("Radio button: %s selected element by value: %s", getName(), value));
    }

    public void selectByIndex(int index) {
        List<WebElement> buttons = this.getButtons();
        if (index >= 0 && index < buttons.size()) {
            this.selectButton(buttons.get(index));
        } else {
            throw new NoSuchElementException(String.format("Cannot locate radio button with index: %d", index));
        }

        log.info(String.format("Radio button: %s selected element by index: %s", getName(), index));

    }

    private void selectButton(WebElement button) {
        if (!button.isSelected()) {
            button.click();
        }

    }

    private List<WebElement> getButtons() {
        String radioName = this.getWrappedElement().getAttribute("name");
        String xpath;
        if (radioName == null) {
            xpath = "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']";
        } else {
            xpath = String.format("self::* | following::input[@type = 'radio' and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']", radioName, radioName);
        }
        return this.getWrappedElement().findElements(By.xpath(xpath));
    }
}
