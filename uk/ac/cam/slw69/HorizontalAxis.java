package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class HorizontalAxis extends JPanel
{
	public float max = 0.0f;
	public float min = 0.0f;
	private int width = 0;
			
	public HorizontalAxis(float max, float min, int width)
	{
		super();
		this.max = max;
		this.min = min;
		this.width = width;
		setLayout(new BorderLayout());
	  setPreferredSize(new Dimension(width,20));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		DecimalFormat df = new DecimalFormat("#.###");
		FontMetrics fm = getFontMetrics(getFont());
		
		g.setColor(Color.BLACK);
		g.drawString(df.format(min), 50, 15);
		g.drawString(df.format(max), width - fm.stringWidth(df.format(max)), 15);
		
	  g.drawLine(50,0,width,0);
	}	
		
	public void update(int currentGen)
	{
		max = (float)currentGen;
		
		if(currentGen - 100 > 0)
			min = (float)(currentGen - 100);
	}
}