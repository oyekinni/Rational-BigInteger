import java.math.BigInteger;

public class Rational extends Number implements Comparable<Rational> {
    private BigInteger numerator;
    private BigInteger denominator;

    // Constructors
    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        // Reduce fraction
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);

        // Keep denominator positive
        if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
            this.numerator = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    // Getters
    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    // Arithmetic methods
    public Rational add(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.denominator)
                .add(denominator.multiply(secondRational.numerator));
        BigInteger d = denominator.multiply(secondRational.denominator);
        return new Rational(n, d);
    }

    public Rational subtract(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.denominator)
                .subtract(denominator.multiply(secondRational.numerator));
        BigInteger d = denominator.multiply(secondRational.denominator);
        return new Rational(n, d);
    }

    public Rational multiply(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.numerator);
        BigInteger d = denominator.multiply(secondRational.denominator);
        return new Rational(n, d);
    }

    public Rational divide(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.denominator);
        BigInteger d = denominator.multiply(secondRational.numerator);
        return new Rational(n, d);
    }

    // Override Number methods
    @Override
    public int intValue() {
        return numerator.divide(denominator).intValue();
    }

    @Override
    public long longValue() {
        return numerator.divide(denominator).longValue();
    }

    @Override
    public float floatValue() {
        return numerator.floatValue() / denominator.floatValue();
    }

    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    // Compare
    @Override
    public int compareTo(Rational o) {
        BigInteger diff = numerator.multiply(o.denominator)
                .subtract(o.numerator.multiply(denominator));
        return diff.compareTo(BigInteger.ZERO);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator + "";
        }
        return numerator + "/" + denominator;
    }
}
