package sp6.engine;

import java.util.ArrayList;
import java.util.List;

import sp6.engine.controllables.GraphicsControllable;



/**
 * 
 * Varje komplett spelbar level. Innehåller baseobject. Har en egen osd: för liv-indikator etc...
 *
 */
public class Level {
	public List<GameObject> objects;
	public HUD levelHUD;
	
	/**
	 * Constructor.
	 */
	public Level(){
		this.objects = new ArrayList<GameObject>();
	}
	 /**
	  * Adds game objects to level.
	  * @param o
	  */
	public void addGameObject(GameObject o){
		this.objects.add(o);
	}
	
	public void update(GameEngine e){
		for (GameObject o : objects){

		}
	}
	
	public <O extends GameObject & GraphicsControllable> ArrayList<O> getGraphicsControllables() {
		ArrayList<O> graphicsControllables = new ArrayList<>();
		for (GameObject o : objects){
			if (o instanceof GraphicsControllable && o instanceof GameObject)
				graphicsControllables.add((O)o);
		}
		return graphicsControllables;
	}
}
