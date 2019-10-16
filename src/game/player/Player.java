package game.player;

import game.GameObject;
import game.GameWindow;
import game.Setting;
import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;

public class Player extends GameObject {

    int hp;

    public Player(){
        renderer = new Renderer("assets/images/players/straight");
        position.set(300, 500);
        hitBox = new BoxCollider(this,32,48);
        hp = 20;
    }

    static Font font = new Font("Verdana", Font.BOLD, 22);
    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString(hp + "", (int) position.x, (int) position.y);
    }

    // limit of player's motion
    private void limit() {
        if(position.y - anchor.y * Setting.PLAYER_HEIGHT < 0) position.y = anchor.y * Setting.PLAYER_HEIGHT;
        if(position.y - anchor.y * Setting.PLAYER_HEIGHT >= Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT){
            position.y = Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT + anchor.y * Setting.PLAYER_HEIGHT;
        }
        if(position.x - anchor.x * Setting.PLAYER_WIDTH >= Setting.BACKGROUND_WIDTH - Setting.PLAYER_WIDTH){
            position.x = Setting.BACKGROUND_WIDTH - Setting.PLAYER_WIDTH + anchor.x * Setting.PLAYER_WIDTH;
        }
        if(position.x - anchor.x * Setting.PLAYER_WIDTH < 0) position.x = anchor.x * Setting.PLAYER_WIDTH;
    }

    // player's motion
    private void move() {
        int speed = 3;
        int vx = 0, vy = 0;
        if(GameWindow.isUpPress){
            vy -= speed;
        }
        if(GameWindow.isDownPress){
            vy += speed;
        }
        if(GameWindow.isRightPress){
            vx += speed;
        }
        if(GameWindow.isLeftPress){
            vx -= speed;
        }
        velocity.set(vx, vy);
        velocity.setLength(speed);
    }

    // player fire
    int fireCount = 0;
    private void fire() {
        fireCount++;
        if(GameWindow.isFirePress && fireCount > 20){
            for (int i = 0; i < 6; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(position.x, position.y);
                bullet.velocity.setAngle(-Math.PI/3 - i * (Math.PI/15));
            }
            fireCount = 0;
        }
    }

    public void takeDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            PlayerExplosion pe = new PlayerExplosion();
            pe.position.set(position.x, position.y);
            this.deactive();
        }
    }

    @Override
    public void run(){
        super.run();
        move();
        limit();
        fire();
    }

}
