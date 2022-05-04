import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements Runnable, ActionListener, MouseListener {

	// attributes relative to the game update
	public static Hero player1 = new Hero(1, 1); //so that they don't spawn on a wall
	public static UFO player2 = new UFO(15, 15); 
	public Character[] players = new Character[]{player1, player2}; //easier to create an array to manipulate the players
	public int playing = 0; // which player has to play
	public static final int WAITING_FOR_CHOICE = 0, WAITING_FOR_ACTION = 1; 
	public int state = 0; //at the beginning no one has chosen an action yet

	public Thread gameThread;

	//attributes relative to the maze
	public static TileGrid theTileGrid; 
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
	public JLabel EndGame;

	// attributes relative to the main Panel
	public JButton throwDice;
	public JButton moveWall;
	public boolean moveWallClicked;
	public boolean throwDiceClicked;

	//attributes relative to the MouseListener
	public static boolean recentlyClicked = false; 
	public static boolean firstSelected = false; //true if the wall to move has been selected already
	public static double mouseX = 0.0;
	public static double mouseY = 0.0;

	/** Constructor
	  */
	public MainPanel() {
		super();
		theTileGrid = new TileGrid(); //creates the maze

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
		
		//graphical component of the EndGame Label
		EndGame = new JLabel("", SwingConstants.CENTER);
		EndGame.setBounds(300, 230, 400, 180);
		EndGame.setOpaque(true);
		EndGame.setBackground(Color.BLACK);
		EndGame.setForeground(Color.WHITE);
		EndGame.setVisible(false);
		
		// button to throw the dice
		throwDice = new JButton("Throw Dice");
		throwDice.setBounds(10, 400, 150, 40);
		throwDice.addActionListener(this);

		// button to move a wall
		moveWall = new JButton("Move Wall");
		moveWall.setBounds(10, 460, 150, 40);
		moveWall.addActionListener(this);

		// JLabel with the Image Icon of the hero
		HeroIcon = new ImageIcon("Icon/avatar.png");
		HeroLabel = new JLabel(HeroIcon);
		HeroLabel.setSize(30, 30);
		HeroLabel.setVisible(false);

		// JLabel with the Image Icon of the UFO
		UFOIcon = new ImageIcon("Icon/UFO.png");
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


	/** starts the thread to launch the game update
	  */
	public void start() {
		gameThread.start();
	}


	/** closes the thread
	  */
	public void stop() {
	}


	/** runs the thread => execution of the game
	  */
	public void run() {
		while (gameThread != null) {
			this.update();
			player1.winOrLose();
			player2.winOrLose(player1.x, player1.y);
			this.repaint();
			try {
				Thread.sleep(200);  
			} catch (InterruptedException e) {
			}
		}

	}


	/** Method that handles the execution of the turn-by-turn mechanism
	  */
	public void update() {
		if(!player1.hasWon && !player2.hasWon) {
			if(state == WAITING_FOR_CHOICE) {
				if(throwDiceClicked) {
					players[playing].availableDisplacement = (int)(Math.random()*6)+1; //acts like a roll of dice												
					throwDiceClicked = false; 
					state = WAITING_FOR_ACTION; // goes next
				} else if (moveWallClicked) {
					players[playing].availableWallMoving = 3;
					moveWallClicked = false;
					state = WAITING_FOR_ACTION;
				}
			
			}else if(state == WAITING_FOR_ACTION) {
				for(Character c : players)c.KeyPressed(); //calls the method KeyPressed for both players
				if(players[playing].availableDisplacement > 0) {
					if(players[playing].updatePosition())players[playing].availableDisplacement-=1; //updatePosition() returns if something changed, i.e. if the character moved														
				}else if(players[playing].availableWallMoving > 0) {
					if(players[playing].updateWalls())players[playing].availableWallMoving-=1; //updateWalls() returns if something changed, i.e. if a wall has been moved	

				}else{
					state = 0; // waiting again for a choice of action
					if(playing == 0)playing = 1; //changes turn
					else playing = 0;

				}
			}

		}

		//graphical component at the end of the game
		if (player1.hasWon){
			EndGame.setText("<html> The Hero made it home! <br/><br/> Congratulations!");
			EndGame.setVisible(true);
			HeroLabel.setVisible(false);
			UFOLabel.setVisible(false);
		} else if (player2.hasWon){
			EndGame.setText("<html> The Hero has been caught! <br/><br/> The UFO wins!");
			EndGame.setVisible(true);
			HeroLabel.setVisible(false);
			UFOLabel.setVisible(false);
		}
	}

	/** paint method
	  */
	public void paintComponent(Graphics g) {

		// display of the grid
		super.paintComponent(g);
		theTileGrid.draw(g);

		//display of the game Info Label
		PA = Integer.toString(players[playing].availableWallMoving);
		PD = Integer.toString(players[playing].availableDisplacement);
		Info.setText("<html> Wall movements available : " + PA + "<br/> Displacement available : " + PD + "<html>");
		if (playing == 0){
		PlayerTurn.setText("Hero's turn!");
		} else if (playing == 1){
		PlayerTurn.setText("UFO's turn!");
		}

		//display of the characters
		HeroLabel.setLocation(200 + player1.x*20, 10 + player1.y*20);
		UFOLabel.setLocation(190 + player2.x*20, player2.y*20);

	}

	/** override of the actionPerformed for the buttons
	  */
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
			if (!(stuckTop && stuckBot && stuckLeft && stuckRight) && state == WAITING_FOR_CHOICE){
				throwDiceClicked = true;
			} else { Warning.setVisible(true);}
		} else if (e.getSource() == moveWall) {
			if(state==WAITING_FOR_CHOICE){
				moveWallClicked = true;
				Warning.setVisible(false);
			}
		} else if (e.getSource() == startGame) {
				HeroLabel.setVisible(true);
				UFOLabel.setVisible(true);
				GameInfo.setVisible(true);
				startGame.setVisible(false);
		}
		FrameMenu.mainFrame.requestFocusInWindow(); 
	}

	/** override of the mousePressed that updates the attributes for the mouse position
	  */
	public void mousePressed (java.awt.event.MouseEvent e){
		mouseX=e.getX();
        mouseY=e.getY();
		recentlyClicked = true;
	}

	/** override of the mouseReleased
	  */
	public void mouseReleased (java.awt.event.MouseEvent e){
		recentlyClicked = false;
	}
	
	@Override
	public void mouseClicked (java.awt.event.MouseEvent e){}
	public void mouseEntered(java.awt.event.MouseEvent e) {}
 	public void mouseExited(java.awt.event.MouseEvent e) {}
}

