import java.awt.*;


public class Wall extends Tile{
	
	public Wall(int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	public void paint(Graphics g){
		g.setColor(color.black);
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}
	
	
}
