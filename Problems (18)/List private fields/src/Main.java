import java.util.*;
import java.lang.reflect.*;

/**
 Get sorted list of private fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        // Add implementation here
        List<String> allFields = new ArrayList<>();
        Class itemClass = object.getClass();
        Field[] fields = itemClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                allFields.add(field.getName());
            }
        }
        Collections.sort(allFields);
        return allFields;
    }

}