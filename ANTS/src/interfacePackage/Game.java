package interfacePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import world.Location;

public class Game extends JPanel {
	boolean GameRunning = true;
	int ms = 1000;
	int FPS = 120;
	double msPerFrame = ms/FPS;
	Location testLoc;
	
	public Game() {
	}
	public void start() {
		while(GameRunning) {
			double now = System.currentTimeMillis();
			tick();
			repaint();//calls the paint component method
			try {
				//wait for a specific amount of time to make sure the game isn´t to fast
				Thread.sleep((long)(now + msPerFrame - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//update game values and positions
	public void tick(){
		
	}
	//render game objects on their updated positions
	public void render(Graphics2D g2) {
		
		
	}
	
	@Override
	//paints the actual game objects from the render method
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		render(g2);
	}
}
