import rotor.Rotor;
import lookup.LookupInputOutput;
import lookup.LookupReflector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Enigma {

    private static final String RIGHT_INITIAL_LETTER = "K";
    private static final String RIGHT_ARROW_LETTER = "V";
    private static final String RIGHT_ROTOR_LABEL = "Right rotor";
    private static final List<String> RIGHT_ROTOR_VALUES = Arrays.asList("B", "D", "F", "H", "J", "L",
            "C", "P", "R", "T", "X", "V", "Z", "N", "Y", "E", "I", "W", "G", "A", "K", "M", "U", "S", "Q", "O");

    private static final String CENTER_INITIAL_LETTER = "C";
    private static final String CENTER_ARROW_LETTER = "E";
    private static final String CENTER_ROTOR_LABEL = "Center rotor";
    private static final List<String> CENTER_ROTOR_VALUES = Arrays.asList("A", "J", "D", "K", "S", "I",
            "R", "U", "X", "B", "L", "H", "W", "T", "M", "C", "Q", "G", "Z", "N", "P", "Y", "F", "V", "O", "E");

    private static final String LEFT_INITIAL_LETTER = "M";
    private static final String LEFT_ARROW_LETTER = "Q";
    private static final String LEFT_ROTOR_LABEL = "Left rotor";
    private static final List<String> LEFT_ROTOR_VALUES = Arrays.asList("E", "K", "M", "F", "L", "G",
            "D", "Q", "V", "Z", "N", "T", "O", "W", "Y", "H", "X", "U", "S", "P", "A", "I", "B", "R", "C", "J");

    private LookupInputOutput lookupInputOutput = new LookupInputOutput();
    private LookupReflector lookupReflector = new LookupReflector();

    private Rotor rightRotor;
    private Rotor centerRotor;
    private Rotor leftRotor;

    private void initEnigma() {
        System.out.println("\n* ( Initializing enigma before encryption... )");
        rightRotor = new Rotor(RIGHT_ROTOR_VALUES, RIGHT_INITIAL_LETTER, RIGHT_ARROW_LETTER, RIGHT_ROTOR_LABEL);
        centerRotor = new Rotor(CENTER_ROTOR_VALUES, CENTER_INITIAL_LETTER, CENTER_ARROW_LETTER, CENTER_ROTOR_LABEL);
        leftRotor = new Rotor(LEFT_ROTOR_VALUES, LEFT_INITIAL_LETTER, LEFT_ARROW_LETTER, LEFT_ROTOR_LABEL);
    }

    private void manageRotorsRotations() {
        if (rightRotor.getCurrentRow().getArrowMarkedRow()) {
            System.out.println("\n* ( Rotating the center rotor because of notch on the right rotor )");
            centerRotor.rotateUp();
        }

        if (centerRotor.getCurrentRow().getArrowMarkedRow()) {
            System.out.println("\n* ( Rotating the center and left rotor because of notch on the center rotor )");
            centerRotor.rotateUp();
            leftRotor.rotateUp();
        }

        System.out.println("\n* ( Rotating the right rotor before beginning )");
        rightRotor.rotateUp();
    }

    private String encryptLetter(int inputLetter) {

        String plainLetter = String.valueOf( (char)inputLetter );

        if (plainLetter.trim().isEmpty()) {
            return plainLetter;
        }

        manageRotorsRotations();

        String encryptedLetter = lookupInputOutput.getLetterForNumber(
                                    rightRotor.getOutputNumberReverse(
                                        centerRotor.getOutputNumberReverse(
                                            leftRotor.getOutputNumberReverse(
                                                lookupReflector.getReflectedRowNumber(
                                            leftRotor.getOutputNumber(
                                        centerRotor.getOutputNumber(
                                    rightRotor.getOutputNumber(
                                 lookupInputOutput.getNumberForLetter(plainLetter)))))))));

        return encryptedLetter;
    }

    public String encryptText(String inputText) {
        // always initialize before encrypting
        initEnigma();
        return inputText.chars()
                        .mapToObj(this::encryptLetter)
                        .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        Enigma enigma = new Enigma();
        System.out.println("\n\nEncrypted string is : " + enigma.encryptText("QMJIDO MZWZJFJR"));
        System.out.println("\n\nEncrypted string is : " + enigma.encryptText("QMJIDO PQDPSRJLCV"));
    }

}