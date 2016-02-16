package sp6.testgame;

import sp6.engine.BaseObject;
import sp6.engine.controller.AnimationController;
import sp6.engine.controller.Component;

import java.util.List;

/**
 *
 */
public class GreenBall extends BaseObject {
	public GreenBall(List<Component> components) {
		super(components);
		zOrder = 60;
	}

	@Override
	public void animationRender(AnimationController animationController) {
		animationController.playStatic("green_ball", 1, 200, 200, 0);
	}
}
