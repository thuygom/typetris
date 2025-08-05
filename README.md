■	과목명	:	객체지향언어2
■	담당교수	:	황기태 교수님
■	제출일	:	12월 12일
■	학과	:	컴퓨터공학부 웹공학 트랙
■	학번	:	1971092
■	성명	:	김정훈









객체지향언어2 미니프로젝트 제안서
< TypeTris >
1971092 김정훈 

1. 타이핑 게임의 가장 주를 이루는 방향성은 위에서 밑으로 내려오는 글자를 바닥에 닿기 전에 치면 되는 게임이라고 해석했다. 나는 떨어지는 단어에 어떠한 속성을 부여하는 것 보다 다른 게임과 합쳐 기존의 타이핑 게임의 형태를 벗어난 게임을 만들어보고 싶었다.

그래서 내가 생각한 합성 대상은 타이핑 게임과 테트리스 이다. 테트리스 또한 위에서 아래로 블록이 떨어지는 속성을 가지고 있다. 그렇기 때문에 2개의 게임의 특성을 적절히 섞는다면 좋은 게임을 만들 수 있을 것 같아 선택하게 되었다. 

타이핑 게임에서는 데이터베이스에서 단어를 선택하여 해당단어를 베이스로 한 객체를 윈도우의 가장 위에 생성하여 밑으로 떨어지도록 정의한다. 이를 개선하여, 
단어를 선택하여 해당 단어를 한 글자별로 블록으로 만든 후 블록들을 붙여서 생성하여 떨어지도록 만든다. 
예를 들어 객체지향언어 라는 단어를 생성한다면 Typetris에서는 6개의 블록이 이어진 


객체가 생성된다. 단어의 글자 개수를 기반으로 블록개수를 정하고 블록의 모양을 정하는 알고리즘을 만들어, 글자를 내부에 가진 블록을 생성한다. 게임 플레이 창의 밑에는 textArea가 있어 블록의 텍스트와 동일하게 입력하면( ex) 객체지향언어 ) 기존 타이핑 게임처럼 해당 블록 객체를 삭제하도록 한다.

블록 내부 텍스트를 입력하지 못한 채 바닥에 닿을 시 바닥에 블록이 쌓이며, 별다른 조건 없이 한줄이 되었을 때 블록이 삭제되도록 한다면, 타이핑 게임이 아니라 그냥 테트리스처럼 플레이 할 수 있기 때문에 특수한 조건을 달려고한다.

블록이 쌓인 것이 자동으로 지워지는 것이아닌, 타이핑을 통해 블록을 지울때마다 removeToken을 1개씩 주고 5개의 removeToken으로 블록 삭제를 실행시킬 수 있다.
따라서 몇줄을 채워도 타이핑 게임을 같이 진행해야만, 쌓은 블록을 지울 수 있는 것이다.
이미지 버튼을 클릭하면 블록삭제를 진행할 수 있다. 

게임 종료 조건은 기존의 테트리스처럼 쌓았던 블록이 윈도우의 가장 위에 닿으면 게임이 종료되도록 한다.

점수가 올르면 스테이지가 올라가고, 점점 내려오는 속도를 증가시켜 난이도를 디자인할 것이다. 블록은 방향키로 움직이거나 방향을 회전할 수 있어야 하며 1개의 블록만 떨어지도록 구성할 것이다. 1개의 블록이 타이핑 되거나, 바닥에 닿으면 다음 블록이 떨어질 것이며
블록이 떨어지는 속도는 초당 20px정도로 설정할 것이다. 이는 추후 직접 게임을 플레이 해보고 기본 블록 하락 속도 및 시간에 따른 블록하락속도의 증가폭을 직접 조정하였다.
추가적으로 스테이지 10을 넘어가면 블록내부 글자가 깜빡이며 보기 어렵게 만들어놓았다.

아이템으로는 현재 타이핑 해야 할 블록의 형태를 고득점을 노리기 쉬운 일자모양로 변경시킬 수 있는 아이템을 만들었다. 5개의 changeToken을 주고 1개의 토큰을 사용해서 모양을 변경할 수 있다.
이미지 버튼을 눌르면 낙하중인 블록을 변경할 수 있다.


2. 프로그램 구조

우선 각 클래스들끼리의 상관관계를 얘기한 후, 세부 객체들을 다루겠다.

[화면구성]
GameApp은 Main클래스로 별다른 기능이 없다. 
- LoginFrame을 생성한다.
LoginFrame
- gameStart 시 GameFrame을 생성하며 login한 user의 이름을 넘겨준다.
- ranking을 확인할 수 있는 창을 띄워준다.
GameFrame
- 게임화면이다. GamePanel을 띄운다.
GamePanel
- 3분할 되어있는 Panel이다. GameGrond/ ScorePanel/ EditPanel 이렇게 3개의 판넬을 분할하여 띄운다.
TextSource
- request를 받으면 words.txt에서 단어를 반환한다.

[블록]
block은 1개짜리 블록이며 block자체로는 그려지지도, 충돌처리가 되지도 않는다.
blockUnion은 block List를 가지고 있는 클래스이다. 이제 하나의 객체로 게임에서 활동한다.
collisionDetector는 blockUnion이 벽 혹은 죽은 블록들과 충돌했는지를 확인해서 움직일 수 없도록 한다.
blockRenderer는 화면에 blockUnion을 그려준다.

각 클래스들에 대한 관계를 설명했다. 이제 각 클래스별 주요 객체에 대해서 설명하도록 하겠다.

2-1. Block클래스
char형 charactor를 가진다. point값 x,y를 가진다. 이를 반환하는 setter및 getter함수를 보유한다.

2-2. BlockUnion클래스
ArrayList로 Block객체들을 보관한다. 이 블록들이 합쳐진 blockUnion이 게임에서 떨어지는 테트리스 블록이다.
ArrayList로 색 List또한 보관한다. 생성될 때 자신의 색을 랜덤으로 정한다.
각 줄마다 몇 개의 블록이 있는지 카운트/ 블록 한줄 지우기/ 시계방향 회전/ 콜리전 디텍터를 통한 충돌처리/ 모양 변경(아이템) 등의 함수를 보유하고 있다.

2-3 BlockRenderer
그래픽으로 그려주는 함수들이 들어있는 클래스라 주요 객체는 없다.

2-4 CollisionDetector
Block이 가진 x,y값을 기반으로 충돌을 처리하는 수식함수들이 들어있는 클래스라 주요 객체는 없다.

2-5 GameApp
main클래스이고, LoginFrame객체를 생성한다.

2-6 LoginFrame
userName을 입력받아서 GameFrame생성 시 넘겨준다.
Rank.txt를 읽어서 보여주는 창을 띄우는 버튼을 가졌다.

2-7. textSource
Vector<String> wordVector를 가진다. 여기에 words.txt의 내용을 넣고, request함수를 통해서 단어 1개를 반환한다.

2-8. GameFrame
GamePanel을 띄운다.
textSource를 생성해서 words.txt파일을 읽어온다.
scorePanel을 생성하며 LoginFrame에서 받아온 유저이름을 넘겨준다.
gameGrond를 생성해서 scorePanel및 textSource를 생성자의 인자로 넘겨준다.
메뉴바를 가지고있으며, 메뉴바의 saveButton을 통해서 words.txt수정 창을 띄운다.

2-9 GamePanel
Edit / Score / GameGround 3개의 창을 pane에 끼워서 추가한다.

2-10. GameGround
대부분의 객체가 메인게임화면인 GameGround 에있다.

JTextField textInput
- actionListener로 enter시 입력한 글자가 blockUnion내부 글자와 맞는지 확인하고 맞다면 점수를 높인다.

ScorePanel mySp
- scorePanel을 가져와서 점수를 get/ set한다.

Int removeToken
- 시작은 0이며 하강하는 블록들을 타이핑해서 맞춰없앤다면 1개씩 늘어난다. 줄을 가득채운 블록을 삭제하려면, bomb버튼을 눌러 remove작업을 실행해야하는데 이때 removeToken이 필요한다. 테트리스나 타이핑게임 두게가 혼합되어있기 때문에 양쪽다 참여하도록 구성한 token이다. 따라서 블록을 지우려면 타이핑게임을 필수적으로 진행해야한다.

Int chageToken
- 시작은 5이며 하강하는 블록의 모양을 1자로 바꿀 수 있는 change버튼을 사용할 때 필요한 토큰이다. 1회사용시 1개씩 줄어든다.

BlockUnion blockUnion
- textSource로부터 request받은 단어를 기반으로 하위 블록들을 생성하며, blockRenderer를 통해 자신을 출력하며 CollisionDetector를 통해 벽 혹은 deadBlocks와 충돌을 처리한다.
BlockUnion객체이다

BlockUnion DeadBlocks
- fallenThread를 통해 블록은 바닥으로 떨어지는데 바닥에 닿은 블록은 죽은 블록에 합쳐진다.
이러한 블록이 가로를 다 채운다면 bomb버튼을 통해 지우고 높은 점수를 얻을 수 있다. DeadBlocks는 blockUnion객체이다.

FallenThread fallTh
- 생성된 블록을 스테이지별로 변동되는 시간별로 바닥으로 1칸씩 움직인다. stop혹은 resume이 가능하다.

StageThread stageTh
- 점수를 기반으로 스테이지를 변경한다.

따라서 GameGround는 2개의 BlockUnion이 있다. 하강하는 블록과, 바닥에 닿아 죽은 블록
스레드를 통해 블록은 계속해서 바닥으로 하락한다. 텍스트 area를 통해 맞추면 블록은 없어지고 블록이 쌓인다면 버튼을 1줄을 다쌓은 블록에 하냏 지울 수 있다.

3. 프로그램 실행 과정

- Login화면이다.
Username 및 password를 통해 Login할 수 있으며, Ranking 버튼을 누르면 이전에 플레이한 사람들의 점수 및 등수를 확인할 수 있다.

- 랭킹 확인 화면.

- 메뉴 file-edit을 통한 words.txt 수정가능 창이다.

- 게임화면
메뉴바의 file-edit을 통해서 words.txt를 수정할 수 있으며, 오른쪽 scorePanel에서는 사용자의 점수 및 다음에 내려올 블록을 미리 보여준다. 오른쪽 밑 EditPanel에는 사용자의 토큰수를 보여주어 아이템을 사용할 수 있는지 알려준다.

