package sp6;

import sp6.engine.BaseObject;
import sp6.engine.GameWorld;
import sp6.engine.Loader;

import java.util.List;


public class Main {
    public static void main(String [] args) {
        Loader loader = new Loader();
        loader.parse();
        List<BaseObject> baseObjects = loader.getBaseObjects();
        GameWorld gameWorld = new GameWorld(baseObjects);
        gameWorld.startGameLoop();
    }
}
