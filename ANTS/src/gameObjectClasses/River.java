package gameObjectClasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import world.Grid;

public class River extends SolidObject {

	public River(int baseTile, Grid g) {
		this.baseTile = baseTile;
		
		makeShape("0,0;-1,0;0,-1;1,0;0,1;-1,-1;1,1;1,-1;-1,1;-2,-1;-2,0;-2,1;2,-1;2,0;2,1;-1,2;0,2;1,2;-3,0;3,0;0,3;-1,-2;0,-2;1,-2;0,-3;");

		
		setOccupiedTiles(g);
		
		sprites = new BufferedImage[3];
		
		try {
			sprites[0] = ImageIO.read(new File("ANTS/src/River/maxresdefault.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
