import javax.awt.*;

public abstract class Block{

	protected int posX;
	protected int posY;
	protected int height;
	protected int width;
	
	public Block(int x, int y , int height, int width){
		this.posX = x;
		this.posY = y;
		this.height = height;
		this.width = width ; 
	}
	
	public abstract paint(Graphics g);
}
