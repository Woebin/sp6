package sp6.engine.controller;


import java.awt.Graphics2D;
import java.util.ArrayList;

import sp6.engine.GameEngine;
import sp6.engine.GameObject;
import sp6.engine.controllables.GraphicsControllable;

public class GraphicsController extends AbstractController {

	public GraphicsController(GameEngine engine) {
		super(engine);
	}

	public <O extends GameObject & GraphicsControllable> void update(ArrayList<O> g, Graphics2D graphics){
		for (O object : g) {
			// System.out.println("Drawing " + object.getPosX() + " " + object.getPosY() + " " + object.getWidth() + " " + object.getHeight());
			graphics.drawImage(object.getSprite(), object.getPosX(), object.getPosY(), 
					object.getWidth(), object.getHeight(), null);
		}
	}
}
