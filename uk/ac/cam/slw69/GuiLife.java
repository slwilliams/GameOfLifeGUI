package uk.ac.cam.slw69;

import uk.ac.cam.acr31.life.World;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.List;

public class GuiLife extends JFrame
{
  private PatternPanel patternPanel;
  private ControlPanel controlPanel;
  private GamePanel gamePanel;
  private StatisticsPanel statisticsPanel;
  
  public World world;
  private int timeDelay = 500;
  private int timeStep = 0;
  
  private Timer playTimer = new Timer(timeDelay, new ActionListener()  
  {
  	public void actionPerformed(ActionEvent e)
  	{
  		doTimeStep();
 	   	statisticsPanel.update(world, timeStep);
 	   	repaint();
  	}
  });
  
  private void doTimeStep()
  {
  	if(world != null)
  	{
  		world = world.nextGeneration(timeStep);
  		gamePanel.display(world);
  	}
  }
  
  public void startTimer()
  {
  	playTimer.start();
  }

  public GuiLife() 
  {
    super("GuiLife");
    setSize(810, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    JComponent optionsPanel = createOptionsPanel();
    add(optionsPanel, BorderLayout.WEST);
    JComponent gamePanel = createGamePanel();
    add(gamePanel, BorderLayout.CENTER);
    JComponent statisticsPanel = createStatisticsPanel();
    add(statisticsPanel, BorderLayout.EAST);   
  }

  private JComponent createOptionsPanel() 
  {
    Box result = Box.createVerticalBox();
    result.add(createSourcePanel());
    result.add(createPatternPanel());
    result.add(createControlPanel());
    return result;
  }

  private void addBorder(JComponent component, String title) 
  {
    Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Border tb = BorderFactory.createTitledBorder(etch,title);
    component.setBorder(tb);
  }

  private JComponent createGamePanel() 
  {
    JPanel result = new JPanel();
    gamePanel = new GamePanel();
    addBorder(result, Strings.PANEL_GAMEVIEW);
    result.add(gamePanel);
    return new JScrollPane(result);
  }
  
  private JComponent createStatisticsPanel() 
  {
    JPanel result = new JPanel();
    statisticsPanel = new StatisticsPanel();
    addBorder(result, Strings.PANEL_STATISTICS);
    result.add(statisticsPanel);
    return new JScrollPane(result);
  }

  private JComponent createSourcePanel() 
  {
    JPanel result = new SourcePanel()
    {
			protected boolean setSourceFile() 
			{
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(this);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					File f = chooser.getSelectedFile();
					try 
					{
						List<Pattern> list = PatternLoader.load(new FileReader(f));
						patternPanel.setPatterns(list);
						resetWorld();
						return true;
					} 
					catch (IOException ioe) {}
				}
				return false;
			}
			
			protected boolean setSourceNone() 
			{
				world = null;
				patternPanel.setPatterns(null);
				resetWorld();
				return true;
			}
			
			protected boolean setSourceFourStar()
			{
				String u = "http://www.cl.cam.ac.uk/teaching/1213/ProgJava/competition.txt";
				return setSourceWeb(u);
			}
			
			protected boolean setSourceLibrary() 
			{
				String u = "http://www.cl.cam.ac.uk/teaching/current/ProgJava/nextlife.txt";
				return setSourceWeb(u);
			}
				
			private boolean setSourceWeb(String url) 
			{
				try 
				{
					List<Pattern> list = PatternLoader.loadFromURL(url);
					patternPanel.setPatterns(list);
					resetWorld();
					return true;
				} catch (IOException ioe) {}
				return false;
			}
		};
    addBorder(result, Strings.PANEL_SOURCE);
    return result;
  }

  private JComponent createPatternPanel() 
  { 
    patternPanel = new PatternPanel()    
    {
    	protected void onPatternChange()
    	{
    		resetWorld();
    	}
    };
    addBorder(patternPanel, Strings.PANEL_PATTERN);
    return patternPanel; 
  }

  private JComponent createControlPanel() 
  { 
    controlPanel = new ControlPanel()
    {	
 		  protected void onSpeedChange(int value) 
 		  {
        playTimer.setDelay(1+(100-value)*10);
      }
      
      protected void onStepChange(int value)
      {
      	timeStep = value;
      }
      
      protected void onZoomChange(int value)
      {
      	gamePanel.setZoom(value);
      }
    };
    addBorder(controlPanel,Strings.PANEL_CONTROL);
    return controlPanel;
  }
  
  private void resetWorld() 
  {
		Pattern current = patternPanel.getCurrentPattern();
		world = null;
		if(current != null) 
		{
			try 
			{
				world = controlPanel.initialiseWorld(current);
			} catch (PatternFormatException e) 
			{
				JOptionPane.showMessageDialog(this,
				"Error initialising world",
				"An error occurred when initialising the world. "+e.getMessage(),
				JOptionPane.ERROR_MESSAGE);
			}
		}
		statisticsPanel.reset();
		gamePanel.display(world);
		repaint();
	}

  public static void main(String[] args) 
  {
    GuiLife gui = new GuiLife();
    
    if(args.length == 0)
    {
      gui.startTimer();
      gui.resetWorld();
    }
    else
    {
	    CommandLineOptions c = null;
	    try
	    {
	      c = new CommandLineOptions(args);
	    } catch(CommandLineException e) {System.out.println(e.getMessage()); return;}
	    
	    if(c.getIndex() == null)
	    {
	    	System.out.println("no patern specified");
	    	return;
	    }
	    
	    try 
	    {
	      String url = c.getSource();
	      List<Pattern> list = PatternLoader.loadFromURL(url);
	      gui.patternPanel.setPatterns(list);      
	      gui.world = gui.controlPanel.initialiseWorld(list.get(c.getIndex()));
	      gui.gamePanel.display(gui.world);
	      gui.startTimer();
	    } 
	    catch(IOException ioe) {System.out.println("Error loading pattern");}
	    catch(PatternFormatException e) {System.out.println(e.getMessage());}
    }    
    gui.setVisible(true);
  }
}
