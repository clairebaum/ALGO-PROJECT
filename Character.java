public class Character implements KeyHandler{
    int x;
    int y;
    int availableDisplacement;
    boolean hasWinned;
    KeyHandler control;
    int player ; 
    boolean isPlaying;
    boolean upPressed, downPressed, leftPressed, rightPressed, pressed ; 
    
    

    public Character(int xini,int yini){ //constructor setting the initial position
        this.x=xini;
        this.y=yini;
        this.availableDisplacement = 0;
        this.hasWinned = false;
        this.control = control; 
        this.isPlaying= false ;
        
    }
    
    public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

    /*public void displacement (int horizontal, int vertical){ //will work with Actionlistener and takes values 0, 1 or -1
        // must check before calling displacement if there is a Wall (tileGrid[x/y +ou- 1]) depending on the arrow pressed
        
        this.x += horizontal*10;
        this.y += vertical*10;
    }
    //after calling method displacement we must update the graphical interface
    */
    public void updatePosition() {
	
		switch{
			case upPressed:
				this.y += 1;
			break ;
			case downPressed :
				this.y += -1;
			case rightPressed:
				this.x += 1;
			break ;
			case downPressed :
				this.x += -1;	
			break;
		}
	}
	
	public void KeyPressed() {
		
	upPressed = false;
	downPressed = false;
	leftPressed = false;
	rightPressed = false;
	pressed = false ;
	
		if (canMove){ // check if player is allowed to move
		pressed = control.pressed;
		
			if(player == 1 && pressed) { // controls for Hero
			
			upPressed = control.zPressed;
			downPressed = control.sPressed;
			leftPressed = control.qPressed;
			rightPressed = control.dPressed;
		  
			} else if(player == 2 && pressed) { // controls for UFO
			 
			upPressed = control.upPressed;
			downPressed = control.downPressed;
			leftPressed = control.leftPressed;
			rightPressed = control.rightPressed;
		  
			}
		}

	}
}
