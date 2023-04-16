package exercise;

import java.util.*;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> firstDictionary,
                                              Map<String, Object> secondDictionary) {

        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(firstDictionary.keySet());
        keys.addAll(secondDictionary.keySet());

        for (String key: keys) {
            if (!firstDictionary.containsKey(key)) {
                result.put(key, "added");
            } else if (!secondDictionary.containsKey(key)) {
                result.put(key, "deleted");
            } else if (firstDictionary.get(key).equals(secondDictionary.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        return result;
    }
}
//END
