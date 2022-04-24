import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;


public class frameGame extends JFrame implements ActionListener, KeyListener{
	// attributes of class frameGame
	public JPanel mainLabel;
	public gridPanel grid;
	public JButton throwDice;
	public JButton moveWall;
	private ImageIcon HeroIcon;
	private ImageIcon UFOIcon;
    	private JLabel HeroLabel;
	private JLabel UFOLabel;

	
	public frameGame(String name, int width, int height) {
		super(name);
		setSize(width, height);
		setLocation(200,5);
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
		
		//JLabel with the Image Icon of the hero
		HeroIcon= new ImageIcon("C:/Users/Eléonore/Desktop/2A/algo/Projet/avatar.png");
		HeroLabel= new JLabel(HeroIcon);
		
		HeroLabel.setSize(30,30);
		HeroLabel.setLocation(0,0);
		
		//JLabel with the Image Icon of the UFO
		UFOIcon= new ImageIcon("C:/Users/Eléonore/Desktop/2A/algo/Projet/UFO.png");
		UFOLabel= new JLabel(UFOIcon);
		UFOLabel.setSize(45,45);
		UFOLabel.setLocation(300,300);

		// JPanel that will contain the grid
		grid=new gridPanel();
		grid.setLayout(null);
		grid.setBounds(200, 20, 600,600);
		grid.setBackground(Color.pink);
		grid.add(HeroLabel);
		grid.add(UFOLabel);

		// JPanel that will contain everything (grid, buttons...)
		mainLabel= new JPanel();
		mainLabel.setLayout(null);
		mainLabel.add(throwDice);
		mainLabel.add(moveWall);
		mainLabel.add(grid);
		mainLabel.setBounds(0,0,900,700);
		mainLabel.setBackground(new Color (61, 61, 92));
		

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
		this.grid.repaint();
	}
	
	public void keyTyped(KeyEvent ke){
		System.out.println();
	}

}
