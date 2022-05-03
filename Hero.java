public class Hero extends Character {

    public Hero (int xini,int yini){ 
        super(xini,yini);
        this.player = 1 ;
    }

    public void winOrLose (){
        if (this.x>13 && this.x<17 && this.y>13 && this.y<17){ //center coordinates
            this.hasWon = true;
            System.out.println("The hero has won!");
        }
    }

}
