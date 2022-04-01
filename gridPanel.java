import javax.swing.*;
import java.awt.*;

public class gridPanel extends JPanel {
    public TileGrid theTileGrid;

    public gridPanel (){
        super();
        this.theTileGrid = new TileGrid();
    }
    public void paintComponent (Graphics g){ 
        super.paintComponent(g);
        theTileGrid.draw(g); 
    }
    
}
