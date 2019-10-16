package game;

import game.enemy.Enemy;
import game.player.Player;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    ArrayList<Enemy> enemies;
    Player player;
    Background bg;
    Random rd = new Random();

    public GamePanel(){
        enemies = new ArrayList<>();
        bg = new Background();
        player = new Player();
    }


    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if(object.active){
                object.render(g);
            }
        }
    }

    public void gameLoop(){
        long lastLoop = 0;
        long delay = 1000/60;
        while(true){
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastLoop > delay){
                runAll();//logic game
                renderAll();// render image cua game
                lastLoop = currentTime;
            }
        }
    }

    //enemy spawn
    int summonCount = 0;
    int wayCount = 0;
    int enemyCount = 0;
    int enemyX = 100 + rd.nextInt(200);
    private void summonEnemies(){
        wayCount++;
        if(wayCount > 120){
            summonCount++;
            if(summonCount > 15){
                //Enemy enemy = new Enemy();
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(enemyX,-100);
                enemy.velocity.setAngle(Math.PI/9);
                enemies.add(enemy);
                summonCount = 0;
                enemyCount++;
                if(enemyCount > 4){
                    wayCount = 0;
                    enemyCount = 0;
                    enemyX = 100 + rd.nextInt(200);
                }
            }
        }
    }


    private void renderAll() {
        repaint();// goi lai ham paint()
    }

    private void runAll() {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if (object.active){
                object.run();
            }
        }
        summonEnemies();
    }


}
