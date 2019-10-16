package game.enemy;

import game.GameObject;
import game.Setting;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {

    int damage;

    public EnemyBullet(){
        BufferedImage image;
        image = SpriteUtils.loadImage("assets/images/enemies/bullets/green.png");
        renderer = new Renderer(image);
        velocity.set(0, 5);
        hitBox = new BoxCollider(this,16,16);
        damage = 1;
    }

    @Override
    public void run(){
        super.run();
        deactiveIfNeeded();
        checkIntesects();
    }

    private void deactiveIfNeeded() {
        if (this.position.y > Setting.GAME_HEIGHT + 50){
            this.deactive();
        }
    }

    private void checkIntesects(){
        Player player = GameObject.findIntersects(Player.class, this);
        if(player != null){
            this.deactive();
            player.takeDamage(damage);
        }
    }

}
