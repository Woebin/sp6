package sp6.engine;

import sp6.engine.controller.*;

import java.util.List;

/**
 * 
 * Den mest avancerade. Kan ha fysik, kollision, animering etc. R�rlig eller statisk.
 *
 */
public class GameObject extends BaseObject implements GraphicsControllable, PhysicsControllable, CollisionControllable {


    public GameObject(List<Component> components) {
        super(components);
    }

    @Override // GraphicsControllable
    public String getTestString() {
        return "GraphicsControllableTestString";
    }
}
