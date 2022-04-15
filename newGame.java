import java.awt.*;
import javax.swing.*;

public class newGame extends JFrame implements Runnable{
	
	private boolean running =false;
	public Hero player1= new Hero(0,0);
	public UFO player2= new UFO(0,0);
	public static final String Name = "UFO ATTACK";
	public Thread gameThread ;
	
	
	public newGame(){
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
	
	public paintComponent(Graphics g) {
		if(!gameOver){
			Graphics2D g2 = (Graphics2D)g;
			
			mainFrame.grid.draw(g2); 
		}
	
	
	
	public void update() { 
		System.out.println("in game");
		if(!player1.hasWinned && !player2.hasWinned){
		
			if(player1.availableAction != 0){
					
				if (mainFrame.moveWallClicked){
					// action if wall is moved
					player1.availableAction = 0;
				}
				
				if (mainFrame.throwDiceClicked){
					player1.availableDisplacement = (int)Math.random()*6 ;
					if(player1.availableDisplacement!=0){
						player1.updatePosition();
						mainFrame.updateCharacterIcon(1, player1.getX(), player1.getY());
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
					
				if (mainFrame.moveWallClicked){
					// action if wall is moved
					player2.availableAction = 0;
				}
				
				if (mainFrame.throwDiceClicked){
					player2.availableDisplacement = (int)Math.random()*6 ;
					if(player2.availableDisplacement!=0){
						player2.updatePosition() ;
						mainFrame.updateCharacterIcon(2, player2.getX(), player2.getY());
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
}
