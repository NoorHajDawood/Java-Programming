package il.ac.shenkar.assignments;

import java.util.List;

interface IReflectionUtilities {
    public List<String> getNamesOfAllMethods(String className) throws ClassNotFoundException;
    public List<String> getNamesOfAllFields(String className) throws ClassNotFoundException;
    public String getNameOfParentClass(String className) throws ClassNotFoundException;
}