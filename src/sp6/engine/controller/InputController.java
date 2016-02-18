package sp6.engine.controller;


import sp6.engine.BaseObject;
import sp6.engine.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class InputController extends Component implements KeyListener {
    private final Set<Integer> keys = new HashSet<>();

    // @TODO populate from loader
    public enum MappedKey {
        NULL(KeyEvent.VK_UNDEFINED),
        LEFT(KeyEvent.VK_LEFT),
        RIGHT(KeyEvent.VK_RIGHT),
        UP(KeyEvent.VK_UP),
        DOWN(KeyEvent.VK_DOWN),
        FIRE(KeyEvent.VK_SPACE);

        private final int keyEventKey;

        MappedKey(final int keyEventKey) {
            this.keyEventKey = keyEventKey;
        }

        public int code() {
             return keyEventKey;
        }
    }


    @Override
    public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {
        baseObject.inputUpdate(keys);
    }

    @Override
    public void render(GameWindow gameWindow, BaseObject baseObject, double deltaTime) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }
}
