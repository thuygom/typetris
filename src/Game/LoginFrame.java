package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

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
            	if(usernameField.getText() != "") {
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

    private void openGameFrame() {
        // 로그인 성공 시, 게임 화면으로 전환
    	String name = usernameField.getText();
    	System.out.println(name);
        new GameFrame(name);
        // 현재 창 닫기
        dispose();
    }

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

        public ImagePanel(String imagePath) {
            try {
                this.backgroundImage = new ImageIcon(imagePath).getImage();
                System.out.println("Image loaded successfully");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to load image");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            if (backgroundImage != null) {
                return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
            } else {
                return super.getPreferredSize();
            }
        }
    }


}
