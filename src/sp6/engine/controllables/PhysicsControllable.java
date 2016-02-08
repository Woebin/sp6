package sp6.engine.controllables;


public interface PhysicsControllable<GameObject> {
	double forceX = 0;
	double forceY = 0;
	double mass = 0;
	
	boolean reactsToGravity = false;
	
	
}
