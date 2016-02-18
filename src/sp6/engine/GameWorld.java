package sp6.engine;


import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/** 
 * Har loopen. Har information om hela spelets tillst�nd. Hanterar levels
 *
 */
public class GameWorld {
    private Loader loader;
    private SortedMap<Integer, List<BaseObject>> baseObjectMap;
    private GameWindow gameWindow;
    private static boolean gameLoopRun = false;


    // @TODO Fix complete handling of levels and so on...
    public GameWorld(Loader loader) {
        this.loader = loader;
        baseObjectMap = loader.getBaseObjectMap();

        gameWindow = loader.getGameWindow();

        (new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();

        // @TODO create base objects here
    }

    // @TODO fix better game loop
 	public void gameLoop() {
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
            update(deltaTime);
            render(deltaTime);

            try {
                long timeout = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (timeout < 0) {
                    timeout = 0;
                }
                Thread.sleep(timeout);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
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



    private void update(double deltaTime) {
        for (Map.Entry<Integer, List<BaseObject>> entry : baseObjectMap.entrySet()) {
            System.out.println("level: " + entry.getKey());
            List<BaseObject> baseObjects = entry.getValue();
            for (BaseObject baseObject : baseObjects) {
                if (baseObject.isActive()) {
                    baseObject.update(baseObjects, deltaTime);
                }
            }
        }
    }

    private void render(double deltaTime) {
        gameWindow.clear();

        for (Map.Entry<Integer, List<BaseObject>> entry : baseObjectMap.entrySet()) {
            List<BaseObject> baseObjects = entry.getValue();
            for (BaseObject baseObject : baseObjects) {
                if (baseObject.isActive()) {
                    baseObject.render(gameWindow, deltaTime);
                }
            }
        }
        gameWindow.draw();
    }


}
