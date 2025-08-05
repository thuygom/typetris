package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class GamePanel extends JPanel{
	
	private ScorePanel scorePanel;
	private EditPanel editPanel;
	public GameGround gameGround;
	
	private TextSource textSource = null;
	
	public GamePanel(TextSource textSource, String name) {
		this.textSource = textSource;
		scorePanel = new ScorePanel(name);
		gameGround = new GameGround(scorePanel,textSource);
		gameGround.requestFocus();
		editPanel = new EditPanel(gameGround);
		setBackground(Color.black);
		setLayout(new BorderLayout());
		splitPanel();
	}
	
	@Override
	public String toString() {
		return "i'm GamePanel에요";
	}
	
	private void splitPanel() {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); //좌우로 나눔
		hPane.setDividerLocation(502); //divider 위치 설정
		add(hPane);
		
		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); //vertical split
		vPane.setDividerLocation(300); //divider 위치 설정
		vPane.setTopComponent(scorePanel); //우-상 판넬은 점수 판넬로 설정
		vPane.setBottomComponent(editPanel); //우-하 판넬은 수정 판넬로 설정
		
		hPane.setRightComponent(vPane);
		hPane.setLeftComponent(gameGround); //좌 판넬은 GameGround로 설정한다.
		
	}
}