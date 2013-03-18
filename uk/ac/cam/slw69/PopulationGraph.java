package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PopulationGraph extends GraphType
{
	private ArrayList<Integer> dataPoints = new ArrayList<Integer>();
	private float vMinAxis = 0.0f;
	private float vMaxAxis = 0.0f;

  public void paintComponent(Graphics g)
	{
		//draw all datapoints solid using fillRect
		g.setColor(Color.GREEN);		
			
		for(int i = 0; i < dataPoints.size(); i++)
		{
			//get Raw data point
			int rawPoint = dataPoints.get(i).intValue();
			
			//scale point, should really pass in 100
			float scalingFactor = (100.0f)/(vMaxAxis);		
		
		  int point1 = (int)(scalingFactor*rawPoint);	
		  		 
		  g.fillRect(i*2, 100-point1, 2, point1);
		}
	}	
		
	public void update(float data, VerticalAxis vAxis, HorizontalAxis hAxis)
	{
		//update the current axis for scalling
		this.vMaxAxis = vAxis.max;
		this.vMinAxis = vAxis.min;
		
		//add the new datapoint
		dataPoints.add(new Integer((int)data));		
	
	  //we only want previous 100 gens so chop off front if > 100
		if(dataPoints.size() == 101)
		{
			dataPoints.remove(0);
			ArrayList<Integer> sorted = (ArrayList<Integer>)dataPoints.clone();
			Collections.sort(sorted);
			vAxis.min = sorted.get(0);
			vAxis.max = sorted.get(sorted.size()-1);
			this.vMaxAxis = vAxis.max;
		  this.vMinAxis = vAxis.min;
		}
			
		//update the graph
		repaint();
	}
	
	public void reset()
	{
		dataPoints.clear();
	}
}