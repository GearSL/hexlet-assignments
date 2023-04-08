package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static Long getCountOfFreeEmails(List<String> emailList) {
        List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emailList.stream()
                .map(email -> email.substring(email.indexOf("@") + 1))
                .filter(freeDomains::contains).count();
    }
}
// END