메인화면에는 떨어지는 블록 내부에 글자를 타이핑할 수 있는 textArea 및 1줄을 가득채운 블록을 지울 수 있는 bomb 그리고 내려오는 블록의 모양을 바꿀 수 있는 change버튼이 있다.

가장 밑에는 pause및 resume버튼을 추가하였다. 해당버튼을 누르면 thread의 flag를 변경하여 멈추고 다시 이어할 수 있다.

4. 프로그램 소스 코드
Block.java
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
BlcokRenderer
package Game;

import java.awt.Color;
import java.awt.Graphics;

public class BlockRenderer {
    public static final int BLOCK_SIZE = 20;

    /**
     * 블록 한 개를 그립니다.
     *
     * @param g              그릴 Graphics 객체입니다.
     * @param block          그릴 Block 객체입니다.
     * @param randomColor    블록을 채울 랜덤 색상입니다.
     * @param visibleString  블록 안에 문자를 표시할지 여부입니다.
     */
    public static void drawBlock(Graphics g, Block block, Color randomColor, boolean visibleString) {
        int x = block.getX() * BLOCK_SIZE;
        int y = block.getY() * BLOCK_SIZE;

        // 블록 테두리 그리기
        g.setColor(Color.BLACK);
        g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

        // 블록을 랜덤 색상으로 채우기
        g.setColor(randomColor);
        g.fillRect(x + 2, y + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 1);

        // 필요에 따라 블록 안에 문자 표시
        if (visibleString) {
            g.setColor(Color.white);
            g.drawString(String.valueOf(block.getCharacter()), x + 7, y + 15);
        }
    }

    /**
     * 지정된 매개변수로 사각형을 그립니다.
     *
     * @param g      그릴 Graphics 객체입니다.
     * @param x      사각형의 좌상단 x좌표입니다.
     * @param y      사각형의 좌상단 y좌표입니다.
     * @param sizeX  사각형의 가로 크기입니다.
     * @param sizeY  사각형의 세로 크기입니다.
     */
    public static void drawRect(Graphics g, int x, int y, int sizeX, int sizeY) {
        // 사각형 테두리 그리기
        g.setColor(Color.BLACK);
        g.drawRect(x, y, sizeX, sizeY);

        // 사각형을 사용자 정의 색상으로 채우기
        Color c = new Color(139, 45, 43);
        g.setColor(c);
        g.fillRect(x + 2, y + 1, sizeX - 2, sizeY - 1);
    }
}
CollisionDetector
package Game;

public class CollisionDetector {
    // 충돌 여부를 검사하는 메서드
    public static boolean isCollisionWall(BlockUnion blockUnion, int leftWallX, int rightWallX, int floorY) {
        for (Block block : blockUnion.getBlocks()) {
            int blockX = block.getX();
            int blockY = block.getY();
            int blockSize = BlockRenderer.BLOCK_SIZE; // 블록 크기, BlockRenderer에서 상수로 정의되어 있다고 가정
            
            // 왼쪽 벽에 충돌한 경우
            if ((blockX * blockSize) < leftWallX) {
                return true;
            }

            // 오른쪽 벽에 충돌한 경우
            if ((blockX * (blockSize + 1)) > rightWallX) {
                return true;
            }

            // 바닥에 충돌한 경우
            if ((blockY * (blockSize + 1)) > floorY) {
                return true;
            }
        }

        // 모든 블록이 경계 내에 있다면 충돌하지 않은 것으로 간주
        return false;
    }
    
    // 블록 유니언과 죽은 블록 간 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockU(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 2 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    // 블록 유니언과 죽은 블록 간 좌측 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockL(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 2 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    // 블록 유니언과 죽은 블록 간 우측 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockR(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 2 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
}

BlockUnion
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BlockUnion {
    private ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Color> backColorList;
    public int[] blockCountArray = new int[30];
    public Color unionColor;

    // 블록 유니언 생성 시 초기화 메서드
    public BlockUnion(String word, int startX, int startY) {
        int x = startX;
        int y = startY;
        Random random = new Random();

        for (char character : word.toCharArray()) {
            Block block = new Block(character, x, y);
            blocks.add(block);

            // 0에서 99까지의 난수 생성
            int randomValue = random.nextInt(100);

            // 35%의 확률로 아래로 이동, 65%의 확률로 오른쪽으로 이동
            if (randomValue < 35) {
                y++; // 아래로 이동
            } else {
                x++; // 오른쪽으로 이동
            }
        }

        createColorList();
        unionColor = getRandomColor();
    }

    // 블록의 색상 리스트 생성
    public static void createColorList() {
        backColorList = new ArrayList<>();
        backColorList.add(Color.RED);
        backColorList.add(Color.BLUE);
        backColorList.add(Color.BLACK);
        backColorList.add(Color.DARK_GRAY);
        backColorList.add(Color.PINK);
        backColorList.add(Color.MAGENTA);
    }

    // 블록 수를 세는 메서드
    public void countBlock() {
        Arrays.fill(blockCountArray, 0);

        // 각 y 좌표에 대한 블록 개수를 세기
        for (Block block : blocks) {
            int y = block.getY();
            blockCountArray[y]++;
        }
    }

    // 특정 높이의 라인 삭제 및 블록 재구성
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

    // 특정 높이보다 위에 있는 블록들을 재구성
    public void reConstructBlock(int y) {
        for (Block block : blocks) {
            if (block.getY() < y) {
                block.setY(block.getY() + 1);
            }
        }
    }

    // 블록 유니언의 색상 반환
    public Color getColor() {
        return unionColor;
    }

    // 블록 유니언의 위치 설정
    public void setLocation(int x, int y) {
        int deltaX = x - getX();
        int deltaY = y - getY();

        for (Block block : blocks) {
            block.setX(block.getX() + deltaX);
            block.setY(block.getY() + deltaY);
        }
    }

    // 왼쪽 벽과의 충돌 여부 검사
    public boolean isCollisionBlockWall(int leftWallX, int rightWallX, int floorY) {
        return CollisionDetector.isCollisionWall(this, leftWallX, rightWallX, floorY);
    }

    // 다른 블록 유니언과의 충돌 여부 검사
    public boolean isCollisionU(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockU(this, otherBlockUnion);
    }

    // 다른 블록 유니언과의 좌측 충돌 여부 검사
    public boolean isCollisionL(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockL(this, otherBlockUnion);
    }

    // 다른 블록 유니언과의 우측 충돌 여부 검사
    public boolean isCollisionR(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockR(this, otherBlockUnion);
    }

    // 블록 유니언을 시계 방향으로 90도 회전
    public void rotateClockwise() {
        int totalX = 0;
        int totalY = 0;

        // 중심 계산
        for (Block block : blocks) {
            totalX += block.getX();
            totalY += block.getY();
        }

        int centerX = totalX / blocks.size();
        int centerY = totalY / blocks.size();

        // 각 블록을 중심으로 90도 시계 방향 회전
        for (Block block : blocks) {
            int currentX = block.getX();
            int currentY = block.getY();

            int relativeX = currentX - centerX;
            int relativeY = currentY - centerY;

            int newX = centerX - relativeY;
            int newY = centerY + relativeX;

            block.setX(newX + 1);
            block.setY(newY);
        }
    }

    // 블록 위치 변경 (한 칸씩 이동)
    public void change() {
        Block topLeftBlock = findTopLeftBlock();

        if (topLeftBlock == null) {
            return;
        }

        int relativeX = topLeftBlock.getX();
        int relativeY = topLeftBlock.getY();
        int cnt = 0;

        for (Block block : blocks) {
            int newX = topLeftBlock.getX();
            int newY = topLeftBlock.getY() + cnt++;
            block.setX(newX);
            block.setY(newY);
        }
    }

    // 현재 블록 유니언 중에서 가장 왼쪽 위 블록을 찾아 반환
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

    // 블록 유니언의 최소 x 좌표 반환
    public int getX() {
        int minX = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minX = Math.min(minX, block.getX());
        }
        return minX;
    }

    // 블록 유니언의 최소 y 좌표 반환
    public int getY() {
        int minY = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minY = Math.min(minY, block.getY());
        }
        return minY;
    }

    // 다른 블록 유니언의 블록을 현재 블록 유니언에 추가
    public void add(BlockUnion blockUnion) {
        for (Block block : blockUnion.getBlocks()) {
            blocks.add(new Block(block.getCharacter(), block.getX(), block.getY()));
        }
    }

    // 현재 블록 유니언의 블록 리스트 반환
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // 블록 유니언 그리기
    public void draw(Graphics g, boolean visibleString) {
        for (Block block : blocks) {
            BlockRenderer.drawBlock(g, block, unionColor, visibleString);
        }
    }

    // 현재 블록 유니언의 블록 문자열 반환
    public String getBlockWord() {
        StringBuilder wordBuilder = new StringBuilder();
        for (Block block : blocks) {
            wordBuilder.append(block.getCharacter());
        }
        return wordBuilder.toString();
    }

    // 랜덤한 색상 반환
    private static Color getRandomColor() {
        Random random = new Random();
        int index = random.nextInt(backColorList.size());
        return backColorList.get(index);
    }
    // 기타 필요한 메서드 추가 가능
}

EditPanel
package Game;

import javax.swing.*;
import java.awt.Color;

public class EditPanel extends JPanel {
    private GameGround gg;
    JLabel label3, label4;

    // EditPanel 생성자
    public EditPanel(GameGround gg) {
        setBackground(Color.black);
        setLayout(null);
        this.gg = gg;

        // Bomb 버튼 생성
        JButton reqFoc = new JButton();
        reqFoc.setSize(60, 60);
        reqFoc.setLocation(50, 50);
        ImageIcon reqFocIcon = new ImageIcon("bomb.png");
        reqFocIcon.setImage(reqFocIcon.getImage().getScaledInstance(reqFoc.getWidth(), reqFoc.getHeight(), 0));
        reqFoc.setIcon(reqFocIcon);
        reqFoc.setBackground(Color.white);
        add(reqFoc);

        // Bomb 레이블 생성
        JLabel label = new JLabel("bomb needs 5 R-Token");
        label.setForeground(Color.WHITE);
        label.setBounds(120, 50, 150, 20);
        add(label);

        // R-Token 개수 표시 레이블 생성
        label3 = new JLabel("0");
        label3.setForeground(Color.WHITE);
        label3.setBounds(120, 80, 150, 20);
        add(label3);

        // Change 버튼 생성
        JButton changeBtn = new JButton();
        changeBtn.setSize(60, 60);
        changeBtn.setLocation(50, 200);
        changeBtn.setBackground(Color.white);
        ImageIcon changeBtnIcon = new ImageIcon("renew.png");
        changeBtnIcon.setImage(changeBtnIcon.getImage().getScaledInstance(changeBtn.getWidth(), changeBtn.getHeight(), 0));
        changeBtn.setIcon(changeBtnIcon);
        add(changeBtn);

        // Change 레이블 생성
        JLabel label2 = new JLabel("change needs 1 C-Token");
        label2.setForeground(Color.WHITE);
        label2.setBounds(120, 200, 150, 20);
        add(label2);

        // C-Token 개수 표시 레이블 생성
        label4 = new JLabel("0");
        label4.setForeground(Color.WHITE);
        label4.setBounds(120, 230, 150, 20);
        add(label4);

        // R-Token과 C-Token 개수를 주기적으로 업데이트하는 쓰레드 시작
        TextUpdate update = new TextUpdate();
        update.start();
        setFocusable(false);
    }

