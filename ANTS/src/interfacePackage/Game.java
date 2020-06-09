package interfacePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import gameObjectClasses.GameObject;
import gameObjectClasses.Pile;
import handlers.Camera;
import world.Grid;
import world.Location;
import world.Tile;

public class Game extends JPanel {
	boolean GameRunning = true;
	int ms = 1000;
	int UPS = 60;
	int x = 0;
	double msPerUpdate = ms/UPS;
	
	int contentPanelWidth;
	int contentPanelHeight;
	
	public static double screenRatio;
	
	Camera camera;

	Grid grid;
	Tile t;
	Location l;
	
	public Game(int screenWidth, int screenHeight) {
		setLayout(null);

		contentPanelWidth = screenWidth;
		contentPanelHeight = screenHeight;
		Game.screenRatio = (double)contentPanelWidth/1920;
		System.out.println(Game.screenRatio);
		Tile.tileSideLenght = (int) Math.round(40*Game.screenRatio);
		Tile.tilePossibleSizeRange = new int[] {(int) Math.round(Tile.tileSideLenght*0.6),(int) Math.round(Tile.tileSideLenght*1.4)};

		l = new Location();
		camera = new Camera(l, screenWidth, screenHeight);
		camera.updateCameraBorders();

		
	}
	
	public void start() {
		System.out.println("Started");
		double previous = System.currentTimeMillis();
		double delta = 0;
		while(GameRunning) {
			double now = System.currentTimeMillis();
			double elapsed = now - previous;
			previous = now;
			delta += elapsed;
			
			while(delta>=msPerUpdate) {
				tick();
				delta-=msPerUpdate;

			}
			/*try {
				//wait for a specific amount of time to make sure the game isn´t to fast
				Thread.sleep((long)(now + msPerFrame - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			/*this.repaint();*///calls the paint component method
			this.repaint();


		}
	}
	public void stop() {
		GameRunning = false;
	}
	//	update game values and positions
	public void tick(){
		camera.zoom(-0.002);
	//	camera.move(3, 3);
	//	camera.handleCameraMoving(/*mouseInputX, mouseInputY*/);
	//	System.out.println("Ticked");
	}
	//render game objects on their updated positions
	public void render(Graphics2D g2) {
		//System.out.println("RENDER");
		camera.renderTiles(g2);

	
	}
	
	//paints the actual game objects from the render method
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		render(g2);
	}
}
