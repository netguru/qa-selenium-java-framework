package ngelements;

public class NGCheckbox extends NGHtmlElement {

    public void select() {
        if (!this.isSelected()) {
            this.getWrappedElement().click();
            log.info("Element: " + getName() + " is checked");
        } else {
            log.info("Element: " + getName() + " was already checked");
        }
    }

    public void deselect() {
        if (this.isSelected()) {
            this.getWrappedElement().click();
            log.info("Element: " + getName() + " is unchecked");
        } else {
            log.info("Element: " + getName() + " was already unchecked");
        }
    }

    public void set(boolean value) {
        if (value) {
            this.select();
        } else {
            this.deselect();
        }
    }
}
