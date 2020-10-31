import java.lang.reflect.Method;
class MethodFinder {

    public static String findMethod(String methodName, String[] classNames) throws ClassNotFoundException {
        boolean isMatch = false;
        String className = "";
        for (String name : classNames) {
            Method[] methods = Class.forName(name).getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    isMatch = true;
                    className = name;
                    break;
                }
            }
            if (isMatch) {
                break;
            }
        }
        return className;

    }
}