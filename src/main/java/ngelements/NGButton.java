package ngelements;

public class NGButton extends NGHtmlElement {

    @Override
    public void click() {
        this.getWrappedElement().click();
        log.info(String.format("NGButton: %s was clicked", this.getName()));
    }
}

