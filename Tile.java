import java.awt.*;
public abstract class Tile{ //mother class of Ground and Wall

	public int posX;
	public int posY;
	public int height;
	public int width; 
	
	/** Constructor
	  */
	public Tile (int x, int y , int height, int width){
		this.posX = x;
		this.posY = y;
		this.height = height;
		this.width = width ; 
	}
	
	/** paint method
	  */
	public abstract void paint(Graphics g);
}
