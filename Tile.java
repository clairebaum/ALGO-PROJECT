import java.awt.*;
public abstract class Tile{

	public int posX;
	public int posY;
	public int height;
	public int width; 
	
	public Tile (int x, int y , int height, int width){
		this.posX = x;
		this.posY = y;
		this.height = height;
		this.width = width ; 
	}
	
	public abstract void paint(Graphics g);
}
