package sp6.engine;

import sp6.engine.controller.*;

import java.util.List;

/**
 * Den mest avancerade. Kan ha fysik, kollision, animering etc. Rörlig eller statisk.
 */
public abstract class GameObject {
	private int width, height;
	private int posX, posY;
	
	public void setDimensions(int w, int h, int x, int y) {
		this.width = w;
		this.height = h;
		this.posX = x;
		this.posY = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public abstract void updateState();
	
	
}
