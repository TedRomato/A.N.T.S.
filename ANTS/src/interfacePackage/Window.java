package interfacePackage;
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
		game = new Game();
		add(game);	
		game.start();
		
		
	}
	
	
	public static void main(String[] args) {
		new Window();
		System.out.println("Welcome to ANTS git repo :]");
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
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
