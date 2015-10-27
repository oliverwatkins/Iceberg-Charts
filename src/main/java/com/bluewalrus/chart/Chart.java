package com.bluewalrus.chart;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.Legend;

/**
 *   
 * 
 * @author oliver
 */
public abstract class Chart extends JPanel {
	
	//file locations in local file system TODO this probably does not belong here. Have class like ChartExt with meta data like file location?
	public String fileLocation = "";

	public Font legendFont = new Font("Arial", Font.PLAIN, 12);

	public Color backgroundColor = Color.WHITE;
	
	public Color borderLineColor = Color.BLACK;

	// offsets (padding of actual chart to its border)
	public int leftOffset = 140;
	public int topOffset = 120;
	public int bottomOffset = 100;
	public int rightOffset = 15;

//	public int leftOffset = 2;
//	public int topOffset = 2;
//	public int bottomOffset = 2;
//	public int rightOffset = 2;
	
	
	public int width = 500; // total width of the component
	public int height = 430; // total height of the component

	public int heightChart; // generated
	public int widthChart; // generated

	public Font titleFont = new Font("Arial", Font.BOLD, 18);
	public Color titleColor = Color.BLACK;

	public String title = "My Fruits";
	
	
	public Chart() {
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				
//				System.out.println("Chart height " + Chart.this.getHeight());
				
				Chart.this.height = Chart.this.getHeight();
				Chart.this.width = Chart.this.getWidth();
				Chart.this.repaint();
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
//				System.out.println("componentResized " + Chart.this.getHeight());
				
				Chart.this.height = Chart.this.getHeight();
				Chart.this.width = Chart.this.getWidth();
				Chart.this.repaint();
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
//				System.out.println("componentResized " + Chart.this.getHeight());
				
				Chart.this.height = Chart.this.getHeight();
				Chart.this.width = Chart.this.getWidth();
				Chart.this.repaint();
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				
//				System.out.println("componentResized " + Chart.this.getHeight());
				
				Chart.this.height = Chart.this.getHeight();
				Chart.this.width = Chart.this.getWidth();
				Chart.this.repaint();
				// TODO Auto-generated method stub
				
			}
		});
	}

	protected void drawTitle(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		FontMetrics fmT = getFontMetrics(titleFont);
		int titleStringWidth = fmT.stringWidth(title);
		int titleStringHeight = fmT.getHeight();

		g2d.setFont(titleFont);
		int titleX = (leftOffset + rightOffset + widthChart) / 2
				- titleStringWidth / 2;
		int titleY = topOffset / 2 + titleStringHeight / 2;

		g2d.setColor(titleColor);
		g2d.drawString(title, titleX, titleY);

		// drawTrialVersion(g2d);
	}

	/**
	 * Must be called at every paint() call.
	 */
	protected void calculateHeighAndWidthOfChart() {

		this.heightChart = height - (topOffset + bottomOffset);
		this.widthChart = width - (leftOffset + rightOffset);
	}

	

	protected void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);

		
		
		
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.BLACK);
	}

	abstract protected void drawGraph(Graphics g);

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

	public void drawLegend(Graphics2D g, ArrayList<Category> categories,
			int offset) {

		legend = new Legend(legendFont, this, offset);

		legend.drawLegend(g, this, categories);

	}
	
	public void setLocation(String location) {

		this.firePropertyChange("location", fileLocation, location);
		this.fileLocation = location;
		
	}

	public Legend legend;

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
}
