public class Character {
	int x;
	int y;
	int availableDisplacement;
	int availableWallMoving;
	boolean hasWinned;
	int player;
	boolean upPressed, downPressed, leftPressed, rightPressed;

	public Character(int xini, int yini) { // constructor setting the initial position
		this.x = xini;
		this.y = yini;
		this.availableDisplacement = 0;
		this.hasWinned = false;
		this.availableWallMoving = 0;

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
			if (getY() > 0 && MainPanel.theTileGrid.binaryMap[getX()][getY() - 1] != 1) { //tests if it's a wall 
				this.y += -1; //upPressed corresponds to minus 1 because the scale is downwards
				somethingChanged = true;
			}
		} else if (downPressed) {
			if (getY() < MainPanel.theTileGrid.binaryMap[0].length-1 && MainPanel.theTileGrid.binaryMap[getX()][getY() + 1] != 1) {
				this.y += 1;
				somethingChanged = true;
			}
		} else if (rightPressed) {
			if (getX() < MainPanel.theTileGrid.binaryMap.length-1 && MainPanel.theTileGrid.binaryMap[getX() + 1][getY()] != 1) {
				this.x += 1;
				somethingChanged = true;
			}
		} else if (leftPressed) {
			if (getX() > 0 && MainPanel.theTileGrid.binaryMap[getX() - 1 ][getY()] != 1){
				this.x -= 1;
				somethingChanged = true;
			}
		}	
		return somethingChanged;	
	}


	public boolean updateWalls (){
		boolean somethingChanged = false;
		if(MainPanel.recentlyClicked){
			int x = (int)((MainPanel.mouseX-200)/20);
			int y = (int)((MainPanel.mouseY-20)/20);
			System.out.println(x+" "+y);
			System.out.println("firstSelected: " + MainPanel.firstSelected);

			if(0<=x && x<=30 && y>=0 && y<=30 && !MainPanel.firstSelected){
				System.out.println("entrée dans la boucle de conditions de coordonnées first");
				if(MainPanel.theTileGrid.binaryMap[x][y] ==1){
					System.out.println("entrée dans la boucle de condition binaryMap first");
					MainPanel.theTileGrid.binaryMap[x][y]=0;
					MainPanel.theTileGrid.map[x][y]=new Ground(20*x+200,20*y+20,20,20);
					MainPanel.firstSelected=true;
					System.out.println("recentlyClicked: " + MainPanel.recentlyClicked);
				}
			} else if(0<=x && x<=30 && y>=0 && y<=30 && MainPanel.firstSelected){
				System.out.println("entrée dans la boucle de conditions de coordonnées second"); 
				boolean p1Pos = (x==MainPanel.player1.getX() && y==MainPanel.player1.getY()); //return true if the case clicked is the case where the Hero is
				System.out.println("player1" + p1Pos);
				boolean p2Pos = (x==MainPanel.player2.getX() && y==MainPanel.player2.getY()); //same but for the Ufo
				System.out.println("player2" + p2Pos);
				if(MainPanel.theTileGrid.binaryMap[x][y] ==0 && !p1Pos && !p2Pos){
					System.out.println("entrée dans la boucle de condition binaryMap second");
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
