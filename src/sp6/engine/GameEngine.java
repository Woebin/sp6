package sp6.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

import javax.swing.JFrame;

import sp6.engine.controllables.GraphicsControllable;
import sp6.engine.controller.AudioController;
import sp6.engine.controller.GraphicsController;
import sp6.engine.controller.PhysicsController;

/** 
 * Har loopen. Har information om hela spelets tillstånd. Hanterar levels
 *
 */
public class GameEngine {

	private JFrame window;
	private int windowWidth, windowHeight;
	private String windowTitle;
	
    private boolean gameLoopRun = false;
    private GameWorld currentWorld;
    private BufferStrategy bufferStrategy;
    private GraphicsController graphicsController;
    private PhysicsController physicsController;
    private AudioController audioController;
    
    public void attachWorld(GameWorld world) {
    	if (this.currentWorld != null) {
    		this.detachWorld();
    	}
    	this.currentWorld = world;
    }
    
    public void detachWorld() {
    	this.currentWorld = null;
    }
   
    // @TODO Fix complete handling of levels and so on...
    public GameEngine(int width, int height, String title) {
    	this.windowWidth = width;
    	this.windowHeight = height;
    	this.windowTitle = title;

        // @TODO create base objects here
    }
    
    private void createWindow(){
    	this.window = new JFrame();
    	this.window.setSize(this.windowWidth, this.windowHeight);
    	this.window.setTitle(this.windowTitle);
		this.window.setBackground(Color.black);
		this.window.setResizable(false);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setVisible(true);
		this.window.createBufferStrategy(2);
      	this.bufferStrategy = this.window.getBufferStrategy();
    }
    
    private void createControllers(){
    	this.graphicsController = new GraphicsController(this);
    	
    }

    public void startLoop() {
    	createWindow();
    	createControllers();
    	if (gameLoopRun == true) {
    		return;
    	}
    	
    	gameLoopRun = true;
    	
        (new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();
    }
    
    public void pauseGameLoop() {
        gameLoopRun = false;
    }
    
    // @TODO fix better game loop
 	private void gameLoop() {
        // @SOURCE http://www.java-gaming.org/index.php?topic=24220.0

        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        long now = 0;
        long updateLength = 0;
        double deltaTime = 0;
        int fps = 0;
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while (gameLoopRun) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            now = System.nanoTime();
            updateLength = now - lastLoopTime;
            lastLoopTime = now;
            deltaTime = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, this.windowWidth, this.windowHeight);
            
            if (this.currentWorld != null) {
            	this.update();
            	
                this.graphicsController.update(this.currentWorld.getGraphicsControllables(), g);    
            }
            
            bufferStrategy.show();
    		g.dispose();
            try {
            	
            	long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(sleepTime > 0 ? sleepTime : 0);
            } catch (InterruptedException ie) {
                // @ TODO handle exception
            }
        }
	}
 
 	private void update(){
 		
 	}
}
