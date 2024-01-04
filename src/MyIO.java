import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 04.01.2024
 */
public class MyIO {
  private MyIO() {}

  static Scanner sc = new Scanner(System.in);

  public static String promptAndRead(String prompt) {
    System.out.print(prompt);
    return sc.nextLine();
  }

  public static void write(String s) {
    System.out.print(s);
  }

  public static void writeln(String s) {
    System.out.println(s);
  }

  public static boolean readBoolean(String prompt) {
    System.out.print(prompt);
    return Boolean.parseBoolean(sc.nextLine().trim());
  }

  public static byte readByte(String prompt) {
    System.out.print(prompt);
    return Byte.parseByte(sc.nextLine().trim());
  }

  public static short readShort(String prompt) {
    System.out.print(prompt);
    return Short.parseShort(sc.nextLine().trim());
  }

  public static int readInt(String prompt) {
    System.out.print(prompt);
    return Integer.parseInt(sc.nextLine().trim());
  }

  public static long readLong(String prompt) {
    System.out.print(prompt);
    return Long.parseLong(sc.nextLine().trim());
  }

  public static float readFloat(String prompt) {
    System.out.print(prompt);
    return Float.parseFloat(sc.nextLine().trim());
  }

  public static double readDouble(String prompt) {
    System.out.print(prompt);
    return Double.parseDouble(sc.nextLine().trim());
  }

  public static BigInteger readBigInteger(String prompt) {
    System.out.print(prompt);
    return new BigInteger(sc.nextLine().trim());
  }

  public static BigDecimal readBigDecimal(String prompt) {
    System.out.print(prompt);
    return new BigDecimal(sc.nextLine().trim());
  }

  public static Fraction readFraction(String prompt) {
    System.out.print(prompt);
    String input = sc.nextLine().trim();

    boolean hasSlash = false;
    int slashIndex = 0;

    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '/') {
        hasSlash = true;
        slashIndex = i;
        break;
      }
    }

    if (!hasSlash || slashIndex == 0 || slashIndex == input.length() - 1) {
      throw new InputMismatchException("Ungültiges Format für Fraction. Verwenden Sie z.B. 1/2.");
    }

    try {
      int numerator = Integer.parseInt(input.substring(0, slashIndex).trim());
      int denominator = Integer.parseInt(input.substring(slashIndex + 1).trim());

      return new Fraction(numerator, denominator);
    } catch (NumberFormatException e) {
      throw new InputMismatchException("Ungültige Eingabe für Numerator oder Denominator.");
    }
  }
}