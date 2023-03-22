package il.ac.shenkar.java.samples.enums;

public class CurrencyValuesDemo {
    public static void main(String args[]) {
        Currency vec[] = Currency.values();
        for(Currency ob : vec)
        {
            System.out.println(ob);
        }
    }
}
