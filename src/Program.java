import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setTitle("Game Touhou");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(800,600));
        panel.setBackground(Color.CYAN);
        window.add(panel);
        window.pack();
        window.setVisible(true);
        panel.gameLoop();

    }
}
