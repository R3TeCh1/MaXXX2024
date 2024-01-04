/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 04.01.2024
 */
import java.util.Random;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational() {
        Random random = new Random();
        while(true){
            numerator = random.nextInt(989) + 10;
            denominator = random.nextInt(989) + 10;
            if(getFinalNumber()>1){
                break;
            }
        }
    }
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }
    public int getFinalNumber(){
        return getNumerator()/getDenominator();
    }
}
