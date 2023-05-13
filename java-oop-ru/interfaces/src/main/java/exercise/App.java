package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static void main(String[] args) {
        CharSequence text = new ReversedSequence("abcdef");
        System.out.println(text.toString()); // "fedcba"
        System.out.println(text.charAt(1)); // 'e'
        System.out.println(text.length()); // 6
        System.out.println(text.subSequence(1, 4).toString()); // "edc"
    }

    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream().sorted(Home::compareTo).limit(n).map(Object::toString).collect(Collectors.toList());
    }
}
// END
