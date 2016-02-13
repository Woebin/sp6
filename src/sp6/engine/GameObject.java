package sp6.engine;

import sp6.engine.controller.*;

import java.util.List;

/**
 * Den mest avancerade. Kan ha fysik, kollision, animering etc. Rörlig eller statisk.
 */
public abstract class GameObject {
	private int width, height;
	private int posX, posY, posZ, animRows, animNumber;
	
	/**
	 * 
	 * @param w		Width of object.
	 * @param h		Height of object.
	 * @param x		X-axis positioning.
	 * @param y		Y-axis positioning.
	 * @param z 	Z-level position to determine what gets drawn on what layer + collision probably.
	 * @param aR 	Number of rows in spritesheet belonging to this object.
	 * @param aN	Number of sprites per row in spritesheet belonging to this object.
	 */
	public void setDimensions(int w, int h, int x, int y, int z, int aR, int aN) {
		this.width = w;
		this.height = h;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.animRows = aR;
		this.animNumber= aN;
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
	
	public int getPosZ(){
		return posZ;
	}
	
	/**
	 *  
	 * @return total number of sprites for animation purposes.
	 */	
	public int getAnimNumber(){
		return animNumber * animRows;
	}
	
	public abstract void updateState();
	
	
}