    // 텍스트 업데이트를 위한 내부 쓰레드 클래스
    private class TextUpdate extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    // R-Token 개수 업데이트
                    SwingUtilities.invokeLater(() -> label3.setText(gg.getRemoveToken() + "개"));
                    // C-Token 개수 업데이트
                    SwingUtilities.invokeLater(() -> label4.setText(gg.getChangeToken() + "개"));
                    // 500ms 대기
                    sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

GameApp
package Game;

// 해당 프로젝트의 메인 클래스로, 게임을 실행하는 클래스입니다.
public class GameApp {
    // 프로그램의 시작점
    public static void main(String[] args) {
        // LoginFrame 객체를 생성하여 게임을 시작합니다.
        new LoginFrame();
    }
}

GameFrame
package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

// GameApp에서 호출되는 GameFrame이다. Swing을 통해서 창을 구성하고 GameApp에서 해당 클래스가 생성되면 창을 띄워준다.
public class GameFrame extends JFrame {
    private GamePanel gamePanel = null;
    private TextSource textSource = null;
    public GameGround gameGround;
    private ScorePanel scorePanel;

    // GameFrame 생성자
    public GameFrame(String name) {
        setTitle("TypeTris"); // 창 제목
        setSize(800, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 종료 시 프로그램 종료
        scorePanel = new ScorePanel(name);
        Container contentPane = getContentPane();

        makeMenu(); // 메뉴 생성
        textSource = new TextSource(this); // words.txt 파일을 읽어온다.
        gameGround = new GameGround(scorePanel, textSource);

        gamePanel = new GamePanel(textSource, name); // GamePanel(화면 분할 기능 수행)을 생성한다.
        contentPane.add(gamePanel, BorderLayout.CENTER); // 생성한 GamePanel을 추가한다.

        // 포커스 변화를 감지하기 위한 이벤트 리스너 등록
        // KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(
        //        new PropertyChangeListener() {
        //            @Override
        //            public void propertyChange(PropertyChangeEvent evt) {
        //                String prop = evt.getPropertyName();
        //                if ("permanentFocusOwner".equals(prop)) {
        //                    Component newFocus = (Component) evt.getNewValue();
        //                    System.out.println("Focus is on: " + newFocus);
        //                }
        //            }
        //        });

        setResizable(false);
        setVisible(true); // 보이도록 설정
    }

    @Override
    public String toString() {
        return "I'm GameFrame!";
    }

    // 메뉴 생성 메서드
    private void makeMenu() {
        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        JMenu fileMenu = new JMenu("File");
        JMenuItem editItem = new JMenuItem("Edit");
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditFrame();
            }
        });
        fileMenu.add(editItem);
        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mb.add(fileMenu);
    }

    // 편집 프레임 열기 메서드
    private void openEditFrame() {
        // 편집 버튼 클릭 시, 편집 화면 열기
        JFrame editFrame = new JFrame("Edit Words");
        editFrame.setSize(400, 300);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 텍스트 영역 생성
        JTextArea editTextArea = new JTextArea();

        // words.txt 파일 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                editTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            editTextArea.setText("Failed to read words.txt");
        }

        // 텍스트 영역을 스크롤 가능하도록 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(editTextArea);

        // 편집 화면에 스크롤 패널 추가
        editFrame.add(scrollPane);

        // 저장 버튼 추가
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWordsToFile(editTextArea.getText());
                editFrame.dispose(); // 창 닫기
            }
        });

        // 편집 화면에 저장 버튼 추가
        editFrame.add(saveButton, BorderLayout.SOUTH);

        // 화면 중앙에 위치
        editFrame.setLocationRelativeTo(this);
        editFrame.setVisible(true);
    }

    // 단어 저장 메서드
    private void saveWordsToFile(String words) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("words.txt"))) {
            writer.write(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

GamePanel
package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

// GameFrame에서 호출되는 GamePanel 클래스로, 게임의 주요 패널 및 화면 분할 기능을 담당합니다.
public class GamePanel extends JPanel {
    private ScorePanel scorePanel;
    private EditPanel editPanel;
    public GameGround gameGround;

    private TextSource textSource = null;

    // GamePanel 생성자
    public GamePanel(TextSource textSource, String name) {
        this.textSource = textSource;
        scorePanel = new ScorePanel(name);
        gameGround = new GameGround(scorePanel, textSource);
        gameGround.requestFocus();
        editPanel = new EditPanel(gameGround);
        setBackground(Color.black);
        setLayout(new BorderLayout());
        splitPanel();
    }

    @Override
    public String toString() {
        return "I'm GamePanel!";
    }

    // 패널 분할 설정 메서드
    private void splitPanel() {
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 좌우로 나눔
        hPane.setDividerLocation(502); // divider 위치 설정
        add(hPane);

        JSplitPane vPane = new JSplitPane();
        vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); // vertical split
        vPane.setDividerLocation(300); // divider 위치 설정
        vPane.setTopComponent(scorePanel); // 우-상 판넬은 점수 판넬로 설정
        vPane.setBottomComponent(editPanel); // 우-하 판넬은 수정 판넬로 설정

        hPane.setRightComponent(vPane);
        hPane.setLeftComponent(gameGround); // 좌 판넬은 GameGround로 설정한다.
    }
}
LoginFrame
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 로그인 화면을 담당하는 LoginFrame 클래스
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // LoginFrame 생성자
    public LoginFrame() {
        setTitle("TypeTris Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지를 그릴 패널 생성
        ImagePanel imagePanel = new ImagePanel("background.png");
        setContentPane(imagePanel);
        imagePanel.setBackground(Color.black);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.setOpaque(false); // 배경을 투명하게 설정

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);

        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);

        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.yellow);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 클릭 시, 게임 화면으로 전환
                if (!usernameField.getText().isEmpty()) {
                    openGameFrame();
                }
            }
        });

        JButton rankButton = new JButton("Ranking");
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 랭킹 버튼 클릭 시, 랭킹 화면 열기
                openRankFrame();
            }
        });
        rankButton.setBackground(Color.GREEN);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(rankButton); // 랭킹 버튼 추가
        loginPanel.add(loginButton);

        imagePanel.add(loginPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // 화면 중앙에 위치
        setVisible(true);
    }

    // 게임 화면 열기 메서드
    private void openGameFrame() {
        String name = usernameField.getText();
        System.out.println(name);
        new GameFrame(name);
        // 현재 창 닫기
        dispose();
    }

    // 랭킹 화면 열기 메서드
    private void openRankFrame() {
        // 랭킹 버튼 클릭 시, 랭킹 화면 열기
        JFrame rankFrame = new JFrame("Ranking");
        rankFrame.setSize(400, 300);
        rankFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 텍스트 영역 생성
        JTextArea rankTextArea = new JTextArea();
        rankTextArea.setEditable(false); // 읽기 전용으로 설정

        // rank.txt 파일 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader("rank.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rankTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            rankTextArea.setText("Failed to read rank.txt");
        }

        // 텍스트 영역을 스크롤 가능하도록 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(rankTextArea);

        // 랭킹 화면에 스크롤 패널 추가
        rankFrame.add(scrollPane);

        // 화면 중앙에 위치
        rankFrame.setLocationRelativeTo(this);
        rankFrame.setVisible(true);
    }

    // 프로그램 시작 메서드
    public static void main(String[] args) {
        // 프로그램 시작 시, 로그인 화면을 보여줌
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }

    // 배경 이미지를 그리는 패널
    private class ImagePanel extends JPanel {
        private Image backgroundImage;

        // ImagePanel 생성자
        public ImagePanel(String imagePath) {
            try {
                this.backgroundImage = new ImageIcon(imagePath).getImage();
                System.out.println("Image loaded successfully");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to load image");
            }
        }

        // 배경 이미지 그리기
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
       

ScorePanel
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;

// 점수를 표시하고 관리하는 ScorePanel 클래스
public class ScorePanel extends JPanel {
    private int score = 0;
    private JLabel scoreLabel = new JLabel(Integer.toString(score)); // score 출력
    private BlockRenderer blockRenderer;
    private BlockUnion spareBlock;
    public String userName;

    // ScorePanel 생성자
    public ScorePanel(String name) {
        userName = name;
        JLabel label = new JLabel("score: ");
        label.setForeground(Color.WHITE);
        add(label);

        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        setBackground(Color.black);
        setFocusable(false);
    }

    // 예비 블록을 설정하는 메서드
    public void setBlockUnion(BlockUnion spareBlock) {
        this.spareBlock = spareBlock;
        repaint();
    }

    // 그림을 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        blockRenderer.drawRect(g, 40, 40, 190, 190);
        spareBlock.draw(g, true);
    }

    // 현재 점수를 반환하는 메서드
    public int getScore() {
        return score;
    }

    // 점수를 증가시키는 메서드
    public void increase(int incScore) {
        score += incScore;
        scoreLabel.setText(Integer.toString(score));
    }

    // 점수를 감소시키는 메서드
    public void decrease(int decScore) {
        score -= decScore;
        if (score < 0)
            score = 0;
        scoreLabel.setText(Integer.toString(score));
    }

    // 사용자 이름을 반환하는 메서드
    public String getName() {
        return userName;
    }

    // 랭킹을 계산하고 파일에 저장하는 메서드
    public void rank() {
        String fileName = "rank.txt";
        List<String> scoreList = loadScoreList(fileName);

        // 현재 유저의 정보 추가
        String userEntry = userName + "," + score;
        scoreList.add(userEntry);

        // 내림차순으로 정렬
        Collections.sort(scoreList, new ScoreComparator());

        // 파일에 저장
        saveScoreList(fileName, scoreList);
    }

    // 파일에서 랭킹 정보를 읽어오는 메서드
    private List<String> loadScoreList(String fileName) {
        List<String> scoreList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                scoreList.add(line);
            }
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 무시
        }

        return scoreList;
    }

    // 랭킹 정보를 파일에 저장하는 메서드
    private void saveScoreList(String fileName, List<String> scoreList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String scoreEntry : scoreList) {
                writer.write(scoreEntry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 랭킹을 비교하기 위한 내부 클래스
    private static class ScoreComparator implements Comparator<String> {
        @Override
        public int compare(String entry1, String entry2) {
            int score1 = Integer.parseInt(entry1.split(",")[1]);
            int score2 = Integer.parseInt(entry2.split(",")[1]);

            // 내림차순으로 정렬
            return Integer.compare(score2, score1);
        }
    }
}

TextSource
package Game;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// 단어 소스를 관리하는 TextSource 클래스
public class TextSource {
    private Vector<String> wordVector = new Vector<String>(30000); // 단어를 저장하는 벡터

    // TextSource 생성자
    public TextSource(Component parent) {
        try {
            Scanner scanner = new Scanner(new FileReader("words.txt")); // 파일 읽기
            while (scanner.hasNext()) {
                String word = scanner.nextLine();

                // 첫 글자를 대문자로 변경
                if (word.length() > 0) {
                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                }

                // 길이가 6 미만인 단어만 추가
                if (word.length() < 6)
                    wordVector.add(word);
            }
            scanner.close();
            JOptionPane.showMessageDialog(parent, "Reading TextSource success"); // 파일 읽는데 성공 시 다이얼로그 출력
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(0);
        }
    }

    // 랜덤하게 단어를 요청하는 메서드
    public String request() {
        Random random = new Random();
        int index = random.nextInt(wordVector.size());
        return wordVector.get(index);
    }
}

GameGround
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGround extends JPanel{
	
	private JTextField textInput = new JTextField(20); //입력창
	private ScorePanel mySp; //스코어판넬
	public int removeToken = 0; //쌓인 블록 지우기 토큰
	public int changeToken = 5; //하강블록 모양변경토큰
	private int stage = 1; //스테이지
	private BlockRenderer blockRenderer; //블록렌더러
	BlockUnion blockUnion; //하강블록
	BlockUnion deadBlocks = new BlockUnion("",0,0); //블록 지우기용 합체
	private boolean visibleString = true; //문자보이기
	JButton reqFoc; //버튼
	FallenThread fallTh; //하강 스레드
	private static final int BOX_SIZE = 20; // 각 상자의 크기
    private static final int NUM_BOXES_X = 800 / BOX_SIZE; // x 방향 상자의 개수 (800은 패널의 가로 크기)
    private static final int NUM_BOXES_Y = 600 / BOX_SIZE; // y 방향 상자의 개수 (600은 패널의 세로 크기)
	
	private TextSource ts;
	public GameGround(ScorePanel sp, TextSource ts) {
		deadBlocks.unionColor=Color.gray; //죽은 블록은 회색이다.
		mySp = sp; //대입
		
		this.ts = ts; //대입
		setBackground(Color.white);//배경색 설정
		setLayout(null);//널 레이아웃
		
		textInput.setSize(300,20);
		textInput.setLocation(100,640);
		textInput.addActionListener(new WriteAnswer());//답입력시 액션리스너 연결
		add(textInput); //텍스트 인풋
		
		prepareBlockUnions("init"); // 단어를 블록으로 분리하고 초기 위치에 배치
		
		fallTh = new FallenThread();//블록하강 스레드
		fallTh.start();//run안됨 start할것.
		
		StageThread stageTh = new StageThread();//점수기반 스테이지 설정 스레드
		stageTh.start();
		
		reqFoc = new JButton();
		reqFoc.setSize(60, 60);
		reqFoc.setLocation(30, 620);
		// Set image for reqFoc button
		ImageIcon reqFocIcon = new ImageIcon("bomb.png");
		reqFocIcon.setImage(reqFocIcon.getImage().getScaledInstance(reqFoc.getWidth(), reqFoc.getHeight(), 0));//사이즈설정
        reqFoc.setIcon(reqFocIcon);
        reqFoc.setBackground(Color.white);
		reqFoc.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(removeToken >= 5) {
		            removeToken -= 5; //토큰줄이고
		            deadBlocks.countBlock();//줄별 블록 세고
		            int cnt = 0;
		            for(int i = 0; i < 30; i++) {
		                if(deadBlocks.blockCountArray[i] == 25) {
		                    deadBlocks.deleteLine(i);//가득찬 라인삭제
		                    cnt++;
		                }
		            }
		            mySp.increase(500 * cnt);//점수증가
		        }
		    }
		});
		add(reqFoc); //bomb버튼인데 이름 까먹고 안빠꿈

		// Similarly for changeBtn...

		JButton changeBtn = new JButton();
		changeBtn.setSize(60, 60);
		changeBtn.setLocation(410, 620);
		changeBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon changeBtnIcon = new ImageIcon("renew.png");
		changeBtnIcon.setImage(changeBtnIcon.getImage().getScaledInstance(changeBtn.getWidth(), changeBtn.getHeight(), 0));
        changeBtn.setIcon(changeBtnIcon);

		changeBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(changeToken >= 0) {
		            blockUnion.change();//모양변경
		            changeToken--;//토큰감소
		            requestFocus();//포커스 넘기기
		        }
		    }
		});
		add(changeBtn);
        
		JButton stopBtn = new JButton();
		stopBtn.setSize(60, 60);
		stopBtn.setLocation(260, 690);
		stopBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon stopBtnIcon = new ImageIcon("stop.png");
		stopBtnIcon.setImage(stopBtnIcon.getImage().getScaledInstance(stopBtn.getWidth(), stopBtn.getHeight(), 0));
		stopBtn.setIcon(stopBtnIcon);

		stopBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        fallTh.stopGame();//게임 중지
		    }
		});
		add(stopBtn);
		
		JButton resumeBtn = new JButton();
		resumeBtn.setSize(60, 60);
		resumeBtn.setLocation(180, 690);
		resumeBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon resumeBtnIcon = new ImageIcon("play.png");
		resumeBtnIcon.setImage(resumeBtnIcon.getImage().getScaledInstance(resumeBtn.getWidth(), resumeBtn.getHeight(), 0));
		resumeBtn.setIcon(resumeBtnIcon);

		resumeBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        fallTh.resumeGame();//게임 재게
		    }
		});
		add(resumeBtn);
		
        requestFocus();
		addKeyListener(new BlockUnionKeyListener()); // 키 이벤트 리스너 추가
	}
	
	class FallenThread extends Thread {
	    private boolean stopGame = true;

	    public void stopGame() {
	        stopGame = false;
	    }

	    public void resumeGame() {
	        stopGame = true;
	        synchronized (this) {
	            notify();//wait해제
	        }
	    }

	    @Override
	    public void run() {
	        try {
	            while (true) {
	                if (blockUnion.isCollisionBlockWall(-100, 600, 600) || blockUnion.isCollisionU(deadBlocks)) {
	                    deadBlocks.add(blockUnion);
	                    prepareBlockUnions("collision Detect");

	                    repaint(); // notify() 전에 repaint() 호출
	                } else {
	                    moveBlock(blockUnion, 0, +1);
	                    sleep(((9000 / stage) / 5) + 400 / stage);

	                    repaint(); // 이 부분도 추가
	                }

	                if (!stopGame) {
	                    synchronized (this) {
	                        wait();
	                    }
	                }
	            }
	        } catch (InterruptedException e) {
	            return;
	        }
	    }
	}
	
	public int getRemoveToken() {
		return removeToken;
	}
	
	public int getChangeToken() {
		return changeToken;
	}
	
	@Override
	public String toString() {
		return "i'm GameGround에요";
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public void setBlockUnion(BlockUnion blockUnion) {
        this.blockUnion = blockUnion;
    }
	
	public class WriteAnswer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField tf = (JTextField)e.getSource();
			if(tf.getText().equals(blockUnion.getBlockWord())){
				mySp.increase(100);//정답맞추면 점수증가
				blockCorrect(blockUnion,12,-2);//블록 새로 생성
				removeToken++;//정답맞추면 토큰증가
			}else {
				//mySp.decrease(5);
			}
			tf.setText("");
			requestFocus();
		}
	}
	
	private void prepareBlockUnions(String msg) {
		if(msg.equals("init")) {
			String initWord = ts.request();
			int x = 12; // 초기 x 위치
			int y = -2; // 초기 y 위치
			blockUnion = new BlockUnion(initWord, x, y); //사용할 블럭
			
			String word2 = ts.request();
			BlockUnion spareBlock = new BlockUnion(word2,5,5);
			mySp.setBlockUnion(spareBlock); //spare블록
			
		}else {
			blockUnion = mySp.getBlockUnion();
			blockUnion.setLocation(12, -2);
			
			
			String spareWord = ts.request();
			int x = 5; // 초기 x 위치
			int y = 5; // 초기 y 위치

			BlockUnion spareBlock = new BlockUnion(spareWord,x,y);
			mySp.setBlockUnion(spareBlock);
		}
    }

	private class BlockUnionKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if(blockUnion.isCollisionBlockWall(1, 1000, 1000) || blockUnion.isCollisionR(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,-1,0);	
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(blockUnion.isCollisionBlockWall(-100, 499, 1000) || blockUnion.isCollisionL(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,+1,0);	
                }
                break;
            case KeyEvent.VK_UP:
                blockUnion.rotateClockwise();
                repaint();
                //moveBlockUnionSub(blockUnion,0,-1);
                break;
            case KeyEvent.VK_DOWN:
                if(blockUnion.isCollisionBlockWall(-100, 1000, 600) || blockUnion.isCollisionU(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,0,+1);	
                }
                break;
            case KeyEvent.VK_A: // 추가한 부분
                textInput.requestFocus();
                break;
        }
    }
}

	 @Override
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        blockUnion.draw(g,visibleString);
	        deadBlocks.draw(g,true);
	        BlockRenderer.drawRect(g, 0, 600, 500, 185);
	 }
	
	 public void moveBlock(BlockUnion blockUnion, int deltaX, int deltaY) {
	        int newX = blockUnion.getX() + deltaX;
	        int newY = blockUnion.getY() + deltaY;
	        
	        blockUnion.setLocation(newX, newY);
	        repaint(); // 화면 다시 그리기
	  }
	 
	 public void blockCorrect(BlockUnion blockUnion, int deltaX, int deltaY) {
	        int newX = deltaX;
	        int newY = deltaY;
	        prepareBlockUnions("play");
	        blockUnion.setLocation(newX, newY);
	        
	        repaint(); // 화면 다시 그리기
	    }
	 
	 public void gameOver() {
	        System.out.println("gameOver");

	        // 스레드 정지
	        fallTh.stopGame();

	        // 작은 창 띄우기
	        int result = JOptionPane.showConfirmDialog(
	                null,
	                "Game Over\nDo you want to register your score?",
	                "Game Over",
	                JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	            // 예를 선택한 경우 랭킹 등록 함수 호출
	            mySp.rank();
	        }
	        System.exit(0);
	    }
	 
	class StageThread extends Thread{ //thread는 cpu가 실행하는 작업의 단위다.
		@Override
		public void run() {
			while(true) {
				try {
					stage = (mySp.getScore()/100) + 1;
					if(stage>10) {
						if(visibleString) {
							visibleString = false;
						}else{
							visibleString = true;
						}
					}
					if(deadBlocks.getY() <= 1) {
						gameOver();
					}
					sleep(1300);
					//System.out.println(stage);
				}catch(InterruptedException e) {
					return;
				}
			}
		}
	}
}
GameGround설명
게임 화면 초기화 및 필요한 변수 선언
게임 화면의 속성 설정 및 기능 초기화
블록이 떨어지는 동작을 담당하는 FallenThread클래스
게임 화면의 그리기를 담당하는 paintComponent메서드
블록 이동, 정답 입력 등의 동작 처리 메서드
게임 오버 처리 및 스테이지 업데이트를 담당하는 StageThread클래스

