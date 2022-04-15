import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class newFrameGame extends JFrame{
	
	// attributes of class frameGame
	public newMainPanel mainPanel;
	
	
	public newFrameGame(String name, int width, int height) {
		super(name);
		this.setSize(width, height);
		this.setLocation(200,5);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel that will contain everything (grid, buttons...)
		mainPanel= new newMainPanel();
		
		
		this.add(mainPanel);

		this.setVisible(true);
		this.setFocusable(true);
        
	}
}
