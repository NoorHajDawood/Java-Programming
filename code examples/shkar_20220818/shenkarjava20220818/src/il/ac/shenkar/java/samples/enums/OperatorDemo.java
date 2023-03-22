package il.ac.shenkar.java.samples.enums;

public class OperatorDemo {
    public static void main(String args[]) throws OperatorException {
        System.out.println(Operator.PLUS.calculate(3,4));
        System.out.println(Operator.DIVIDE.calculate(3,0));
    }
}