5. 결론
 미니프로젝트를 수행하면서 자바로 GUI 창을 띄우고 라벨 및 버튼들을 넣고 다양한 리스너를 통해서 키보드 마우스 이벤트를 처리하는 방법에 대해 알게 되었다. Java Swing에 대한 숙련도가 많이 올라간 것 같아서 공부하면서 즐거웠다.
 또한 스레드에 대해 알게되었고, 게임 스레드를 중지버튼을 통해 멈추는 것이 계속해서 deadLock에 들어가 상당히 어려웠는데, 스레드 동기화에 대해 자세히 공부하여 wait() notify()에 대한 내용을 잘 알게 되었고 문제를 해결하였다. 
 이후에는 자원의 중복사용과 Java및 여러 프로그래밍 언어 및 소프트웨어의 작업 스케쥴링에 대한 내용을 공부해야 할 필요성을 느꼈다. 문제를 해결하였다.

 이번 미니프로젝트는 타이핑 게임이라는 주제로 한정되어있어 그 안에서 최대한 남들과 다른 것을 해보고자 테트리스와 타이핑 게임을 합쳐 typetris를 만들었고, 이전에 GameEngine Unity나 Unreal엔진을 사용해서 만들것과 같이 collisionDetector혹은 Renderer를 만들며 Java Project를 진행해보니 좋은 성능을 내지는 않지만 직접 엔진단위부터 만들어가며 게임을 만드는 것이 참 즐거웠다. 그럼에도 게임을 만들면서 자료를 찾아보니 프로그래밍 언어적으로 높은 레벨의 스킬들은 아직 내 프로젝트에서 일상적으로 사용하기에는 어려운 것들이 많이 있었다. 
 또한 게임을 제작하면서 ranking등록도 만들었는데 서버가 없이 내부 클라이언트에 txt데이터에 저장 및 삭제를 하니 부족함을 많이 느꼈다. 현재 python서버나 nodejs서버는 웹프로그래밍을 하면서 실력을 많이 향상시켰지만, Java를 공부하였으니 Java Spring서버도 이 기회에 배워보는 것이 좋을것같다고 생각하였다. 이번 방학에는 SpringServer를 공부할까한다.
 훌륭한 컴퓨터 전문가로서 성장하기 위해서는 실제로 해보는 프로젝트가 중요함을 깨달았고 많은 프로젝트 경험을 쌓기 위해 앞으로도 노력할 것이다. 이상입니다.

