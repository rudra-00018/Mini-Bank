package util;

import model.annotation.Positive;

import java.lang.reflect.Field;
import java.util.ArrayList;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        ArrayList<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(MaxLength.class)) {

                field.setAccessible(true);

                try {

                    String value = (String) field.get(obj);

                    MaxLength maxLength =
                            field.getAnnotation(MaxLength.class);

                    if (value.length() > maxLength.value()) {

                        errors.add(
                                field.getName() + " is too long"
                        );
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        return errors.toArray(new String[0]);
    }
}