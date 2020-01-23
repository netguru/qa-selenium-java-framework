package ngelements;

import java.util.Arrays;

public class NGTextInput extends NGHtmlElement {

    public void sendVulnerableData(CharSequence... keysToSend) {
        super.sendKeys(keysToSend);
        log.info(String.format("Text was send to element: %s", getName()));
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        super.sendKeys(keysToSend);
        log.info(String.format("Text: %s was send to element: %s", Arrays.toString(keysToSend), getName()));
    }

    @Override
    public void clear() {
        super.clear();
        log.info(String.format("Element: %s was cleared", getName()));
    }
}
