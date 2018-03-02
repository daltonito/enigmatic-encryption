package rotor;

import rotor.core.Rotor;

import java.util.Arrays;
import java.util.List;

public class CenterRotor extends Rotor {

    static List<String> rightInitValues = Arrays.asList("A", "J", "D", "K", "S", "I",
            "R", "U", "X", "B", "L", "H", "W", "T", "M", "C", "Q", "G", "Z", "N", "P", "Y", "F", "V", "O", "E");

    public CenterRotor(String offsetLetter) {
        super(rightInitValues, "E", "Center rotor");
        setInitialLetter(offsetLetter);
    }

}
