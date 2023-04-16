package exercise;

import java.util.*;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> firstDictionary, Map<String, Object> secondDictionary) {
        Map<String, String> result = new LinkedHashMap<>();

        for (String key : firstDictionary.keySet()) {
            if (secondDictionary.containsKey(key)) {
                if (firstDictionary.get(key).equals(secondDictionary.get(key))) {
                    result.put(key, "unchanged");
                } else {
                    result.put(key, "changed");
                }
            } else {
                result.put(key, "deleted");
            }
        }
        for (String key : secondDictionary.keySet()) {
            if (!firstDictionary.containsKey(key)) {
                result.put(key, "added");
            }
        }
        return result;
    }
}
//END
