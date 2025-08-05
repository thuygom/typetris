package Game;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class BlockRenderer {
    public static final int BLOCK_SIZE = 20;
    
    public static void drawBlock(Graphics g, Block block, Color randomColor, boolean visibleString) {
        int x = block.getX() * BLOCK_SIZE;
        int y = block.getY() * BLOCK_SIZE;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

        g.setColor(randomColor);
        g.fillRect(x + 2, y+1, BLOCK_SIZE - 2, BLOCK_SIZE - 1);
        
        if(visibleString) {
        	g.setColor(Color.white);
        	g.drawString(String.valueOf(block.getCharacter()), x + 7, y + 15);
        }
    }
    
    public static void drawRect(Graphics g, int x, int y, int sizeX, int sizeY) {
    	g.setColor(Color.BLACK);
        g.drawRect(x, y, sizeX, sizeY);
        Color c = new Color(139,45,43);
        g.setColor(c);
        g.fillRect(x + 2, y+1, sizeX-2, sizeY-1);
    }
}
