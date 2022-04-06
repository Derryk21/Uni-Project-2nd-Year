import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.nio.file.Files;

/**
 * This class is used for mode 1 and 2 to find strings in the form of xyx for the alphabet
 * symbols and adds the xyx strings to a hashset for mode 2
 */
public class Sequences {
    static String AlphabetA = "A";
    static String AlphabetC = "C";
    static String AlphabetG = "G";
    static String AlphabetT = "T";

    Integer Mode = Repeats.getArgs0();
    String textfile = Repeats.getArgs1() + "_chk";

    ArrayList<String> storeASequences = new ArrayList<String>();
    ArrayList<String> storeCSequences = new ArrayList<String>();
    ArrayList<String> storeGSequences = new ArrayList<String>();
    ArrayList<String> storeTSequences = new ArrayList<String>();

    /**
     * This method reads in the file for mode 1 and retuns a string
     *
     * @return string value
     */
    public static String readFileAsString() {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(Repeats.getArgs1() + ".in")));
        } catch (IOException e) {

            System.err.println("ERROR: invalid or missing file");
            System.exit(0);

        }

        return text;
    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 1
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol A
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerA() {
        Pattern newPattern = new Pattern(readFileAsString());
        newPattern.searchAlphabet(AlphabetA, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 1
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol C
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerC() {
        Pattern newPattern = new Pattern(readFileAsString());
        newPattern.searchAlphabet(AlphabetC, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 1
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol G
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerG() {
        Pattern newPattern = new Pattern(readFileAsString());
        newPattern.searchAlphabet(AlphabetG, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 1
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol T
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerT() {
        Pattern newPattern = new Pattern(readFileAsString());
        newPattern.searchAlphabet(AlphabetT, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 2
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol A
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerAMode2(String pattern) {
        Pattern newPattern = new Pattern(pattern);
        newPattern.searchAlphabet(AlphabetA, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 2
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol C
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerCMode2(String pattern) {
        Pattern newPattern = new Pattern(pattern);
        newPattern.searchAlphabet(AlphabetC, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 2
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol G
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerGMode2(String pattern) {
        Pattern newPattern = new Pattern(pattern);
        newPattern.searchAlphabet(AlphabetG, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Creates an object used to search for xyx repetiions for an alphabet symbol in mode 2
     * This method calls on the pattern class and provides the input string and calls the
     * method in that class to search for xyx repetitions for alphabet symbol T
     *
     * @return arraylist
     */
    public static ArrayList<Integer> AlphabetReturnerTMode2(String pattern) {
        Pattern newPattern = new Pattern(pattern);
        newPattern.searchAlphabet(AlphabetT, 0);
        return newPattern.getReturnAlphabetArrayList();

    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 1
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerA() {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerA().size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerA().size(); j++) {
                String temp = readFileAsString().substring(AlphabetReturnerA().get(i), AlphabetReturnerA().get(j) + 1);

                ReturnRep.add(temp);
                //check for duplicates

            }
        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 1
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerC() {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerC().size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerC().size(); j++) {
                String temp = readFileAsString().substring(AlphabetReturnerC().get(i), AlphabetReturnerC().get(j) + 1);

                ReturnRep.add(temp);
            }
        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 1
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerG() {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerG().size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerG().size(); j++) {
                String temp = readFileAsString().substring(AlphabetReturnerG().get(i), AlphabetReturnerG().get(j) + 1);

                ReturnRep.add(temp);
                // System.out.println(returnAlphabetArrayList.get(i) + "  "+ returnAlphabetArrayList.get(j)) ;
            }

        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 1
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerT() {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerT().size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerT().size(); j++) {
                String temp = readFileAsString().substring(AlphabetReturnerT().get(i), AlphabetReturnerT().get(j) + 1);

                ReturnRep.add(temp);
            }
        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 2
     * Its use for mode 2 is to determine if there are repetitons in a created string
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerAMode2(String tempo) {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerAMode2(tempo).size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerAMode2(tempo).size(); j++) {
                String temp = tempo.substring(AlphabetReturnerAMode2(tempo).get(i), AlphabetReturnerAMode2(tempo).get(j) + 1);

                ReturnRep.add(temp);
                //check for duplicates

            }
        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 2
     * Its use for mode 2 is to determine if there are repetitons in a created string
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerCMode2(String tempo) {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerCMode2(tempo).size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerCMode2(tempo).size(); j++) {
                String temp = tempo.substring(AlphabetReturnerCMode2(tempo).get(i), AlphabetReturnerCMode2(tempo).get(j) + 1);

                ReturnRep.add(temp);
            }
        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 2
     * Its use for mode 2 is to determine if there are repetitons in a created string
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerGMode2(String tempo) {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerGMode2(tempo).size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerGMode2(tempo).size(); j++) {
                String temp = tempo.substring(AlphabetReturnerGMode2(tempo).get(i), AlphabetReturnerGMode2(tempo).get(j) + 1);

                ReturnRep.add(temp);
            }

        }
        return ReturnRep;
    }

    /**
     * Strings in the form xyx are created for the given symbol for mode 2
     * Its use for mode 2 is to determine if there are repetitons in a created string
     * An arraylist of integer values is used for the indexes of a alphabet symbol.
     * These indexes are then used in 2 for loops to create all possible xyx strings of the alphabet
     * symbol and added to a hashset.
     *
     * @return hashset
     */
    public static Set<String> SequenceReturnerTMode2(String tempo) {
        Set<String> ReturnRep = new HashSet<>();

        for (int i = 0; i < AlphabetReturnerTMode2(tempo).size() - 1; i++) {
            for (int j = i + 1; j < AlphabetReturnerTMode2(tempo).size(); j++) {
                String temp = tempo.substring(AlphabetReturnerTMode2(tempo).get(i), AlphabetReturnerTMode2(tempo).get(j) + 1);

                ReturnRep.add(temp);
            }
        }
        return ReturnRep;
    }

}