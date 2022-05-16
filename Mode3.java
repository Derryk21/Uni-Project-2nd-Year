import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class is used to implement mode 3 and its methods
 * The final result is a textfile with the longest string without
 * repetition within a given time period.
 */
public class Mode3 {

    static int number = 1;
    static int alphabetsize;
    static String setOfCharacters = "ACGT";
    static String alphabetSymbol = "A";
    static String characters = setOfCharacters.replace(alphabetSymbol, "");
    static Boolean repetition = false;
    static Random random = new Random();
    static int randomInt = random.nextInt(characters.length());
    static char randomChar = characters.charAt(randomInt);
    static int numberfile = fileName();
    static HashMap<Integer, String> storeStrings = new HashMap<Integer,
    String>();
    static FileWriter fw;
    int maxlength;
    String startsymbol = "";
    static int j = 0;

    Boolean aChar = false;
    Boolean cChar = false;
    Boolean gCHar = false;
    Boolean tChar = false;
    static String longestWord = "";
    static String current = "";
    static String current1 = "";
    static String testcurrent = "";
    static long start;

    /**
     * Creates all the necessary values for mode 3
     * It receives all the necessary paraemeters, determines which alphabet
     * symbols to include. It uses an arraylist to add a symbol provided that
     * there is no repetition if the symbol is added.
     * If there is no repetition it adds the string to the arraylist.
     * The longest word without repetition is added to a textfile
     *
     * @param numChar     Number of alphabet symbols used
     * @param startSymbol Starting symbol
     * @param time  Maximum time allowed to generat string
     * @param starttime The system timer is started from main in Repeats.
     */
    public static void hashtest(int numChar, String startSymbol,
                                double time, double starttime) {
        if (time < 0.1) {
            time = 0.6 * time;
        
         } else {
             time = time - 0.07;
            }

        Boolean stop = false;
        Boolean aIncluded = false;
        Boolean cIncluded = false;
        Boolean gIncluded = false;
        Boolean tIncluded = false;

        switch (numChar) {
            case 1:
                aIncluded = true;
                break;
            case 2:
                aIncluded = true;
                cIncluded = true;
                break;
            case 3:
                aIncluded = true;
                cIncluded = true;
                gIncluded = true;
                break;
            case 4:
                aIncluded = true;
                cIncluded = true;
                gIncluded = true;
                tIncluded = true;
                break;
            default:
                break;
        }

        int i = 0;

        String addA = "";
        String addC = "";
        String addG = "";
        String addT = "";

        storeStrings.put(j, startSymbol);
        j++;

        long end = (long) (starttime + time * 1000);

        while (System.currentTimeMillis() < end && !stop) {
            current = storeStrings.get(i);

            i++;
            addA = current + "A";
            addC = current + "C";
            addG = current + "G";
            addT = current + "T";
            if (aIncluded && !Repeats.returnRepetitionStatus(addA)) {
                storeStrings.put(j, addA);
                j++;

            }
            if (cIncluded && !Repeats.returnRepetitionStatus(addC)) {
                storeStrings.put(j, addC);
                j++;

            }
            if (gIncluded && !Repeats.returnRepetitionStatus(addG)) {
                storeStrings.put(j, addG);
                j++;
            }
            if (tIncluded && !Repeats.returnRepetitionStatus(addT)) {
                storeStrings.put(j, addT);
                j++;

            }

            if (i == storeStrings.size()) {
                stop = true;
            }

        }

        try {   // this is for monitoring runtime Exception within the block

            String content = storeStrings.get(j - 1).length() + " - " 
                + storeStrings.get(j - 1); // content to write into the file

            File file = new File("../out/out" + (numberfile)
                + "_opt.txt/"); // here file not created here

            // if file doesnt exists, then create it
            if (!file.exists()) {   // checks whether the file is Exist or not
                file.createNewFile(); // here if file not exist new file created
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); 
            BufferedWriter bw = new BufferedWriter(fw); 
            bw.write(content); 
            bw.close(); 


        } catch (IOException e) { // if any exception occurs it will catch
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Creates the textfile
     * Used in order to create the textfile at the start of the program as
     * to prevent a file not found error with big inputs
     *
     */

    public static void createFile() {
        try { // Open the file in append mode.
            FileWriter fw = new FileWriter("/out/" + (numberfile)
                    + "_opt.txt/", true);

            fw.close();


        } catch (IOException e) {
            // try open the textfile
            e.printStackTrace();
        }
    }

    /**
     * This method is used to obtain the correct number
     * for the increasing values of the out file
     *
     * @return integer value
     */
    public static int fileName() {
        recursiveFind(Paths.get("."), p -> {
            if (p.toFile().getName().toString().matches("out" + "[0-9]*"
                    + "_opt.txt")) {
                number++;

            }
        });
        return number;
    }

    /**
     * This method is used to recursively find a file in a given path
     *
     * @param path filepath
     * @param c    consumer path
     */
    public static void recursiveFind(Path path, Consumer<Path> c) {
        try (DirectoryStream<Path> newDirectoryStream =
                     Files.newDirectoryStream(path)) {
            StreamSupport.stream(newDirectoryStream.spliterator(), false)
                    .peek(p -> {
                        c.accept(p);
                        if (p.toFile()
                                .isDirectory()) {
                            recursiveFind(p, c);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
