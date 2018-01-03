package com.frontangle.ichart.chart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.frontangle.ichart.chart.legend.LegendPosition;
import com.frontangle.ichart.chart.legend.LegendVertical;

/**
 * Chart is an abstract chart for all charts.
 * 
 * It extends a JPanel and defines the basic geometry of where the chart is
 * positioned in the JPanel.
 * 
 * Additionally a Chart is composed of a Title and optionally a Legend
 * 
 * @author oliver
 */
public abstract class Chart extends JPanel {

	public LegendVertical legend;
	public LegendPosition legendPosition = LegendPosition.RIGHT;

	// file locations in local file system TODO this probably does not belong
	// here. Have class like ChartExt with meta data like file location?
	public String fileLocation = "";

	// TODO this does not belong here
	public Font legendFont = new Font("Arial", Font.PLAIN, 12);

	public Color backgroundColor = Color.WHITE;

	// offsets (padding of actual chart to its border)
	public int leftOffset = 140;
	public int topOffset = 120;
	public int bottomOffset = 100;
	public int rightOffset = 15;

	public int heightChart; // generated
	public int widthChart; // generated

	public Title title = new Title();

	private Image background = null;

	public Chart() {
	}

	/**
	 * Must be called at every paint() call.
	 */
	protected void calculateHeighAndWidthOfChart() {

		if ((topOffset + bottomOffset) > getHeight()) {
			throw new RuntimeException("Offset is greater than height ");

		}
		if ((leftOffset + rightOffset) > getWidth()) {
			throw new RuntimeException("Offset (" + leftOffset + " " + rightOffset
					+ ")is greater than width (" + getWidth() + ")");
		}

		this.heightChart = getHeight() - (topOffset + bottomOffset);
		this.widthChart = getWidth() - (leftOffset + rightOffset);
	}

	// Draw the background. Just a blank white rectangle.
	protected void drawBackground(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2d.setColor(backgroundColor);

		g2d.fillRect(leftOffset, topOffset, this.getWidth() - leftOffset - rightOffset,
				this.getHeight() - topOffset - bottomOffset);

		if (background != null) {
			float opacity = 0.5f;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

			g2d.drawImage(background, leftOffset, topOffset, this.getWidth() - leftOffset
					- rightOffset, this.getHeight() - topOffset - bottomOffset, null);

			opacity = 1f;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		}
	}

	/**
	 * Draw the graph ontop of the background within the bounds.
	 * 
	 * @param g
	 *            graphics context
	 */
	abstract protected void drawGraphData(Graphics g);

	// Get the bounds of the actual chart (not the background canvas).
	public Rectangle getChartBounds() {
		return new Rectangle(leftOffset, topOffset, widthChart, heightChart);
	}

	public void setLocation(String location) {

		this.firePropertyChange("location", fileLocation, location);
		this.fileLocation = location;
	}

	protected void drawTitle(Graphics g) {
		title.drawTitle(g, this);
	}

	public Font getTitleFont() {
		return title.titleFont;
	}

	public void setTitleFont(String string, int plain, int i) {
		title.titleFont = new Font(string, plain, i);
	}

	public void setTitleFont(Font font) {
		title.titleFont = font;
	}

	public void setTitle(String t) {
		title.setTitle(t);
	}

	public String getTitle() {
		return title.getTitle();
	}

	public void setBackgroundImage(Image background) {
		this.background = background;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
