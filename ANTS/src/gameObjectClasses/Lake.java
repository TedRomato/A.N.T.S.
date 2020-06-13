package gameObjectClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import handlers.Animated;
import world.Grid;

public class Lake extends SolidObject implements Animated{
	
	double animationTimer = 0;
	
	public Lake(int baseTile, Grid g) {
		this.baseTile = baseTile;
		
		//sets shape of this object on grid
		//relative to baseTile
		makeShape("0,0;-1,0;0,-1;1,0;0,1;-1,-1;1,1;1,-1;-1,1;-2,-1;-2,0;-2,1;2,-1;2,0;2,1;-1,2;0,2;1,2;-3,0;3,0;0,3;-1,-2;0,-2;1,-2;0,-3;");

		setOccupiedTiles(g);
		
		//amount of images used
		sprites = new BufferedImage[3];
		
		//sets image offset and side ratio
		setImageScale(18,14); //height width
		setImageOffset(-5.2, -11.5); // x y
		
		//loading images
		try {
			sprites[0] = ImageIO.read(new File("ANTS/src/Lake/Lake1.png"));
			sprites[1] = ImageIO.read(new File("ANTS/src/Lake/Lake2.png"));
			sprites[2] = ImageIO.read(new File("ANTS/src/Lake/Lake3.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAnimation() {
		// TODO Zmenit podle delta time
		animationTimer ++;
		if(animationTimer > 80) {
			currentSpritePointer = 2;
		}else if(animationTimer > 60) {
			currentSpritePointer = 1;
		}else{
			currentSpritePointer = 0;
		}
		if(animationTimer > 100) {
			animationTimer = 0;
		}
	}
	
	
}
