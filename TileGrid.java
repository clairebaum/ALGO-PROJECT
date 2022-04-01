import java.awt.*;
public class TileGrid {

	public Tile[][] map = new Ground[30][30];

	public TileGrid(){		//contructor creates always the same map
		for(int i = 0; i<map.length; i++){
			for(int j =0 ; j<map[i].length; j++){
				map[i][j] = new Ground(i*10 , j*10, 10, 10); 
			}
		}
	}
	
	public void draw(Graphics g){
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++){
				map[i][j].paint(g);
			}
		}
	}
}
