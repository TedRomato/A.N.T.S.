package gameObjectClasses;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import handlers.Animated;
import world.Grid;

public class Tree extends SolidObject implements Animated{
	
	double animationTimer = 0;
	
	public Tree(int baseTile, Grid g) {
		this.baseTile = baseTile;
		
		makeShape("0,0;-1,0;0,-1;1,0;0,1;");
		
		setOccupiedTiles(g);
		
		sprites = new BufferedImage[3];
		try {
			sprites[0] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree1.png"));
			sprites[1] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree2.png"));
			sprites[2] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree3.png"));

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
