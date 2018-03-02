package scale;

import scale.core.ScaleRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class InputOutputScale {

    static List<String> initValues = Arrays.asList("A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public List<ScaleRow> inputOutputRows = new ArrayList<>();

    public InputOutputScale() {
        IntStream.rangeClosed(0, initValues.size() - 1)
                 .forEachOrdered(i -> inputOutputRows.add(new ScaleRow(i, initValues.get(i))));
    }

    public int getNumberForLetter(final String letter) {
        int result = inputOutputRows.stream()
                                    .filter(row -> row.getValue().equals(letter))
                                    .map(ScaleRow::getPosition).findFirst().get();

        System.out.println("\n=================================================================");
        System.out.println("*** INPUT ENCRYPT letter : " + letter + " has index : " + result);

        return result;
    }

    public String getLetterForNumber(final int rowNumber) {
        String result = inputOutputRows.stream()
                                       .filter(row -> row.getPosition() == rowNumber)
                                       .map(ScaleRow::getValue).findFirst().get();

        System.out.println("\n*** OUTPUT ENCRYPT index : " + rowNumber + " corresponds to the letter : " + result);

        return result;
    }

}
