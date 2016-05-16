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
	
	public StackedXYChart(XYChart... lineChart) {
		
		
//		super("", "", "", new ArrayList<E>());
		
		charts = Arrays.asList(lineChart);
		
		
		
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
//		drawLeftLine(g2d);

		// title
		drawTitle(g2d);

		
//		charts.get(0).prePaint(g2d, null);

		
		
		
//		// draws actual data 
//		drawGraphData(g2d);
//		
//		
////		charts.get(0)
//		
//		
//		charts.get(1).bottomOffset = 1;
//		charts.get(1).bottomOffset = 100;

		charts.get(0).borderLineColor = Color.BLUE;

		drawStackedChart(g2d, charts.get(0), this.topOffset,this.heightChart / 2, false );

		charts.get(1).borderLineColor = Color.RED;

		drawStackedChart(g2d, charts.get(1), this.topOffset + this.heightChart / 2,this.heightChart / 2, true );
		
		charts.get(0).drawGraphData(g2d);
		
		charts.get(1).drawGraphData(g2d);
		
//		// draws axis, frame etc
//		charts.get(1).prePaint(g2d, charts.get(1).data);
//
//		// draws actual data 
//		drawGraphData(g2d);
//		
////		for (XYChart xyChart : charts) {
////			
////		}
//
//		
//		// x axis
////		xAxis.axisScaling.drawAll(g2d, this, charts.get(0).data); //data only used here for enumeration
////		xAxis.drawLabel(g2d, this);
////		xAxis.drawBorderLine(g2d, this);
	}


	private void drawStackedChart(Graphics2D g, XYChart chart, int topOffset, int height, boolean drawXAxis) {
		
		chart.widthChart = this.widthChart;
		
		
		chart.heightChart = height;
		
		chart.topOffset = topOffset;
		
		chart.leftOffset = this.leftOffset;
		
		
		g.setColor(borderLineColor);
		
		chart.drawLeftLine(g);
//		chart.drawBottomLine(g);
		
		// fills
		chart.yAxis.axisScaling.drawAllPre(g, chart, chart.data);
		chart.yAxis.drawBorderLine(g, chart);

		// y axis
		chart.yAxis.axisScaling.drawAll(g, chart, chart.data);
		chart.yAxis.drawLabel(g, this);
//		yAxis.drawBorderLine(g2d, this);

//		chart.xAxis
		
		chart.xAxis.drawBorderLine(g, this);
//		chart.drawBottomLine(g);
//		chart.xAxis.axisScaling.

//		chart.xAxis.axisScaling.drawAllPre(g, chart, chart.data);

		if (drawXAxis) {
			chart.xAxis.axisScaling.drawAll(g, chart, chart.data);
			chart.xAxis.drawLabel(g, this);
			
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
