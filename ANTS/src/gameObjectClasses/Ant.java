package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import handlers.Animated;
import handlers.Camera;

public class Ant extends LivingObject implements Animated{
	int animationTimer = 0;

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
			sprites[0] = ImageIO.read(new File("ANTS/src/AntSprites/Ant1.png"));
			sprites[1] = ImageIO.read(new File("ANTS/src/AntSprites/Ant2.png"));
			sprites[2] = ImageIO.read(new File("ANTS/src/AntSprites/Ant3.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void render(Graphics2D g, Camera c) {
		super.render(g, c);
		
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

	

}
