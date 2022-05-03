import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements Runnable, ActionListener, MouseListener {

	// attributes relative to the game update
	public static Hero player1 = new Hero(1, 1); //so that they don't spawn on a wall
	public static UFO player2 = new UFO(15, 15); 

	public Character[] players = new Character[]{player1, player2}; //easier to create an array to manipulate the players
	public int playing = 0; // which player has to play
	public static final int WAITING_FOR_DICE = 0, WAITING_FOR_ACTION = 1; //useful later on to determine the state of the game (action=choosing a direction or a wall to move)
	public int state = 0; //at the beginning no one has clicked on ThrowDice

	public Thread gameThread;

	//attributes relative to the TileGrid 
	public static TileGrid theTileGrid; //static because we access it from other classes and there is only one TileGrid
	private ImageIcon HeroIcon;
	private ImageIcon UFOIcon;
	private JLabel HeroLabel;
	private JLabel UFOLabel;

	// atributes relative to the GameInfo Panel
	private JPanel GameInfo;
	private JLabel Info;
	private JLabel PlayerTurn;
	private String PA;
	private String PD;
	public JButton startGame;
	public JLabel Warning ;

	// attributes relative to the main Panel
	public JButton throwDice;
	public JButton moveWall;
	public boolean moveWallClicked;
	public boolean throwDiceClicked;
	public boolean waitingForDirection = false;

	//attributes relative to the MouseListener
	public static boolean recentlyClicked = false; 
	public static boolean firstSelected = false;
	public static double mouseX = 0.0;
	public static double mouseY = 0.0;

	// constructor
	public MainPanel() {
		super();
		theTileGrid = new TileGrid();

		// graphical component of the main Panel
		this.setLayout(null);
		this.setBounds(0, 0, 900, 700);
		this.setBackground(new Color (61, 61, 92));;

		//graphical component of the PlayerTurn label
		PlayerTurn = new JLabel("Game is starting");
		PlayerTurn.setBounds(10,10, 160, 40);
	
		
		//graphical component of the Info label
		PA = Integer.toString(players[playing].availableWallMoving);
		PD = Integer.toString(players[playing].availableDisplacement);
		Info = new JLabel(" ");
		Info.setBounds(40, 50, 160, 40);
		Info.setBackground(Color.BLACK);
		
		// button to start game
		startGame = new JButton("START");
		startGame.setBounds(10, 200, 150, 40);
		startGame.setBackground(new Color(123, 165, 248));
		startGame.addActionListener(this);
		startGame.setVisible(true);
		
		//graphical component of the GameInfo Panel
		GameInfo = new JPanel();
		GameInfo.setBounds(10, 200, 180, 80);
		GameInfo.setBackground(new Color(123, 165, 248));
		GameInfo.add(PlayerTurn);
		GameInfo.add(Info);
		GameInfo.setVisible(false);
		
		//graphical component of the Warning Label
		Warning = new JLabel("You 're stuck! Get out by moving the wall around you", SwingConstants.CENTER);
		Warning.setBounds(300, 660, 400, 40);
		Warning.setOpaque(true);
		Warning.setBackground(Color.RED);
		Warning.setForeground(Color.WHITE);
		Warning.setVisible(false);
		
		// button to throw the dice
		throwDice = new JButton("Throw Dice");
		throwDice.setBounds(10, 400, 150, 40);
		throwDice.addActionListener(this);

		// button to move a wall
		moveWall = new JButton("Move Wall");
		moveWall.setBounds(10, 460, 150, 40);
		moveWall.addActionListener(this);

		// JLabel with the Image Icon of the hero
		HeroIcon = new ImageIcon("C:/Users/gadis/Desktop/algo final/ALGO-PROJECT-FINAL/Icon/Hero.png");
		HeroLabel = new JLabel(HeroIcon);
		HeroLabel.setSize(30, 30);
		HeroLabel.setVisible(false);

		// JLabel with the Image Icon of the UFO
		UFOIcon = new ImageIcon("C:/Users/gadis/Desktop/algo final/ALGO-PROJECT-FINAL/Icon/UFO.png");
		UFOLabel = new JLabel(UFOIcon);
		UFOLabel.setSize(45, 45);
		UFOLabel.setVisible(false);

		// add
		this.add(throwDice);
		this.add(moveWall);
		this.add(HeroLabel);
		this.add(UFOLabel);
		this.add(GameInfo);
		this.add(startGame);
		this.add(Warning);
		addMouseListener(this);

		// thread creation
		gameThread = new Thread(this); 
	}

	// starts the thread to launch the game update
	public void start() {
		gameThread.start();
	}

	// closes the thread
	public void stop() {
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


	public void update() {
		for(Character c : players)c.KeyPressed(); //calls the method KeyPressed for both players, it couldn't work before because it was never called
		if(!player1.hasWon && !player2.hasWon) {
			if(state == WAITING_FOR_DICE) {
				if(throwDiceClicked) {
					players[playing].availableDisplacement = (int)(Math.random()*6)+1; //before it always gave 0 because without parenthesis it only casts Math.random, giving always 0
																					//we add 1 to get something between 1 and 6 instead of between 0 and 5
					throwDiceClicked = false; //we need to put it back to false, we didn't before
					state = WAITING_FOR_ACTION; // goes next
				} else if (moveWallClicked) {
					players[playing].availableWallMoving = 3;
					moveWallClicked = false;
					state = WAITING_FOR_ACTION;
				}
			
			}else if(state == WAITING_FOR_ACTION) {
				if(players[playing].availableDisplacement > 0) {
					if(players[playing].updatePosition())players[playing].availableDisplacement-=1; // shorter way to write an if-loop
																			// we changed updatePosition so that it returns a boolean (equivalent to the previous 'pressed' we could maybe change it back to pressed idk)
				}else if(players[playing].availableWallMoving > 0) {
					if(players[playing].updateWalls())players[playing].availableWallMoving-=1;

				}else{
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

		//variable for the game Info Label
		PA = Integer.toString(players[playing].availableWallMoving);
		PD = Integer.toString(players[playing].availableDisplacement);
		Info.setText("<html> Wall movements available : " + PA + "<br/> Displacement available : " + PD + "<html>");
		if (playing == 0){
		PlayerTurn.setText("Hero's turn!");
		} else if (playing == 1){
		PlayerTurn.setText("UFO's turn!");
		}

		// graphical component of the characters
		HeroLabel.setLocation(200 + player1.getX()*20, 10 + player1.getY()*20);
		UFOLabel.setLocation(190 + player2.getX()*20, player2.getY()*20);

	}

	// action listener for the buttons
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == throwDice) {
			boolean stuckTop = true;
			boolean stuckBot = true;
			boolean stuckLeft = true;
			boolean stuckRight = true;
			
			if (players[playing].x != 0) { stuckLeft = (theTileGrid.binaryMap[players[playing].x-1][players[playing].y]==1);}
			if (players[playing].x != 29) { stuckRight = (theTileGrid.binaryMap[players[playing].x+1][players[playing].y]==1);}
			if(players[playing].y != 0) { stuckTop = (theTileGrid.binaryMap[players[playing].x][players[playing].y -1]==1);}
			if (players[playing].y != 29) {stuckBot = (theTileGrid.binaryMap[players[playing].x][players[playing].y +1]==1);}
			if (!(stuckTop && stuckBot && stuckLeft && stuckRight) && state == WAITING_FOR_DICE){
				throwDiceClicked = true;
			} else { Warning.setVisible(true);}
		} else if (e.getSource() == moveWall) {
			if(state==WAITING_FOR_DICE){
				moveWallClicked = true;
				Warning.setVisible(false);
			}
		} else if (e.getSource() ==startGame) {
				HeroLabel.setVisible(true);
				UFOLabel.setVisible(true);
				GameInfo.setVisible(true);
				startGame.setVisible(false);
		}
		FrameMenu.mainFrame.requestFocusInWindow(); //the java KeyListener only works when the window is focused  
	}


	public void mousePressed (java.awt.event.MouseEvent e){
		mouseX=e.getX();
        mouseY=e.getY();
		recentlyClicked = true;
	}
	public void mouseReleased (java.awt.event.MouseEvent e){
		recentlyClicked = false;
	}
	
	@Override
	public void mouseClicked (java.awt.event.MouseEvent e){}
	public void mouseEntered(java.awt.event.MouseEvent e) {}
 	public void mouseExited(java.awt.event.MouseEvent e) {}
}





