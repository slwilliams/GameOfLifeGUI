package uk.ac.cam.slw69;

import java.awt.Graphics;
import java.util.*;
import javax.swing.*;

public abstract class GraphType extends JPanel
{
	public abstract void update(float data, VerticalAxis vAxis, HorizontalAxis hAxis);
	public abstract void paintComponent(Graphics g);
	public abstract void reset();
}