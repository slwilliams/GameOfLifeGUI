package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import uk.ac.cam.acr31.life.World;


public class GamePanel extends JPanel 
{
  private int zoom = 10; //Number of pixels used to represent a cell
  private int width = 1; //Width of game board in pixels
  private int height = 1;//Height of game board in pixels  
  private World current = null;

  public Dimension getPreferredSize() 
  {
    return new Dimension(width, height);
  }
  
  public void setZoom(int value)
  {
  	zoom = value;
  }

  protected void paintComponent(Graphics g) 
  {
    if(current == null) return;
    g.setColor(java.awt.Color.WHITE);
    g.fillRect(0, 0, width, height);
    current.draw(g, width, height);
    if(zoom > 4) 
    {
      g.setColor(java.awt.Color.LIGHT_GRAY);
  
      for(int col = 0; col < width; col += zoom)
      {
        g.drawLine(col, 0, col, height);
      }
      for(int row = 0; row < height; row += zoom)
      {
        g.drawLine(0, row, width, row);
      }
    }
  }

  private void computeSize() 
  {
    if(current == null) return;

    int newWidth = current.getWidth() * zoom;
    int newHeight = current.getHeight() * zoom;

    if(newWidth != width || newHeight != height) 
    { 
      width = newWidth;
      height = newHeight;
      revalidate();
    }
  }

  public void display(World w) 
  {
    current = w;
    computeSize();
    repaint();
  }
}
