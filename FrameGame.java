import javax.swing.*;

public class FrameGame extends JFrame {

	// attributes of class frameGame
	public MainPanel mainPanel;


	public FrameGame(String name, int width, int height) {
		super(name);
		this.setSize(width, height);
		this.setLocation(200, 5);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel that will contain everything (grid, buttons...)
		mainPanel = new MainPanel();

		this.addKeyListener(new KeyHandler()); //we never added the KeyListener before so it couldn't work
											   //must be added to the frame because this is where the focus is	

		this.add(mainPanel);

		this.setVisible(true);
		this.setFocusable(true);

	}
}
