package game.enemy;

import game.GameObject;
import game.renderer.Renderer;

public class EnemyExplosion extends GameObject {
    public EnemyExplosion() {
        this.renderer = new Renderer(
                "assets/images/enemies/explosion",
                true
        );
    }
}
