package rotor;

import rotor.core.Rotor;

import java.util.Arrays;
import java.util.List;

public class LeftRotor extends Rotor {

    static List<String> rightInitValues = Arrays.asList("E", "K", "M", "F", "L", "G",
            "D", "Q", "V", "Z", "N", "T", "O", "W", "Y", "H", "X", "U", "S", "P", "A", "I", "B", "R", "C", "J");

    public LeftRotor(String offsetLetter) {
        super(rightInitValues, "Q", "Left rotor");
        setInitialLetter(offsetLetter);
    }

}
