package sp6.engine.controller;

import sp6.engine.controllables.PhysicsControllable;
import sp6.engine.other.BaseObject;

public class PhysicsController extends Component {

    public void update(PhysicsControllable physicsControllable, double deltaTime) {

    }

    @Override
    public Prio getPrio() {
        return prio;
    }

    @Override
    protected void update_(BaseObject baseObject, double deltaTime) {

    }
}
