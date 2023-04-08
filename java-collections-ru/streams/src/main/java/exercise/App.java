package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static Long getCountOfFreeEmails(List<String> emailList) {
        return emailList.stream()
                .map(email -> email.substring(email.indexOf("@") + 1))
                .filter(App::checkEmail).count();
    }

    private static boolean checkEmail(String domain) {
        String[] freeEmails = {"gmail.com", "yandex.ru", "hotmail.com"};
        for(String freeEmail : freeEmails) {
            if(freeEmail.equals(domain)) {
                return true;
            }
        }
        return false;
    }
}
// END
