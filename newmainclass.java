import java.awt.event.KeyEvent;

//has not changed
public class newmainclass{
	public static newFrameGame mainFrame;
	public static void main(String[] args){
    String Name = "UFO Attack";
	mainFrame = new newFrameGame(Name, 800, 600); //creates the game window
	mainFrame.mainPanel.gameThread.start(); //launches the game
	}
}
