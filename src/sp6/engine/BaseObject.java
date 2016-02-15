package sp6.engine;

import sp6.engine.controller.*;
import sp6.engine.controller.Component;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * 
 * Basobjektet f�r alla interagerbara objekt i en level. Fr�gan �r om det beh�vs n�gon uppdelning? S�tter man
 * egenskaperna p� ett object vart efter s� skulle man kunna klara sig bara med ett BaseObject
 *
 */
public abstract class BaseObject implements Comparable<BaseObject>, // Z-order
                                            AnimationControllable,
                                            AudioControllable,
                                            CollisionControllable,
                                            InputControllable,
                                            PhysicsControllable {
    private final List<Component> components;
    private String name;
    private Rectangle rectangle;
    protected boolean active = true;
    protected Integer zOrder = 0;

    public BaseObject(List<Component> components) {
        this.components = components;
        rectangle = new Rectangle(-1, -1, 0, 0);
    }

    public void update(List<BaseObject> baseObjects, double deltaTime) {
        for (Component component : components) {
            component.update(this, baseObjects, deltaTime);
        }
    }

    public void render(GameWindow gameWindow, double deltaTime) {
        for (Component component : components) {
            component.render(gameWindow, this, deltaTime);
        }
    }

    public boolean isActive() {
        return active;
    }

    public Integer getZOrder() {
        return zOrder;
    }

    // @TODO test
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void animationUpdate(AnimationController animationController) {
    }

    @Override
    public void animationRender(AnimationController animationController) {
    }

    @Override
    public void inputUpdate(Set<Integer> keys) {

    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void setAnimationRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void collisionOnCollision(BaseObject baseObject) {
        System.out.println("baseObject.collisionOnCollision");
    }

    @Override
    public void collisionLeaveCollision(BaseObject baseObject) {

    }

    @Override
    public int compareTo(BaseObject otherObject) {
        if (zOrder < otherObject.zOrder) {
            return -1;
        } else if (zOrder > otherObject.zOrder) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean collisionEnabled() {
        return false;
    }



}


