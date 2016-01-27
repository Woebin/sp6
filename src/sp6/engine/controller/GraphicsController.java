package sp6.engine.controller;


import sp6.engine.BaseObject;

public class GraphicsController extends Component {
    public void update(GraphicsControllable graphicsControllable, double deltaTime) {

    }

    @Override
    public Prio getPrio() {
        return Component.Prio.HIGH;
    }

    @Override
    protected void update_(BaseObject baseObject, double deltaTime) {

        GraphicsControllable graphicsControllable = (GraphicsControllable) baseObject;
        System.out.println(graphicsControllable.getTestString());
    }
}
