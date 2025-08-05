package Game;

public class Block {
    private char character;
    private int x;
    private int y;

    public Block(char character, int x, int y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public char getCharacter() {
        return character;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}