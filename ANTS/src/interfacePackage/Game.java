package interfacePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import gameObjectClasses.GameObject;
import gameObjectClasses.Pile;
import world.Grid;
import world.Location;
import world.Tile;

public class Game extends JPanel {
	boolean GameRunning = true;
	int ms = 1000;
	int UPS = 120;
	int x = 0;
	double msPerUpdate = ms/UPS;
	
	Grid grid;
	Tile t;
	Location l;
	
	public Game() {
		setLayout(null);
		grid = new Grid(20,20);
		t= new Tile(50,50);
		l = new Location();
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
			
			this.repaint();//calls the paint component method
		}
	}
	public void stop() {
		GameRunning = false;
	}
	//update game values and positions
	public void tick(){
		//System.out.println("Ticked");
	}
	//render game objects on their updated positions
	public void render(Graphics2D g2) {
		//System.out.println("RENDER");

		l.render(g2);

	
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
