public class mainclass{
	public static void main(String[] args){
		GameUpdate gameUp = new GameUpdate(); // creates the thread which updates the frame
		Game game = new Game(); // creates the thread which launches the game mechanism
		gameUp.start();
		game.start();
		gameUp.run();
		game.run();
	}
}
