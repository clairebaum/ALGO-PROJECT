public class UFO extends Character {


    public UFO (int xini,int yini){ 
        super(xini,yini);
        this.player = 2;
    }

    public void winOrLose (int xHero, int yHero){
        if(this.x==xHero && this.y==yHero){
            this.hasWon=true;
            System.out.println("The UFO has won!");
        }
    }

}
