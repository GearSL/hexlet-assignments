package exercise;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
//        Address address = new Address(null, "London", "1-st street", "7", "2");
//        List<String> notValidFields = Validator.validate(address);
//        System.out.println(notValidFields); // => [country]

        Address address = new Address("Kazakhstan", "Texas", null, "7", "2");
        Map<String, List<String>> notValidFields = Validator.advancedValidate(address);
        System.out.println(notValidFields); // =>  {country=[length less than 4], street=[can not be null]}
    }
}
