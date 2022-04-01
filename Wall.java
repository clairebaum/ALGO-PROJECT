import java.awt.*;

public class Wall extends Tile{
	
	public Wall(int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(this.posX, this.posY, this.height, this.width);
		g.setColor(Color.GREEN);
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}
	
	
}
