package sp6.engine.controller;

import sp6.engine.BaseObject;

/**
 * Null object pattern for engines not used by a base object.
 *
 */
public class NullEngine extends Component {
    //@TODO log calls to this!
    @Override
    public Prio getPrio() {
        return prio;
    }

    public void update(BaseObject baseObject, double deltaTime) {}

    @Override
    protected void update_(BaseObject baseObject, double deltaTime) {

    }
}
