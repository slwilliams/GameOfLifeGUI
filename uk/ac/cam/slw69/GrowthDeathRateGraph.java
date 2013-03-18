package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GrowthDeathRateGraph extends GraphType
{
	private ArrayList<Float> dataPoints = new ArrayList<Float>();
	private float vMinAxis = 0.0f;
	private float vMaxAxis = 0.0f;	

  public void paintComponent(Graphics g)
	{
		//draw a line graph using drawLine
		g.setColor(Color.RED);
		
		for(int i = 0; i < dataPoints.size()-1; i++)
		{
			//grab two consecutive data points
			float f1 = dataPoints.get(i).floatValue();
			float f2 = dataPoints.get(i+1).floatValue();
		
		  //this means zero is not at the axis
		  if(vMinAxis < 0)
		  {
		  	float total = Math.abs(vMinAxis) + Math.abs(vMaxAxis);
		  	float pixelsPerRaw = 100.0f/total;
		  	
		  	//zero is actually here:
		  	int zeroPixel = (int)(Math.abs(vMaxAxis) * pixelsPerRaw);
		  	
		  	int p1 = (int)(f1 * pixelsPerRaw);
		  	int p2 = (int)(f2 * pixelsPerRaw);
		  	
		  	g.drawLine(i*2, zeroPixel - p1, (i+1)*2, zeroPixel - p2);
		  }
		  else
		  {
		  	float total = Math.abs(vMaxAxis) - Math.abs(vMinAxis);
		  	float pixelsPerRaw = 100.0f/total;
		  	
		  	int zeroPixel = 100;
		  	
		  	int p1 = (int)(f1 * pixelsPerRaw);
		  	int p2 = (int)(f2 * pixelsPerRaw);
		  	
		  	g.drawLine(i*2, zeroPixel - p1, (i+1)*2, zeroPixel - p2);
		  }		  
		}
	}	
		
  public void update(float data, VerticalAxis vAxis, HorizontalAxis hAxis)
	{
		//update the current axis for scalling
		this.vMaxAxis = vAxis.max;
		this.vMinAxis = vAxis.min;
		
		//add the new datapoint
		dataPoints.add(new Float(data));		
	
	  //we only want previous 100 gens so chop off front if > 100
	  //rescale axis as appropriate
		if(dataPoints.size() == 101)
		{
			dataPoints.remove(0);
			ArrayList<Float> sorted = (ArrayList<Float>)dataPoints.clone();
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