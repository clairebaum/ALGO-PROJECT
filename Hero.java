public class Hero extends Character {

    public Hero (int xini,int yini){ 
        super(xini,yini);
        this.player = 1 ;
    }

    public void winOrLose (){
        if (this.x==3 && this.y==3){ //change 3 to the centre coordinates!!!
            this.hasWinned = true;
        }
    }

}
