package lookup;

public class LookupRow {

    private int position;
    private String value;

    public LookupRow(int position, String value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
