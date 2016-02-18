package sp6.engine;

import sp6.engine.controller.*;
import sp6.engine.controller.Component;

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
                                            InputControllable,
                                            PhysicsControllable {
    private final List<Component> components;
    private String name;
    private boolean active = false;
    private Integer zOrder = 0;
    private SimpleVector2D vector2D;



    public BaseObject(int x, int y, List<Component> components) {
        this.components = components;
        vector2D = new SimpleVector2D(x, y);
    }

    public void setVector2D(SimpleVector2D vector2D) {
        if (vector2D != null) {
            this.vector2D = vector2D;
        }
    }

    public SimpleVector2D getVector2D() {
        return vector2D;
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

    public Component getComponent(Class componentClass) {
        for (Component component : components) {
            if (component.getClass() == componentClass) {
                return component;
            }
        }
        return null;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getZOrder() {
        return zOrder;
    }

    public void setzOrder(int zOrder) {
        this.zOrder = zOrder;
    }

    // @TODO test
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
    public void animationUpdate(AnimationController animationController) {
    }

    @Override
    public void animationRender(AnimationController animationController) {
    }


    @Override
    public void inputUpdate(Set<Integer> keys) {
    }


    @Override
    public void physicsEnterCollision(BaseObject baseObject) {
        System.out.println("baseObject.collisionOnCollision");
    }


    @Override
    public void physicsUpdate(PhysicsController physicsController) {
    }
}


