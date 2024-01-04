import java.util.Random;
import java.util.Scanner;

/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 04.01.2024
 */
public class MaXx {
    private static Rational[][] board = new Rational[8][8];
    private static Scanner sc = new Scanner(System.in);
    private static Player p1 = new Player('W');
    private static Player p2 = new Player('B');

    public static void main(String[] args) {
        initGameBoard();
        boolean whitesTurn = true;
        System.out.println("=======Wilkommen======");
        System.out.println("Spielregeln:\n\tSpieler W -> N, S, O, W und NO" +
                            "\n\tSpieler B -> N, S, O, W und SW");
        System.out.println("---------------------------------");
        System.out.println("Bitte beliebige Eingabe eingeben zum starten...");
        sc.nextLine();
        while (true) {
            printGameBoard();
            System.out.println("==============================");
            System.out.println("Spieler W Punkte: " + p1.getScore());
            System.out.println("Spieler B Punkte: " + p2.getScore());
            System.out.println("==============================");
            if (whitesTurn) {
                while(true){
                    System.out.print("W's Zug(N Hoch/ S Runter/ O Rechts/ W Links/ NO Rechts-Oben): ");
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
            checkWin();
            printGameBoard();
            System.out.println("==============================");
            System.out.println("Spieler W Punkte: " + p1.getScore());
            System.out.println("Spieler B Punkte: " + p2.getScore());
            System.out.println("==============================");
            if (whitesTurn == false) {
                while (true){
                    System.out.print("B's Zug(N Hoch/ S Runter/ O Rechts/ W Links/ SW Links-Unten): ");
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
            checkWin();
        }
    }

    public static void checkWin() {
        Player winner = null;
        if (p1.getScore() > 53) {
            winner = p1;
        } else if (p2.getScore() > 53) {
            winner = p2;
        }

        if (winner != null) {
            System.out.println(winner);
            System.out.println("1. Erneut spielen ");
            System.out.println("2. Spiel verlassen");
            int choose = sc.nextInt();
            if (choose == 1) {
                initGameBoard();
            } else if (choose == 2) {
                System.exit(0);
            }
        }
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
                if(p.getCharacter() == 'W')
                    return setPlayerCoordination(p,Movements.NORTH_EAST);
                break;
            case "SW":
                if(p.getCharacter() == 'B')
                    return setPlayerCoordination(p,Movements.SOUTH_WEST);
                break;
        }
        return false;
    }
//
    public static void initGameBoard() {
        Random r = new Random();
        p1.i = r.nextInt(8);
        p1.j = r.nextInt(8);
        p2.i = r.nextInt(8);
        p2.j = r.nextInt(8);

        p1.setScore(0);
        p2.setScore(0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Rational();
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
//
    public static void printGameBoard() {
        System.out.println();
        for (int i = 0; i < 8; i++) { //Arrays umzug in Zeile
            for (int j = 0; j < 8; j++) { //Arrays umzug in Spalte
                if (i == p1.i && j == p1.j) { //Wenn i/Zeile und j/Spalte mit Spielerkoordinaten gleich waren
                    System.out.printf("%-3s%-4s", "", ""); //printf: Ausgabe basierend auf dem angegebenen Format. d.h : beispielweisse ("%s","A") bedeutet anstatt %s muss es A stellen.
                    //%-3s bedeutet für jedem Zähler(Bruche) 3 mal space betrachten.
                    //%-3s bedeutet anstatt Zähler drucken, muss es 3 space stellen, und dann 4 space neben es.
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else { //wenn else dann es printet Zähler
                    if(board[i][j].getNumerator()!=-1){
                        System.out.printf("%-3s%-4s", board[i][j].getNumerator(), "");
                    }else{
                        System.out.printf("%-3s%-4s", "", "");
                    }
                }
            }
            System.out.println(); //bis hier war alles für Zähler

            for (int j = 0; j < 8; j++) { //ab hier ist alles gleich wie oben aber für Bruchstrich
                if (i == p1.i && j == p1.j) {
                    System.out.printf("%-3s%-4s", "W", "");
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "B", "");
                } else {
                    if(board[i][j].getNumerator()!=-1 && board[i][j].getDenominator()!=-1){
                        System.out.printf("%-3s%-4s", "---", "");
                    }else{
                        System.out.printf("%-3s%-4s", "", "");
                    }
                }
            }
            System.out.println();

            for (int j = 0; j < 8; j++) { //ab hier ist alles gleich wie oben aber für Nenner
                if (i == p1.i && j == p1.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else if (i == p2.i && j == p2.j) {
                    System.out.printf("%-3s%-4s", "", "");
                } else {
                    if(board[i][j].getNumerator()!=-1){
                        System.out.printf("%-3s%-4s", board[i][j].getDenominator(), "");
                    }else{
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
                    if(board[i][j].getNumerator()!=-1 && board[i][j].getDenominator()!=-1) {
                        p1.addScore(board[i][j].getFinalNumber());
                        board[i][j].setNumerator(-1);
                        board[i][j].setDenominator(-1);
                    }
                }
            }
        }
    }
}
