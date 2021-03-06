import java.awt.*;
public class TileGrid {

	public Tile[][] map = new Tile[30][30];
	int[][] binaryMap=                     {{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
						{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
						{0,0,0,0,0,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,0,0,0,0,0},
						{0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
						{0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
						{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
						{0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0},
						{0,0,0,0,0,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//line of zeros
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//line of zeros
						{0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0},
						{0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0},
						{0,0,1,0,0,1,0,0,1,0,0,0,0,1,1,1,1,0,0,0,0,1,0,0,1,0,0,1,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,1,0,1,2,2,1,0,1,0,0,0,0,0,0,0,0,0,0,0},//symmetry axis
						{0,0,0,0,0,0,0,0,0,0,0,1,0,1,2,2,1,0,1,0,0,0,0,0,0,0,0,0,0,0},//symmetry axis
						{0,0,1,0,0,1,0,0,1,0,0,0,0,1,1,1,1,0,0,0,0,1,0,0,1,0,0,1,0,0},
						{0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0},
						{0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//line of zeros
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//line of zeros
						{0,0,0,0,0,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,0,0,0,0,0},
						{0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0},
						{0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
						{0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
						{0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
						{0,0,0,0,0,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,0,0,0,0,0},
						{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
						{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1}};

	public TileGrid(){		//contructor creates always the same map
		for(int i = 0; i<map.length; i++){
			for(int j=0 ; j<map.length; j++){
				if (binaryMap[i][j]==0){
					map[i][j] = new Ground(i*20, j*20, 20, 20); 
				} else if(binaryMap[i][j]==1){
					map[i][j] = new Wall(i*20, j*20, 20, 20); 
				} else if(binaryMap[i][j]==2){
					map[i][j] = new WinningTile(i*20, j*20, 20, 20);
				}
			}
		}
	}
	
		
	//displays the map
	public void draw(Graphics g){
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++){
				map[i][j].paint(g); //ground and wall tiles have different paint methods
			}
		}
	}
}
	
