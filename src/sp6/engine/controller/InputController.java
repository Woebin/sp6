package sp6.engine.controller;

import java.awt.event.KeyEvent;

import sp6.engine.controllables.InputControllable;
import sp6.engine.other.BaseObject;

public class InputController extends Component {
	public void update(InputControllable inputControllable, double deltaTime) {

	}

	/**
	 * Keypress listener. Currently returns a value for each key, the effects of which can be specified in the game.
	 * @param event
	 * @return
	 */
	public int keyPressed(KeyEvent event) {
		int i = 0;
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			i = 1;
		case KeyEvent.VK_RIGHT:
			i = 2;
		case KeyEvent.VK_UP:
			i = 3;
		case KeyEvent.VK_DOWN:
			i = 4;
		}
		return i;
	}

	/**
	 * Keypress listener. Currently returns a value for each key, the effects of which can be specified in the game.
	 * @param event
	 * @return
	 */
	public int keyReleased(KeyEvent event) {
		int i = 0;
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			i = 1;
		case KeyEvent.VK_RIGHT:
			i = 2;
		case KeyEvent.VK_UP:
			i = 3;
		case KeyEvent.VK_DOWN:
			i = 4;
		}
		return i;
	}

	@Override
	public Prio getPrio() {
		return prio;
	}

	@Override
	protected void update_(BaseObject baseObject, double deltaTime) {

	}
}
