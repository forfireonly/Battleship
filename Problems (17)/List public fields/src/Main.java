/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
import java.util.List;
import java.util.ArrayList;
class FieldGetter {

    public String[] getPublicFields(Object object) {
        // Add implementation here
        List<String> list = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers())) {
                list.add(field.getName());
            }
        }
        return  list.toArray(new String[0]);
    }
}