public class Hero extends Character {

    public Hero (int xini,int yini){ 
        super(xini,yini);
        this.player = 1 ;
    }

    public void winOrLose (){
        if (this.x>12 && this.x<17 && this.y>12 && this.y<17){ //change 3 to the centre coordinates!!!
            this.hasWinned = true;
            System.out.println("The hero has winned!");
        }
    }

}
