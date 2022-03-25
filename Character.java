public class Character {
    int x;
    int y;
    int availableDisplacement;
    boolean hasWinned;
    
    //constructor
    public Character(int xini,int yini){ //constructor setting the initial position
        this.x=xini;
        this.y=yini;
        this.availableDisplacement = 0;
        this.hasWinned = false;
    }
    
    // getters
    public int getX {
		return this.x;
	}
	
	public int getY {
		return this.y;
	}
    
    // update the position of the character
    public void displacement (int horizontal, int vertical){ //will work with Actionlistener and takes values 0, 1 or -1
        // must check before calling displacement if there is a Wall (tileGrid[x/y +ou- 1]) depending on the arrow pressed
        this.x += horizontal;
        this.y += vertical;
    }
    //after calling method displacement we must update the graphical interface

}
