package sp6.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage sheet;

	public SpriteSheet(String fName){
		try {
          sheet = ImageIO.read(new File(fName));
      } catch (IOException e) {
          e.printStackTrace();
      }
	}
	
	public BufferedImage getSprite(int x, int y, int w, int h){
		return this.sheet.getSubimage(x, y, w, h);
	}
}
