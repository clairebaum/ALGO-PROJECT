import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newMainPanel extends JPanel implements Runnable, ActionListener{
	
	//attributes relative to the game update
	private boolean running =false;
	public Hero player1= new Hero(0,0);
	public UFO player2= new UFO(0,0);
	public Thread gameThread ;
	
	// attributes relative to the gridPanel
    	public TileGrid theTileGrid;
    	private ImageIcon HeroIcon;
	private ImageIcon UFOIcon;
    	private JLabel HeroLabel;
	private JLabel UFOLabel;
	
	//atributes relative to the main Panel
	public JButton throwDice ;
	public JButton moveWall;
	public boolean moveWallClicked;
	public boolean throwDiceClicked;
	
	
	//constructor
   	public newMainPanel (){
        super();
        
        
        // graphical component of the main Panel
		this.setVisible(true);
		this.setLayout(null);
		this.setBounds(0,0,900,700);
		this.setBackground(new Color (255,255,153));
		
		
        
        // button to throw the dice
		throwDice= new JButton("Throw Dice");
		throwDice.setBounds(10,200,150,40);
		throwDice.addActionListener(this);

	// button to move a wall
		moveWall= new JButton("Move Wall");
		moveWall.setBounds(10,260,150,40);
		moveWall.addActionListener(this);
        
        // TileGrid
       		 this.theTileGrid = new TileGrid(grid.getX(),grid.getY());
        
        // JLabel with the Image Icon of the hero
		HeroIcon= new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet/Icon/Hero.png");
		HeroLabel= new JLabel(HeroIcon);
		HeroLabel.setSize(30,30);
		HeroLabel.setLocation(0,0);
		
	//JLabel with the Image Icon of the UFO
		UFOIcon= new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet/Icon/UFO.png");
		UFOLabel= new JLabel(UFOIcon);
		UFOLabel.setSize(45,45);
		UFOLabel.setLocation(300,300);
		
	// add component to main panel
		this.add(throwDice);
		this.add(moveWall);
		this.add(HeroLabel);
		this.add(UFOLabel);
		
		
        
		
		//thread creation
		gameThread = new Thread(this);
    }
    
    // starts the thread to launch the game update 
	public void start(){
		running = true;
		gameThread.start();
	}
	
	// closes the thread
	public void stop(){
		running =false;

	}
	
	// runs the thread <=> execution of the game
	public void run(){
		
		while (gameThread != null){
			
			this.update();
			this.repaint();
			
			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e){
			}
		}
		
	}
	
	//update function : gameplay turn mechanism
	public void update() { 
		System.out.println("in game");
		if(!player1.hasWinned && !player2.hasWinned){
		
			if(player1.availableAction != 0){
					
				if (this.moveWallClicked){
					// action if wall is moved
					player1.availableAction = 0;
				}
				
				if (this.throwDiceClicked){
					player1.availableDisplacement = (int)Math.random()*6 ;
					if(player1.availableDisplacement!=0){
						player1.updatePosition();
						player1.availableDisplacement -= 1;
					}
				}
				if (player1.availableDisplacement == 0){
					player1.availableAction = 0;	
				}
				
			}
		
			
			player1.winOrLose();
			
			player2.availableAction = 1;
			
			if(player2.availableAction != 0){
					
				if (this.moveWallClicked){
					// action if wall is moved
					player2.availableAction = 0;
				}
				
				if (this.throwDiceClicked){
					player2.availableDisplacement = (int)Math.random()*6 ;
					if(player2.availableDisplacement!=0){
						player2.updatePosition() ;
						player2.availableDisplacement -= 1;
						player2.winOrLose(player1.getX(), player1.getY());
					}
					
					if (player1.availableDisplacement == 0){
					player2.availableAction = 0;
					}
				}
			}
			
			player1.availableAction = 1;
		}
	}
	
    //repaint function
    public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		
		//graphical component of the grid
		super.paintComponent(g2);
		grid.setBackground(new Color (0,0,0,0));
		
		//graphical component of the TileGrid
		theTileGrid.draw(g2);
		
		//graphical component of the characters
		HeroLabel.setLocation(200+player1.getX(), 20+player1.getY());
		UFOLabel.setLocation(200+player2.getX(), 20+player2.getY());
		
	}
	
	//action listener for the buttons
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == throwDice) {
			throwDiceClicked = true ;
	    } else if (e.getSource() == moveWall){
			moveWallClicked = true ;
		}
	}
}	
	
		
