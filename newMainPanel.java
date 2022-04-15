import javax.swing.*;
import java.awt.*;

public class newMainPanel extends JPanel implements Runnable, ActionListener{
	
	//attributes relative to the game update
	private boolean running =false;
	public Hero player1= new Hero(0,0);
	public UFO player2= new UFO(0,0);
	public static final String Name = "UFO ATTACK";
	public Thread gameThread ;
	
	// attributes relative to the gridPanel
	public Panel grid = new Panel();
    public TileGrid theTileGrid;
    private ImageIcon HeroIcon;
	private ImageIcon UFOIcon;
    private JLabel HeroLabel;
	private JLabel UFOLabel;
	
	//atributes relative to the main Panel
	public JButton throwDice;
	public JButton moveWall;
	public boolean moveWallClicked;
	public boolean throwDiceClicked;
	
	
	//constructor
    public newMainPanel (){
        super();
        this.theTileGrid = new TileGrid();
        
        // graphical component of the main Panel
		
		this.setLayout(null);
		this.setBounds(0,0,900,700);
		this.setBackground(new Color (255,255,153));
		this.add(throwDice);
		this.add(moveWall);
		this.add(grid);
		
        
        // button to throw the dice
		throwDice= new JButton("Throw Dice");
		throwDice.setBounds(10,200,150,40);
		throwDice.addActionListener(this);

		// button to move a wall
		moveWall= new JButton("Move Wall");
		moveWall.setBounds(10,260,150,40);
		moveWall.addActionListener(this);
        
        //graphical component of the grid
		grid.setLayout(null);
		grid.setBounds(200, 20, 600,600);
		grid.setBackground(Color.pink);
		grid.add(HeroLabel);
		grid.add(UFOLabel);
        
        //JLabel with the Image Icon of the hero
		HeroIcon= new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet/Icon/Hero.png");
		HeroLabel= new JLabel(HeroIcon);
		HeroLabel.setSize(30,30);
		HeroLabel.setLocation(0,0);
		
		//JLabel with the Image Icon of the UFO
		UFOIcon= new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet/Icon/UFO.png");
		UFOLabel= new JLabel(UFOIcon);
		UFOLabel.setSize(45,45);
		UFOLabel.setLocation(300,300);
		
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
		
		//graphical component of the grid
		super.paintComponent(g);
		
		//graphical component of the TileGrid
		theTileGrid.draw(g);
		
		//graphical component of the characters
		HeroLabel.setLocation(player1.getX(), player1.getY());
		UFOLabel.setLocation(player2.getX(), player2.getY());
		
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
		
		
