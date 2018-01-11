package com.frontangle.ichart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.frontangle.ichart.chart.axis.XAxis;


/**
 * A stacked XY Chart is a number of charts that are stacked on top of each other that 
 * share the same X axis. 
 * 
 * @author Oliver Watkins
 */
public class StackedXYChart extends XYChart{

	public XAxis xAxis;

	private ArrayList<Integer> percentages; 
	private ArrayList<XYChart> charts;

	public StackedXYChart(String string, ArrayList<XYChart> charts,
			ArrayList<Integer> percentages) {
		setTitle(string);
		
		this.charts = charts;
		this.percentages = percentages;
		
		this.xAxis = charts.get(0).xAxis; //all xAxis are shared, so just use the first one.
	}


	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		// draws axis, frame etc
		this.prePaint(g2d);

		// draws actual data 
		drawGraphData(g2d);
	}

	
	@Override
	protected void drawGraphData(Graphics g) {
		// ? nothing needed here
	}
	
	public void prePaint(Graphics2D g) {
		this.calculateHeighAndWidthOfChart();

		/**
		 * Maybe we want a filled colored area instead of some lines???
		 */
		drawBackground(g);
		drawBottomLine(g);

		// title
		drawTitle(g);

		int i = 0;
		int tOffset = this.topOffset;
		for (XYChart chart : charts) {
			
			int chartHeight = (int)((this.heightChart*(int)this.percentages.get(i))/100);
			
			if (i == (charts.size()-1)) { //if the last chart then we draw X axis
				drawStackedChart(g, chart, tOffset, chartHeight, true );
			}else {
				drawStackedChart(g, chart, tOffset, chartHeight, false );
			}
			chart.drawGraphData(g);
			
			tOffset = tOffset + chartHeight;
			
			i++;
		}
		
	}


	private void drawStackedChart(Graphics2D g, XYChart chart, int topOffset, int height, boolean drawXAxis) {
		
		chart.widthChart = this.widthChart;
		
		chart.heightChart = height;
		
		chart.topOffset = topOffset;
		
		chart.leftOffset = this.leftOffset;
		
		g.setColor(borderLineColor);
		
		chart.drawLeftLine(g);
		
		// fills
		chart.yAxis.axisScaling.drawAllPre(g, chart, chart.data);
		chart.yAxis.drawBorderLine(g, chart);

		// y axis
		chart.yAxis.axisScaling.drawAll(g, chart, chart.data);
		chart.yAxis.drawLabel(g, chart);
		
		chart.drawBottomLine(g);

		if (drawXAxis) {
			chart.xAxis.axisScaling.drawAll(g, chart, chart.data);
			chart.xAxis.drawLabel(g, this);
			chart.xAxis.drawBorderLine(g, chart);
		}

		chart.drawGraphData(g);
	}
	
	
	/**
	 * TODO copy and pasted from XYChart
	 * 
	 * Inner line just inside of the axis line. Potentially optional??
	 * 
	 */
	protected void drawBottomLine(Graphics2D g) {

		if (chartBorderLine == null) {
			chartBorderLine = new BasicStroke(1, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, new float[] { 2, 0 }, // no
																			// dash
					0.0f);
		}

		g.setStroke(chartBorderLine);
		g.setColor(borderLineColor);
		g.drawLine(leftOffset, heightChart + topOffset,
				leftOffset + widthChart, heightChart + topOffset);
	}
	
	transient BasicStroke chartBorderLine = new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {
					2, 0 }, // no dash
					0.0f);

	public Color borderLineColor = Color.BLACK;
	

	/**
	 * TODO copy and pasted from XYChart
	 * 
	 * Inner line just inside of the axis line. Potentially optional?? Eg
	 * colored area instead?
	 * 
	 */
	protected void drawLeftLine(Graphics2D g) {

//		g.setStroke(chartBorderLine);
//		g.setColor(borderLineColor);
//		g.drawLine(leftOffset+50, topOffset, leftOffset, heightChart + topOffset);
	}
	
	public ArrayList<XYChart> getCharts() {
		return charts;
	}


	public void setCharts(ArrayList<XYChart> charts) {
		this.charts = charts;
	}
}
