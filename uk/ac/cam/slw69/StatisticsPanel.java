package uk.ac.cam.slw69;

import javax.swing.*;
import uk.ac.cam.acr31.life.World;
import java.awt.*;
import java.util.*;

public class StatisticsPanel extends JPanel
{
	private StatisticsText statisticsText;
	private Graph population;
	private Graph populationChange;
	private Graph growthDeathRate;
	private Statistics stat = new Statistics();
	
	private int currentGeneration = 0;
	private int prevPopulation = 1;
	//Horrible way I know.
	private boolean firstTime = true;
	
	public StatisticsPanel()
	{
		super();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		statisticsText = new StatisticsText(); 
		add(statisticsText);
		add(Box.createVerticalStrut(5));
		
	  population = new Graph("Population:", new PopulationGraph());
	  populationChange = new Graph("Population Change:", new PopulationChangeGraph());
	  growthDeathRate = new Graph("Growth rate:", new GrowthDeathRateGraph());	  
	  	
	  add(population);
	  add(Box.createVerticalStrut(5));
	  
	  add(populationChange);
    add(Box.createVerticalStrut(5));
    
    add(growthDeathRate);    
 }
 
 	public void reset()
	{	
		if(statisticsText != null) statisticsText.reset();
  	if(population != null) population.reset();
 	 	if(populationChange != null) populationChange.reset();
  	if(growthDeathRate != null) growthDeathRate.reset();
    firstTime = true;
    currentGeneration = 0;
    stat = new Statistics();
 	}
	
	public void update(World w, int timeStep)
	{
		if(w == null) return;
		if(firstTime)
		{
			prevPopulation = w.getPopulation();
			stat.setMinFirst(prevPopulation);
			firstTime = false;
		}
		
		if(timeStep == 0) timeStep = 1;
		
		currentGeneration += timeStep;
				
		//update statisticsText and graphs
		int currentPopulation = w.getPopulation();
		
		stat.setMaximumPopulation(currentPopulation);
		stat.setMinimumPopulation(currentPopulation);
		
		//update graphs by passing in relevent data 
		population.update(currentPopulation);
		
		int changeInPopulation = currentPopulation - prevPopulation;
		populationChange.update(changeInPopulation);
			
		double growthRate = (currentPopulation - prevPopulation)/(double)prevPopulation;
    double deathRate = (prevPopulation - currentPopulation)/(double)prevPopulation;
    stat.setMaximumDeathRate(deathRate);
    stat.setMaximumGrowthRate(growthRate);
    growthDeathRate.update((float)(growthRate));		
		
		statisticsText.update(stat, currentPopulation, currentGeneration);
			
		prevPopulation = currentPopulation;
	}	
}