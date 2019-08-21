package ngelements;

public class NGCheckbox extends NGHtmlElement {

    private void select() {
        if (!this.isSelected()) {
            this.getWrappedElement().click();
            log.info(String.format("Element: %s is checked", getName()));
        } else {
            log.info(String.format("Element: %s was already checked", getName()));
        }
    }

    private void deselect() {
        if (this.isSelected()) {
            this.getWrappedElement().click();
            log.info(String.format("Element: %s is unchecked", getName()));
        } else {
            log.info(String.format("Element: %s was already unchecked", getName()));
        }
    }

    public void setValue(boolean value) {
        if (value) {
            this.select();
        } else {
            this.deselect();
        }
    }
}
