package Homework_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class FileReader {

    public static TreeMap<String, Integer> getMapFromFile(File file){
        TreeMap<String, Integer> words = new TreeMap<>();
        try { // possible exceptions from Scanner(), .hasNext, .next(), .put(), .get()
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[,;:!&?()\"\\/\\-_\\s\\.]+"); //RegExp should have been like this:   ([\.,;:!?&\-_'"\(\)\s])+
            while (scanner.hasNext()) {  //read file + put words into TreeMap while counting number of encounters for each one
                String word = scanner.next();
                word = word.toLowerCase(Locale.ROOT);
                if (words.containsKey(word)) {
                    words.put(word, words.get(word) + 1);
                } else {
                    words.put(word, 1);
                }
            }
        } catch (NullPointerException| FileNotFoundException |IllegalStateException| NoSuchElementException |
                ClassCastException e){
            e.printStackTrace();
            return null;
        }
        return words;
    }
}
