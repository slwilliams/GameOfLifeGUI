package uk.ac.cam.slw69;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graph extends JPanel
{
	private final int GRAPH_WIDTH = 250;
	private final int GRAPH_HEIGHT = 100;
	private int currentGen = 0;
	
	private GraphType graph = null;
	private VerticalAxis vAxis = null;
	private HorizontalAxis hAxis = null;
	
	public Graph(String title, GraphType graph)
	{
	  super();
	  this.graph = graph;
	  
	  setLayout(new BorderLayout());
	  setPreferredSize(new Dimension(GRAPH_WIDTH,GRAPH_HEIGHT));
	  
	  add(new JLabel(title), BorderLayout.NORTH);
	  add(graph, BorderLayout.CENTER);
	  
	  vAxis = new VerticalAxis(0.0f, 0.0f, GRAPH_HEIGHT);
	  hAxis = new HorizontalAxis(0.0f, 0.0f, GRAPH_WIDTH);
	  
	  add(vAxis, BorderLayout.WEST);
	  add(hAxis, BorderLayout.SOUTH);
	}	
		
	public void update(float dataPoint)
	{
		vAxis.update(dataPoint);
		hAxis.update(currentGen);
		
	  graph.update(dataPoint, vAxis, hAxis);
		currentGen++;		
	}
	
	public void reset()
	{
		graph.reset();
		vAxis.min = 0.0f;
		vAxis.max = 0.0f;
		hAxis.min = 0.0f;
		hAxis.max = 0.0f;
	}
}