package gameObjectClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import world.Grid;

public class Needle extends Item{
	public Needle(Point p) {
		super(p);
		
		//amount of images used
		sprites = new BufferedImage[1];
		
		makeMainCollider(1);
		
		//sets image offset and side ratio
		setImageScale(10, 10); //height width
		setImageOffset(0.45,0.58); // x y
		
		//loading images
		try {
			sprites[0] = ImageIO.read(new File("src/SmallItems/Needle.png"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
