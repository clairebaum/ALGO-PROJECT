public class Hero extends Character {

	/** Constructor
	  */
    public Hero (int xini,int yini){ 
        super(xini,yini);
        this.player = 1 ;
    }

	/** Checks if the Hero has won
	  */
    public void winOrLose (){
        if (MainPanel.theTileGrid.binaryMap[this.x][this.y]==2){ //the Hero is on a winning tile 
            this.hasWon = true;
            System.out.println("The hero has won!");
        }
    }

	/** Override of KeyPressed from Character, checks if a key has been pressed and stores the information in the attributes
	  */
    public void KeyPressed(){
        upPressed = KeyHandler.isUpPressed(); //since these are static attributes we can access them here
        downPressed = KeyHandler.isDownPressed(); 
        leftPressed = KeyHandler.isLeftPressed();
        rightPressed = KeyHandler.isRightPressed();
    }

}
