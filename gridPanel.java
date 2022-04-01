import javax.swing.*;
import java.awt.*;

public class gridPanel extends JPanel {
    public TileGrid theTileGrid;

    public gridPanel (TileGrid aTileGrid){
        super();
        this.theTileGrid = aTileGrid;
    }
    public void paintComponent (Graphics g){ 
        super.paintComponent(g);
        theTileGrid.draw(g);
    }
}
