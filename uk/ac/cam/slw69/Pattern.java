package uk.ac.cam.slw69;

import uk.ac.cam.acr31.life.World;

public class Pattern
{
  private String name;
  private String author;
  private int width;
  private int height;
  private int startCol;
  private int startRow;
  private String cells;
  
  public String rawPattern;

  public String getName()
  {
    return name;
  }

  public String getAuthor()
  {
    return author;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public int getStartCol()
  {
    return startCol;
  }

  public int getStartRow()
  {
    return startRow;
  }

  public String getCells()
  {
    return cells;
  }

  public Pattern(String format) throws PatternFormatException
  {
    try
    {
      rawPattern = format;
      String[] splitString = format.split(":");
      
      name = splitString[0];
      author = splitString[1];
      cells = splitString[6];
      width = Integer.parseInt(splitString[2]);
      height = Integer.parseInt(splitString[3]);
      startCol = Integer.parseInt(splitString[4]);
      startRow = Integer.parseInt(splitString[5]);
    }
    catch(Exception e){throw new PatternFormatException("Incorrectly formatted input string");}
  }

  public void initialise(World world) throws PatternFormatException
  {
    String[] cellsArray = cells.split(" ");
    for(int i = 0; i < cellsArray.length; i ++)
    {
      for(int j = 0; j < cellsArray[i].length(); j ++)
      {
        if(!(cellsArray[i].charAt(j) == '1' || cellsArray[i].charAt(j) == '0'))
          throw new PatternFormatException("Incorrectly formatted input string");
        world.setCell(startCol + j, startRow + i, (cellsArray[i].charAt(j) == '1'));
      }
    }
  }
}
