package sp6.engine.controller;

import sp6.engine.BaseObject;

import java.awt.*;

/**
 *
 */
public interface CollisionControllable {
	public Rectangle getRectangle();

	public boolean collisionEnabled();

	public void collisionOnCollision(BaseObject baseObject);

	public void collisionLeaveCollision(BaseObject baseObject);
}
