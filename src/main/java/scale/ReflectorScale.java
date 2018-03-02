package scale;

import scale.core.ScaleRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ReflectorScale {

    static List<String> initValues = Arrays.asList("A", "B", "C", "D", "E", "F",
            "G", "D", "I", "J", "K", "G", "M", "K", "M", "I", "E", "B", "F", "T", "C", "V", "V", "J", "A", "T");

    public List<ScaleRow> reflectorList = new ArrayList<ScaleRow>();

    public ReflectorScale() {
        IntStream.rangeClosed(0, 25)
                 .forEachOrdered(i -> reflectorList.add(new ScaleRow(i, initValues.get(i))));
    }

    public int getReflectedRowNumber(final int rowNumber) {
        String rowValue = reflectorList.stream()
                                     .filter(row -> row.getPosition() == rowNumber)
                                     .map(ScaleRow::getValue).findFirst().get();

        System.out.println("\n*** Reflector input index : " + rowNumber + " corresponds to the letter : " + rowValue);

        int result = reflectorList.stream()
                    .filter(row -> row.getPosition() != rowNumber)
                    .filter(row -> row.getValue().equals(rowValue))
                    .mapToInt(ScaleRow::getPosition)
                    .findFirst()
                    .getAsInt();

        System.out.println("*** Reflector output index : " + result);

        return result;
    }

}
