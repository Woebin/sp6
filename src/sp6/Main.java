

import sp6.engine.BaseObject;
import sp6.engine.GameWorld;
import sp6.engine.Loader;

import java.util.List;

/**
 * Collision på pixelnivå - alpha
 * vector som hanterar riktning och hastighet
 * precollision och postcollision
 * single animation som spelas en gång (typ explosion)
 */
public class Main {
    public static void main(String [] args) {
        GameWorld gameWorld = new GameWorld(new Loader());
        gameWorld.startGameLoop();
    }
}
