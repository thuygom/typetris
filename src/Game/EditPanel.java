package Game;

import javax.swing.*;
import java.awt.Color;

public class EditPanel extends JPanel {
    private GameGround gg;
    JLabel label3, label4;

    public EditPanel(GameGround gg) {
        setBackground(Color.black);
        setLayout(null);
        this.gg = gg;
        JButton reqFoc = new JButton();
        reqFoc.setSize(60, 60);
        reqFoc.setLocation(50, 50);
        ImageIcon reqFocIcon = new ImageIcon("bomb.png");
        reqFocIcon.setImage(reqFocIcon.getImage().getScaledInstance(reqFoc.getWidth(), reqFoc.getHeight(), 0));
        reqFoc.setIcon(reqFocIcon);
        reqFoc.setBackground(Color.white);
        add(reqFoc);

        JLabel label = new JLabel("bomb needs 5 R-Token");
        label.setForeground(Color.WHITE);
        label.setBounds(120, 50, 150, 20);
        add(label);

        label3 = new JLabel("0");
        label3.setForeground(Color.WHITE);
        label3.setBounds(120, 80, 150, 20);
        add(label3);

        JButton changeBtn = new JButton();
        changeBtn.setSize(60, 60);
        changeBtn.setLocation(50, 200);
        changeBtn.setBackground(Color.white);
        ImageIcon changeBtnIcon = new ImageIcon("renew.png");
        changeBtnIcon.setImage(changeBtnIcon.getImage().getScaledInstance(changeBtn.getWidth(), changeBtn.getHeight(), 0));
        changeBtn.setIcon(changeBtnIcon);
        add(changeBtn);

        JLabel label2 = new JLabel("change needs 1 C-Token");
        label2.setForeground(Color.WHITE);
        label2.setBounds(120, 200, 150, 20);
        add(label2);

        label4 = new JLabel("0");
        label4.setForeground(Color.WHITE);
        label4.setBounds(120, 230, 150, 20);
        add(label4);

        textUpdate update = new textUpdate();
        update.start();
        setFocusable(false);
    }

    private class textUpdate extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    SwingUtilities.invokeLater(() -> label3.setText(gg.getRemoveToken() + "개"));
                    SwingUtilities.invokeLater(() -> label4.setText(gg.getChangeToken() + "개"));
                    sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
