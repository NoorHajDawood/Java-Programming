package il.ac.shenkar.java.fraction;

public class Fraction {
    private int nominator;
    private int denominator;

    public Fraction(int nominator, int denominator) throws FractionException {
        setNominator(nominator);
        setDenominator(denominator);
    }

    public Fraction divideBy(Fraction other) throws FractionException {
        return new Fraction(this.nominator * other.denominator,
                this.denominator * other.nominator);
    }

    public int getNominator() {
        return nominator;
    }

    public void setNominator(int nominator) {
        this.nominator = nominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) throws FractionException {
        if(denominator == 0) {
            throw new FractionException("denominator cannot be zero");
        }
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "nominator=" + nominator +
                ", denominator=" + denominator +
                '}';
    }
}
