package uk.ac.cam.slw69;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class PackedWorld extends WorldImpl
{
  private long cells;

  public PackedWorld()
  {
    super(8,8);
    cells = 0L;
  }

  protected PackedWorld(PackedWorld prev) 
  {
    super(prev);
    cells = 0L;
  }

  public boolean getCell(int col, int row) 
  {
    if(sanitiser(col, row))
      return false;

    int bitPos = getBitPos(col, row);
    return PackedLong.get(cells, bitPos);
  }

  public void setCell(int col, int row, boolean alive)
  {
    if(sanitiser(col, row))
      return;

    int bitPos = getBitPos(col, row);
    cells = PackedLong.set(cells, bitPos, alive);
  }

  private int getBitPos(int col, int row)
  {
    return row*8 + col;
  }

  private boolean sanitiser(int col, int row)
  {
    //returns true if arguments are outisde of allowd range
    return ((col < 0 || row < 0) || (col > 7 || row > 7));
  }

  protected PackedWorld nextGeneration()
  {
    PackedWorld world = new PackedWorld(this);

    for(int row = 0; row < 8; row++)
      for(int col = 0; col < 8; col++)
        world.setCell(col, row, computeCell(col,row));

    return world;
  }
}
