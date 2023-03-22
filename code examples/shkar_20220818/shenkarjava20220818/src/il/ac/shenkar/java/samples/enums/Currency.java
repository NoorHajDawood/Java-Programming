package il.ac.shenkar.java.samples.enums;

public enum Currency
{
    USD(3.25),EURO(3.3),GBP(4.2),SGD(2.7);
    private double exchangeRate;
    private Currency(double rate)
    {
        setExchangeRate(rate);
    }
    public void setExchangeRate(double value)
    {
        exchangeRate = value;
    }
    public double convert(double sum, Currency otherCurrency)
    {
        return otherCurrency.exchangeRate * sum / this.exchangeRate;
    }
}