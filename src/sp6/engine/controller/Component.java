package sp6.engine.controller;

import sp6.engine.BaseObject;

/**
 * 
 * En tanke �r att ha egenskaper i form av komponenter som �pluggas� in till olika objekt f�r att f�rr�ndra deras 
 * beteende. Beh�ver ett objekt fysiksimulering s� pluggas en fysikkomponent p� den.
 *
 */
public abstract class Component {
    // Default prio
    protected Prio prio = Prio.LOW;

    public enum Prio {
        HIGH,
        MEDIUM,
        LOW
    }

    public abstract Component.Prio getPrio();

    public void update(BaseObject baseObject, double deltaTime) {
        update_(baseObject, deltaTime);
    }

    protected abstract void update_(BaseObject baseObject, double deltaTime);
}
