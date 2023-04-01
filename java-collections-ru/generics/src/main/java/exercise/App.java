package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Map<String, String>> books = new ArrayList<>();

        Map<String, String> book1 = new HashMap<>(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book2 = new HashMap<>(
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
        );
        Map<String, String> book3 = new HashMap<>(
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book4 = new HashMap<>(
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
        );
        Map<String, String> book5 = new HashMap<>(
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        //System.out.println(books);

        Map<String, String> where = new HashMap<>(Map.of("author", "Shakespeare", "year", "1611"));
        List<Map<String, String>> result = App.findWhere(books, where);

        //System.out.println(result);

        //System.out.println("Final: " + App.findWhere(books, where));
    }

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
