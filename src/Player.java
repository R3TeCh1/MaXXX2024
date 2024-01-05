/*
    @author Kadir Erzurum, Nazanin Golalizadeh, Irina Samsonyan
    @version 05.01.2024
 */
public class Player {
    private char character;
    private double score;
    int i,j;
    public Player(char character) {
        this.character = character;
        if(character=='⚪'){
            i = 4;
            j = 4;
        } else if (character == '⚫')
        {
            i = 3;
            j = 3;
        }
    }

    public void setPosition(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean moveUpRight(){
        if(i>0 && i<=7 && j>= 0 && j<7){
            i--;
            j++;
            return true;
        }
        return false;
    }

    public boolean moveDownLeft(){
        if(i>=0 && i<7 && j>0 &&j<=7){
            i++;
            j--;
            return true;
        }
        return false;
    }
    public boolean moveDown(){
        if(i>=0 && i<7){
            i++;
            return true;
        }
        return false;
    }
    public boolean moveUp(){
        if(i>0 && i<=7){
            i--;
            return true;
        }
        return false;
    }
    public boolean moveLeft(){
        if(j>0 &&j<=7){
            j--;
            return true;
        }
        return false;
    }
    public boolean moveRight(){
        if(j>= 0 && j<7){
            j++;
            return true;
        }
        return false;
    }

    public void setScore(int score){
        this.score = score;
    }
    public char getCharacter() {
        return character;
    }
    public double getScore() {
        return score;
    }
    public void addScore(double s){
        score += s;
    }
    @Override
    public String toString(){
        return character+" HAT GEWONNEN: " + score + " PUNKTE.";
    }
}
