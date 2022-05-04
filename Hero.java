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

}
