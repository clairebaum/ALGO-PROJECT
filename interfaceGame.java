import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class interfaceGame extends JFrame implements ActionListener{
	// attributes of class interfaceGame
	public JPanel mainLabel;
	public JPanel grid;
	public JButton throwDice;
	public JButton moveWall;
	
	public interfaceGame(String name, int width, int height) {
		super(name);
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


	}
	public static void main(String[] args) {
		interfaceGame i = new interfaceGame("UFO ATTACK", 800, 600);
	}

}
