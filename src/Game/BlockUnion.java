package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.text.Position;


public class BlockUnion {
    private ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Color> backColorList;
    public int[] blockCountArray = new int[30];

    public Color unionColor;
    
    public static void createColorList() {
    	backColorList = new ArrayList<>();
    	backColorList.add(Color.RED);
    	backColorList.add(Color.BLUE);
    	backColorList.add(Color.black);
    	backColorList.add(Color.DARK_GRAY);
    	backColorList.add(Color.PINK);
    	backColorList.add(Color.MAGENTA);
    	
    }
    
    public BlockUnion(String word, int startX, int startY) {
        int x = startX;
        int y = startY;
        Random random = new Random();

        for (char character : word.toCharArray()) {
            Block block = new Block(character, x, y);
            blocks.add(block);

            // 0에서 99까지의 난수 생성
            int randomValue = random.nextInt(100);

            // 30%의 확률로 아래로 이동, 70%의 확률로 오른쪽으로 이동
            if (randomValue < 35) {
                y++; // 아래로 이동
            } else {
                x++; // 오른쪽으로 이동
            }
        }

        createColorList();
        unionColor = getRandomColor();
    }
    
    public void countBlock() {
        // blockCountArray를 0으로 초기화
        Arrays.fill(blockCountArray, 0);

        // 각 y 좌표에 대한 블록 개수를 세기
        for (Block block : blocks) {
            int y = block.getY();
            blockCountArray[y]++;
        }
    }

    public void deleteLine(int y) {
        Iterator<Block> iterator = blocks.iterator();

        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block.getY() == y) {
                iterator.remove();
            }
        }

        reConstructBlock(y);
    }

    public void reConstructBlock(int y) {
        for (Block block : blocks) {
            if (block.getY() < y) {
                block.setY(block.getY() + 1); // y값이 자신보다 작으면 1씩 증가
            }
        }
    }
    
    public Color getColor() {
    	return unionColor;
    }
    
    public void setLocation(int x, int y) {
        int deltaX = x - getX();
        int deltaY = y - getY();

        for (Block block : blocks) {
            block.setX(block.getX() + deltaX);
            block.setY(block.getY() + deltaY);
        }
    }
    
    public boolean isCollisionBlockWall(int leftWallX, int rightWallX, int floorY) {
        return CollisionDetector.isCollisionWall(this, leftWallX, rightWallX, floorY);
    }
    
    public boolean isCollisionU(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockU(this, otherBlockUnion);
    }
    
    public boolean isCollisionL(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockL(this, otherBlockUnion);
    }
    
    public boolean isCollisionR(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockR(this, otherBlockUnion);
    }
    
    public void rotateClockwise() {
        // 중심 계산
        int totalX = 0;
        int totalY = 0;

        for (Block block : blocks) {
            totalX += block.getX();
            totalY += block.getY();
        }

        int centerX = totalX / blocks.size();
        int centerY = totalY / blocks.size();

        // 각 블록을 중심으로 90도 시계 방향 회전
        for (Block block : blocks) {
            // 현재 블록의 위치
            int currentX = block.getX();
            int currentY = block.getY();

            // 중심을 기준으로 상대 위치 계산
            int relativeX = currentX - centerX;
            int relativeY = currentY - centerY;

            // 90도 시계 방향 회전 적용
            int newX = centerX - relativeY;
            int newY = centerY + relativeX;

            // 위치를 설정
            block.setX(newX + 1);
            block.setY(newY);
        }
    }

    public void change() {
        // 현재 가장 왼쪽 위 블록 찾기
        Block topLeftBlock = findTopLeftBlock();

        // 가장 왼쪽 위 블록이 없다면 종료
        if (topLeftBlock == null) {
            return;
        }

        // 가장 왼쪽 블록의 오른쪽에 다른 블록을 추가
        int relativeX = topLeftBlock.getX();
        int relativeY = topLeftBlock.getY();
        int cnt = 0;
        for (Block block : blocks) {
            int newX = topLeftBlock.getX(); // 가장 왼쪽 블록의 오른쪽에 추가하므로 X 값은 그냥 +1
            int newY = topLeftBlock.getY() + cnt++; // 가장 왼쪽 블록의 Y 값 + 1
            block.setX(newX);
            block.setY(newY);
        }
    }

    private Block findTopLeftBlock() {
        if (blocks.isEmpty()) {
            return null;
        }

        int topLeftX = getX();
        int topLeftY = getY();

        for (Block block : blocks) {
            if (block.getX() == topLeftX && block.getY() == topLeftY) {
                return block;
            }
        }

        return null;
    }
    
    public int getX() {
        int minX = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minX = Math.min(minX, block.getX());
        }
        return minX;
    }

    public int getY() {
        int minY = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minY = Math.min(minY, block.getY());
        }
        return minY;
    }

    public void add(BlockUnion blockUnion) {
        for (Block block : blockUnion.getBlocks()) {
            // 동일 위치에 블록 추가
            blocks.add(new Block(block.getCharacter(), block.getX(), block.getY()));
        }
    }
    
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void draw(Graphics g, boolean visibleString) {
    	int cnt = 0;
        for (Block block : blocks) {
            BlockRenderer.drawBlock(g,block,unionColor,visibleString);
        }
    }

    public String getBlockWord() {
        StringBuilder wordBuilder = new StringBuilder();
        for (Block block : blocks) {
            wordBuilder.append(block.getCharacter());
        }
        return wordBuilder.toString();
    }
    
    private static Color getRandomColor() {
        Random random = new Random();
        int index = random.nextInt(backColorList.size());
        return backColorList.get(index);
    }
    // 기타 필요한 메서드 추가 가능
}
