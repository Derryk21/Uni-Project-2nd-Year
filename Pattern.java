import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class contains all the methods used to identify repetitions within the given
 * input for mode 1 and 2
 */
public class Pattern {
    ArrayList<Integer> storeIndexes = new ArrayList<Integer>();

    private ArrayList<Integer> returnAlphabetArrayList = new ArrayList<Integer>();

    /**
     * Returns an arraylist that contains all the strings in the xyx form for a given symbol
     *
     * @return Arraylist
     */
    public ArrayList<Integer> getReturnAlphabetArrayList() {
        return this.returnAlphabetArrayList;
    }

    /**
     * Used to determine if a input contains any repetitions for mode 1
     *
     * @return boolean value
     */
    public Boolean ContainsMatch() {
        return ContainsMatch;
    }

    /**
     * Used to determine if a input contains any repetitions for mode 2
     *
     * @return boolean value
     */
    public Boolean ContainsMatch1() {
        return ContainsMatch1;
    }

    /**
     * Used to access the alphabet arraylist
     *
     * @param returnAlphabetArrayList
     */
    public void setReturnAlphabetArrayList(ArrayList<Integer> returnAlphabetArrayList) {
        this.returnAlphabetArrayList = returnAlphabetArrayList;
    }

    Boolean ContainsMatch = false;
    Boolean ContainsMatch1 = false;
    private String txt;

    /**
     * Used to initalize the constructor
     *
     * @param txt
     */
    Pattern(String txt) {
        this.txt = txt;

    }

    /**
     * This method searches the input string in mode 1 for potential repetiions from a given parameter
     * It makes use of the Boyer-Moore algorithm to search for strings
     *
     * @param pattern
     * @param start
     */
    public void search(String pattern, int start) { // Search for pattern in txt.
        //Array to store int values 

        int[] right;
        String pat = pattern;

        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < M; j++)
            right[pat.charAt(j)] = j;

        int N = txt.length();
        int skip;

        // Does the pattern match the text at position i ?
        for (int i = start; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0) {
                storeIndexes.add(i);

                search(pat, i + 1);
                return;

            }
        }
        //necessary to add values to textfile
        printvalues(pat);
        return;

    }

    /**
     * This method searches for a string within a given string for mode 2
     * It makes use of the Boyer-Moore algorithm to search for strings
     *
     * @param pattern
     * @param start
     */
    public void searchMode2(String pattern, int start) { // Search for pattern in txt.
        //Array to store int values 

        int[] right;
        String pat = pattern;

        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < M; j++)
            right[pat.charAt(j)] = j;

        int N = txt.length();
        int skip;

        // Does the pattern match the text at position i ?
        for (int i = start; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0) {
                storeIndexes.add(i);

                searchMode2(pat, i + 1);
                return;

            }
        }
        //necessary to add values to textfile
        printvaluesMode2(pat);
        return;

    }

    /**
     * If repetitions are found they are printed to the textfile with this method
     * for mode 1
     *
     * @param RepetitionString
     */
    public void printvalues(String RepetitionString) {

        for (int i = 0; i < storeIndexes.size() - 1; i++) {
            for (int j = i + 1; j < storeIndexes.size(); j++) {
                try ( // Open the file in append mode.

                      FileWriter fw = new FileWriter("../out/" + Repeats.getTextName() + "_chk.txt/", true)) {
                    PrintWriter out = new PrintWriter(fw);

                    out.println(RepetitionString + " " + storeIndexes.get(i) + " " + storeIndexes.get(j));
                    ContainsMatch = true;

                    // Close the file.
                    if (i == storeIndexes.size() - 2)
                        out.close();

                } catch (IOException e) {
                    // Textfile not found
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * This method is used to determine whether any repetitions are found in mode 2
     *
     * @param RepetitionString
     */
    public void printvaluesMode2(String RepetitionString) {
        if (storeIndexes.size() > 1)
            ContainsMatch1 = true;

    }

    /**
     * This method is used to find all the indexes of an alphabet symbol and add them to an arraylist
     *
     * @param pattern
     * @param start
     */
    public void searchAlphabet(String pattern, int start) { // Search for pattern in txt.
        //Array to store int values 
        int N = txt.length();

        int[] right;
        String pat = pattern;

        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < M; j++)
            right[pat.charAt(j)] = j;

        int skip;
        for (int i = start; i <= N - M; i += skip) { // Does the pattern match the text at position i ?
            skip = 0;
            for (int j = M - 1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0) {
                returnAlphabetArrayList.add(i);
                //System.out.println(printvalues());
                //System.out.println("String found at position "+ i +" " + (i+M-1));
                searchAlphabet(pat, i + 1);
                return;

            }
        }

        return;

    }
}
