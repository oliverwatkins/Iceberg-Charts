package com.bluewalrus.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bluewalrus.chart.axis.XAxis;

public class StackedXYChart extends XYChart{

	List<XYChart> charts;

	public XAxis xAxis;
	private ArrayList percentages; 
	
	public StackedXYChart(XYChart... lineChart) {
		
		charts = Arrays.asList(lineChart);
		
	}

	
	public StackedXYChart(String string, ArrayList charts2,
			ArrayList percentages) {
		
		charts = charts2;
		this.percentages = percentages;
		
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
		// TODO Auto-generated method stub
		
	}
	
	public void prePaint(Graphics2D g2d) {
		this.calculateHeighAndWidthOfChart();

		/**
		 * Maybe we want a filled colored area instead of some lines???
		 */
		drawBackground(g2d);
		drawBottomLine(g2d);

		// title
		drawTitle(g2d);


		boolean isLastChart = false;
		int i = 0;
		int tOffset = this.topOffset;
		for (XYChart chart : charts) {
			
			
			int chartHeight = (int)((this.heightChart*(int)this.percentages.get(i))/100);
			
			if (i == (charts.size()-1)) {
				drawStackedChart(g2d, chart, tOffset, chartHeight, true );
				
			}else {
				drawStackedChart(g2d, chart, tOffset, chartHeight, false );
			}
			 
			
			i++;
			
			tOffset = tOffset + chartHeight;
		}

//		drawStackedChart(g2d, charts.get(1), this.topOffset + (this.heightChart / 3),(this.heightChart*2 / 3), true );
		
		charts.get(0).drawGraphData(g2d);
		
		charts.get(1).drawGraphData(g2d);
		
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
		
//		
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

		g.setStroke(chartBorderLine);
		g.setColor(borderLineColor);
		g.drawLine(leftOffset+50, topOffset, leftOffset, heightChart + topOffset);
	}

	
	
	

}
