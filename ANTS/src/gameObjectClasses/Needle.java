package gameObjectClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import world.Grid;

public class Needle extends Item{
	public Needle(int baseTile, Grid g) {
		this.baseTile = baseTile;
		
		
		makeShape("0,0;");

		setOccupiedTiles(g);
		
		//amount of images used
		sprites = new BufferedImage[3];
		
		//sets image offset and side ratio
		setImageScale(18,14); //height width
		setImageOffset(-5.2, -11.5); // x y
		
		//loading images
		try {
			sprites[0] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree1.png"));
			sprites[1] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree2.png"));
			sprites[2] = ImageIO.read(new File("ANTS/src/TreeSprites/Tree3.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
