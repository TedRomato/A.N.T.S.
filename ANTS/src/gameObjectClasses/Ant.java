package gameObjectClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import handlers.Animated;
import handlers.Camera;
import handlers.Carrier;
import world.Tile;

public class Ant extends LivingObject implements Animated, Carrier{
	int animationTimer = 0;
	Item carriedItem;
	boolean isSelected = false;

	public Ant(Point p) {
		super(p , 1);
		makeMainCollider(1);
		addCollisionSquare(0, 30, 0.8);
		addCollisionSquare(0, -30, 0.8);
		
		sprites = new BufferedImage[3];
		
		//sets image offset and side ratio
		setImageScale(2.5,2.5); //height width
		setImageOffset(1.25, 1.25); // x y
		
		setNewGoalDestination(new Point(100,100));

		
		//loading images
		try {
			sprites[0] = ImageIO.read(new File("src/AntSprites/Ant1.png"));
			sprites[1] = ImageIO.read(new File("src/AntSprites/Ant2.png"));
			sprites[2] = ImageIO.read(new File("src/AntSprites/Ant3.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move() {
		super.move();

	}
	

	@Override
	public void render(Graphics2D g, Camera camera) {
		if(isSelected()) {
			g.setColor(Color.green);
			g.drawRect(	(int)((this.getRp().getX()-35)*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getX()), 
						(int)((this.getRp().getY()-35)*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getY()),
						(int)(camera.getTileRenderSize()*1.5) - 1, 
						(int)(camera.getTileRenderSize()*1.5) - 1);
		}
		super.render(g, camera);
		
	}

	@Override
	public void updateAnimation() {
		if(!moving) {
			currentSpritePointer = 0;

		}else {
			animationTimer ++;
			if(animationTimer > 75) {
				currentSpritePointer = 0;
			}else if(animationTimer > 50) {
				currentSpritePointer = 2;
			}else if(animationTimer > 25) {
				currentSpritePointer = 0;
			}
			else{
				currentSpritePointer = 1;
			}
			if(animationTimer > 100) {
				animationTimer = 0;
			}
		}
		
	}


	@Override
	public void pickUp(Item item) {
		if(item.checkCollision(this)) {
			item.pickUp();
			carriedItem = item;
		}
		
	}


	@Override
	public void dropCarriedItem() {
		// TODO Auto-generated method stub
		if(carriedItem != null) {
			carriedItem.drop(this.getRp());
			carriedItem = null;
		}
		
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	
	public void setIsSelected(boolean b) {
		isSelected = b;
	}
	

}
