package sp6.engine.controller;


import sp6.engine.BaseObject;

import java.awt.*;

public interface PhysicsControllable {
    public void physicsUpdate(PhysicsController physicsController);
    public void physicsEnterCollision(BaseObject baseObject);
}
