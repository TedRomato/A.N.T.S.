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
		sprites = new BufferedImage[1];
		
		//sets image offset and side ratio
		setImageScale(10, 10); //height width
		setImageOffset(0.3,0); // x y
		
		//loading images
		try {
			sprites[0] = ImageIO.read(new File("src/SmallItems/Needle.png"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
