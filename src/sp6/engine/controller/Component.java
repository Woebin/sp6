package sp6.engine.controller;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;

import java.util.List;
import java.util.SortedSet;

/**
 * 
 * En tanke �r att ha egenskaper i form av komponenter som pluggas in till olika objekt f�r att f�r�ndra deras 
 * beteende. Beh�ver ett objekt fysiksimulering s� pluggas en fysikkomponent p� den.
 *
 */
public abstract class Component {
     public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {

    }

    public void render(GameWindow gameWindow, BaseObject baseObject, double deltaTime) {

    }
}
