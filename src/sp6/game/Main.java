package sp6.game;

import sp6.engine.GameEngine;
import sp6.engine.GameWorld;

public class Main {
	public static void main(String [] args) {
		GameEngine g = new GameEngine(640,480,"TestGame");
		g.startLoop();

		GameWorld w = new GameWorld();
		
		SpriteSheet heroSheet = new SpriteSheet("zelda.png");
		SpriteSheet baddieSheet = new SpriteSheet("baddies.png");
		
		Character link = new Character(heroSheet.getSprite(0, 0, 16, 16), 16, 16, 60, 60);
		Character baddie1 = new Character(baddieSheet.getSprite(91, 180, 47, 16), 47, 16, 40, 40);
		
		w.addGameObject(link);
		w.addGameObject(baddie1);
		
		g.attachWorld(w);
		
	}
}