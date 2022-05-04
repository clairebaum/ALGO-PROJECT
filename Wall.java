import java.awt.*;

public class Wall extends Tile{

	/** Constructor
	  */
	public Wall(int x, int y , int height, int width){
	super(x, y, height, width); 
	}
	/** paint method
	  */	
	public void paint(Graphics g){ //draws a dark square and its contour at the position of the wall
		g.setColor(new Color(46,46,31));
		g.fillRect(this.posX, this.posY, this.height, this.width);
		g.setColor(new Color(0,0,0));
		g.drawRect(this.posX, this.posY, this.height, this.width);
		
	}	
}
