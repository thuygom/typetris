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

public class ScorePanel extends JPanel {
	private int score = 0;
	private JLabel scoreLabel = new JLabel(Integer.toString(score)); //score출력
	private BlockRenderer blockRenderer;
	private BlockUnion spareBlock;
	public String userName;
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
	
	public BlockUnion getBlockUnion() {
		return spareBlock;
	}
	
	public void setBlockUnion(BlockUnion spareBlock) {
		this.spareBlock = spareBlock;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    blockRenderer.drawRect(g, 40, 40,190,190);
	    spareBlock.draw(g,true);
	}
	
	public int getScore() {
		return score;
	}
	
	public void increase(int incScore) { //점수 증가 함수
		score += incScore;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public void decrease(int decScore) { //점수 감소 함수
		score -= decScore;
		if(score < 0)
			score = 0;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public String getName() {
		return userName;
	}
	
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
