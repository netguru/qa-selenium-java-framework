package base.api;

public enum Paths {
    PEOPLE("people/%d");

    String path;

    Paths(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
