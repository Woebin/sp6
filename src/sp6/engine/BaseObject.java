package sp6.engine;

import sp6.engine.controller.Component;

import java.util.List;

/**
 * 
 * Basobjektet för alla interagerbara objekt i en level. Frågan är om det behövs någon uppdelning? Sätter man
 * egenskaperna på ett object vart efter så skulle man kunna klara sig bara med ett BaseObject
 *
 */
public abstract class BaseObject {
    private final List<Component> components;

    public BaseObject(List<Component> components) {
        this.components = components;
    }

    public void update(Component.Prio prio, double deltaTime) {
        for (Component component : components) {
            System.out.println("Update BaseObject prio: " + prio);

            if (component.getPrio() == prio) {
                System.out.println("Update BaseObject equals prio: " + prio);
                component.update(this, deltaTime);
            }
        }
    }
}
