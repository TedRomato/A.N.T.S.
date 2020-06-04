package interfacePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Game extends JPanel {
	boolean GameRunning = true;
	int ms = 1000;
	int UPS = 120;
	double msPerUpdate = ms/UPS;
	public Game() {
		
	}
	public void start() {
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
			
			repaint();//calls the paint component method
		}
	}
	public void stop() {
		GameRunning = false;
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
