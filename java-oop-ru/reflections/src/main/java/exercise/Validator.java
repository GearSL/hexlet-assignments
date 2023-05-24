package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// BEGIN
class Validator {
    public static List<String> validate(Address address) {
        List<String> nonValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();

        for(Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(address);
            } catch (IllegalAccessException e) {
                System.out.println("Error while try to get value from class field" + e);
            }

            for(Annotation annotation : annotations) {
                String annotationName = annotation.annotationType().getSimpleName();

                if (annotationName.equals("NotNull") && fieldValue == null) {
                    nonValidFields.add(field.getName());
                }
            }
        }
        return nonValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> nonValidFields = new LinkedHashMap<>();
        List<String> nullFields = validate(address);
        List<String> minLengthFields = validateMinLength(address);

        for (String record : nullFields) {
            nonValidFields.put(record, new ArrayList<>(Collections.singleton("can not be null")));
        }
        for (String record : minLengthFields) {
            if (nonValidFields.get(record) != null) {
                List<String> tempValue = nonValidFields.get(record);
                tempValue.add("length less than 4");
                nonValidFields.put(record, tempValue);
            } else {
                nonValidFields.put(record, new ArrayList<>(Collections.singleton("length less than 4")));
            }
        }
        return nonValidFields;
    }

    private static List<String> validateMinLength(Address address) {
        List<String> nonValidMinLength = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                String annotationName = annotation.annotationType().getSimpleName();
                if (!annotationName.equals("MinLength")) {
                    continue;
                }
                int minLength = field.getAnnotation(MinLength.class).minLength();
                Object fieldValue = null;

                try {
                    fieldValue = field.get(address);
                } catch (IllegalAccessException e) {
                    System.out.println("Error while try to get value from class field" + e);
                }
                assert fieldValue != null;
                int fieldLength = fieldValue.toString().length();

                if (minLength < fieldLength) {
                    nonValidMinLength.add(field.getName());
                }
            }
        }
        return nonValidMinLength;
    }
}
// END
