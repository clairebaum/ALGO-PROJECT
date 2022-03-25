import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;


public class frameGame extends JFrame implements ActionListener, KeyListener{
	// attributes of class frameGame
	public JPanel mainLabel;
	public JPanel grid;
	public JButton throwDice;
	public JButton moveWall;
	public TileGrid theTileGrid;

	
	public frameGame(String name, int width, int height, TileGrid aTileGrid) {
		super(name);
		this.theTileGrid = aTileGrid;
		setSize(width, height);
		setLocation(200,50);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// button to throw the dice
		throwDice= new JButton("Throw Dice");
		throwDice.setBounds(10,200,150,40);
		throwDice.addActionListener(this);

		// button to move a wall
		moveWall= new JButton("Move Wall");
		moveWall.setBounds(10,260,150,40);
		moveWall.addActionListener(this);
		moveWall.addKeyListener(this);

		// JPanel that will contain the grid
		grid=new JPanel();
		grid.setLayout(null);
		grid.setBounds(200, 60, 500,400);
		grid.setBackground(Color.CYAN);

		// JPanel that will contain everything (grid, buttons...)
		mainLabel= new JPanel();
		mainLabel.setLayout(null);
		mainLabel.add(throwDice);
		mainLabel.add(moveWall);
		mainLabel.add(grid);
		mainLabel.setBounds(0,0,800,600);
		mainLabel.setBackground(Color.YELLOW);
		

		add(mainLabel);

		setVisible(true);
		setFocusable(true);
        setFocusTraversalKeysEnabled(false);


	}
	
	public void actionPerformed(ActionEvent e){
		moveWall.setBackground(Color.MAGENTA);
	}
	
	public void keyPressed(KeyEvent ke){
		System.out.println();
	}
	
	public void keyReleased(KeyEvent ke){
		if (ke.getKeyCode() == KeyEvent.VK_LEFT){
			moveWall.setBackground(Color.MAGENTA);
			
		}
		else if(ke.getKeyCode() == KeyEvent.VK_UP){
			moveWall.setBackground(Color.BLACK);
		}
		else {
			moveWall.setBackground(Color.GREEN);
		}
		repaint();
	}
	
	public void keyTyped(KeyEvent ke){
		System.out.println();
	}

	public void repaint(){
		this.theTileGrid.draw();
	}
}
