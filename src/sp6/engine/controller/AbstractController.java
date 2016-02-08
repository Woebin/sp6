package sp6.engine.controller;

import sp6.engine.GameEngine;
import sp6.engine.GameObject;
import sp6.engine.controllables.Controllable;

public abstract class AbstractController {
	private GameEngine engine;
	public AbstractController(GameEngine engine) {
		this.engine = engine;
	}
}
