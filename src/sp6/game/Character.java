package sp6.game;

import java.awt.image.BufferedImage;

import sp6.engine.GameObject;
import sp6.engine.controllables.GraphicsControllable;

public class Character extends GameObject implements GraphicsControllable {

	private BufferedImage sprite;
	
	public Character(BufferedImage image, int w, int h, int x, int y, int aR, int aN){
		this.sprite = image;
		this.setDimensions(w, h, x, y, 1, aR, aN);
	}
	
	@Override
	public BufferedImage getSprite() {
		return sprite;
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}

}
