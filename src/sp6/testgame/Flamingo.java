package sp6.testgame;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;
import sp6.engine.controller.AnimationController;
import sp6.engine.controller.Component;

import javax.swing.*;
import java.util.List;

/**
 *
 */
public class Flamingo extends BaseObject {
    // @TODO hardcoded testvalue
    private int y = 370;
    // @TODO hardcoded testvalue
    private int x = 130;

    private int dx = 0;
    private int dy = 0;
    private int velocity = 1;

    public Flamingo(List<Component> components) {
        super(components);
        zOrder = 50;
    }

    @Override
    public void animationRender(AnimationController animationController) {
        animationController.playRepeat("flamingo", 1, 400, 340);
    }

    @Override
    public void collisionOnCollision(BaseObject baseObject) {
//        active = false;
    }

    @Override
    public void collisionLeaveCollision(BaseObject baseObject) {
    }

    @Override
    public boolean collisionEnabled() {
        return true;
    }

    @Override
    public void render(GameWindow gameWindow, double deltaTime) {
        super.render(gameWindow, deltaTime);

    }
}
