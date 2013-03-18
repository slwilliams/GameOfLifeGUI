package uk.ac.cam.slw69;

import java.util.List;
import java.io.*;
import uk.ac.cam.acr31.life.World;

public class TextLife 
{
	public static void main(String[] args) 
	{
		CommandLineOptions c = null;
		try
		{
			c = new CommandLineOptions(args);
		}catch(CommandLineException e){System.out.println(e.getMessage()); return;}
		List<Pattern> list;
		
		if(c.getSource().startsWith("http://"))
		{
			try
			{
				list = PatternLoader.loadFromURL(c.getSource());
			}catch(IOException e){System.out.println(e.getMessage()); return;}
			
		}
		else
		{
			try
			{
				list = PatternLoader.loadFromDisk(c.getSource());
			}catch(IOException e){System.out.println(e.getMessage()); return;}			
		}
		
		if(c.getIndex() == null) 
		{
			int i = 0;
			for (Pattern p : list)
			System.out.println((i++)+" "+p.getName()+" "+p.getAuthor());
		} 
		else
	  {
	  	if(c.getIndex() < 0 || c.getIndex() >= list.size()) {System.out.println("Error: Index out of bounds"); return;}
	  	Pattern p = list.get(c.getIndex());
			World w = null;
			
			if(c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_AGING)) 
			{
			  w = new AgingWorld(p.getWidth(), p.getHeight());
			} else if(c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_ARRAY)) 
			{
				w = new ArrayWorld(p.getWidth(), p.getHeight());
			} 
			else 
			{
			  w = new PackedWorld();
		  }
			
			try
			{
			  p.initialise(w);
			}catch(PatternFormatException e){System.out.println(e.getMessage()); return;}
			
			int userResponse = 0;
			
			while (userResponse != 'q') 
			{
			  w.print(new OutputStreamWriter(System.out));
			  try 
			  {
			    userResponse = System.in.read();
			  } catch (IOException e) {}
			  w = w.nextGeneration(0);
			}
		}
	}
}