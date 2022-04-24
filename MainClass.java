import java.awt.event.KeyEvent;

//has not changed
public class MainClass{
	
	public static FrameGame mainFrame;
	public static FrameGame menuFrame;
	
	public static void main(String[] args){
    String Name = "UFO Attack";
    
	mainFrame = new FrameGame(Name, 1000, 800); //creates the game window
	mainFrame.mainPanel.gameThread.start(); //launches the game
	}
}
