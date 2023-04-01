package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> listOfBooks,
                                                      Map<String, String> whereBooks) {
        List<Map<String, String>> result = new ArrayList<>();

        boolean match;

        for (Map<String, String> book : listOfBooks) {
            match = true;
            for (Map.Entry<String, String> entry : whereBooks.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!book.containsKey(key) || !book.get(key).equals(value)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
