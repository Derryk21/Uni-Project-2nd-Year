import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to obtain the arguments and it contains methods for mode1
 */
public class Repeats {

    private static String textfile;
    private static int Mode;
    static Boolean Error = false;
    static FileWriter fw;

    //method to pass arguments to pattern class

    /**
     * Obtains the argument for mode 1
     *
     * @return String value
     */
    public static String getArgs1() {
        String text = "";
        text = textfile.replaceAll(".in", "");
        return text;
    }

    /**
     * Creates the textfile name
     *
     * @return String value
     */
    public static String getTextName() {
        String text = textfile;
        String temp = "";
        int tempint = 0;

        tempint = text.lastIndexOf("/") + 1;
        // tempint2 = text.lastIndexOf(".")-1;
        temp = text.substring(tempint, text.length());
        temp = temp.replaceAll(".in", "");
        return temp;
    }

    /**
     * Obtains the mode argument
     *
     * @return
     */
    public static Integer getArgs0() {
        return Mode;
    }

    /**
     * Contains error checking functions and receives arguments
     *
     * @param args
     */
    public static void main(String[] args) {

        //check number of arguments
        //|| Sequences.readFileAsString().equals("")
        if (args.length != 2 && args.length != 4) {

            System.err.println("ERROR: invalid number of arguments");
            return;
        }

        try {
            Mode = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) { // handle your exception
            System.err.println("ERROR: invalid argument type");
            return;
        }


        if (Mode < 1 || Mode > 3) {

            System.err.println("ERROR: invalid mode");
            return;
        } else if (Mode == 2 && !args[2].matches("A|C|G|T")) {
            System.err.println("ERROR: invalid alphabet symbol");
            return;
        }


        if (Mode == 1) {
            textfile = args[1];
            Mode1();

        } else if (Mode == 2) {

            Mode2.hashtest(Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
        }

    }

    /**
     * Creates the textfile
     * This method was used in order to create the textfile at the start of the program as
     * to prevent a file not found error with big inputs
     *
     * @return FileWriter
     */
    public static FileWriter getFile() {
        try ( // Open the file in append mode.

              FileWriter fw = new FileWriter("../out/" + getTextName() + "_chk.txt/", true)) {
        } catch (IOException e) {
            // Textfile not found
            e.printStackTrace();
        }

        return fw;
    }

    /**
     * This method is used to create the values for mode 1
     * For loops are used to check the input string for all the alphabet symbols that have repetitions
     * It also contains a function to print none if no repetitions are found
     */
    public static void Mode1() {
        getFile();
        Pattern newPat = new Pattern(Sequences.readFileAsString());
        for (String Rep : Sequences.SequenceReturnerA()) {
            newPat.storeIndexes.clear();
            newPat.search(Rep, 0);
        }
        for (String Rep : Sequences.SequenceReturnerC()) {
            newPat.storeIndexes.clear();
            newPat.search(Rep, 0);
        }

        for (String Rep : Sequences.SequenceReturnerG()) {
            newPat.storeIndexes.clear();
            newPat.search(Rep, 0);
        }

        for (String Rep : Sequences.SequenceReturnerT()) {
            newPat.storeIndexes.clear();
            newPat.search(Rep, 0);
        }
        // prints none if no matches found
        if (newPat.ContainsMatch() == false) {

            try ( // Open the file in append mode.
                  FileWriter fw = new FileWriter("../out/" + getTextName() + "_chk.txt/")) {
                PrintWriter out = new PrintWriter(fw);
                // System.out.println(Repeats.getArgs1());
                // Append the name of ocean to the file
                out.println("None");

                out.close();

            } catch (IOException e) {
                // Try open textfile
                e.printStackTrace();
            }

        }
    }

    /**
     * A boolean value is returned if a given string contains any repetitions
     * For loops are used to check for repetions for each alphabet symbol
     *
     * @param newPattern
     * @return Boolean value
     */
    public static Boolean returnRepetitionStatus(String newPattern) {

        Pattern newPat = new Pattern(newPattern);

        for (String Rep : Sequences.SequenceReturnerAMode2(newPattern)) {
            newPat.storeIndexes.clear();
            newPat.searchMode2(Rep, 0);
        }

        for (String Rep1 : Sequences.SequenceReturnerCMode2(newPattern)) {
            newPat.storeIndexes.clear();
            newPat.searchMode2(Rep1, 0);
        }
        for (String Rep2 : Sequences.SequenceReturnerGMode2(newPattern)) {
            newPat.storeIndexes.clear();
            newPat.searchMode2(Rep2, 0);
        }

        for (String Rep3 : Sequences.SequenceReturnerTMode2(newPattern)) {
            newPat.storeIndexes.clear();
            newPat.searchMode2(Rep3, 0);
        }
        return newPat.ContainsMatch1();
    }

}