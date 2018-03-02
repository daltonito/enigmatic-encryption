package rotor;

import rotor.core.Rotor;

import java.util.Arrays;
import java.util.List;

public class RightRotor extends Rotor {

    static List<String> rightInitValues = Arrays.asList("B", "D", "F", "H", "J", "L",
            "C", "P", "R", "T", "X", "V", "Z", "N", "Y", "E", "I", "W", "G", "A", "K", "M", "U", "S", "Q", "O");

    public RightRotor(String offsetLetter) {
        super(rightInitValues, "V", "Right rotor");
        setInitialLetter(offsetLetter);
    }

}
