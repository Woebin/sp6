package sp6.game;

import sp6.engine.GameEngine;
import sp6.engine.Level;

/**
 * I don't know what I'm doing. This just draws a couple of sprites in an otherwise black window right now. Okay.
 * Now it draws a background too. That's an improvement, right?
 * @author Theo Walther
 *
 */
public class Main {
	public static void main(String [] args) {
		GameEngine g = new GameEngine(640,480,"TestGame");
		g.startLoop();

		Level w = new Level();
		
		SpriteSheet heroSheet = new SpriteSheet("zelda.png");
		SpriteSheet baddieSheet = new SpriteSheet("baddies.png");
		SpriteSheet bgSheet = new SpriteSheet("backgroundtest.png");
		
		Background screen1 = new Background(bgSheet.getSprite(0, 480, 640, 480), 640, 480, 0, 0);		
		Character baddie1 = new Character(baddieSheet.getSprite(293, 176, 56, 24), 56, 24, 40, 40, 2, 4);
		Character link = new Character(heroSheet.getSprite(0, 0, 16, 16), 16, 16, 150, 100, 6, 4);
		
		w.addGameObject(screen1);
		w.addGameObject(link);
		w.addGameObject(baddie1);
		
		g.attachWorld(w);
		
	}
}