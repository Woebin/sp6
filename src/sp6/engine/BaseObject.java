package sp6.engine;

import sp6.engine.controller.Component;

import java.util.List;

/**
 * 
 * Basobjektet f�r alla interagerbara objekt i en level. Fr�gan �r om det beh�vs n�gon uppdelning? S�tter man
 * egenskaperna p� ett object vart efter s� skulle man kunna klara sig bara med ett BaseObject
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
