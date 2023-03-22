package il.ac.shenkar.java.fraction;

public class FractionDemo {
    public static void main(String[] args) {
        try {
            Fraction frac1 = new Fraction(2,5);
            Fraction frac2 = new Fraction(1, 2);
            Fraction frac3 = frac1.divideBy(frac2);
            System.out.println(frac3);

            frac2.setNominator(0);
            frac3= frac1.divideBy(frac2);
            System.out.println(frac3);
        } catch (FractionException e) {
            e.printStackTrace();
        }
    }
}
