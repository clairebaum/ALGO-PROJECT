import java.awt.*;

public class WinningTile extends Ground {	
	
	/** Constructor
	  */
	public WinningTile (int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	
	/** paint method, overriden from Ground
	  */
	public void paint(Graphics g){ //draws a square with orange contour at the position of the WinningTile (in the center of the maze)
		g.setColor(Color.BLACK);
		g.fillRect(this.posX, this.posY, this.height, this.width);
		g.setColor(new Color(255,0,0));
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}	
}
