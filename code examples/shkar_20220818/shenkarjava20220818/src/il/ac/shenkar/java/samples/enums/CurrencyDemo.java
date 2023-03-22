package il.ac.shenkar.java.samples.enums;

public class CurrencyDemo {
    public static void main(String args[]) {
        Currency.EURO.setExchangeRate(3.3);
        Currency.USD.setExchangeRate(3.25);
        double priceInEuro = 320;
        double priceInUSD = Currency.EURO.convert(priceInEuro,Currency.USD);
        System.out.println("priceInEuro=" + priceInEuro);
        System.out.println("priceInUSD=" + priceInUSD);
    }
}
