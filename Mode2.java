import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class is used to implement mode 2 and its methods
 */
public class Mode2 {

    static int number = 1;
    static int alphabetsize;
    int maxlength;
    String startsymbol = "";
    static String setOfCharacters = "ACGT";
    static String alphabetSymbol = "A";
    static String characters = setOfCharacters.replace(alphabetSymbol, "");
    static Boolean repetition = false;
    static Random random = new Random();
    static int randomInt = random.nextInt(characters.length());
    static char randomChar = characters.charAt(randomInt);

    Boolean aChar = false;
    Boolean cChar = false;
    Boolean gCHar = false;
    Boolean tChar = false;

    /**
     * Creates all the necessary values for mode 2
     * It receives all the necessary paraemeters, determines which alphabet 
     * symbols to include. It uses an arraylist to add a symbol provided that there 
     * is no repetition if the symbol is added.
     * If there is no repetition it adds the string and its length to a textfile
     *
     * @param numChar
     * @param startSymbol
     * @param maxLength
     */
    public static void hashtest(int numChar, String startSymbol, int maxLength) {
        int numberfile = fileName();
        Boolean Stop = false;
        Boolean AIncluded = false;
        Boolean CIncluded = false;
        Boolean GIncluded = false;
        Boolean TIncluded = false;

        switch (numChar) {
            case 1:
                AIncluded = true;
                break;
            case 2:
                AIncluded = true;
                CIncluded = true;
                break;
            case 3:
                AIncluded = true;
                CIncluded = true;
                GIncluded = true;
                break;
            case 4:
                AIncluded = true;
                CIncluded = true;
                GIncluded = true;
                TIncluded = true;
                break;
        }

        int i = 0;
        String current = "";
        String addA = "";
        String addC = "";
        String addG = "";
        String addT = "";

        ArrayList<String> set = new ArrayList<>();
        set.add(startSymbol);

        while (Stop != true) {

            current = set.get(i);
            i++;
            //prevent words that are too big from being added
            if (current.length() == maxLength)
                break;

            addA = current + "A";
            addC = current + "C";
            addG = current + "G";
            addT = current + "T";
            if (AIncluded == true && Repeats.returnRepetitionStatus(addA) != true) {
                set.add(addA);

            }
            if (CIncluded == true && Repeats.returnRepetitionStatus(addC) != true) {
                set.add(addC);

            }
            if (GIncluded == true && Repeats.returnRepetitionStatus(addG) != true) {
                set.add(addG);

            }
            if (TIncluded == true && Repeats.returnRepetitionStatus(addT) != true) {
                set.add(addT);

            }

            if (i == set.size()) {

                Stop = true;
            }

        }

        for (int k = 0; k < set.size(); k++) {
            try ( // Open the file in append mode.
                  FileWriter fw = new FileWriter("../out/gen" + (numberfile) + "_bf.txt/", true)) {
                PrintWriter out = new PrintWriter(fw);
                //System.out.println("out/gen"+ (numberfile)  +"_bf.txt/");
                // Append the name of ocean to the file
                out.println(set.get(k).length() + " - " + set.get(k));

                //ContainsMatch1=true;
                // Close the file.
                if (k == set.size())
                    out.close();

            } catch (IOException e) {
                // try open the textfile
                e.printStackTrace();
            }

        }
    }


    /**
     * This method is used to obtain the correct number for the increasing values of the gen file
     *
     * @return integer value
     */
    public static int fileName() {

        //String[] flist = directory.list(filter);
        recursiveFind(Paths.get("."), p -> {
            if (p.toFile().getName().toString().matches("gen" + "[0-9]*" + "_bf.txt")) {
                number++;

            }
        });
        return number;
    }

    /**
     * This method is used to recursively find a file in a given path
     *
     * @param path
     * @param c
     */
    public static void recursiveFind(Path path, Consumer<Path> c) {
        try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path)) {
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
