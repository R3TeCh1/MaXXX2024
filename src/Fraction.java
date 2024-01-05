import java.math.BigInteger;

/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 05.01.2024
 */
public final class Fraction extends Number implements Comparable<Fraction> {
  final static Fraction NaN = new Fraction(BigInteger.ZERO, BigInteger.ZERO);
  private BigInteger numerator;
  private BigInteger denominator;

  public Fraction(BigInteger n, BigInteger d) {
    if (d.equals(BigInteger.ZERO)) {
      numerator = BigInteger.ZERO;
      denominator = BigInteger.ONE;
    } else {
      numerator = n;
      denominator = d;

      BigInteger t = n.gcd(d);
      numerator = numerator.divide(t);
      denominator = denominator.divide(t);

      if (denominator.signum() == -1) {
        numerator = numerator.negate();
        denominator = denominator.negate();
      }
    }
  }

  public Fraction(long numerator, long denominator) {
    this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
  }

  public Fraction(String numeratorStr, String denominatorStr) {
    BigInteger numerator = new BigInteger(numeratorStr);
    BigInteger denominator = new BigInteger(denominatorStr);

    if (denominator.equals(BigInteger.ZERO)) {
      this.numerator = BigInteger.ZERO;
      this.denominator = BigInteger.ONE;
    } else {
      this.numerator = numerator;
      this.denominator = denominator;

      BigInteger t = numerator.gcd(denominator);
      this.numerator = this.numerator.divide(t);
      this.denominator = this.denominator.divide(t);

      if (this.denominator.signum() == -1) {
        this.numerator = this.numerator.negate();
        this.denominator = this.denominator.negate();
      }
    }
  }

  public Fraction add(Fraction r) {
    BigInteger commonDenominator = this.denominator.multiply(r.getDenominator());
    BigInteger sumNumerator = this.numerator.multiply(r.getDenominator())
        .add(r.getNumerator().multiply(this.denominator));
    return new Fraction(sumNumerator, commonDenominator);
  }

  public Fraction subtract(Fraction r) {
    BigInteger commonDenominator = this.denominator.multiply(r.getDenominator());
    BigInteger diffNumerator = this.numerator.multiply(r.getDenominator())
        .subtract(r.getNumerator().multiply(this.denominator));
    return new Fraction(diffNumerator, commonDenominator);
  }

  public Fraction multiply(Fraction r) {
    return new Fraction(this.numerator.multiply(r.getNumerator()),
        this.denominator.multiply(r.getDenominator()));
  }

  public Fraction divide(Fraction r) {
    if (r.getNumerator().equals(BigInteger.ZERO)) {
      return NaN;
    }

    return new Fraction(this.numerator.multiply(r.getDenominator()),
        this.denominator.multiply(r.getNumerator()));
  }

  public BigInteger getNumerator() {
    return numerator;
  }

  public BigInteger getDenominator() {
    return denominator;
  }

  public void setNumerator(BigInteger numerator) {
    this.numerator = numerator;
  }

  public void setDenominator(BigInteger denominator) {
    this.denominator = denominator;
  }

  public boolean isInteger() {
    return denominator.equals(BigInteger.ONE);
  }

  @Override
  public int compareTo(Fraction r) {
    return numerator.multiply(r.denominator).compareTo(r.numerator.multiply(denominator));
  }

  @Override
  public String toString() {
    if (this.equals(NaN)) {
      return "NaN";
    } else if (denominator.equals(BigInteger.ONE)) {
      return numerator.toString();
    } else {
      return numerator + "/" + denominator;
    }
  }

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
}

