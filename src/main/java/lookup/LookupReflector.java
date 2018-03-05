package lookup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LookupReflector {

    private static List<String> initValues = Arrays.asList("A", "B", "C", "D", "E", "F",
            "G", "D", "I", "J", "K", "G", "M", "K", "M", "I", "E", "B", "F", "T", "C", "V", "V", "J", "A", "T");

    private List<LookupRow> reflectorRows = new ArrayList<LookupRow>();

    public LookupReflector() {
        IntStream.rangeClosed(0, initValues.size() -1)
                 .forEachOrdered(i -> reflectorRows.add(new LookupRow(i, initValues.get(i))));
    }

    public int getReflectedRowNumber(final int rowNumber) {
        String rowValue = reflectorRows.stream()
                                     .filter(row -> row.getPosition() == rowNumber)
                                     .map(LookupRow::getValue).findFirst().get();

        System.out.println("\n*** Reflector input index : " + rowNumber + " corresponds to the letter : " + rowValue);

        int result = reflectorRows.stream()
                    .filter(row -> row.getPosition() != rowNumber)
                    .filter(row -> row.getValue().equals(rowValue))
                    .mapToInt(LookupRow::getPosition)
                    .findFirst()
                    .getAsInt();

        System.out.println("*** Reflector output index : " + result);

        return result;
    }

}
