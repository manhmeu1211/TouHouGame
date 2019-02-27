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

    private final int END_LEFT = 740;
    private final int END_RIGHT = 350;
    private final int END_TOP = 0;
    private final int END_BOTTOM = 520;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerX = 100;
        playerY = 100;
        backgroundX = 0;
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
        backgroundY += 5;
        if (backgroundY > 0) {
            backgroundY = 0;
        }
        //player move
        int playerSpeed = 5;
        if (GameWindow.isUpPress) {
            playerY -= playerSpeed;
        }
        if (GameWindow.isDownPress) {
            playerY += playerSpeed;
        }
        if (GameWindow.isRightPress) {
            playerX -= playerSpeed;
        }
        if (GameWindow.isLeftPress) {
            playerX += playerSpeed;
        }
        if (playerX < 0) {
            playerX = 0;
        }
        //player limited position
        if(playerX > backgroundImage.getWidth() - playerImage.getWidth()){
            playerX = backgroundImage.getWidth() - playerImage.getWidth();
        }
        if(playerY < 0){
            playerY = 0;
        }
        if(playerY > 600 - playerImage.getHeight()){
            playerY = 600 - playerImage.getHeight();
        }
    }
}

