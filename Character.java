public class Character {
	int x;
	int y;
	int availableDisplacement;
	int availableAction;
	boolean hasWinned;
	int player;
	boolean upPressed, downPressed, leftPressed, rightPressed;

	public Character(int xini, int yini) { // constructor setting the initial position
		this.x = xini;
		this.y = yini;
		this.availableDisplacement = -1;
		this.hasWinned = false;
		this.availableAction = -1;

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	
	public boolean updatePosition() {
		boolean somethingChanged = false; //the boolean that will be returned
		if (upPressed) {
			if (getY() > 0 && newMainPanel.theTileGrid.binaryMap[getX()][getY() - 1] != 1) { //tests if it's a wall 
				this.y += -1; //upPressed corresponds to minus 1 because the scale is downwards
				somethingChanged = true;
			}
		} else if (downPressed) {
			if (getY() < newMainPanel.theTileGrid.binaryMap[0].length-1 && newMainPanel.theTileGrid.binaryMap[getX()][getY() + 1] != 1) {
				this.y += 1;
				somethingChanged = true;
			}
		} else if (rightPressed) {
			if (getX() < newMainPanel.theTileGrid.binaryMap.length-1 && newMainPanel.theTileGrid.binaryMap[getX() + 1][getY()] != 1) {
				this.x += 1;
				somethingChanged = true;
			}
		} else if (leftPressed) {
			if (getX() > 0 && newMainPanel.theTileGrid.binaryMap[getX() - 1 ][getY()] != 1){
				this.x -= 1;
				somethingChanged = false;
			}
		}	
		return somethingChanged;	
	}
	public void KeyPressed(){
		if(player == 1) { // controls for Hero
			
			upPressed = KeyHandler.isUpPressed();
			downPressed = KeyHandler.isDownPressed();
			leftPressed = KeyHandler.isLeftPressed();
			rightPressed = KeyHandler.isRightPressed();
		  
		} else if(player == 2) { // controls for UFO
			 
			upPressed = KeyHandler.isZPressed();
			downPressed = KeyHandler.isSPressed();
			leftPressed = KeyHandler.isQPressed();
			rightPressed = KeyHandler.isDPressed();
		}
	}
}
