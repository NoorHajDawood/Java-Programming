package il.ac.shenkar.assignments;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        NoorHajDawoodReflectionUtilities noor = new NoorHajDawoodReflectionUtilities();
        List<String> list;
        try {
            // PRINT METHODS OF STRING
            list = noor.getNamesOfAllMethods("java.lang.String");
            System.out.println("## METHODS ##");
            System.out.println(list);

            // PRINT FIELDS OF STRING
            list = noor.getNamesOfAllFields("java.lang.String");
            System.out.println("## FIELDS ##");
            System.out.println(list);

            // PRINT PARENT CLASS OF STRING
            String parentClass = noor.getNameOfParentClass("java.lang.String");
            System.out.println("## PARENT CLASS ##");
            System.out.println(parentClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
