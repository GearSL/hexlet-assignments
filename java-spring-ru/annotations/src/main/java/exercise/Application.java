package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                var methodReturns = method.getReturnType();
                System.out.println("Method " + method.getName() + " returns a value of type " + getTypeName(method.getReturnType().toString()));
            }
        }

        // END
    }

    public static String getTypeName(String fullString) {
        int lastIndex = fullString.lastIndexOf(".");
        return fullString.substring(lastIndex + 1);
    }
}
