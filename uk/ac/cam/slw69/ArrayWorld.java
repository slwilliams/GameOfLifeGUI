package uk.ac.cam.slw69;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class ArrayWorld extends WorldImpl
{
  private boolean[][] cells;

  public ArrayWorld(int w, int h)
  {
    super(w,h);
    cells = new boolean[h][w];
  }

  protected ArrayWorld(ArrayWorld prev) 
  {
    super(prev);
    cells = new boolean[getHeight()][getWidth()];
  }

  public boolean getCell(int col, int row) 
  {
    try
    {
      return cells[row][col];
    }catch(Exception e){return false;}
  }

  public void setCell(int col, int row, boolean alive)
  {
    cells[row][col] = alive;
  }

  protected ArrayWorld nextGeneration()
  {
    //Construct a new ArrayWorld object to hold the next generation:
    ArrayWorld world = new ArrayWorld(this);

    //Use for loops with "setCell" and "computeCell" to populate "world"
    for(int row = 0; row < cells.length; row++)
      for(int col = 0; col < cells[row].length; col++)
        world.setCell(col, row, computeCell(col,row));
    return world;
  }
}
