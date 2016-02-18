package sp6.engine.controller;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.List;

public class AnimationController extends Component {
    private Graphics2D graphics2D;
    private BufferedImage collisionImage;
    private boolean playSingle = false;

    private BufferedImage spriteSheet;
    private BufferedImage spaceBackground;
    private BufferedImage greenBall;
    private HashMap<String, Animation> animations = new HashMap<>();
    private Rectangle rectangle = new Rectangle();

    public AnimationController() {
        try {
            spriteSheet = ImageIO.read(new File("res/Aliens_Enemies_cropped.png"));
            spaceBackground = ImageIO.read(new File("res/Space.tga_.jpg"));
            greenBall = ImageIO.read(new File("res/green_ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        testPopulation();
    }

    @Override
    public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {
        baseObject.animationUpdate(this);
    }

    @Override
    public void render(GameWindow gameWindow, BaseObject baseObject, double deltaTime) {
        graphics2D = gameWindow.getGraphics2D();
        baseObject.animationRender(this);
        graphics2D.dispose();
    }

    // @TODO testcode
    private void testPopulation() {
        animations.put("alien1", new Animation("alien1", spriteSheet, 24, 24, 48, 64, 6, 1));
        animations.put("flamingo", new Animation("flamingo", spriteSheet, 24, 104, 48, 64, 11, 3));
        animations.put("piranha", new Animation("piranha", spriteSheet, 24, 238, 48, 64, 18, 8));

        animations.put("space_background", new Animation("space_background", spaceBackground, 0, 0, 1920, 1080, 1, 1));
        animations.put("green_ball", new Animation("green_ball", greenBall, 0, 0, 249, 176, 1, 1));
    }

    public BufferedImage getAnimationImage() {
        return collisionImage;
    }

    public void playRepeat(String name, int direction, int x, int y) {
        if (playSingle) {
            return;
        }

        Animation animation = animations.get(name);
        setAnimationRectangle(name, x, y);

        if (direction == -1) {
            AffineTransform translate = AffineTransform.getTranslateInstance(animations.get(name).getWidth(), 0);
            AffineTransform scale = AffineTransform.getScaleInstance(-1d, 1d);
            translate.concatenate(scale);
            AffineTransform original = graphics2D.getTransform();
            graphics2D.setTransform(translate);
            x = -x;

            collisionImage = animation.playRepeat();
            graphics2D.drawImage(collisionImage, x, y, null);
            graphics2D.setTransform(original);
        } else {
            collisionImage = animation.playRepeat();
            graphics2D.drawImage(collisionImage, x, y, null);
        }
    }

    public boolean playSingle(String name, int direction, int x, int y) {
        Animation animation = animations.get(name);
        setAnimationRectangle(name, x, y);

        if (direction == -1) {
            AffineTransform translate = AffineTransform.getTranslateInstance(animations.get(name).getWidth(), 0);
            AffineTransform scale = AffineTransform.getScaleInstance(-1d, 1d);
            translate.concatenate(scale);
            AffineTransform original = graphics2D.getTransform();
            graphics2D.setTransform(translate);
            x = -x;

            collisionImage = animation.playSingle();
            if (collisionImage == null) {
                return false;
            }
            graphics2D.drawImage(collisionImage, x, y, null);
            graphics2D.setTransform(original);
        } else {
            collisionImage = animation.playSingle();
            if (collisionImage == null) {
                return false;
            }
            graphics2D.drawImage(collisionImage, x, y, null);
        }
        return playSingle;
    }


    public void playStatic(String name, int direction, int x, int y, int idx) {
        if (playSingle) {
            return;
        }

        Animation animation = animations.get(name);
        setAnimationRectangle(name, x, y);

        if (direction == -1) {
            AffineTransform translate = AffineTransform.getTranslateInstance(animations.get(name).getWidth(), 0);
            AffineTransform scale = AffineTransform.getScaleInstance(-1d, 1d);
            translate.concatenate(scale);
            AffineTransform original = graphics2D.getTransform();
            graphics2D.setTransform(translate);
            x = -x;

            collisionImage = animation.playStatic(idx);
            graphics2D.drawImage(collisionImage, x, y, null);
            graphics2D.setTransform(original);
        } else {
            collisionImage = animation.playStatic(idx);
            graphics2D.drawImage(collisionImage, x, y, null);
        }
    }


    public Rectangle getAnimationRectangle() {
        return rectangle;
    }

    private void setAnimationRectangle(String animationName, int x, int y) {
        Animation animation = animations.get(animationName);
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = animation.getWidth();
        rectangle.height = animation.getHeight();
    }

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

        public int getHeight() {
            return height;
        }


        public BufferedImage playStatic(int idx) {
            return bufferedImage.getSubimage(xGrid + (idx * width), yGrid, width, height);
        }

        public BufferedImage playRepeat() {
            elapsedTimeRepeat += System.currentTimeMillis() - startTimeRepeat;

            if (elapsedTimeRepeat >= (1000 / fps)) {
                elapsedTimeRepeat = 0;
                startTimeRepeat = System.currentTimeMillis();
                imgIdxRepeat = ++imgIdxRepeat % count;
            }
            return bufferedImage.getSubimage(xGrid + (imgIdxRepeat * width), yGrid, width, height);
        }

        public BufferedImage playSingle() {
            elapsedTimeRepeat += System.currentTimeMillis() - startTimeRepeat;

            if (elapsedTimeRepeat >= (1000 / fps)) {
                if (imgIdxRepeat < count) {
                    playSingle = true;
                    elapsedTimeRepeat = 0;
                    startTimeRepeat = System.currentTimeMillis();
                    imgIdxRepeat = ++imgIdxRepeat;
                } else {
                    imgIdxRepeat = 0;
                    playSingle = false;
                    return null;
                }
            }
            return bufferedImage.getSubimage(xGrid + (imgIdxRepeat * width), yGrid, width, height);

        }
    }
}