package Homework_2;

import java.io.File;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        File file = FileVerifier.getFile();
        if(file == null) return;
        TreeMap<String, Integer> words = FileReader.getMapFromFile(file);
        if(words == null) return;
        DataParser.parseMap(words);
    }
}
