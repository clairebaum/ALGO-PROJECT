import javax.swing.*;

public class FrameGame extends JFrame { //redirects to MainPanel

	public MainPanel mainPanel;

	/** Constructor
	  */
	public FrameGame(String name, int width, int height) {
		super(name);
		this.setSize(width, height);
		this.setLocation(200, 5);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel that will contain everything (grid, buttons...)
		mainPanel = new MainPanel(); 

		this.addKeyListener(new KeyHandler()); //see KeyHandler, the class dealing with all the key-actions						   
		this.add(mainPanel);
		this.setVisible(true);
		this.setFocusable(true);

	}
}
