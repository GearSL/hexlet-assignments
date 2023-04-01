package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();

        if (sentence.isEmpty()) {
            return result;
        }

        String[] words = sentence.split(" ");

        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }

    public static String toString(Map<String, Integer> stringCollection) {

        if (stringCollection.isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        for (Map.Entry<String, Integer> entry : stringCollection.entrySet()) {
            stringBuilder.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
//END
