package Homework_2;

import java.util.Map;
import java.util.TreeMap;

public class DataParser {
    public static int parseMap(TreeMap<String, Integer> map){
        if(map == null) return 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {//print out sorted list of words + number of encounters
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        int maxEncounters = -1;
        String mostUsedWord = "";
        for (Map.Entry<String, Integer> item : map.entrySet()) {//accumulate into mostUsedWord all words that have most encounters
            if (item.getValue() == maxEncounters) {
                try { // possible exceptions from .join
                    mostUsedWord = String.join(", ", mostUsedWord, item.getKey());
                } catch (NullPointerException e){
                    e.printStackTrace();
                    return 1;
                }
            } else if (item.getValue() > maxEncounters) {
                maxEncounters = item.getValue();
                mostUsedWord = item.getKey();
            }
        }
        System.out.println("Mostly used: " + mostUsedWord + ". Number of encounters: " + maxEncounters);
        return 0;
    }
}