6. 패치노트(개발 과정 노트)
11월 26
단어를 기반으로 테트리스 블록을 생성하는 프로그램을 구성한다.
(테트리스 및 타이핑 게임 합성)
현재 개발 상황

- 교수님과 만들었던 대로 화면을 좌우 분할, 우판넬을 상하 분할진행
(좌 판넬에서 게임 진행/ 우-상 판넬에서 score기록)
~12월 3일
블록을 떨어지도록 하고, 떨어지는 블록을 타이핑 할 시 삭제되도록 이벤트를 적용시키고, 1줄을 완전히 채운 블록을 특수키를 입력후, 입력 시 1줄 삭제 기능을 적용시킨다.
아이템 사용 시, 현재 낙하중인 블록의 형태 변경을 할 수 있도록 만든다.
(typeTris만의 기능 추가)
- words.txt에서 단어를 읽어와 해당단어의 글자수만큼 block을 생성시킨다.(Block 클래스 정의)
(글자 한 개를 가진 블록을 정의)

- 생성시킨 block들을 합쳐 blockUnion이라는 객체로 만들고, 해당 객체가 테트리스 게임을 진행할 블록이 된다.
(한 단어를 여러 블록으로 나눈 후, 다시 블록들을 합쳐서 여러 개의 블록이 합쳐진 blockUnion 객체로 정의)

- BlockRenderor를 통해 1개짜리 블록들의 집합인 blockUnion을 그려주는 클래스를 작성
(그래픽을 사용하여 블록을 rect형태로 draw한다.)

- EditPanel을 통해 BlockUnion내부 글자를 입력 시 점수를 올림
( 맞췄을 때, 이벤트는 구성되어있다. ) 

- 블록위치를 초기화시키고 새로운 글자를 request해서 새로운 블록 생성.

- FallenThread를 통해 BlockUnion을 시간이 지남에 따라 밑으로 이동시킨다.

- KeyAdapter를 추가하여 키보드를 통해 블록의 좌우 위치를 변경시킬 수 있다.

- CollisionDetector 클래스 추가하여 벽을 넘어가지 못하도록 설정함
- rotateClockWise함수를 통해서 윗 방향키를 누를 시, 블록 회전 구현

- int형 변수 stage를 통해 블록 낙하속도 조정.

- enter가 입력되면 블록에 포커스/ a키를 입력하면 textArea에 포커스

- 죽은 블록들이 합쳐져 있는 BlockUnion deadBlocks 통해 바닥에 닿으면 죽은 블록이 되도록 구현

- private int removeToken생성 ( 블록이 한줄을 채웠을 시 지울 수 있는 textArea의 visible을 true로 바꿔줄 버튼의 생성 조건 ) 블록의 내부 텍스트를 맞출수록 토큰이 생성되고 토큰을 5개 소비하여 remove함수를 실행

[todo list]
[★★★]
1. blockUnion의 콜리전 디텍터
- blockUnion끼리의 충돌 처리( 블록을 쌓을 수 있도록 ) // 최우선

2. 블록이 1줄을 채웠을 때 remove function // 최우선
- deadBlock에서 y축 값을 기반으로 채워나가고 1줄이 다 채워지면 지울 수 있도록 하면 될 듯.
countBlock으로 deadBlocks의 y축 별 블록 카운트 후 25개 달성 및 remove버튼 클릭시 deleteLine을 실행하여 해당 라인 지움

- 바닥에 쌓인 블록이 지워졌을 때, 위에 죽은 블록들을 바닥으로 땡겨야함
(1줄 지울때, 지운 줄의 y축값 기반으로 밑은 냅두고 위는 20씩 낮추면 될 듯.)
이 후, reConstructBlock함수를 실행해서 해당 y값을 기반으로 위 라인은 밑으로 댕김

[★★]
4. 난이도 증가
- 점수에 따른 블록 낙하속도 증가
- 블록내부 글자의 출력시간 조정( 보였다가 안보였다가 ) // 나중에 처리할 것

[★]
5. 현 블록 모양 변경 아이템(1자로 변경)
change함수로 모양변경 구현완료
6. pause등 기능 
- Thread stop하면 될듯
7. words edit기능 랭킹
- 파일 입출력좀 보면 될듯
8. 시작화면 필요
- 화면 넘어가기
9. 배경화면
- 이미지 넣는법 음악 넣는법
10. 게임종료 랭킹
11. 보고서
~12월 10일
버그 수정 후 제출
■	과목명	:	객체지향언어2
■	담당교수	:	황기태 교수님
■	제출일	:	12월 12일
■	학과	:	컴퓨터공학부 웹공학 트랙
■	학번	:	1971092
■	성명	:	김정훈
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
package Game;

import java.awt.Color;
import java.awt.Graphics;

public class BlockRenderer {
    public static final int BLOCK_SIZE = 20;

    /**
     * 블록 한 개를 그립니다.
     *
     * @param g              그릴 Graphics 객체입니다.
     * @param block          그릴 Block 객체입니다.
     * @param randomColor    블록을 채울 랜덤 색상입니다.
     * @param visibleString  블록 안에 문자를 표시할지 여부입니다.
     */
    public static void drawBlock(Graphics g, Block block, Color randomColor, boolean visibleString) {
        int x = block.getX() * BLOCK_SIZE;
        int y = block.getY() * BLOCK_SIZE;

        // 블록 테두리 그리기
        g.setColor(Color.BLACK);
        g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

        // 블록을 랜덤 색상으로 채우기
        g.setColor(randomColor);
        g.fillRect(x + 2, y + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 1);

        // 필요에 따라 블록 안에 문자 표시
        if (visibleString) {
            g.setColor(Color.white);
            g.drawString(String.valueOf(block.getCharacter()), x + 7, y + 15);
        }
    }

    /**
     * 지정된 매개변수로 사각형을 그립니다.
     *
     * @param g      그릴 Graphics 객체입니다.
     * @param x      사각형의 좌상단 x좌표입니다.
     * @param y      사각형의 좌상단 y좌표입니다.
     * @param sizeX  사각형의 가로 크기입니다.
     * @param sizeY  사각형의 세로 크기입니다.
     */
    public static void drawRect(Graphics g, int x, int y, int sizeX, int sizeY) {
        // 사각형 테두리 그리기
        g.setColor(Color.BLACK);
        g.drawRect(x, y, sizeX, sizeY);

        // 사각형을 사용자 정의 색상으로 채우기
        Color c = new Color(139, 45, 43);
        g.setColor(c);
        g.fillRect(x + 2, y + 1, sizeX - 2, sizeY - 1);
    }
}
package Game;

