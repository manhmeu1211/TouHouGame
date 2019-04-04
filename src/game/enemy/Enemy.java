package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {
    int fireCount;
    int hp;

    public Enemy() {
        renderer = new Renderer("assets/images/enemies/level0/pink");
        velocity.set(0, 3);
        fireCount = 0;
        hitBox = new BoxCollider(this, 28, 28);
        hp = 3;
    }

    static Font font = new Font("Verdana"
            , Font.BOLD, 32);
    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString(hp + ""
            , (int) position.x
            , (int) position.y);
    }

    @Override
    public void run() {
        super.run();
        changeDirection();
        fire();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT + 50) {
            this.deactive();
        }
    }

    private void fire() {
        fireCount++;
        if(fireCount > 120) {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(this.position);
            fireCount = 0;
        }
    }

    private void changeDirection() {
        if(position.x > Settings.BACKGROUND_WIDTH - 28 && velocity.x > 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if(position.x < 0 && velocity.x < 0) {
            velocity.set(-velocity.x, velocity.y);
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        hp = 3;
    }

    @Override
    public void deactive() {
        super.deactive(); // active = false;
        EnemyExplosion explosion = GameObject.recycle(EnemyExplosion.class);
        explosion.position.set(position);
    }
}
