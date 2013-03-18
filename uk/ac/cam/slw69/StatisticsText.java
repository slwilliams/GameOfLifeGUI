package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class StatisticsText extends JPanel
{	
  private int currentGeneration = 0;
  private int population = 0;
  private int maxPopulation = 0;
  private int minPopulation = 0;
  private double maxGrowthRate = 0.0d;
  private double maxDeathRate = 0.0d;

	public StatisticsText()
	{
		super();
	  setLayout(new BorderLayout());
	  setPreferredSize(new Dimension(250,100));	  
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		DecimalFormat df = new DecimalFormat("#.###");
   
   	g.setColor(Color.BLACK);
	
		g.drawString(Strings.STATISTICS_TEXT_GENERATION + " " + currentGeneration, 5, 15);
		g.drawString(Strings.STATISTICS_TEXT_POPULATION + " " + population, 5, 30);
		g.drawString(Strings.STATISTICS_TEXT_MAXPOPULATION + " " + maxPopulation, 5, 45);
		g.drawString(Strings.STATISTICS_TEXT_MINPOPULATION + " " + minPopulation, 5, 60);	 
		g.drawString(Strings.STATISTICS_TEXT_MAXGROWTHRATE + " " + df.format(maxGrowthRate), 5, 75);
		g.drawString(Strings.STATISTICS_TEXT_MAXDEATHRATE + " " + df.format(maxDeathRate), 5, 90);
	}
	
	public void update(Statistics stat, int currentPopulation, int currentGen)
	{
		currentGeneration = currentGen;
		population = currentPopulation;
	  maxPopulation = stat.getMaximumPopulation();
    minPopulation = stat.getMinimumPopulation();
    maxGrowthRate = stat.getMaximumGrowthRate();
    maxDeathRate = stat.getMaximumDeathRate();
	}
	
	public void reset()
	{
		currentGeneration = 0;
		population = 0;
	  maxPopulation = 0;
    minPopulation = 0;
    maxGrowthRate = 0.0d;
    maxDeathRate = 0.0d;
	}
}