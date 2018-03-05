package rotor;

public class RotorRow {

    private boolean arrowMarkedRow = false;
    private int currentPosition;
    private String leftColumnValue;
    private String rightColumnValue;

    public RotorRow(int currentPosition, String leftColumnValue, String rightColumnValue) {
        this.currentPosition = currentPosition;
        this.leftColumnValue = leftColumnValue;
        this.rightColumnValue = rightColumnValue;
    }

    public boolean getArrowMarkedRow() {
        return arrowMarkedRow;
    }

    public void setArrowMarkedRow(boolean arrowMarkedRow) {
        this.arrowMarkedRow = arrowMarkedRow;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getLeftColumnValue() {
        return leftColumnValue;
    }

    public void setLeftColumnValue(String leftColumnValue) {
        this.leftColumnValue = leftColumnValue;
    }

    public String getRightColumnValue() {
        return rightColumnValue;
    }

    public void setRightColumnValue(String rightColumnValue) {
        this.rightColumnValue = rightColumnValue;
    }
}
