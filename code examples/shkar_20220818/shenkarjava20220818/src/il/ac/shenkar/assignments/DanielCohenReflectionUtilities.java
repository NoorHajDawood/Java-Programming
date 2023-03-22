package il.ac.shenkar.assignments;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class DanielCohenReflectionUtilities implements IReflectionUtilities {
    @Override
    public List<String> getNamesOfAllMethods(String className) throws ClassNotFoundException {
        //throw new UnsupportedOperationException();
        Method[] methods = Class.forName(className).getDeclaredMethods();
        List<String> list = new LinkedList<>();
        for(Method method : methods) {
            list.add(method.getName());
        }
        return list;
    }

    @Override
    public List<String> getNamesOfAllFields(String className) throws ClassNotFoundException {
        Field[] fields = Class.forName(className).getDeclaredFields();
        List<String> list = new LinkedList<>();
        for(Field field : fields) {
            list.add(field.getName());
        }
        return list;
    }

    @Override
    public String getNameOfParentClass(String className) throws ClassNotFoundException {
        return Class.forName(className).getSuperclass().getName();
    }
}
