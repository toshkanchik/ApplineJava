package Homework_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String currPath = currentRelativePath.toAbsolutePath().toString();
        System.out.print("Current working path is: " + currPath + "\nEnter full or relative InputFilePath: ");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        File enteredPathFile = null;
        try{
            while (true){
                enteredPathFile = new File(currPath + "//" + filePath);
                if(enteredPathFile.exists()){
                    break;
                }
                else {
                    enteredPathFile = new File(filePath);
                    if(enteredPathFile.exists()){
                        break;
                    }
                }
                System.out.print("No such file. Enter full or relative InputFilePath: ");
                filePath = sc.nextLine();
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(enteredPathFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            scanner.close();
            return;
        }
        scanner.useDelimiter("[,;:!&?()\"\\/\\-_\\s\\.]+"); //RegExp should have been like this:   ([\.,;:!?&\-_'"\(\)\s])+
        TreeMap<String, Integer> words = new TreeMap<>();
        while (scanner.hasNext()){  //read file + put words into TreeMap while counting number of encounters for each one
            String word = scanner.next();
            word = word.toLowerCase(Locale.ROOT);
            if(words.containsKey(word)){
                words.put(word, words.get(word) + 1);
            }
            else{
                words.put(word, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : words.entrySet()) {//print out sorted list of words + number of encounters
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        int maxEncounters = -1;
        String mostUsedWord = "";
        for (Map.Entry<String, Integer> item : words.entrySet()) {//accumulate into mostUsedWord all words that have most encounters
            if (item.getValue() == maxEncounters) {
                mostUsedWord = String.join(", ", mostUsedWord, item.getKey());
            } else if (item.getValue() > maxEncounters) {
                maxEncounters = item.getValue();
                mostUsedWord = item.getKey();
            }
        }
        System.out.println("Mostly used: " + mostUsedWord + ". Number of encounters: " + maxEncounters);
    }
}
