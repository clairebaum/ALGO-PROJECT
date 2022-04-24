import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements Runnable, ActionListener {

	// attributes relative to the game update
	private boolean running = false;
	public Hero player1 = new Hero(1, 1); //so that they don't spawn on a wall
	public UFO player2 = new UFO(1, 2); //must be changed so that they're far away

	public Character[] players = new Character[]{player1, player2}; //easier to create an array to manipulate the players
	public int playing = 0; // which player has to play
	public static final int WAITING_FOR_DICE = 0, WAITING_FOR_ACTION = 1; //useful later on to determine the state of the game (action=choosing a direction or a wall to move)
	public int state = 0; //at the beginning noone has clicked on ThrowDice

	public Thread gameThread;

	// attributes relative to the gridPanel
	// public Panel grid = new Panel();
	public static TileGrid theTileGrid;
	private ImageIcon HeroIcon;
	private ImageIcon UFOIcon;
	private JLabel HeroLabel;
	private JLabel UFOLabel;
	
	//atributes relative to the GameInfo Panel
	private JPanel GameInfo; 
	private JLabel HeroInfo;
	private JLabel UFOInfo;
	private JLabel PlayerTurn; 
	// atributes relative to the main Panel
	public JButton throwDice;
	public JButton moveWall;
	public boolean moveWallClicked;
	public boolean throwDiceClicked;
	public boolean waitingForDirection = false;

	

	// constructor
	public MainPanel() {
		super();
		this.theTileGrid = new TileGrid();

		// graphical component of the main Panel

		this.setLayout(null);
		this.setBounds(0, 0, 900, 700);
		this.setBackground(new Color(255, 255, 153));
		
		//graphical component of the PlayerTurn label
		PlayerTurn = new JLabel("Game is starting");
		PlayerTurn.setBounds(10,10, 160, 40);
		if (playing == 1){
			PlayerTurn.setText("Hero's turn!");
		} else if (playing == 2){
			PlayerTurn.setText("UFO's turn!");
		}
		
		//graphical component of the HeroInfo label
		String HeroPA = Integer.toString(player1.availableAction);
		String HeroPD = Integer.toString(player1.availableDisplacement);
		HeroInfo = new JLabel("<html>HERO: <br/> Action Point : " + HeroPA + "<br/> Displacement available : " + HeroPD + "<html>");
		HeroInfo.setBounds(40, 50, 160, 40);
		HeroInfo.setBackground(Color.BLACK); 
		
		//graphical component of the UFOInfo label
		String UFOPA = Integer.toString(player2.availableAction);
		String UFOPD = Integer.toString(player2.availableDisplacement);
		UFOInfo = new JLabel("<html>UFO: <br/> Action Point : " + UFOPA + "<br/> Displacement available : " + UFOPD + "<html>");
		UFOInfo.setBounds(40, 90, 160, 40);
		UFOInfo.setBackground(Color.BLACK); 
		
		//graphical component of the GameInfo Panel
		GameInfo = new JPanel();
		GameInfo.setBounds(10,200, 180, 160);
		GameInfo.setBackground(Color.blue);
		GameInfo.add(PlayerTurn);
		GameInfo.add(HeroInfo);
		GameInfo.add(UFOInfo);
		
		
		// button to throw the dice
		throwDice = new JButton("Throw Dice");
		throwDice.setBounds(10, 400, 150, 40);
		throwDice.addActionListener(this);

		// button to move a wall
		moveWall = new JButton("Move Wall");
		moveWall.setBounds(10, 460, 150, 40);
		moveWall.addActionListener(this);

		// JLabel with the Image Icon of the hero
		HeroIcon = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/Icon/Hero.png");
		HeroLabel = new JLabel(HeroIcon);
		HeroLabel.setSize(30, 30);

		// JLabel with the Image Icon of the UFO
		UFOIcon = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/Icon/UFO.png");
		UFOLabel = new JLabel(UFOIcon);
		UFOLabel.setSize(45, 45);

		// add
		this.add(throwDice);
		this.add(moveWall);
		this.add(HeroLabel);
		this.add(UFOLabel);
		this.add(GameInfo);
		
		// thread creation
		gameThread = new Thread(this); //I don't remember if it was already there
	}

	// starts the thread to launch the game update
	public void start() {
		running = true;
		gameThread.start();
	}

	// closes the thread
	public void stop() {
		running = false;

	}

	// runs the thread <=> execution of the game
	public void run() {

		while (gameThread != null) {

			this.update();
			this.repaint();

			try {
				Thread.sleep(200);  
			} catch (InterruptedException e) {
			}
		}

	}

	// update function : gameplay turn mechanism, OLD VERSION
	/*
	public void update() {
		// System.out.println("in game");
		player1.KeyPressed();
		player2.KeyPressed();
		if (!player1.hasWinned && !player2.hasWinned) {
			
			if (player1.availableAction != 0) {

				if (this.moveWallClicked) {
					// action if wall is moved
					player1.availableAction = 0;
				}

				if (this.throwDiceClicked) {
					player1.availableDisplacement = (int) (Math.random() * 6);
					waitingForDirection = true;
					System.out.println("Throw Dice cliked");
					System.out.println(player1.availableDisplacement);

					this.throwDiceClicked = false;
				}
				if (player1.availableDisplacement == 0) {
					player1.availableAction = 0;
				}

			}
			if (waitingForDirection && player1.availableDisplacement != 0) {
				System.out.println(player1.availableDisplacement);
				waitingForDirection = !player1.updatePosition();
				player1.availableDisplacement -= 1;
			}

			player1.winOrLose();

			player2.availableAction = 1;

			if (player2.availableAction != 0) {

				if (this.moveWallClicked) {
					// action if wall is moved
					player2.availableAction = 0;
				}

				if (this.throwDiceClicked) {
					player2.availableDisplacement = (int) Math.random() * 6;
					if (player2.availableDisplacement != 0) {
						player2.updatePosition();
						player2.availableDisplacement -= 1;
						player2.winOrLose(player1.getX(), player1.getY());
					}

					if (player1.availableDisplacement == 0) {
						player2.availableAction = 0;
					}
					this.throwDiceClicked = false;
				}
			}

			player1.availableAction = 1;
		}
	}*/

	public void update() {
		for(Character c : players)c.KeyPressed(); //calls the method KeyPressed for both players, it couldn't work before because it was never called
		if(!player1.hasWinned && !player2.hasWinned) {
			if(state == WAITING_FOR_DICE) {
				if(throwDiceClicked) {
					players[playing].availableDisplacement = (int)(Math.random()*6)+1; //before it always gave 0 because without parenthesis it only casts Math.random, giving always 0
																					//we add 1 to get something between 1 and 6 instead of between 0 and 5
					throwDiceClicked = false; //we need to put it back to false, we didn't before
					state = WAITING_FOR_ACTION; // goes next
				}
			}else if(state == WAITING_FOR_ACTION) {
				if(players[playing].availableDisplacement > 0) {
					if(players[playing].updatePosition())players[playing].availableDisplacement-=1; // shorter way to write an if-loop
																			// we changed updatePosition so that it returns a boolean (equivalent to the previous 'pressed' we could maybe change it back to pressed idk)
					
				}else {
					state = 0; // waiting again for a dice roll
					if(playing == 0)playing = 1; //changes turn
					else playing = 0;

				}
			}

		}
	}

	// repaint function, has not changed
	public void paintComponent(Graphics g) {

		// graphical component of the grid
		super.paintComponent(g);
		theTileGrid.draw(g);

		// graphical component of the characters
		HeroLabel.setLocation(200 + player1.getX()*20, 10 + player1.getY()*20);
		UFOLabel.setLocation(190 + player2.getX()*20, player2.getY()*20);

	}

	// action listener for the buttons
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == throwDice) {
			throwDiceClicked = true;
		} else if (e.getSource() == moveWall) {
			moveWallClicked = true;
		}

		MainClass.mainFrame.requestFocusInWindow(); //the java KeyListener only works when the window is focused  
	}
}
