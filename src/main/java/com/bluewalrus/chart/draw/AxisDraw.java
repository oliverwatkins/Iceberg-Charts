package com.bluewalrus.chart.draw;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.AbstractInterval;

public abstract class AxisDraw {

	public double maxValue = 100; // arbitrary
	public double minValue = 0; // arbitrary

	public AbstractInterval interval1; 
	public AbstractInterval interval2; 
	public AbstractInterval interval3; 

	/**
	 * Pre-rendering. Things that can overpaint things of another axis such as grid fills should go in here.
	 * 
	 * For example the grid fill of X would overpaint the grid lines of Y if they were in the drawAll. All drawAllPre are
	 * performed together followed by the drawAll.
	 * 
	 * @param g2d
	 * @param xyChart
	 * @param data
	 */
	public abstract void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data);
	
	public abstract void drawAll(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data);

	protected abstract void drawGridLineOnZero(Graphics2D g);

	protected abstract double getMultiplicationFactor(XYChart chart);


	public void drawGridFills(Graphics2D g, XYChart chart) {

		if (interval1 != null && interval1.isValid()
				&& interval3.graphLine != null && interval1.graphLine.gridFill != null) {
			this.drawGridFill(interval1, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval3.graphLine != null && interval2.graphLine.gridFill != null) {
			this.drawGridFill(interval2, g, chart);
		}
		if (interval3 != null && interval3.isValid()
				&& interval3.graphLine != null && interval3.graphLine.gridFill != null) {
			this.drawGridFill(interval3, g, chart);
		}
	}

	
	public void drawGridLines(Graphics2D g, XYChart chart) {
		
		if (interval1 != null && interval1.isValid()
				&& interval1.graphLine != null) {
			this.drawGridLine(interval1, g, chart);
		}
		if (interval2 != null && interval2.isValid()
				&& interval2.graphLine != null) {
			this.drawGridLine(interval2, g, chart);
		}
		if (interval3 != null && interval3.isValid()
				&& interval3.graphLine != null) {
			this.drawGridLine(interval3, g, chart);
		}
	}

	protected abstract void drawGridLine(AbstractInterval interval,
			Graphics2D g, XYChart chart);
	
	protected abstract void drawGridFill(AbstractInterval interval,
			Graphics2D g, XYChart chart);

}
