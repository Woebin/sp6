package sp6.engine.controller;

import java.awt.*;

/**
 *
 */
public interface AnimationControllable {
    void animationUpdate(AnimationController animationController);
    void animationRender(AnimationController animationController);
    void setAnimationRectangle(Rectangle rectangle);
}
