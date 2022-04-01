public class Hero extends Character {

    // for now there aren't any additional attribute but we can add them here and in the constructor

    public Hero (int xini,int yini){ 
        super(xini,yini);
        this.player = 1 ;
    }
    //I didn't override displacement because no additional feature 

    public void winOrLose (){
        if (this.x==3 && this.y==3){ //change 3 to the centre coordinates!!!
            this.hasWinned = true;
    }
    //in the main we should call a method endGame(boolean 1 or boolean 2) {} 
    //where 1 and 2 are nameHero.hasWinned and nameUFO.hasWinned

}

