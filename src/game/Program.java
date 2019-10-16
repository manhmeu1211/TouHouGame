package game;

import javax.swing.*;
import java.awt.*;

public class Program {

    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setTitle("game Touhou");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(Setting.GAME_WIDTH, Setting.GAME_HEIGHT));
        panel.setBackground(Color.BLUE);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panel.gameLoop();
    }
}