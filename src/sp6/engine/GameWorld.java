package sp6.engine;

import sp6.engine.controller.*;

import java.util.List;

/** 
 * Har loopen. Har information om hela spelets tillstånd. Hanterar levels
 *
 */
public class GameWorld {
    private List<BaseObject> baseObjects;
    private static boolean gameLoopRun = false;


    // @TODO Fix complete handling of levels and so on...
    public GameWorld(List<BaseObject> baseObjects) {
        this.baseObjects = baseObjects;

        (new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();

        // @TODO create base objects here
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

            // @TODO maybe put this in a separate thread?!
            update(Component.Prio.HIGH, deltaTime);

            update(Component.Prio.MEDIUM, deltaTime);
            update(Component.Prio.LOW, deltaTime);

            try {
                Thread.sleep( (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException ie) {
                // @ TODO handle exception
            }
        }
	}

    public void startGameLoop() {
        gameLoopRun = true;
    }

    public void pauseGameLoop() {
        gameLoopRun = false;
    }

    private void update(Component.Prio prio, double deltaTime) {
        for (BaseObject baseObject : baseObjects) {
            baseObject.update(prio, deltaTime);
        }
    }


}
