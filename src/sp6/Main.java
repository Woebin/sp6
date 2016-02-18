package sp6;

import sp6.engine.BaseObject;
import sp6.engine.GameWorld;
import sp6.engine.Loader;

import java.util.List;

/**
 * Collision at pixel level, alpha vector handling direction and velocity,
 * precollision and postcollision, single animation that plays once (i.e.
 * explosions)
 */
public class Main {
	public static void main(String[] args) {
		GameWorld gameWorld = new GameWorld(new Loader());
		gameWorld.startGameLoop();
	}
}
