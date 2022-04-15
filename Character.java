public class Character{
    int x;
    int y;
    int availableDisplacement;
    int availableAction ; 
    boolean hasWinned;
    KeyHandler control = new KeyHandler();
    int player ;
    boolean upPressed, downPressed, leftPressed, rightPressed, pressed ; 
    
    

    public Character(int xini,int yini){ //constructor setting the initial position
        this.x=xini;
        this.y=yini;
        this.availableDisplacement = -1;
        this.hasWinned = false;
        this.control = control;
        this.availableAction = -1;
        
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
	
		if (upPressed){
				this.y += 1;
		}
		
		if (downPressed){
				this.y += -1;
		}
		
		if(rightPressed){
				this.x += 1;
		}
		
		if (downPressed){
				this.x += -1;	
		}
	}
	
	public void KeyPressed() {
		
	upPressed = false;
	downPressed = false;
	leftPressed = false;
	rightPressed = false;
	pressed = false ;

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
