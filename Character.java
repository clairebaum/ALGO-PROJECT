public class Character {
    int x;
    int y;
    int availableDisplacement;

    public Character(int xini,int yini){
        this.x=xini;
        this.y=yini;
        this.availableDisplacement = 0;
    }

    public void displacement (int horizontal, int vertical){ //will work with Actionlistener and takes values 0, 1 or -1
        // must check before doing displacement if there is a Wall (tileGrid[x/y +ou- 1]) depending on the arrow pressed
        this.x += horizontal;
        this.y += vertical;
    }
    //after calling method displacement we must update the graphical interface
}