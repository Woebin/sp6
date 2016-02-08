package sp6.engine.controller;

import sp6.engine.other.BaseObject;

/**
 * 
 * En tanke är att ha egenskaper i form av komponenter som pluggas in till olika objekt för att förändra deras 
 * beteende. Behöver ett objekt fysiksimulering så pluggas en fysikkomponent på den.
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
