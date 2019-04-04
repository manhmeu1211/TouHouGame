package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setTitle("Game Touhou");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));
        panel.setBackground(Color.CYAN);

        window.add(panel);
        window.pack();

        window.setVisible(true);

        panel.gameLoop();

//        String s = "a4, b1, c2, d0";
//        // d0 - b1 - c2 - a4
//        java.util.List<String> list = Arrays.asList(s.split(","));
//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int i1 = Integer.parseInt(o1.trim().substring(1));
//                int i2 = Integer.parseInt(o2.trim().substring(1));
//                return i1 - i2;
//            }
//        });
//
//        for(String str : list) {
//            System.out.println(str);
//        }
    }
}
