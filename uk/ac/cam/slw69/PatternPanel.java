package uk.ac.cam.slw69;

import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.event.*;

public abstract class PatternPanel extends JPanel 
{
  private JList guiList;
  private Pattern currentPattern;
  private List<Pattern> patternList;
  
  protected abstract void onPatternChange();

  public PatternPanel() 
  {
    super();
    currentPattern = null;
    setLayout(new BorderLayout());
    guiList = new JList();
    add(new JScrollPane(guiList));
    
    guiList.addListSelectionListener(new ListSelectionListener() 
    {
			public void valueChanged(ListSelectionEvent e) 
			{
				if(!e.getValueIsAdjusting() && (patternList != null)) 
				{
					int sel = guiList.getSelectedIndex();
					
					if (sel != -1) 
					{
						currentPattern = patternList.get(sel);
						onPatternChange();
					}
				}
			}
		});
	}
  
  public Pattern getCurrentPattern()
  {
  	return currentPattern;
  }

  public void setPatterns(List<Pattern> list)
  {
  	patternList = list;
  	if(list == null) 
 		{
 			currentPattern = null; //if list is null, then no valid pattern
 			guiList.setListData(new String[]{}); //no list item to select
 			return;
 		}
 		
    ArrayList<String> names = new ArrayList<String>();
    for(Pattern p : list)
    {
      names.add(p.getName() + " (" + p.getAuthor() + ")");
    }  
    guiList.setListData(names.toArray());
    
    currentPattern = list.get(0); //select first element in list
    guiList.setSelectedIndex(0); //select first element in guiList
  }
}
