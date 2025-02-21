public class Element {
    private String value;
    private boolean isVisible;

    public Element(String value) {
        this.value = value;
        this.isVisible = false;
    }
    public String getValue() {
        return value;
    }
    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
