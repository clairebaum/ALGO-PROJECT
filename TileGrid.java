public class TileGrid{

	protected Tile[][] map;
	
		map = new Tile[20][20];
		for(int i = 0; i<map.length; i++){
			for(int j =0 ; j<map[i].length, j++){
				map[][] = new Tile(i*10 , j*10, i, j);
			}
		}
	}
}
//we still have to add the Walls and add the centre "win" tile
