package exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
class App {
    public static void main(String[] args) {
        scrabble("jvayu", "java");
    }

    public static boolean scrabble(String symbols, String word) {
        List<String> symbolsList = new ArrayList<>(Arrays.asList(symbols.toLowerCase().split("")));
        List<String> wordCharsList = new LinkedList<>(Arrays.asList(word.toLowerCase().split("")));

        for (String wordChar : wordCharsList) {
            if (symbolsList.contains(wordChar)) {
                symbolsList.remove(wordChar);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