public class CollisionDetector {
    // 충돌 여부를 검사하는 메서드
    public static boolean isCollisionWall(BlockUnion blockUnion, int leftWallX, int rightWallX, int floorY) {
        for (Block block : blockUnion.getBlocks()) {
            int blockX = block.getX();
            int blockY = block.getY();
            int blockSize = BlockRenderer.BLOCK_SIZE; // 블록 크기, BlockRenderer에서 상수로 정의되어 있다고 가정
            
            // 왼쪽 벽에 충돌한 경우
            if ((blockX * blockSize) < leftWallX) {
                return true;
            }

            // 오른쪽 벽에 충돌한 경우
            if ((blockX * (blockSize + 1)) > rightWallX) {
                return true;
            }

            // 바닥에 충돌한 경우
            if ((blockY * (blockSize + 1)) > floorY) {
                return true;
            }
        }

        // 모든 블록이 경계 내에 있다면 충돌하지 않은 것으로 간주
        return false;
    }
    
    // 블록 유니언과 죽은 블록 간 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockU(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 2 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    // 블록 유니언과 죽은 블록 간 좌측 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockL(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 2 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    // 블록 유니언과 죽은 블록 간 우측 충돌 여부를 검사하는 메서드
    public static boolean isCollisionBlockR(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 2 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
}

package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BlockUnion {
    private ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Color> backColorList;
    public int[] blockCountArray = new int[30];
    public Color unionColor;

    // 블록 유니언 생성 시 초기화 메서드
    public BlockUnion(String word, int startX, int startY) {
        int x = startX;
        int y = startY;
        Random random = new Random();

        for (char character : word.toCharArray()) {
            Block block = new Block(character, x, y);
            blocks.add(block);

            // 0에서 99까지의 난수 생성
            int randomValue = random.nextInt(100);

            // 35%의 확률로 아래로 이동, 65%의 확률로 오른쪽으로 이동
            if (randomValue < 35) {
                y++; // 아래로 이동
            } else {
                x++; // 오른쪽으로 이동
            }
        }

        createColorList();
        unionColor = getRandomColor();
    }

    // 블록의 색상 리스트 생성
    public static void createColorList() {
        backColorList = new ArrayList<>();
        backColorList.add(Color.RED);
        backColorList.add(Color.BLUE);
        backColorList.add(Color.BLACK);
        backColorList.add(Color.DARK_GRAY);
        backColorList.add(Color.PINK);
        backColorList.add(Color.MAGENTA);
    }

    // 블록 수를 세는 메서드
    public void countBlock() {
        Arrays.fill(blockCountArray, 0);

        // 각 y 좌표에 대한 블록 개수를 세기
        for (Block block : blocks) {
            int y = block.getY();
            blockCountArray[y]++;
        }
    }

    // 특정 높이의 라인 삭제 및 블록 재구성
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

    // 특정 높이보다 위에 있는 블록들을 재구성
    public void reConstructBlock(int y) {
        for (Block block : blocks) {
            if (block.getY() < y) {
                block.setY(block.getY() + 1);
            }
        }
    }

    // 블록 유니언의 색상 반환
    public Color getColor() {
        return unionColor;
    }

    // 블록 유니언의 위치 설정
    public void setLocation(int x, int y) {
        int deltaX = x - getX();
        int deltaY = y - getY();

        for (Block block : blocks) {
            block.setX(block.getX() + deltaX);
            block.setY(block.getY() + deltaY);
        }
    }

    // 왼쪽 벽과의 충돌 여부 검사
    public boolean isCollisionBlockWall(int leftWallX, int rightWallX, int floorY) {
        return CollisionDetector.isCollisionWall(this, leftWallX, rightWallX, floorY);
    }

    // 다른 블록 유니언과의 충돌 여부 검사
    public boolean isCollisionU(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockU(this, otherBlockUnion);
    }

    // 다른 블록 유니언과의 좌측 충돌 여부 검사
    public boolean isCollisionL(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockL(this, otherBlockUnion);
    }

    // 다른 블록 유니언과의 우측 충돌 여부 검사
    public boolean isCollisionR(BlockUnion otherBlockUnion) {
        return CollisionDetector.isCollisionBlockR(this, otherBlockUnion);
    }

    // 블록 유니언을 시계 방향으로 90도 회전
    public void rotateClockwise() {
        int totalX = 0;
        int totalY = 0;

        // 중심 계산
        for (Block block : blocks) {
            totalX += block.getX();
            totalY += block.getY();
        }

        int centerX = totalX / blocks.size();
        int centerY = totalY / blocks.size();

        // 각 블록을 중심으로 90도 시계 방향 회전
        for (Block block : blocks) {
            int currentX = block.getX();
            int currentY = block.getY();

            int relativeX = currentX - centerX;
            int relativeY = currentY - centerY;

            int newX = centerX - relativeY;
            int newY = centerY + relativeX;

            block.setX(newX + 1);
            block.setY(newY);
        }
    }

    // 블록 위치 변경 (한 칸씩 이동)
    public void change() {
        Block topLeftBlock = findTopLeftBlock();

        if (topLeftBlock == null) {
            return;
        }

        int relativeX = topLeftBlock.getX();
        int relativeY = topLeftBlock.getY();
        int cnt = 0;

        for (Block block : blocks) {
            int newX = topLeftBlock.getX();
            int newY = topLeftBlock.getY() + cnt++;
            block.setX(newX);
            block.setY(newY);
        }
    }

    // 현재 블록 유니언 중에서 가장 왼쪽 위 블록을 찾아 반환
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

    // 블록 유니언의 최소 x 좌표 반환
    public int getX() {
        int minX = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minX = Math.min(minX, block.getX());
        }
        return minX;
    }

    // 블록 유니언의 최소 y 좌표 반환
    public int getY() {
        int minY = Integer.MAX_VALUE;
        for (Block block : blocks) {
            minY = Math.min(minY, block.getY());
        }
        return minY;
    }

    // 다른 블록 유니언의 블록을 현재 블록 유니언에 추가
    public void add(BlockUnion blockUnion) {
        for (Block block : blockUnion.getBlocks()) {
            blocks.add(new Block(block.getCharacter(), block.getX(), block.getY()));
        }
    }

    // 현재 블록 유니언의 블록 리스트 반환
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // 블록 유니언 그리기
    public void draw(Graphics g, boolean visibleString) {
        for (Block block : blocks) {
            BlockRenderer.drawBlock(g, block, unionColor, visibleString);
        }
    }

    // 현재 블록 유니언의 블록 문자열 반환
    public String getBlockWord() {
        StringBuilder wordBuilder = new StringBuilder();
        for (Block block : blocks) {
            wordBuilder.append(block.getCharacter());
        }
        return wordBuilder.toString();
    }

    // 랜덤한 색상 반환
    private static Color getRandomColor() {
        Random random = new Random();
        int index = random.nextInt(backColorList.size());
        return backColorList.get(index);
    }
    // 기타 필요한 메서드 추가 가능
}
package Game;

import javax.swing.*;
import java.awt.Color;

public class EditPanel extends JPanel {
    private GameGround gg;
    JLabel label3, label4;

    // EditPanel 생성자
    public EditPanel(GameGround gg) {
        setBackground(Color.black);
        setLayout(null);
        this.gg = gg;

        // Bomb 버튼 생성
        JButton reqFoc = new JButton();
        reqFoc.setSize(60, 60);
        reqFoc.setLocation(50, 50);
        ImageIcon reqFocIcon = new ImageIcon("bomb.png");
        reqFocIcon.setImage(reqFocIcon.getImage().getScaledInstance(reqFoc.getWidth(), reqFoc.getHeight(), 0));
        reqFoc.setIcon(reqFocIcon);
        reqFoc.setBackground(Color.white);
        add(reqFoc);

        // Bomb 레이블 생성
        JLabel label = new JLabel("bomb needs 5 R-Token");
        label.setForeground(Color.WHITE);
        label.setBounds(120, 50, 150, 20);
        add(label);

        // R-Token 개수 표시 레이블 생성
        label3 = new JLabel("0");
        label3.setForeground(Color.WHITE);
        label3.setBounds(120, 80, 150, 20);
        add(label3);

        // Change 버튼 생성
        JButton changeBtn = new JButton();
        changeBtn.setSize(60, 60);
        changeBtn.setLocation(50, 200);
        changeBtn.setBackground(Color.white);
        ImageIcon changeBtnIcon = new ImageIcon("renew.png");
        changeBtnIcon.setImage(changeBtnIcon.getImage().getScaledInstance(changeBtn.getWidth(), changeBtn.getHeight(), 0));
        changeBtn.setIcon(changeBtnIcon);
        add(changeBtn);

        // Change 레이블 생성
        JLabel label2 = new JLabel("change needs 1 C-Token");
        label2.setForeground(Color.WHITE);
        label2.setBounds(120, 200, 150, 20);
        add(label2);

        // C-Token 개수 표시 레이블 생성
        label4 = new JLabel("0");
        label4.setForeground(Color.WHITE);
        label4.setBounds(120, 230, 150, 20);
        add(label4);

        // R-Token과 C-Token 개수를 주기적으로 업데이트하는 쓰레드 시작
        TextUpdate update = new TextUpdate();
        update.start();
        setFocusable(false);
    }

    // 텍스트 업데이트를 위한 내부 쓰레드 클래스
    private class TextUpdate extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    // R-Token 개수 업데이트
                    SwingUtilities.invokeLater(() -> label3.setText(gg.getRemoveToken() + "개"));
                    // C-Token 개수 업데이트
                    SwingUtilities.invokeLater(() -> label4.setText(gg.getChangeToken() + "개"));
                    // 500ms 대기
                    sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package Game;

// 해당 프로젝트의 메인 클래스로, 게임을 실행하는 클래스입니다.
public class GameApp {
    // 프로그램의 시작점
    public static void main(String[] args) {
        // LoginFrame 객체를 생성하여 게임을 시작합니다.
        new LoginFrame();
    }
}
package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

// GameApp에서 호출되는 GameFrame이다. Swing을 통해서 창을 구성하고 GameApp에서 해당 클래스가 생성되면 창을 띄워준다.
public class GameFrame extends JFrame {
    private GamePanel gamePanel = null;
    private TextSource textSource = null;
    public GameGround gameGround;
    private ScorePanel scorePanel;

