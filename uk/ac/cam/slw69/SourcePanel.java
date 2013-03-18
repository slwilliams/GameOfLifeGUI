package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.event.*;

public abstract class SourcePanel extends JPanel 
{
	protected JRadioButton current;
	
	protected JRadioButton none;
	protected JRadioButton file;
	protected JRadioButton library;
	protected JRadioButton fourStar;
	
	
	protected abstract boolean setSourceFile();
	protected abstract boolean setSourceNone();
	protected abstract boolean setSourceLibrary();
	protected abstract boolean setSourceFourStar();
	
	
  public SourcePanel() 
  {
    super();
    setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
   
    none.addActionListener(new ActionListener() 
    {
			public void actionPerformed(ActionEvent e) 
			{
				if(setSourceNone())
					current = none; 
				else
					current.setSelected(true); 
			}
		});
    
    file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
    
    file.addActionListener(new ActionListener() 
    {
			public void actionPerformed(ActionEvent e) 
			{
				if(setSourceFile())
					current = file; 
				else
					current.setSelected(true); 
			}
		});
    
    library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
    
    library.addActionListener(new ActionListener() 
    {
			public void actionPerformed(ActionEvent e) 
			{
				if(setSourceLibrary())
					current = library; 
				else
					current.setSelected(true); 
			}
		});
    
    fourStar = new JRadioButton(Strings.BUTTON_SOURCE_FOURSTAR, true);
    
    fourStar.addActionListener(new ActionListener() 
    {
			public void actionPerformed(ActionEvent e) 
			{
				if(setSourceFourStar())
					current = fourStar; 
				else
					current.setSelected(true); 
			}
		});
    

    add(none);
    add(file);
    add(library);
    add(fourStar);

    ButtonGroup group = new ButtonGroup();
    group.add(none);
    group.add(file);
    group.add(library);
    group.add(fourStar);
    
    current = none;
  }
}
