import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    BufferedImage playerImage;
    Vector2D playerPosition;

    BufferedImage backgroundImage;
    Vector2D backgroundPosition;

    BufferedImage bulletImage;
    ArrayList<Vector2D> bulletPositions;


    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        playerPosition = new Vector2D(100, 320);

        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        backgroundPosition = new Vector2D(0, 600 - backgroundImage.getHeight());

        bulletImage = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        bulletPositions = new ArrayList<>();

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
        g.drawImage(backgroundImage, (int) backgroundPosition.x, (int) backgroundPosition.y, null);

        g.drawImage(playerImage, (int) playerPosition.x, (int) playerPosition.y, null);

        for (int i = 0; i < bulletPositions.size() ; i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            g.drawImage(bulletImage, (int) bulletPosition.x, (int) bulletPosition.y, null);
        }
    }

    private void runAll() {
        backgroudMove();
        playerMove();
        playerLimited();
        bulletsRun();

    }

    private void bulletsRun() {
        for (int i = 0; i < bulletPositions.size() ; i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            bulletPosition.add(0, -1);
        }
    }

    private void playerLimited() {
        //player limited position
        if (playerPosition.x > backgroundImage.getWidth() - playerImage.getWidth()) {
            playerPosition.set(backgroundImage.getWidth() - playerImage.getWidth(), playerPosition.y);
        }
        if (playerPosition.y < 0) {
            playerPosition.set(playerPosition.x, 0);
        }
        if (playerPosition.y > 600 - playerImage.getHeight()) {
            playerPosition.set(playerPosition.x, 600 - playerImage.getHeight());
        }
    }

    private void playerMove() {
        int playerSpeed = 5;
        int bulletSpeed = 5;
        int vx = 0;
        int vy = 0;
        if (GameWindow.isUpPress) {
            vy -= playerSpeed;
        }
        if (GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if (GameWindow.isRightPress) {
            vx -= playerSpeed;
        }
        if (GameWindow.isLeftPress) {
            vx += playerSpeed;
        }
        playerPosition.add(vx, vy);

        if (playerPosition.x < 0) {
            playerPosition.x = 0;
        }

    }

    private void backgroudMove() {
        backgroundPosition.add(0, 5);
        if (backgroundPosition.y > 0) {
            backgroundPosition.set(backgroundPosition.x, 0);
        }
    }
}

