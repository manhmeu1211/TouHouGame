import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    BufferedImage playerImage;
    int playerX;
    int playerY;

    BufferedImage backgroundImage;
    int backgroundX;
    int backgroundY;

    private final int END_LEFT = 384;
    private final int END_RIGHT = 740;
    private final int END_TOP = 0;
    private final int END_BOTTOM = 520;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerX = 400;
        playerY = 100;
        backgroundX = backgroundImage.getWidth();
        backgroundY = 600 - backgroundImage.getHeight();

    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay) {
                runAll(); // logic game
                renderAll(); // render ảnh game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint(); // callback hàm paint
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, backgroundX, backgroundY, null);
        g.drawImage(playerImage, playerX, playerY, null);
    }

    private void runAll() {
        backgroundY += 10;
        if (backgroundY > 0) {
            backgroundY = 0;
        }
        if (GameWindow.isUpPress && playerY > END_TOP) {
            playerY -= 3;
        }
        if (GameWindow.isDownPress && playerY < END_BOTTOM) {
            playerY += 3;

        }
        if (GameWindow.isRightPress && playerX > END_LEFT) {
            playerX -= 3;
        }
        if (GameWindow.isLeftPress && playerX < END_RIGHT) {
            playerX += 3;
        }
    }
}

