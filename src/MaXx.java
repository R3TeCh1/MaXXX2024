import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 04.01.2024
 */
public class MaXx {
    private static Fraction[][] board = new Fraction[8][8];
    private static Scanner sc = new Scanner(System.in);
    private static Player p1 = new Player('⚪');
    private static Player p2 = new Player('⚫');
    private static Random random = new Random();

    public static void main(String[] args) {
        initGameBoard();
        System.out.println();
        System.out.println("❤❤❤Wilkommen❤❤❤");
        System.out.println("Spielregeln:\n\tSpieler W -> N, S, O, W und SW" +
                            "\n\tSpieler B -> N, S, O, W und NO");
        System.out.println("—————————————————————————————————————————————————");
        System.out.println("Bitte beliebige Eingabe eingeben zum starten...");
        sc.nextLine();
        start();
    }

    public static void checkWin() {
        System.out.println("==============================");
        System.out.println("Spieler W Punkte: " + p1.getScore());
        System.out.println("Spieler B Punkte: " + p2.getScore());
        System.out.println("==============================");
        System.out.println();
        Player winner = null;
        if (p1.getScore() > 47) {
            winner = p1;
        } else if (p2.getScore() > 47) {
            winner = p2;
        }

        if (winner != null) {
            System.out.println(winner);
            System.out.println("1. Erneut spielen ");
            System.out.println("2. Spiel verlassen");
            int choose = sc.nextInt();
            if (choose == 1) {
                resetGame();
            } else if (choose == 2) {
                System.exit(0);
            }
        }
    }

    public static void resetGame() {
        initGameBoard();
        p1.setPosition(4,4);
        p2.setPosition(3,3);
        System.out.println();
        System.out.println("=======Wilkommen======");
        System.out.println("Spielregeln:\n\tSpieler W -> N, S, O, W und SW" +
            "\n\tSpieler B -> N, S, O, W und NO");
        System.out.println("—————————————————————————————————————————————————");
        System.out.println("Bitte beliebige Eingabe eingeben zum starten...");
        sc.nextLine();
        start();
    }

    private static boolean movePlayer(String choose,Player p) {
        switch (choose){
            case "N":
                return setPlayerCoordination(p,Movements.NORTH);
            case "S":
                return setPlayerCoordination(p,Movements.SOUTH);
            case "O":
                return setPlayerCoordination(p,Movements.EAST);
            case "W":
                return setPlayerCoordination(p,Movements.WEST);
            case "NO":
                if(p.getCharacter() == 'B')
                    return setPlayerCoordination(p,Movements.NORTH_EAST);
                break;
            case "SW":
                if(p.getCharacter() == 'W')
                    return setPlayerCoordination(p,Movements.SOUTH_WEST);
                break;
        }
        return false;
    }

