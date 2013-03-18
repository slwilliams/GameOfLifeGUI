package uk.ac.cam.slw69;

import java.io.*;
import java.util.*;
import java.net.*;

public class PatternLoader
{
  public static List<Pattern> load(Reader r) throws IOException
  {
    BufferedReader buff = new BufferedReader(r);
    List<Pattern> patterns = new ArrayList<Pattern>();
    String line = "";
    while((line = buff.readLine()) != null)
    {
      try
      {
        patterns.add(new Pattern(line));
      }
      catch(PatternFormatException e)
      {
        System.out.println(e.getMessage());
      }
    }
    return patterns;
  }

  public static List<Pattern> loadFromURL(String url) throws IOException
  {
    URL destination = new URL(url);
    URLConnection conn = destination.openConnection();
    return load(new InputStreamReader(conn.getInputStream()));
  }

  public static List<Pattern> loadFromDisk(String filename) throws IOException 
  {
    return load(new FileReader(filename));
  }
}
