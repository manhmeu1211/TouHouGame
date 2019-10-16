package game.player;

import game.GameObject;
import game.renderer.Renderer;

public class PlayerExplosion extends GameObject {

    public PlayerExplosion(){
        renderer = new Renderer("assets/images/players/explosions");
        position.set(0,0);
    }

}
