public abstract class Character { //mother class of Hero and UFO
	int x;
	int y;
	int availableDisplacement;
	int availableWallMoving;
	boolean hasWon;
	int player;
	boolean upPressed, downPressed, leftPressed, rightPressed; //link the displacement and the keys pressed by the player, see methods KeyPressed() and updatePosition()

	/** Constructor
	  */
	public Character(int xini, int yini) { // constructor setting the initial position
		this.x = xini;
		this.y = yini;
		this.availableDisplacement = 0;
		this.hasWon = false;
		this.availableWallMoving = 0;
	}
	
	/** Changes the position of the avatars, according to the keys pressed
	  */
	public boolean updatePosition() {
		boolean somethingChanged = false; //the boolean that will be returned
		if (upPressed) {
			if (this.y > 0 && MainPanel.theTileGrid.binaryMap[this.x][this.y - 1] != 1) { //tests if it's a wall 
				this.y += -1; //upPressed corresponds to minus 1 because the scale is downwards
				somethingChanged = true;
			}
		} else if (downPressed) {
			if (this.y < MainPanel.theTileGrid.binaryMap[0].length-1 && MainPanel.theTileGrid.binaryMap[this.x][this.y + 1] != 1) {
				this.y += 1;
				somethingChanged = true;
			}
		} else if (rightPressed) {
			if (this.x < MainPanel.theTileGrid.binaryMap.length-1 && MainPanel.theTileGrid.binaryMap[this.x + 1][this.y] != 1) {
				this.x += 1;
				somethingChanged = true;
			}
		} else if (leftPressed) {
			if (this.x > 0 && MainPanel.theTileGrid.binaryMap[this.x - 1 ][this.y] != 1){
				this.x -= 1;
				somethingChanged = true;
			}
		}	
		return somethingChanged;	
	}

	/** removes and adds walls, according to the tiles clicked
	  */
	public boolean updateWalls (){
		boolean somethingChanged = false; 
		if(MainPanel.recentlyClicked){
			int x = (int)((MainPanel.mouseX-200)/20); //gets the coordinates of the mouse (stored in MainPanel) and converts them into the index of the tile
			int y = (int)((MainPanel.mouseY-20)/20);

			//to select the wall to be moved
			if(0<=x && x<=30 && y>=0 && y<=30 && !MainPanel.firstSelected){ //the boolean firstSelected is false when the wall to move has yet to be selected
				if(MainPanel.theTileGrid.binaryMap[x][y] ==1){
					MainPanel.theTileGrid.binaryMap[x][y]=0;
					MainPanel.theTileGrid.map[x][y]=new Ground(20*x+200,20*y+20,20,20);
					MainPanel.firstSelected=true;
				}
			//to select where to put the wall back
			} else if(0<=x && x<=30 && y>=0 && y<=30 && MainPanel.firstSelected){ 
				boolean p1Pos = (x==MainPanel.player1.x && y==MainPanel.player1.y); //returns true if the case clicked is the case where the Hero is
				boolean p2Pos = (x==MainPanel.player2.x && y==MainPanel.player2.y); //same but for the Ufo
				if(MainPanel.theTileGrid.binaryMap[x][y] ==0 && !p1Pos && !p2Pos){ //so that we cannot place a wall on a character or on an existing wall
					MainPanel.theTileGrid.binaryMap[x][y]=1;
					MainPanel.theTileGrid.map[x][y]=new Wall(20*x+200,20*y+20,20,20);
					MainPanel.firstSelected=false;
					somethingChanged = true;
				}

				
			} else {
				System.out.println("You clicked on the side!");
			}
		}
		return somethingChanged;
	}

	/** Checks if a key has been pressed and stores the information in the attributes
	  */
	public void KeyPressed(){
		if(player == 1) { // controls for Hero
			
			upPressed = KeyHandler.isUpPressed(); //since these are static attributes we can access them here
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
