package gameObjectClasses;

import java.awt.Graphics;

import handlers.Camera;

public class Ant extends LivingObject{

	public Ant(Point p) {
		super(p, 0, -10, 4);
		makeMainCollider(1);
		addCollisionSquare(0, 30, 0.8);
		addCollisionSquare(0, -30, 0.8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, Camera c) {
		super.render(g, c);
		
	}

	

}
