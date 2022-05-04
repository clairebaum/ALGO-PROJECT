public class UFO extends Character {

	/** Constructor
	  */
    public UFO (int xini,int yini){ 
        super(xini,yini);
        this.player = 2;
    }

	/** checks if the UFO has won
	  */
    public void winOrLose (int xHero, int yHero){
        if(this.x==xHero && this.y==yHero){ //the UFO has caught the Hero, they are on the same tile
            this.hasWon=true;
            System.out.println("The UFO has won!");
        }
    }

}
