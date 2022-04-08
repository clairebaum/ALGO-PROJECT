import java.awt.*;
import javax.swing.*;

public class Game extends JFrame implements Runnable{
	public TileGrid aTileGrid = new TileGrid ();
	public static final String Name = "UFO ATTACK";
	private frameGame mainFrame = new frameGame(Name, 800, 600, aTileGrid); 
	private boolean running =false;
	public Character player1= new Hero(0,0);
	public Character player2= new UFO(0,0);
	public Game(){
	}
				
	// starts the thread to launch the game 
	public void start(){
		running = true;
		new Thread(this).start();
		mainFrame.grid.repaint();
		
		
		
	}
	
	// closes the thread
	public void stop(){
		running =false;

	}
	
	// runs the thread <=> execution of the code that launches the game
	public void run(){
		
		int ticks =0; // number of time the game was updated
		long time1 = System.currentTimeMillis(); 
		double delta = 0 ; // difference between time1 and current time
		double FPS = 1000/60; // number of ms between each update of the game
		
		while(running){
			long currentTime = System.currentTimeMillis();
			delta += (currentTime - time1)/ FPS;
			time1 = currentTime;
			
			while(delta>=1){
				ticks++;
				tick();
				delta-=1;
			}
			
		}
	}
	
	public void tick(){
		mainFrame.grid.repaint();
	}
	
	//jsp ou introduire la fonction (peut etre dans la fonction run cf l42) mais ca serait ca :
	public void play(){
		while(!player1.hasWinned && !player2.hasWinned){
			
			while(player1.availableAction != 0){
					
				if (mainFrame.moveWallClicked){
					// action if wall is moved
					player1.availableAction = 0;
				}
				
				if (mainFrame.throwDiceClicked){
					player1.availableDisplacement = (int)Math.random()*6 ;
					while(player1.availableDisplacement!=0){
						player1.updatePosition() ;
						mainFrame.updateCharacterIcon(1, player1.getX(), player1.getY());
						player1.availableDisplacement -= 1;
					}
					
					player1.availableAction = 0;
					
				}
			}
			
			player1.winOrLose();
			
			player2.availableAction = 1;
			
			while(player2.availableAction != 0){
					
				if (mainFrame.moveWallClicked){
					// action if wall is moved
					player2.availableAction = 0;
				}
				
				if (mainFrame.throwDiceClicked){
					player2.availableDisplacement = (int)Math.random()*6 ;
					while(player2.availableDisplacement!=0){
						player2.updatePosition() ;
						mainFrame.updateCharacterIcon(2, player2.getX(), player2.getY());
						player2.availableDisplacement -= 1;
						player2.winOrLose(player1.getX(), player1.getY());
					}
					
					player2.availableAction = 0;
				}
			}
			
			
			player1.availableAction = 1;
		}
	}
}