    public static void initGameBoard() {
        p1.setScore(0);
        p2.setScore(0);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    System.out.printf("%-3s%-4s", "", "");
                    System.out.printf("%-3s%-4s", "", "");
                    System.out.printf("%-3s%-4s", "", "");
                } else {
                    board[i][j] = generateFraction();
                }
            }
        }
    }


    public static Fraction generateFraction(){
        BigInteger numerator;
        BigInteger denominator;

        do {
            numerator = BigInteger.valueOf(random.nextInt(989) + 10);
            denominator = BigInteger.valueOf(random.nextInt(989) + 10);
        } while (numerator.divide(denominator).compareTo(BigInteger.ONE) <= 0);

        return new Fraction(numerator, denominator);
    }

    public static void start(){
        boolean whitesTurn = true;
        while (true) {
            printGameBoard();
            checkWin();
            if (whitesTurn) {
                while(true){
                    System.out.print("W's Zug(N Hoch/ S Runter/ O Rechts/ W Links/ SW Rechts-Oben): ");
                    String choose = sc.nextLine();
                    choose = choose.toUpperCase();
                    boolean validMove = movePlayer(choose,p1);
                    if(!validMove){
                        System.out.println("Ungültige Eingabe. Bitte erneut eingeben...");
                    }else {
                        whitesTurn = false;
                        break;
                    }
                }
            }
            printGameBoard();
            checkWin();
            if (whitesTurn == false) {
                while (true){
                    System.out.print("B's Zug(N Hoch/ S Runter/ O Rechts/ W Links/ NO Links-Unten): ");
                    String choose = sc.nextLine();
                    choose = choose.toUpperCase();
                    boolean validMove = movePlayer(choose,p2);
                    if(!validMove){
                        System.out.println("Ungültige Eingabe. Bitte erneut eingeben...");
                    }else {
                        whitesTurn = true;
                        break;
                    }
                }
            }
        }
    }

    public static boolean setPlayerCoordination(Player player, Movements m) {
        boolean playerMoved = false;
            if (m == Movements.NORTH) {
                playerMoved = player.moveUp();
            } else if (m == Movements.EAST) {
                playerMoved = player.moveRight();
            }else if (m == Movements.SOUTH) {
                playerMoved = player.moveDown();
            } else if (m == Movements.WEST) {
                playerMoved = player.moveLeft();
            } else if (m == Movements.NORTH_EAST) {
                playerMoved = player.moveUpRight();
            } else if (m == Movements.SOUTH_WEST) {
                playerMoved = player.moveDownLeft();
            }
        if(playerMoved){
                addScoreToPlayer(player);
                return true;
            }
            return false;
    }

    public static void printGameBoard() {
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == p1.i && j == p1.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else {
                    if (Objects.equals(board[i][j], board[3][3]) || Objects.equals(board[i][j], board[4][4])){
                        System.out.printf("%-3s%-4s", "", "");
                    }else if(board[i][j].getNumerator().intValue() != -1){
                        System.out.printf("%-3s%-4s", board[i][j].getNumerator(), "");
                    } else{
                        System.out.printf("%-3s%-4s", "", "");
                    }
                }
            }
            System.out.println();

            for (int j = 0; j < 8; j++) {
                if (i == p1.i && j == p1.j) {
                    System.out.printf("%-3s%-4s", "⚪", "");
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "⚫", "");
                } else {
                    if (Objects.equals(board[i][j], board[3][3]) || Objects.equals(board[i][j], board[4][4])){
                        System.out.printf("%-3s%-4s", "", "");
                    }else if(board[i][j].getNumerator().intValue() != -1 && board[i][j].getDenominator().intValue() != -1){
                        System.out.printf("%-3s%-4s", "———", "");
                    } else{
                        System.out.printf("%-3s%-4s", "", "");
                    }
                }
            }
            System.out.println();

            for (int j = 0; j < 8; j++) {
                if (i == p1.i && j == p1.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else {
                    if (Objects.equals(board[i][j], board[3][3]) || Objects.equals(board[i][j], board[4][4])){
                        System.out.printf("%-3s%-4s", "", "");
                    }else if(board[i][j].getDenominator().intValue() != -1){
                        System.out.printf("%-3s%-4s", board[i][j].getDenominator(), "");
                    } else{
                        System.out.printf("%-3s%-4s", "", "");
                    }
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void addScoreToPlayer(Player p1 ) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == p1.i && j == p1.j) {
                    if (Objects.equals(board[i][j], board[3][3]) || Objects.equals(board[i][j], board[4][4])){
                        System.out.printf("%-3s%-4s", "", "");
                        System.out.printf("%-3s%-4s", "", "");
                        System.out.printf("%-3s%-4s", "", "");
                    }else if(board[i][j].getNumerator().intValue() != -1 && board[i][j].getDenominator().intValue() != -1) {
                        p1.addScore(board[i][j].intValue());
                        board[i][j].setNumerator(BigInteger.valueOf(-1));
                        board[i][j].setDenominator(BigInteger.valueOf(-1));
                    }
                }
            }
        }
    }
}
