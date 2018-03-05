package rotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;



public class Rotor {

    private static List<String> leftInitValues = Arrays.asList("A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    private List<RotorRow> rotorRows;
    protected String rotorLabel;

    public Rotor(List<String> rightInitValues, String initialLetter, String arrowLetter, String label) {
        rotorLabel = label;
        rotorRows = new ArrayList<>();

        IntStream.rangeClosed(0, leftInitValues.size() - 1)
                 .forEachOrdered(i -> {
                    RotorRow row = new RotorRow(i, leftInitValues.get(i), rightInitValues.get(i));
                    row.setArrowMarkedRow(leftInitValues.get(i).equals(arrowLetter));
                    rotorRows.add(row);
                 });

        setInitialLetter(initialLetter);
    }

    public String rotateUp() {
        // rotate the whole rotor row (circular rotation)
        rotorRows.forEach(rotorRow -> rotorRow.setCurrentPosition((rotorRow.getCurrentPosition() + (26 - 1)) % 26));

        String result = rotorRows.stream()
                                 .filter(rotorRow -> rotorRow.getCurrentPosition() == 0)
                                 .findFirst()
                                 .get()
                                 .getLeftColumnValue();

        System.out.println("= Rotate up: " + rotorLabel + " / current letter: " + result);

        return result;
    }

    private void setInitialLetter(String offsetLetter) {
        while (!rotateUp().equals(offsetLetter));
    }

    public int getOutputNumber(final int rowNumber) {
        String rowValue = rotorRows.stream()
                                   .filter(row -> row.getCurrentPosition() == rowNumber)
                                   .map(RotorRow::getRightColumnValue).findFirst().get();

        int result = rotorRows.stream()
                              .filter(row -> row.getLeftColumnValue().equals(rowValue))
                              .mapToInt(RotorRow::getCurrentPosition)
                              .findFirst()
                              .getAsInt();

        System.out.println("\n<<<--- " + rotorLabel + ": ");
        System.out.println("<-- Input index: " + rowNumber + ", letter : " + rowValue + ", output index : " + result);

        return result;
    }

    public int getOutputNumberReverse(final int rowNumber) {
        String rowValue = rotorRows.stream()
                                   .filter(row -> row.getCurrentPosition() == rowNumber)
                                   .map(RotorRow::getLeftColumnValue).findFirst().get();

        int result = rotorRows.stream()
                              .filter(row -> row.getRightColumnValue().equals(rowValue))
                              .mapToInt(RotorRow::getCurrentPosition)
                              .findFirst()
                              .getAsInt();

        System.out.println("\n--->>> " + rotorLabel + ": ");
        System.out.println("--> Input index: " + rowNumber + ", letter : " + rowValue + ", output index : " + result);

        return result;
    }

    public RotorRow getCurrentRow() {
        return rotorRows.stream()
                        .filter(rotorRow -> rotorRow.getCurrentPosition() == 0)
                        .findFirst()
                        .get();
    }

    public List<RotorRow> getRotorRows() {
        return rotorRows;
    }

    public void setRotorRows(List<RotorRow> rotorRows) {
        this.rotorRows = rotorRows;
    }

}
