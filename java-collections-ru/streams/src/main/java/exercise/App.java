package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static Long getCountOfFreeEmails(List emailList) {

        return emailList.stream()
                .map(email -> email.toString().substring(email.toString().indexOf("@") + 1))
                .filter(email -> checkEmail(email.toString()))
                .count();
    }

    private static boolean checkEmail(String domen) {
        String[] freeEmails = {"gmail.com", "yandex.ru", "hotmail.com"};
        for(String freeEmail : freeEmails) {
            if(freeEmail.equals(domen)) {
                return true;
            }
        }
        return false;
    }
}
// END
