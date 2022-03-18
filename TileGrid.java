
public class TileGrid{

	protected Tile[][] map;
	
		map = new Tile[30][30];
		for(int i = 0; i<map.length; i++){
			for(int j =0 ; j<map[i].length, j++){
				map[][] = new Tile(i*10 , j*10, 10, 10);
			}
		}
	}
	
	public void draw(){
		for(int i = 0; i<map.length; i++){
			for(int j =0 ; j<map[i].length; j++){
				Tile t = map[i][j];
				t.paint(Graphics g);
			}
		}
	}		
}
