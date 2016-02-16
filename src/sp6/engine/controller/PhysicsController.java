package sp6.engine.controller;

import sp6.engine.BaseObject;
import sp6.engine.GameWindow;

import java.util.List;
import java.util.SortedSet;

public class PhysicsController extends Component {

	public void update(PhysicsControllable physicsControllable, double deltaTime) {

	}

	/**
	 * Get priority. Probably not going to be used.
	 */
	@Override
	public Prio getPrio() {
		return prio;
	}

	@Override
	public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {

	}

	@Override
	public void render(GameWindow gameWindow, BaseObject baseObject, double deltaTime) {

	}

}
