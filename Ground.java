import java.awt.*;


public class Ground extends Tile{
	
	public Ground(int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	public void paint(Graphics g){
		g.setColor(color.green);
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}
	
	
}
