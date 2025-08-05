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
