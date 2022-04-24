import java.awt.*;

public class WinningTile extends Ground {	
	public WinningTile (int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(this.posX, this.posY, this.height, this.width);
		g.setColor(new Color(255,0,0));
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}	
}
