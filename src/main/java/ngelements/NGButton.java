package ngelements;

public class NGButton extends NGHtmlElement {

    @Override
    public void click() {
        this.getWrappedElement().click();
        log.info("NGButton: " + this.getName() + " was clicked");
    }
}

