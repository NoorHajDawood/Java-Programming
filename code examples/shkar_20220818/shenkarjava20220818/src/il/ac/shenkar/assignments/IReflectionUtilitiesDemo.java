package il.ac.shenkar.assignments;

import java.util.List;

public class IReflectionUtilitiesDemo {
    public static void main(String args[]) throws ClassNotFoundException {
        IReflectionUtilities utilities = new DanielCohenReflectionUtilities();
        String className = "java.lang.String";
        List<String> methods = utilities.getNamesOfAllMethods(className);
        for(String name : methods) {
            System.out.println(name);
        }
        List<String> fields = utilities.getNamesOfAllFields(className);
        for(String field : fields) {
            System.out.println(field);
        }
        System.out.println(utilities.getNameOfParentClass(className));
    }
}
