package ngelements;

import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class NGTextInput extends NGHtmlElement {

    @FindBy(xpath = "self::*/../following-sibling::span")
    private NGTextBlock validator;

    public void sendVulnerableData(CharSequence... keysToSend) {
        super.sendKeys(keysToSend);
        log.info("Text was send to element: " + getName());
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        super.sendKeys(keysToSend);
        log.info("Text: " + Arrays.toString(keysToSend) + " was send to element: " + getName());
    }

    @Override
    public void clear() {
        super.clear();
        log.info("Element: " + getName() + " was cleared");
    }

    public NGTextBlock getValidator() {
      return validator;
    }

}
