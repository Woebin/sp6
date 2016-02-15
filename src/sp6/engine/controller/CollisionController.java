package sp6.engine.controller;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;

import java.awt.*;
import java.util.List;

public class CollisionController extends Component {
	public void update(CollisionControllable collisionControllable, double deltaTime) {

	}

	@Override
	public Prio getPrio() {
		return prio;
	}

	/**
	 * Updates, checks for collisions on objects with collisions enabled.
	 */
	@Override
	public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {
		if (baseObject.collisionEnabled()) {
			Rectangle rectangle = baseObject.getRectangle();

			for (BaseObject otherBaseObject : baseObjects) {
				// System.out.println("baseobject.rect: " +
				// baseObject.getRectangle() +
				// "\n otherObject.rect: " + otherBaseObject.getRectangle() +
				// "\n\t:: " +
				// rectangle.intersects(otherBaseObject.getRectangle()));
				if (baseObject != otherBaseObject && otherBaseObject.collisionEnabled()
						&& rectangle.intersects(otherBaseObject.getRectangle())) {
					baseObject.collisionOnCollision(otherBaseObject);
				}
			}
		}
	}

	@Override
	public void render(GameWindow gameWindow, BaseObject baseObject, double deltaTime) {

	}

}
