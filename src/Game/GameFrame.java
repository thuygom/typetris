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
//GameApp에서 호출되는 GameFrame이다. Swing을 통해서 창을 구성하고 GameApp에서 해당 클래스가 생성되면 창을 띄워준다.
public class GameFrame extends JFrame {	
	private GamePanel gamePanel = null;
	private TextSource textSource = null;
	public GameGround gameGround;
	private ScorePanel scorePanel;
	public GameFrame(String name) {
		setTitle("TypeTris"); //Title
		setSize(800,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame 종료 시 프로그램 종료
		scorePanel = new ScorePanel(name);
		Container contentPane = getContentPane();
		
		makeMenu(); //menu 생성
		textSource = new TextSource(this); //words.txt파일을 읽어온다.
		gameGround = new GameGround(scorePanel,textSource);
		
		gamePanel = new GamePanel(textSource, name); //GamePanel(화면 분할 기능 수행)을 생상힌다.
		contentPane.add(gamePanel,BorderLayout.CENTER); //추가해준다.
		
		// 포커스 변화를 감지하기 위한 이벤트 리스너 등록
		//KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(
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
		setVisible(true); //보이도록 설정
	}
	
	@Override
	public String toString() {
		return "i'm GameFrame에요";
	}
	
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

	private void saveWordsToFile(String words) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("words.txt"))) {
	        writer.write(words);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
