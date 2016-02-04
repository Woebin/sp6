package sp6.game;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationController {
    private BufferedImage spriteSheet = null;
 //   private int TILE_SIZE = 95;
 //   private ArrayList<BufferedImage> images = new ArrayList<>();
 //   private int imagesIdxAnim = 0;
 //   private int imagesIdxPlayOnce = 0;
 //   double startTime = 0;
 //   double elapsedTime = 0;
    private HashMap<String, Animation> animations = new HashMap<>();

	public AnimationController() {
        try {
//            File file = new File("dummy.txt");
//            file.createNewFile();
            spriteSheet = ImageIO.read(new File("Aliens_Enemies_cropped.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        testPopulation();
    }


    private void testPopulation() {
    	animations.put("alien1", new Animation("alien1", spriteSheet, 24, 24, 48, 64, 6, 1));
    	animations.put("flamingo", new Animation("flamingo", spriteSheet, 24, 104, 48, 64, 11, 3));
    	animations.put("piranha", new Animation("piranha", spriteSheet, 24, 238, 48, 64, 18, 8));
    }

    public void playRepeat(Graphics2D graphics, String name, int direction, AnimationMovement animationMovement) {
    	playRepeat(graphics, name, animationMovement.getCoordinate().getX(), animationMovement.getCoordinate().getY(), direction);
    }

    public void playRepeat(Graphics2D graphics, String name, int direction, int x, int y) {
    	
    	if (direction == -1) {
	 		AffineTransform translate = AffineTransform.getTranslateInstance(animations.get(name).getWidth(), 0);
	        AffineTransform scale = AffineTransform.getScaleInstance(-1d, 1d);
	        translate.concatenate(scale);
	        AffineTransform original = graphics.getTransform();
	        graphics.setTransform(translate);
	        x = -x;

			graphics.drawImage(animations.get(name).playRepeat(), x, y, null);
			graphics.setTransform(original);
		} else {		
			graphics.drawImage(animations.get(name).playRepeat(), x, y, null);
		}

//    	return animations.get(name).playRepeat();
    }

    public void playStatic(Graphics2D graphics, String name, int direction, int x, int y, int idx) {

    	if (direction == -1) {
	 		AffineTransform translate = AffineTransform.getTranslateInstance(animations.get(name).getWidth(), 0);
	        AffineTransform scale = AffineTransform.getScaleInstance(-1d, 1d);
	        translate.concatenate(scale);
	        AffineTransform original = graphics.getTransform();
	        graphics.setTransform(translate);
	        x = -x;
			graphics.drawImage(animations.get(name).playStatic(idx), x, y, null);
			graphics.setTransform(original);
		} else {		
			graphics.drawImage(animations.get(name).playStatic(idx), x, y, null);
		}
	}    	
/*
    public BufferedImage getPlayOnce(String name) {
    	return animations.get(name).playOnce();
    }
*/


	private class Animation {
		private final BufferedImage bufferedImage;
		private final int xGrid;
		private final int yGrid;
		private final int width;
		private final int height;
		private final int count;
		private final int fps;

		private double elapsedTimeRepeat;
		private double startTimeRepeat;
		private double elapsedTimePlayOnce;
		private double startTimePlayOnce;

		private int imgIdxRepeat;
		private int imgIdxPlayOnce;

		public Animation(String name, BufferedImage bufferedImage, int xGrid, int yGrid, int width, int height, int count, int fps) {
			this. bufferedImage = bufferedImage;
			this.xGrid = xGrid;
			this.yGrid = yGrid;
			this.width = width;
			this.height = height;
			this.count = count;
			this.fps = fps;
		}
		

		public int getWidth() {
			return width;
		}
/*
		public BufferedImage playOnce() {
			elapsedTimePlayOnce += System.currentTimeMillis() - startTimePlayOnce;
			//	System.out.println("elapsedTimeRepeat: " + elapsedTimeRepeat + 
			//						", startTimeRepeat: " + startTimeRepeat + 
			//						" imgIdxRepeat: " + imgIdxRepeat);
			
			if (elapsedTimePlayOnce >= (1000 / fps)) {
			//	System.out.println("elapsedTimeRepeat: " + elapsedTimeRepeat + "1000 / fps: " + 1000 / fps); 
				elapsedTimePlayOnce = 0;
				startTimePlayOnce = System.currentTimeMillis();
				imgIdxPlayOnce++;
			}
			return bufferedImage.getSubimage(xGrid + (imgIdxPlayOnce * width), yGrid, width, height);	
		}
*/
		public BufferedImage playStatic(int idx) {
			return bufferedImage.getSubimage(xGrid + (idx * width), yGrid, width, height);
		}

		public BufferedImage playRepeat() {
			elapsedTimeRepeat += System.currentTimeMillis() - startTimeRepeat;
		//	System.out.println("elapsedTimeRepeat: " + elapsedTimeRepeat + 
		//						", startTimeRepeat: " + startTimeRepeat + 
		//						" imgIdxRepeat: " + imgIdxRepeat);
			
			if (elapsedTimeRepeat >= (1000 / fps)) {
				//System.out.println("elapsedTimeRepeat: " + elapsedTimeRepeat + "1000 / fps: " + 1000 / fps); 
				elapsedTimeRepeat = 0;
				startTimeRepeat = System.currentTimeMillis();
				imgIdxRepeat = ++imgIdxRepeat % count;
			}
			return bufferedImage.getSubimage(xGrid + (imgIdxRepeat * width), yGrid, width, height);	
		}
	}
}