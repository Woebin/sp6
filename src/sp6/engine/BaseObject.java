package sp6.engine;

import sp6.engine.controller.*;
import sp6.engine.controller.Component;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Class for all game objects (characters, backgrounds, projectiles etc - everything on screen). 
 * Should probably be renamed to GameObject really, name is a relic of prior design.
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

    /**
     * Constructor takes a list of components, components are controllers (why is the class not called "Controller" instead?
     * I dunno.
     * @param components
     */
    public BaseObject(List<Component> components) {
        this.components = components;
        rectangle = new Rectangle(-1, -1, 0, 0);
    }

    /**
     * Call update function from each controller associated with object.
     * @param baseObjects
     * @param deltaTime
     */
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

    /**
     * Flag determining if object is active; if not, it's not drawn.
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Get Z-level, i.e. which "layer" the object is on. For drawing purposes and possibly collision too.
     * @return
     */
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