    // GameFrame 생성자
    public GameFrame(String name) {
        setTitle("TypeTris"); // 창 제목
        setSize(800, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 종료 시 프로그램 종료
        scorePanel = new ScorePanel(name);
        Container contentPane = getContentPane();

        makeMenu(); // 메뉴 생성
        textSource = new TextSource(this); // words.txt 파일을 읽어온다.
        gameGround = new GameGround(scorePanel, textSource);

        gamePanel = new GamePanel(textSource, name); // GamePanel(화면 분할 기능 수행)을 생성한다.
        contentPane.add(gamePanel, BorderLayout.CENTER); // 생성한 GamePanel을 추가한다.

        // 포커스 변화를 감지하기 위한 이벤트 리스너 등록
        // KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(
        //        new PropertyChangeListener() {
        //            @Override
        //            public void propertyChange(PropertyChangeEvent evt) {
        //                String prop = evt.getPropertyName();
        //                if ("permanentFocusOwner".equals(prop)) {
        //                    Component newFocus = (Component) evt.getNewValue();
        //                    System.out.println("Focus is on: " + newFocus);
        //                }
        //            }
        //        });

        setResizable(false);
        setVisible(true); // 보이도록 설정
    }

    @Override
    public String toString() {
        return "I'm GameFrame!";
    }

    // 메뉴 생성 메서드
    private void makeMenu() {
        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        JMenu fileMenu = new JMenu("File");
        JMenuItem editItem = new JMenuItem("Edit");
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditFrame();
            }
        });
        fileMenu.add(editItem);
        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mb.add(fileMenu);
    }

    // 편집 프레임 열기 메서드
    private void openEditFrame() {
        // 편집 버튼 클릭 시, 편집 화면 열기
        JFrame editFrame = new JFrame("Edit Words");
        editFrame.setSize(400, 300);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 텍스트 영역 생성
        JTextArea editTextArea = new JTextArea();

        // words.txt 파일 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                editTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            editTextArea.setText("Failed to read words.txt");
        }

        // 텍스트 영역을 스크롤 가능하도록 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(editTextArea);

        // 편집 화면에 스크롤 패널 추가
        editFrame.add(scrollPane);

        // 저장 버튼 추가
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWordsToFile(editTextArea.getText());
                editFrame.dispose(); // 창 닫기
            }
        });

        // 편집 화면에 저장 버튼 추가
        editFrame.add(saveButton, BorderLayout.SOUTH);

        // 화면 중앙에 위치
        editFrame.setLocationRelativeTo(this);
        editFrame.setVisible(true);
    }

    // 단어 저장 메서드
    private void saveWordsToFile(String words) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("words.txt"))) {
            writer.write(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

// GameFrame에서 호출되는 GamePanel 클래스로, 게임의 주요 패널 및 화면 분할 기능을 담당합니다.
public class GamePanel extends JPanel {
    private ScorePanel scorePanel;
    private EditPanel editPanel;
    public GameGround gameGround;

    private TextSource textSource = null;

    // GamePanel 생성자
    public GamePanel(TextSource textSource, String name) {
        this.textSource = textSource;
        scorePanel = new ScorePanel(name);
        gameGround = new GameGround(scorePanel, textSource);
        gameGround.requestFocus();
        editPanel = new EditPanel(gameGround);
        setBackground(Color.black);
        setLayout(new BorderLayout());
        splitPanel();
    }

    @Override
    public String toString() {
        return "I'm GamePanel!";
    }

    // 패널 분할 설정 메서드
    private void splitPanel() {
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 좌우로 나눔
        hPane.setDividerLocation(502); // divider 위치 설정
        add(hPane);

        JSplitPane vPane = new JSplitPane();
        vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); // vertical split
        vPane.setDividerLocation(300); // divider 위치 설정
        vPane.setTopComponent(scorePanel); // 우-상 판넬은 점수 판넬로 설정
        vPane.setBottomComponent(editPanel); // 우-하 판넬은 수정 판넬로 설정

        hPane.setRightComponent(vPane);
        hPane.setLeftComponent(gameGround); // 좌 판넬은 GameGround로 설정한다.
    }
}
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 로그인 화면을 담당하는 LoginFrame 클래스
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // LoginFrame 생성자
    public LoginFrame() {
        setTitle("TypeTris Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지를 그릴 패널 생성
        ImagePanel imagePanel = new ImagePanel("background.png");
        setContentPane(imagePanel);
        imagePanel.setBackground(Color.black);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.setOpaque(false); // 배경을 투명하게 설정

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);

        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);

        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.yellow);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 클릭 시, 게임 화면으로 전환
                if (!usernameField.getText().isEmpty()) {
                    openGameFrame();
                }
            }
        });

        JButton rankButton = new JButton("Ranking");
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 랭킹 버튼 클릭 시, 랭킹 화면 열기
                openRankFrame();
            }
        });
        rankButton.setBackground(Color.GREEN);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(rankButton); // 랭킹 버튼 추가
        loginPanel.add(loginButton);

        imagePanel.add(loginPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // 화면 중앙에 위치
        setVisible(true);
    }

    // 게임 화면 열기 메서드
    private void openGameFrame() {
        String name = usernameField.getText();
        System.out.println(name);
        new GameFrame(name);
        // 현재 창 닫기
        dispose();
    }

    // 랭킹 화면 열기 메서드
    private void openRankFrame() {
        // 랭킹 버튼 클릭 시, 랭킹 화면 열기
        JFrame rankFrame = new JFrame("Ranking");
        rankFrame.setSize(400, 300);
        rankFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 텍스트 영역 생성
        JTextArea rankTextArea = new JTextArea();
        rankTextArea.setEditable(false); // 읽기 전용으로 설정

        // rank.txt 파일 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader("rank.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rankTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            rankTextArea.setText("Failed to read rank.txt");
        }

        // 텍스트 영역을 스크롤 가능하도록 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(rankTextArea);

        // 랭킹 화면에 스크롤 패널 추가
        rankFrame.add(scrollPane);

        // 화면 중앙에 위치
        rankFrame.setLocationRelativeTo(this);
        rankFrame.setVisible(true);
    }

    // 프로그램 시작 메서드
    public static void main(String[] args) {
        // 프로그램 시작 시, 로그인 화면을 보여줌
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }

    // 배경 이미지를 그리는 패널
    private class ImagePanel extends JPanel {
        private Image backgroundImage;

        // ImagePanel 생성자
        public ImagePanel(String imagePath) {
            try {
                this.backgroundImage = new ImageIcon(imagePath).getImage();
                System.out.println("Image loaded successfully");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to load image");
            }
        }

        // 배경 이미지 그리기
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
       

package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;

// 점수를 표시하고 관리하는 ScorePanel 클래스
public class ScorePanel extends JPanel {
    private int score = 0;
    private JLabel scoreLabel = new JLabel(Integer.toString(score)); // score 출력
    private BlockRenderer blockRenderer;
    private BlockUnion spareBlock;
    public String userName;

    // ScorePanel 생성자
    public ScorePanel(String name) {
        userName = name;
        JLabel label = new JLabel("score: ");
        label.setForeground(Color.WHITE);
        add(label);

        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        setBackground(Color.black);
        setFocusable(false);
    }

    // 예비 블록을 설정하는 메서드
    public void setBlockUnion(BlockUnion spareBlock) {
        this.spareBlock = spareBlock;
        repaint();
    }

    // 그림을 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        blockRenderer.drawRect(g, 40, 40, 190, 190);
        spareBlock.draw(g, true);
    }

    // 현재 점수를 반환하는 메서드
    public int getScore() {
        return score;
    }

    // 점수를 증가시키는 메서드
    public void increase(int incScore) {
        score += incScore;
        scoreLabel.setText(Integer.toString(score));
    }

    // 점수를 감소시키는 메서드
    public void decrease(int decScore) {
        score -= decScore;
        if (score < 0)
            score = 0;
        scoreLabel.setText(Integer.toString(score));
    }

    // 사용자 이름을 반환하는 메서드
    public String getName() {
        return userName;
    }

    // 랭킹을 계산하고 파일에 저장하는 메서드
    public void rank() {
        String fileName = "rank.txt";
        List<String> scoreList = loadScoreList(fileName);

        // 현재 유저의 정보 추가
        String userEntry = userName + "," + score;
        scoreList.add(userEntry);

        // 내림차순으로 정렬
        Collections.sort(scoreList, new ScoreComparator());

        // 파일에 저장
        saveScoreList(fileName, scoreList);
    }

    // 파일에서 랭킹 정보를 읽어오는 메서드
    private List<String> loadScoreList(String fileName) {
        List<String> scoreList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                scoreList.add(line);
            }
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 무시
        }

        return scoreList;
    }

    // 랭킹 정보를 파일에 저장하는 메서드
    private void saveScoreList(String fileName, List<String> scoreList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String scoreEntry : scoreList) {
                writer.write(scoreEntry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 랭킹을 비교하기 위한 내부 클래스
    private static class ScoreComparator implements Comparator<String> {
        @Override
        public int compare(String entry1, String entry2) {
            int score1 = Integer.parseInt(entry1.split(",")[1]);
            int score2 = Integer.parseInt(entry2.split(",")[1]);

            // 내림차순으로 정렬
            return Integer.compare(score2, score1);
        }
    }
}

package Game;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// 단어 소스를 관리하는 TextSource 클래스
public class TextSource {
    private Vector<String> wordVector = new Vector<String>(30000); // 단어를 저장하는 벡터

    // TextSource 생성자
    public TextSource(Component parent) {
        try {
            Scanner scanner = new Scanner(new FileReader("words.txt")); // 파일 읽기
            while (scanner.hasNext()) {
                String word = scanner.nextLine();

                // 첫 글자를 대문자로 변경
                if (word.length() > 0) {
                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                }

                // 길이가 6 미만인 단어만 추가
                if (word.length() < 6)
                    wordVector.add(word);
            }
            scanner.close();
            JOptionPane.showMessageDialog(parent, "Reading TextSource success"); // 파일 읽는데 성공 시 다이얼로그 출력
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(0);
        }
    }

    // 랜덤하게 단어를 요청하는 메서드
    public String request() {
        Random random = new Random();
        int index = random.nextInt(wordVector.size());
        return wordVector.get(index);
    }
}
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGround extends JPanel{
	
	private JTextField textInput = new JTextField(20); //입력창
	private ScorePanel mySp; //스코어판넬
	public int removeToken = 0; //쌓인 블록 지우기 토큰
	public int changeToken = 5; //하강블록 모양변경토큰
	private int stage = 1; //스테이지
	private BlockRenderer blockRenderer; //블록렌더러
	BlockUnion blockUnion; //하강블록
	BlockUnion deadBlocks = new BlockUnion("",0,0); //블록 지우기용 합체
	private boolean visibleString = true; //문자보이기
	JButton reqFoc; //버튼
	FallenThread fallTh; //하강 스레드
	private static final int BOX_SIZE = 20; // 각 상자의 크기
    private static final int NUM_BOXES_X = 800 / BOX_SIZE; // x 방향 상자의 개수 (800은 패널의 가로 크기)
    private static final int NUM_BOXES_Y = 600 / BOX_SIZE; // y 방향 상자의 개수 (600은 패널의 세로 크기)
	
	private TextSource ts;
	public GameGround(ScorePanel sp, TextSource ts) {
		deadBlocks.unionColor=Color.gray; //죽은 블록은 회색이다.
		mySp = sp; //대입
		
		this.ts = ts; //대입
		setBackground(Color.white);//배경색 설정
		setLayout(null);//널 레이아웃
		
		textInput.setSize(300,20);
		textInput.setLocation(100,640);
		textInput.addActionListener(new WriteAnswer());//답입력시 액션리스너 연결
		add(textInput); //텍스트 인풋
		
		prepareBlockUnions("init"); // 단어를 블록으로 분리하고 초기 위치에 배치
		
		fallTh = new FallenThread();//블록하강 스레드
		fallTh.start();//run안됨 start할것.
		
		StageThread stageTh = new StageThread();//점수기반 스테이지 설정 스레드
		stageTh.start();
		
		reqFoc = new JButton();
		reqFoc.setSize(60, 60);
		reqFoc.setLocation(30, 620);
		// Set image for reqFoc button
		ImageIcon reqFocIcon = new ImageIcon("bomb.png");
		reqFocIcon.setImage(reqFocIcon.getImage().getScaledInstance(reqFoc.getWidth(), reqFoc.getHeight(), 0));//사이즈설정
        reqFoc.setIcon(reqFocIcon);
        reqFoc.setBackground(Color.white);
		reqFoc.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(removeToken >= 5) {
		            removeToken -= 5; //토큰줄이고
		            deadBlocks.countBlock();//줄별 블록 세고
		            int cnt = 0;
		            for(int i = 0; i < 30; i++) {
		                if(deadBlocks.blockCountArray[i] == 25) {
		                    deadBlocks.deleteLine(i);//가득찬 라인삭제
		                    cnt++;
		                }
		            }
		            mySp.increase(500 * cnt);//점수증가
		        }
		    }
		});
		add(reqFoc); //bomb버튼인데 이름 까먹고 안빠꿈

		// Similarly for changeBtn...

		JButton changeBtn = new JButton();
		changeBtn.setSize(60, 60);
		changeBtn.setLocation(410, 620);
		changeBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon changeBtnIcon = new ImageIcon("renew.png");
		changeBtnIcon.setImage(changeBtnIcon.getImage().getScaledInstance(changeBtn.getWidth(), changeBtn.getHeight(), 0));
        changeBtn.setIcon(changeBtnIcon);

		changeBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(changeToken >= 0) {
		            blockUnion.change();//모양변경
		            changeToken--;//토큰감소
		            requestFocus();//포커스 넘기기
		        }
		    }
		});
		add(changeBtn);
        
		JButton stopBtn = new JButton();
		stopBtn.setSize(60, 60);
		stopBtn.setLocation(260, 690);
		stopBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon stopBtnIcon = new ImageIcon("stop.png");
		stopBtnIcon.setImage(stopBtnIcon.getImage().getScaledInstance(stopBtn.getWidth(), stopBtn.getHeight(), 0));
		stopBtn.setIcon(stopBtnIcon);

		stopBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        fallTh.stopGame();//게임 중지
		    }
		});
		add(stopBtn);
		
		JButton resumeBtn = new JButton();
		resumeBtn.setSize(60, 60);
		resumeBtn.setLocation(180, 690);
		resumeBtn.setBackground(Color.white);
		// Set image for changeBtn button
		ImageIcon resumeBtnIcon = new ImageIcon("play.png");
		resumeBtnIcon.setImage(resumeBtnIcon.getImage().getScaledInstance(resumeBtn.getWidth(), resumeBtn.getHeight(), 0));
		resumeBtn.setIcon(resumeBtnIcon);

		resumeBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        fallTh.resumeGame();//게임 재게
		    }
		});
		add(resumeBtn);
		
        requestFocus();
		addKeyListener(new BlockUnionKeyListener()); // 키 이벤트 리스너 추가
	}
	
	class FallenThread extends Thread {
	    private boolean stopGame = true;

	    public void stopGame() {
	        stopGame = false;
	    }

	    public void resumeGame() {
	        stopGame = true;
	        synchronized (this) {
	            notify();//wait해제
	        }
	    }

	    @Override
	    public void run() {
	        try {
	            while (true) {
	                if (blockUnion.isCollisionBlockWall(-100, 600, 600) || blockUnion.isCollisionU(deadBlocks)) {
	                    deadBlocks.add(blockUnion);
	                    prepareBlockUnions("collision Detect");

	                    repaint(); // notify() 전에 repaint() 호출
	                } else {
	                    moveBlock(blockUnion, 0, +1);
	                    sleep(((9000 / stage) / 5) + 400 / stage);

	                    repaint(); // 이 부분도 추가
	                }

	                if (!stopGame) {
	                    synchronized (this) {
	                        wait();
	                    }
	                }
	            }
	        } catch (InterruptedException e) {
	            return;
	        }
	    }
	}
	
	public int getRemoveToken() {
		return removeToken;
	}
	
	public int getChangeToken() {
		return changeToken;
	}
	
	@Override
	public String toString() {
		return "i'm GameGround에요";
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public void setBlockUnion(BlockUnion blockUnion) {
        this.blockUnion = blockUnion;
    }
	
	public class WriteAnswer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField tf = (JTextField)e.getSource();
			if(tf.getText().equals(blockUnion.getBlockWord())){
				mySp.increase(100);//정답맞추면 점수증가
				blockCorrect(blockUnion,12,-2);//블록 새로 생성
				removeToken++;//정답맞추면 토큰증가
			}else {
				//mySp.decrease(5);
			}
			tf.setText("");
			requestFocus();
		}
	}
	
	private void prepareBlockUnions(String msg) {
		if(msg.equals("init")) {
			String initWord = ts.request();
			int x = 12; // 초기 x 위치
			int y = -2; // 초기 y 위치
			blockUnion = new BlockUnion(initWord, x, y); //사용할 블럭
			
			String word2 = ts.request();
			BlockUnion spareBlock = new BlockUnion(word2,5,5);
			mySp.setBlockUnion(spareBlock); //spare블록
			
		}else {
			blockUnion = mySp.getBlockUnion();
			blockUnion.setLocation(12, -2);
			
			
			String spareWord = ts.request();
			int x = 5; // 초기 x 위치
			int y = 5; // 초기 y 위치

			BlockUnion spareBlock = new BlockUnion(spareWord,x,y);
			mySp.setBlockUnion(spareBlock);
		}
    }

	private class BlockUnionKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if(blockUnion.isCollisionBlockWall(1, 1000, 1000) || blockUnion.isCollisionR(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,-1,0);	
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(blockUnion.isCollisionBlockWall(-100, 499, 1000) || blockUnion.isCollisionL(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,+1,0);	
                }
                break;
            case KeyEvent.VK_UP:
                blockUnion.rotateClockwise();
                repaint();
                //moveBlockUnionSub(blockUnion,0,-1);
                break;
            case KeyEvent.VK_DOWN:
                if(blockUnion.isCollisionBlockWall(-100, 1000, 600) || blockUnion.isCollisionU(deadBlocks)) {
                	moveBlock(blockUnion,0,0);	
                }else {
                    moveBlock(blockUnion,0,+1);	
                }
                break;
            case KeyEvent.VK_A: // 추가한 부분
                textInput.requestFocus();
                break;
        }
    }
}

	 @Override
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        blockUnion.draw(g,visibleString);
	        deadBlocks.draw(g,true);
	        BlockRenderer.drawRect(g, 0, 600, 500, 185);
	 }
	
	 public void moveBlock(BlockUnion blockUnion, int deltaX, int deltaY) {
	        int newX = blockUnion.getX() + deltaX;
	        int newY = blockUnion.getY() + deltaY;
	        
	        blockUnion.setLocation(newX, newY);
	        repaint(); // 화면 다시 그리기
	  }
	 
	 public void blockCorrect(BlockUnion blockUnion, int deltaX, int deltaY) {
	        int newX = deltaX;
	        int newY = deltaY;
	        prepareBlockUnions("play");
	        blockUnion.setLocation(newX, newY);
	        
	        repaint(); // 화면 다시 그리기
	    }
	 
	 public void gameOver() {
	        System.out.println("gameOver");

	        // 스레드 정지
	        fallTh.stopGame();

	        // 작은 창 띄우기
	        int result = JOptionPane.showConfirmDialog(
	                null,
	                "Game Over\nDo you want to register your score?",
	                "Game Over",
	                JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	            // 예를 선택한 경우 랭킹 등록 함수 호출
	            mySp.rank();
	        }
	        System.exit(0);
	    }
	 
	class StageThread extends Thread{ //thread는 cpu가 실행하는 작업의 단위다.
		@Override
		public void run() {
			while(true) {
				try {
					stage = (mySp.getScore()/100) + 1;
					if(stage>10) {
						if(visibleString) {
							visibleString = false;
						}else{
							visibleString = true;
						}
					}
					if(deadBlocks.getY() <= 1) {
						gameOver();
					}
					sleep(1300);
					//System.out.println(stage);
				}catch(InterruptedException e) {
					return;
				}
			}
		}
	}
}
게임 화면 초기화 및 필요한 변수 선언
게임 화면의 속성 설정 및 기능 초기화
블록이 떨어지는 동작을 담당하는 FallenThread클래스
게임 화면의 그리기를 담당하는 paintComponent메서드
블록 이동, 정답 입력 등의 동작 처리 메서드
게임 오버 처리 및 스테이지 업데이트를 담당하는 StageThread클래스
