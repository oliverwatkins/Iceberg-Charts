package com.bluewalrus.chart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * <code>Chart<code> is an abstract chart for all charts.   
 * 
 * It extends a JPanel and defines the basic geometry of where the chart is positioned in the JPanel.
 * 
 * Additionally a Chart is composed of a <code>Title</code> and optionally a <code>Legend</code>
 * 
 * 
 * @author oliver
 */
public abstract class Chart extends JPanel {
	
	//file locations in local file system TODO this probably does not belong here. Have class like ChartExt with meta data like file location?
	public String fileLocation = "";

	//TODO this does not belong here
	public Font legendFont = new Font("Arial", Font.PLAIN, 12);

	public Color backgroundColor = Color.WHITE;

	// offsets (padding of actual chart to its border)
	public int leftOffset = 140;
	public int topOffset = 120;
	public int bottomOffset = 100;
	public int rightOffset = 15;
	
	public int heightChart; // generated
	public int widthChart; // generated

	public Legend legend;

	public Title title = new Title();
	
	public Chart() {
	}
	
	public void setTitle(String t) {
		title.title = t;
	}
	public String getTitle() {
		return title.title;
	}

	public void setTitleFont(String string, int plain, int i) {
		title.titleFont = new Font(string, plain, i);
	}

	public void setTitleFont(Font font) {
		title.titleFont = font;
	}
	
	/**
	 * Must be called at every paint() call.
	 */
	protected void calculateHeighAndWidthOfChart() {

		this.heightChart = getHeight() - (topOffset + bottomOffset);
		this.widthChart = getWidth() - (leftOffset + rightOffset);
	}

	
	/**
	 * Draw the background. Just a blank white rectangle.
	 * 
	 * @param g
	 */
	protected void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.BLACK);
	}

	/**
	 * Draw the graph ontop of the background within the bounds. 
	 * 
	 * @param g
	 */
	abstract protected void drawGraphData(Graphics g);

	
	/**
	 * Get the bounds of the actual chart (not the background canvas).
	 * @return
	 */
	public Rectangle getChartBounds() {
		return new Rectangle(leftOffset, topOffset, widthChart, heightChart);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	protected void drawLegend(Graphics2D g, ArrayList<Category> data) {
		
		legend = new Legend(legendFont, this);

		legend.drawLegend(g, this, data);
	}

	
	protected void drawTitle(Graphics g) {
		title.drawTitle(g, this);
	}

	public void drawLegend(Graphics2D g, ArrayList<Category> categories,
			int offset) {

		legend = new Legend(legendFont, this, offset);

		legend.drawLegend(g, this, categories);

	}
	
	public void setLocation(String location) {

		this.firePropertyChange("location", fileLocation, location);
		this.fileLocation = location;
		
	}


	public Shape getLegendBounds() {
		if (legend != null) {
			return legend.getChartBounds();
		}
		return null;
	}
	
	
	
	
	private void drawTrialVersion(Graphics2D g2d) {
		String trialVersion = "Trial Version : ";
		String trialVersion2 = "If you would like a free copy without watermark";
		String trialVersion3 = "Contact me on ";
		String trialVersion4 = "oliver.f.watkins@gmail.com";
		String trialVersion5 = "I want to get a feel for how popular this component is";

		Font yFont = new Font("Arial", Font.BOLD, 15);
		FontMetrics fm = getFontMetrics(yFont);
		int width = fm.stringWidth(trialVersion);
		int height = fm.getHeight();

		Composite c = g2d.getComposite();

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				2 * 0.1f));

		g2d.setFont(yFont);
		g2d.setColor(Color.GRAY);

		int x = 0;
		int y = 0;

		g2d.drawString(trialVersion, x, y);
		g2d.drawString(trialVersion2, x, y + 15);
		g2d.drawString(trialVersion3, x, y + 30);
		g2d.drawString(trialVersion4, x, y + 45);
		g2d.drawString(trialVersion5, x, y + 60);

		g2d.setComposite(c);
	}

	public Font getTitleFont() {
		return title.titleFont;
	}
}
