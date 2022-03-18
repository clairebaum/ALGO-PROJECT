import java.awt.*;
import javax.swing.*;

public class Game extends JFrame implements Runnable{
	
	public static final String Name = "UFO ATTACK";
	private frameGame mainFrame = new frameGame(Name, 800, 600); 
	private boolean running =false; 
	
	public Game(){
		
		
		
	}
	
	// starts the thread to launch the game 
	public void start(){
		running = true;
		new Thread(this).start();
	}
	
	// close the thread
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
		mainFrame.repaint();
	}
			
}

