package game.player;

import game.GameObject;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;


public class PlayerBullet extends GameObject {

    int damage;

    public PlayerBullet(){
        renderer = new Renderer("assets/images/player-bullets/a");
        velocity.set(0,-5);
        hitBox = new BoxCollider(this,24,24);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        checkDeactiveIfNeeded();
        checkIntersects();
    }

    private void checkIntersects() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this);
        if(enemy != null){
            this.deactive();
            enemy.takeDamage(damage);
        }
    }

    private void checkDeactiveIfNeeded() {
        if (this.position.y < -30){
            this.deactive();
        }
    }
}
