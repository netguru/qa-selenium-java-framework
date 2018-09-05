package ngelements;

import java.util.Arrays;

public class NGTextInput extends NGHtmlElement {

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
}
