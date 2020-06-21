
package interfacePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameObjectClasses.Ant;
import gameObjectClasses.GameObject;
import gameObjectClasses.LivingObject;
import gameObjectClasses.Pile;
import gameObjectClasses.Point;
import gameObjectClasses.Tree;
import handlers.Camera;
import handlers.Input;
import handlers.Input.WheelMove;
import world.Grid;
import world.Location;
import world.Tile;

public class Game extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener{
	boolean GameRunning = true;
	int ms = 1000;
	int UPS = 120;
	int x = 0;
	double msPerUpdate = ms/UPS;
	
	public static Input input = new Input();

	int contentPanelWidth;
	int contentPanelHeight;
	
	public static double screenRatio;
	
	public static Camera camera;
	
	Grid grid;
	Tile t;
	Location l;
	
	public Game(int screenWidth, int screenHeight) {
		setLayout(null);
		addMouseWheelListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		grid = new Grid(20,20);
		t= new Tile(50,50);
		
		contentPanelWidth = screenWidth;
		contentPanelHeight = screenHeight;
		Game.screenRatio = (double)contentPanelWidth/1920;
		System.out.println(Game.screenRatio);
		Tile.tileSideLenght = (int) Math.round(60*Game.screenRatio);

		Tile.tilePossibleSizeRange = new int[] {(int) Math.round(Tile.tileSideLenght*0.4),(int) Math.round(Tile.tileSideLenght*1.6)};

	//	Tile.tilePossibleSizeRange = new int[] {(int) Math.round(Tile.tileSideLenght*0.6),(int) Math.round(Tile.tileSideLenght*1.4)};
		
		
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

			this.repaint();//calls the paint component method

		}
	}
	public void stop() {
		GameRunning = false;
	}
	//	update game values and positions
	public void tick(){
		l.updateLocation();
		input.updateInput(camera);
		camera.handleCameraMoving(input.getCursorX(),input.getCursorY());

	}
	//render game objects on their updated positions
	public void render(Graphics2D g2) {
		//System.out.println("RENDER");		
		camera.renderBackground(g2);
		camera.renderAllObjects(g2);
		camera.renderSelectionRect(g2);

		if(input.checkIfKeyPressed('g')) {
			camera.renderGrid(g2);
		}

	
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		render(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == e.BUTTON1) {
			input.setLeftMousePressed(true);
		}
		if(e.getButton() == e.BUTTON3) {
			input.setRightMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == e.BUTTON1) {
			input.setLeftMousePressed(false);
		}
		if(e.getButton() == e.BUTTON3) {
			input.setRightMousePressed(false);
		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		input.checkMouseWheelMove(notches);
		camera.smoothZoom(input.WheelMoveForAmount(0.1));
		}

	@Override
	public void mouseDragged(MouseEvent e) {
		input.setCursorX(e.getXOnScreen(), camera);
		input.setCursorY(e.getYOnScreen(), camera);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		input.setCursorX(e.getXOnScreen(), camera);
		input.setCursorY(e.getYOnScreen(), camera);
		
	}

	public void keyPressed(KeyEvent e) {
		if(e != null) {
			input.keyPressed(e.getKeyChar());
		}
	}


	public void keyReleased(KeyEvent e) {
		if(e != null) {
			input.keyReleased(e.getKeyChar());
		}
	}

}

