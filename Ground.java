import java.awt.*;


public class Ground extends Tile{
	
	public Ground(int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	public void paint(Graphics g){
		g.setColor(new Color (198, 140, 83));
		g.fillRect(this.posX, this.posY, this.height, this.width);
		g.setColor(new Color (135, 89, 44));
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}
	
	
}
