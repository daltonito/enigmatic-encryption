import scale.InputOutputScale;
import scale.ReflectorScale;
import rotor.CenterRotor;
import rotor.LeftRotor;
import rotor.RightRotor;

import java.util.stream.Collectors;

public class Enigma {

    public static RightRotor rightRotor = new RightRotor("K");
    static CenterRotor centerRotor = new CenterRotor("C");
    static LeftRotor leftRotor = new LeftRotor("M");

    static InputOutputScale inputOutputScale = new InputOutputScale();
    static ReflectorScale reflectorScale = new ReflectorScale();

    public static String encryptLetterFirst(String inputLetter) {

        if (inputLetter.trim().isEmpty()) {
            return inputLetter;
        }

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

        int firstTransitionPosition = inputOutputScale.getNumberForLetter(inputLetter);
        int secondTransitionPosition = rightRotor.getOutputNumber(firstTransitionPosition);
        int thirdTransitionPosition = centerRotor.getOutputNumber(secondTransitionPosition);
        int fourthTransitionPosition = leftRotor.getOutputNumber(thirdTransitionPosition);

        int reflectedNumber = reflectorScale.getReflectedRowNumber(fourthTransitionPosition);

        fourthTransitionPosition = leftRotor.getOutputNumberReverse(reflectedNumber);
        thirdTransitionPosition = centerRotor.getOutputNumberReverse(fourthTransitionPosition);
        secondTransitionPosition = rightRotor.getOutputNumberReverse(thirdTransitionPosition);

        String encryptedLetter = inputOutputScale.getLetterForNumber(secondTransitionPosition);

        return encryptedLetter;
    }

    public static String encryptText(String inputText) {
        return inputText.chars()
                        .mapToObj(x -> encryptLetterFirst(String.valueOf((char) x)))
                        .collect(Collectors.toList())
                        .stream()
                        .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        System.out.println("\n\nEncrypted string is : " + encryptText("QMJIDO MZWZJFJR"));
    }

}
