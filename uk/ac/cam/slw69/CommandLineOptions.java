package uk.ac.cam.slw69;

import java.util.List;

public class CommandLineOptions 
{
  public static String WORLD_TYPE_LONG = "--long";
  public static String WORLD_TYPE_AGING = "--aging";
  public static String WORLD_TYPE_ARRAY = "--array";
  
  private String worldType = null;
  private Integer index = null;
  private String source = null;

  public CommandLineOptions(String[] args) throws CommandLineException 
  {
		if(args.length == 1)
		{
			source = args[0];
			return;
		}		
		source = args[1];
		worldType = args.length == 3 ? args[0] : "--array";							
		index = new Integer(Integer.parseInt(args[2]));	
	}
  
  public String getWorldType() {return worldType;}
  public Integer getIndex() {return index;}
  public String getSource() {return source;}
}