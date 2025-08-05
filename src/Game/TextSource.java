package Game;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextSource {
	private Vector<String> wordVector = new Vector<String>(30000);//()에는 size가 들어간다.
	public TextSource(Component parent) {
	    try {
	        Scanner scanner = new Scanner(new FileReader("words.txt")); // 파일 읽기
	        while (scanner.hasNext()) {
	            String word = scanner.nextLine();

	            // 첫 글자를 대문자로 변경
	            if (word.length() > 0) {
	                word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
	            }

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

	
	public String request() {
		Random random = new Random();
		int index = random.nextInt(wordVector.size());
		return wordVector.get(index);
	}
}
