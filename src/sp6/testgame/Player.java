package sp6.testgame;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;
import sp6.engine.controller.*;
import sp6.engine.controller.AnimationController;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class Player extends BaseObject {
	// @TODO hardcoded testvalue
	private int y = 70;
	// @TODO hardcoded testvalue
	private int x = 30;

	private int dx = 0;
	private int dy = 0;
	private int velocity = 1;
	private boolean fire;

	public Player(List<Component> components) {
		super(components);
		zOrder = 50;
	}

	@Override
	public void animationUpdate(AnimationController animationController) {
	}

	@Override
	public void animationRender(AnimationController animationController) {
		if (dx < 0) {
			animationController.playRepeat("alien1", -1, x, y);
		}

		if (dx > 0) {
			animationController.playRepeat("alien1", 1, x, y);
		}

		if (dx == 0) {
			animationController.playStatic("alien1", 1, x, y, 5);
		}

		// rectangle
	}

	/**
	 * Overrides InputController and controls player position based on input.
	 */
	@Override
	public void inputUpdate(Set<Integer> keyCodes) {
		dx = 0;
		dy = 0;

		if (keyCodes.contains(InputController.MappedKey.LEFT.code())) {
			dx = -velocity;
		}
		if (keyCodes.contains(InputController.MappedKey.RIGHT.code())) {
			dx = velocity;
		}
		if (keyCodes.contains(InputController.MappedKey.UP.code())) {
			dy = -velocity;
		}
		if (keyCodes.contains(InputController.MappedKey.DOWN.code())) {
			dy = velocity;
		}

		if (keyCodes.contains(InputController.MappedKey.FIRE.code())) {
			fire = !true;
		}

		x += dx;
		y += dy;
	}

	@Override
	public void collisionOnCollision(BaseObject baseObject) {
		if (baseObject.getName().equals("flamingo")) {
			velocity = 3;
		}
		// System.out.println("Player: Collision!");
	}

	@Override
	public boolean collisionEnabled() {
		return true;
	}

	@Override
	public void render(GameWindow gameWindow, double deltaTime) {
		super.render(gameWindow, deltaTime);
		// if (fire) {

		// gameWindow.getContentPane();
		// }
	}

}
