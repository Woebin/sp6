package sp6.engine.controller;


import sp6.engine.controllables.InputControllable;
import sp6.engine.other.BaseObject;

public class InputController extends Component {
    public void update(InputControllable inputControllable, double deltaTime) {

    }

    @Override
    public Prio getPrio() {
        return prio;
    }

    @Override
    protected void update_(BaseObject baseObject, double deltaTime) {

    }
}
