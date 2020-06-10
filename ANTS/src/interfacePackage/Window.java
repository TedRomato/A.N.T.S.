package interfacePackage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{
	Game game;
	public Window() {
		//window set up
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		
		game = new Game(screenWidth, screenHeight);
		game.setSize(getWidth(), getHeight()); //GAME NEEDS TO HAVE A SPECIFIED SIZE IF NOT THE PAINT COMPONENT METHOD WILL NOT WORK
		getContentPane().add(game);	
		
		game.start();
		
		
	}
	
	
	public static void main(String[] args) {
		new Window();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//getting the window to close on the ESC key
		if(e.getKeyCode() == e.VK_ESCAPE) {
			game.stop();
			dispose();
		}
		game.keyPressed(e);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
