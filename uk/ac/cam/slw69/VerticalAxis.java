package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class VerticalAxis extends JPanel
{
	public float max = 0.0f;
	public float min = 0.0f;
	private int height = 0;
	
	public VerticalAxis(float max, float min, int height)
	{
		super();
		this.max = max;
		this.min = min;
		this.height = height;
		setLayout(new BorderLayout());
	  setPreferredSize(new Dimension(50,height));	  
	}

	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		DecimalFormat df = new DecimalFormat("#.###");
		FontMetrics fm = getFontMetrics(getFont());
		g.setColor(Color.BLACK);
		g.drawString(df.format(max), 47 - fm.stringWidth(df.format(max)), 10);
		g.drawLine(49, 0, 49, height - 10);
		g.drawString(df.format(min), 47 - fm.stringWidth(df.format(min)), height - 38);
	}	
		
	public void update(float dataPoint)
	{
		//data passed in could be larger than axis
		//rescale accordingly
		if(dataPoint > max)
			max = dataPoint;
		
		//also for minimum
		if(dataPoint < min)
			min = dataPoint;
	}
}