package uk.ac.cam.slw69;

public class Statistics
{
  private double maximumGrowthRate;
  private double maximumDeathRate;
  private int maximumPopulation;
  private int minimumPopulation;

  public double getMaximumGrowthRate()
  {
    return maximumGrowthRate;
  }
  
  public int getMinimumPopulation()
  {
  	return minimumPopulation;
  }  

  public double getMaximumDeathRate()
  {
    return maximumDeathRate;
  } 

  public int getMaximumPopulation()
  {
    return maximumPopulation;
  }

  public void setMaximumGrowthRate(double rate)
  {
    if(rate > maximumGrowthRate)
      maximumGrowthRate = rate;
  }

  public void setMaximumDeathRate(double rate)
  {
    if(rate > maximumDeathRate)
      maximumDeathRate = rate;
  }

  public void setMaximumPopulation(int pop)
  {
    if(pop > maximumPopulation) 
      maximumPopulation = pop;
  }
  
  public void setMinimumPopulation(int pop)
  {
  	if(pop < minimumPopulation)
  		minimumPopulation = pop;
  }
  
  public void setMinFirst(int pop)
  {
  	minimumPopulation = pop;
  }
}
