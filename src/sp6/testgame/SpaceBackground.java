package sp6.testgame;

import sp6.engine.BaseObject;
import sp6.engine.controller.AnimationController;
import sp6.engine.controller.Component;

import java.util.List;

/**
 *
 */
public class SpaceBackground extends BaseObject {

    public SpaceBackground(List<Component> components) {
        super(components);
    }

    @Override
    public void animationRender(AnimationController animationController) {
        animationController.playStatic("space_background", 1, 0, 0, 0);
    }
}
