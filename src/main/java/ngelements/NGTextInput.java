package ngelements;

public class NGTextInput extends NGHtmlElement {
    //TODO function for sending protected data

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        super.sendKeys(keysToSend);
        log.info("Text: " + keysToSend + " was send to element: " + getName());
    }

    @Override
    public void clear() {
        super.clear();
        log.info("Element: " + getName() + " was cleared");
    }
}
