package com.frontangle.ichart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.frontangle.ichart.chart.axis.IntervalStyling;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.datapoint.DataPointMultiBar;
import com.frontangle.ichart.chart.datapoint.DataPointWithMagnitude;
import com.frontangle.ichart.chart.datapoint.ValueType;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.plotter.ChartPlotter;
import com.frontangle.ichart.chart.draw.point.UIPointBar;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.chart.draw.point.UIPointXY;
import com.frontangle.ichart.chart.legend.LegendUtil;
import com.frontangle.ichart.chart.legend.Legendable;
import com.frontangle.ichart.scaling.EnumerationAxisScaling;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

/**
 * XYChart is a chart where data is represented by x,y data. Typically the y
 * axis is vertical and on the left, while the x axis runs along the bottom.
 * 
 * @author Oliver Watkins
 */
public class XYChart extends Chart implements Legendable, MouseMotionListener {


	public boolean isYAxis2 = false;

	transient BasicStroke chartBorderLine = new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {
					2, 0 }, // no dash
					0.0f);

	public Color borderLineColor = Color.BLACK;

	public YAxis yAxis;
    public YAxis yAxis2; //special case
	public XAxis xAxis;

	public ArrayList<XYDataSeries> data = new ArrayList<XYDataSeries>();
	public ArrayList<XYDataSeries> dataY2 = new ArrayList<XYDataSeries>();
	
	
	/**
	 * For stacked chart
	 */
	public XYChart() {}

	/*
	 * Create an XY chart passing in also the data set as an arraylist.
	 */
	public XYChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis, XAxis xAxis) {
		this(xAxis, yAxis);
		this.data.addAll(listOfSeries);
	}
	
	public XYChart(XYDataSeries series, String title, String xLabel, String yLabel) {
		this.setTitle(title);
		
		this.data.add(series);
		
		ScalingHelper.initialiseScaling(this, this.data);
		
		this.xAxis.labelText = xLabel;
		this.yAxis.labelText = yLabel;
	}

	/*
	 * Create an XY chart by passing in the two axis. This is the default
	 * constructor for an empty chart.
	 */
	public XYChart(XAxis xAxis, YAxis yAxis) {
		this.yAxis = yAxis;
		this.xAxis = xAxis;

		this.addMouseMotionListener(this);
	}

	//Simple Single Data Series Constructor
	public XYChart(ArrayList<DataPoint> values, String title, String xLabel,
			String yLabel) {
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		XYDataSeries<DataPoint> xy = new XYDataSeries<DataPoint>(values,
				new UIPointSquare(Color.BLACK), new Line(Color.BLACK), "");
		xySeriesList.add(xy);

		ScalingHelper.initialiseScaling(this, xySeriesList);
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
		
		xAxis.labelText = xLabel;
		yAxis.labelText = yLabel;
				
	}
	

	 //Constructor for XYY chart.
	public XYChart(String title, 
						String xLabel, 
						String yLabel, 
						String y2Label, 
						ArrayList<XYDataSeries> xySeries,
						ArrayList<XYDataSeries> xySeriesY2) {
		
		isYAxis2 = true;
		
		this.data.addAll(xySeries);
		this.dataY2.addAll(xySeriesY2);
		
		ChartUtils.setUpSeriesStyle(xySeries, this);

		ScalingHelper.initialiseScaling(this, xySeries);
		
		if (Utils.isSeriesListEnumerable(xySeriesY2)) {
			ScalingHelper.initialiseScalingX_enumeration(xySeriesY2);
		}
		
		this.xAxis.labelText = xLabel;
		this.yAxis.labelText = yLabel;
		
		this.addMouseMotionListener(this);

		this.setTitle(title);
	}

	 //Simple Bar Chart constructor
	public XYChart(ArrayList<DataPointBar> bars, String title, String xLabel,
			String yLabel, int pixelBarWidth) {
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		XYDataSeries<DataPointBar> xy = new XYDataSeries<DataPointBar>(bars,
				new UIPointBar(Color.RED, Color.YELLOW, pixelBarWidth), null, "");
		
		xySeriesList.add(xy);

		xAxis = ScalingHelper.initialiseScalingX_enumeration(xySeriesList);
		yAxis = ScalingHelper.initialiseScalingY_numerical(xySeriesList);
		
		this.setTitle(title);
		
		xAxis.labelText = xLabel;
		yAxis.labelText = yLabel;
		
		this.data.addAll(xySeriesList);
	}
	
	

	public XYChart(String title,
			String xTitle, String yTitle, ArrayList<XYDataSeries> xySeriesList) {
		
		this.data.addAll(xySeriesList);
		ScalingHelper.initialiseScaling(this, xySeriesList);
		
		yAxis.labelText = yTitle;
		xAxis.labelText = xTitle;
		
		this.addMouseMotionListener(this);

		this.setTitle(title);
	}
	
	 //Set up a chart with specific stylings.
	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			IntervalStyling stylingX, IntervalStyling stylingX2,
			IntervalStyling stylingX3, IntervalStyling stylingY,
			IntervalStyling stylingY2, IntervalStyling stylingY3, String xTitle, String yTitle) {
		
		ScalingHelper.initialiseScaling(this, xySeriesList);
		
		//replace the stylings defined in the intialise
		xAxis.axisScaling.interval1.styling = stylingX;
		yAxis.axisScaling.interval1.styling = stylingY;
		xAxis.axisScaling.interval2.styling = stylingX2;
		yAxis.axisScaling.interval2.styling = stylingY2;
		xAxis.axisScaling.interval3.styling = stylingX3;
		yAxis.axisScaling.interval3.styling = stylingY3;
		
		yAxis.labelText = yTitle;
		xAxis.labelText = xTitle;
				
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}
	
	public XYChart(ArrayList<DataPoint> values, String title, YAxis yAxis2,
			XAxis xAxis2) {

		this.setTitle(title);
		
		this.xAxis = xAxis2;
		this.yAxis = yAxis2;
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		XYDataSeries s = new XYDataSeries<DataPoint>(values, "");
		
		this.data.add(s);
	}
	

	
	 //Simple multiple constructor
	public XYChart(String title, String x, String y, XYDataSeries... series) {
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		for (XYDataSeries xyDataSeries : series) {
			xySeriesList.add(xyDataSeries);
		}
		
		ChartUtils.setUpSeriesStyle(xySeriesList, this);

		ScalingHelper.initialiseScaling(this, xySeriesList);
		
		this.xAxis.labelText = x;
		this.yAxis.labelText = y;
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}

	public XYChart(String title, String xAxisTitle, String yAxisTitle,
			IntervalStyling intervalStylingX1, 
			IntervalStyling intervalStylingX2,
			IntervalStyling intervalStylingX3,
			IntervalStyling intervalStylingY1, 
			IntervalStyling intervalStylingY2,
			IntervalStyling intervalStylingY3,
			XYDataSeries... series) {
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		for (XYDataSeries xyDataSeries : series) {
			xySeriesList.add(xyDataSeries);
		}
		
		ScalingHelper.initialiseScaling(this, xySeriesList);
		ChartUtils.setUpSeriesStyle(xySeriesList, this);
		
		this.xAxis.labelText = xAxisTitle;
		this.yAxis.labelText = yAxisTitle;
		
		this.xAxis.axisScaling.interval1.styling = intervalStylingX1;
		this.xAxis.axisScaling.interval2.styling = intervalStylingX2;
		this.xAxis.axisScaling.interval3.styling = intervalStylingX3;

		this.yAxis.axisScaling.interval1.styling = intervalStylingY1;
		this.yAxis.axisScaling.interval2.styling = intervalStylingY2;
		this.yAxis.axisScaling.interval3.styling = intervalStylingY3;

		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
		
	}
	
	 //Bubble only constructor
    public XYChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
            XAxis xAxis, double multipleMagnitudeBy) {

        this(listOfSeries, yAxis, xAxis);

        for (XYDataSeries xyDataSeries : listOfSeries) {

            ArrayList<DataPointWithMagnitude> al = xyDataSeries.dataPoints;

            for (DataPointWithMagnitude dp : al) {
                dp.magnitude = dp.magnitude * multipleMagnitudeBy;
            }
        }
    }

	
	 //Paint the background, Title, Grid and Axis. All the elements of the chart
	 //except for the actual data.
	protected void prePaint(Graphics2D g2d, ArrayList<XYDataSeries> data) {

		this.calculateHeighAndWidthOfChart();

		/**
		 * Maybe we want a filled colored area instead of some lines???
		 */
		drawBackground(g2d);
		
		drawBottomLine(g2d);
		drawLeftLine(g2d);

		// title
		drawTitle(g2d);

		drawLegend(g2d);

		// fills
		yAxis.axisScaling.drawAllPre(g2d, this, data);
		xAxis.axisScaling.drawAllPre(g2d, this, data);

		// y axis
		yAxis.axisScaling.drawAll(g2d, this, data);
		yAxis.drawLabel(g2d, this);
		yAxis.drawBorderLine(g2d, this);

		// x axis
		xAxis.axisScaling.drawAll(g2d, this, data);
		xAxis.drawLabel(g2d, this);
		xAxis.drawBorderLine(g2d, this);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		// draws axis, frame etc
		this.prePaint(g2d, data);

		// draws actual data 
		drawGraphData(g2d);
	}

	@Override
	protected void drawGraphData(Graphics g) {
			
        if (xAxis.axisScaling.getMaxValue() == xAxis.axisScaling.getMinValue()) {
        	throw new RuntimeException("Bummer! range has not been set for enum axis " + xAxis.axisScaling.getMinValue());
        }

		new ChartPlotter().plotData((Graphics2D) g, this, yAxis, xAxis, data);
		
		if (isYAxis2) {
			new ChartPlotter().plotData((Graphics2D) g, this,
					yAxis2, xAxis, dataY2);
			
	        Graphics2D g2d = (Graphics2D) g;
	        
	        drawRightLine(g2d);
	        
	        yAxis2.rightSide = true;
	        
	        yAxis2.axisScaling.drawAll(g2d, this, null); //drawTicksAndLabels(g, this);
	        
	        yAxis2.drawLabel(g, this);
	        
	        drawLegend((Graphics2D) g);
		}
	}


	@Override
	public void drawLegend(Graphics2D g) {

		ArrayList<Category> categories = new ArrayList<Category>();
		
		if (data.isEmpty())
			return;
		if (data.get(0).dataPoints.isEmpty())
			return;

		LegendUtil.setUpLegend(this, g);
		
		if (data.size() == 1) {
			/**
			 * In all other cases (except DataPointMultBar) if there is just one series
			 * then show nothing. ie. break out.
			 */
		}
	}


	 //Inner line just inside of the axis line. Potentially optional??
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

	protected void drawLeftLine(Graphics2D g) {
		/**
		 * Inner line just inside of the axis line. Potentially optional?? Eg
		 * colored area instead?
		 * 
		 */

		g.setStroke(chartBorderLine);
		g.setColor(borderLineColor);
		g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);
	}

	/**
	 * Inner line inside axis line. Not the same as the axis. Only used in
	 * XYYChart
	 * 
	 * @param g graphics context
	 */
	protected void drawRightLine(Graphics2D g) {
		g.setStroke(chartBorderLine);
		g.setColor(borderLineColor);
		g.drawLine(leftOffset + widthChart, topOffset, leftOffset + widthChart,
				heightChart + topOffset);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Point point = e.getPoint();

		for (XYDataSeries xyDataSeries : data) {
			ArrayList al = xyDataSeries.dataPoints;

			for (Object object : al) {
				DataPoint dp = (DataPoint) object;

				UIPointXY uip = dp.uiPointXY;
				
				if (uip != null) {
					boolean b = uip.doesShapeContainPoint(point);

					if (b)
						System.out.println("CONTAINS POINT!!");
				}
			}
		}
		this.updateUI();
	}

	public void setChartBackground(Color green) {
		this.backgroundColor = green;
	}

	public void clearGraphLines() {
		this.yAxis.axisScaling.interval1.styling.graphLine = null;
		this.yAxis.axisScaling.interval2.styling.graphLine = null;
		this.yAxis.axisScaling.interval3.styling.graphLine = null;
	}
	
	public void reInitialiseScaling() {
		ScalingHelper.initialiseScaling(this, data);
	}

}
