package com.bluewalrus.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Legendable;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.XYDataSeriesType;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.EnumerationAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawX;
import com.bluewalrus.chart.draw.LinearNumericalAxisDrawY;
import com.bluewalrus.chart.draw.TimeSeriesAxisDrawX;
import com.bluewalrus.chart.plotter.DatePlotter;
import com.bluewalrus.chart.plotter.NumericalPlotter;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.point.UIPointBar;
import com.bluewalrus.point.UIPointCircle;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.point.UIPointTriangle;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.renderer.XYFactor;

/**
 * XYChart is a chart where data is represented by x,y data. Typically the y
 * axis is vertical and on the left, while the x axis runs along the bottom.
 * 
 * @author Oliver Watkins
 */
public class XYChart extends Chart implements Legendable, MouseMotionListener {

	transient BasicStroke chartBorderLine = new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {
					2, 0 }, // no dash
					0.0f);

	public Color borderLineColor = Color.BLACK;

	public YAxis yAxis;
	public XAxis xAxis;

	public ArrayList<XYDataSeries> data = new ArrayList<XYDataSeries>();

	/**
	 * Create an XY chart passing in also the data set.
	 * 
	 * @param listOfSeries
	 * @param yAxis
	 * @param xAxis
	 */
	public XYChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis, XAxis xAxis) {

		this(xAxis, yAxis);
		this.data.addAll(listOfSeries);
	}
	
	/**
	 * 
	 * @param series
	 * @param title
	 * @param yAxis
	 * @param xAxis
	 */
	public XYChart(XYDataSeries series, String title, YAxis yAxis,
			XAxis xAxis) {
		
		this.setTitle(title);
		
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		
		this.data.add(series);
	}

	/**
	 * Create an XY chart by passing in the two axis. This is the default
	 * constructor for an empty chart.
	 * 
	 * @param xAxis
	 * @param yAxis
	 */
	public XYChart(XAxis xAxis, YAxis yAxis) {
		this.yAxis = yAxis;
		this.xAxis = xAxis;

		this.addMouseMotionListener(this);
	}

	/**
	 * Simple Single Data Series Constructor
	 * 
	 * @param values
	 * @param xAxisMax
	 * @param yAxisMax
	 * @param title
	 */
	public XYChart(ArrayList<DataPoint> values, String title, String xLabel,
			String yLabel) {
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		XYDataSeries<DataPoint> xy = new XYDataSeries<DataPoint>(values,
				new UIPointSquare(Color.BLACK), new Line(Color.BLACK), "");
		xySeriesList.add(xy);

		initialiseScaling(xySeriesList);
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
		
		xAxis.labelText = xLabel;
		yAxis.labelText = yLabel;
				
	}
	
	/**
	 * Simple Mutliple Series Constructor
	 * 
	 * @param title
	 * @param xLabel
	 * @param yLabel
	 * @param xySeriesList
	 */
	public XYChart(String title, String xLabel, String yLabel,
			ArrayList<XYDataSeries> xySeriesList) {

		ChartUtils.setUpSeriesStyle(xySeriesList, this);

		initialiseScaling(xySeriesList);
		
		this.xAxis.labelText = xLabel;
		this.yAxis.labelText = yLabel;
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}
	
	/**
	 * Simple Bar Chart constructor
	 * 
	 * @param bars
	 * @param title
	 * @param xLabel
	 * @param yLabel
	 * @param pixelBarWidth
	 */
	public XYChart(ArrayList<DataPointBar> bars, String title, String xLabel,
			String yLabel, int pixelBarWidth) {
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();

		XYDataSeries<DataPoint> xy = new XYDataSeries<DataPoint>(bars,
				new UIPointBar(Color.RED, Color.YELLOW, pixelBarWidth), null, "");
		xySeriesList.add(xy);

		initialiseScalingForEnumeration(xySeriesList);
		
		this.setTitle(title);
		
		xAxis.labelText = xLabel;
		yAxis.labelText = yLabel;
		
		this.data.addAll(xySeriesList);
	}
	

	/**
	 * TODO is this used?
	 * 
	 * @param xySeriesList
	 * @param title
	 * @param stylingX
	 * @param stylingY
	 */
	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			IntervalStyling stylingX, IntervalStyling stylingY) {
		
		this.data.addAll(xySeriesList);
		initialiseScaling(xySeriesList);
		
		xAxis.axisDraw.interval1.styling = stylingX;
		yAxis.axisDraw.interval1.styling = stylingY;
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}
	
	/**
	 * Set up a chart with specific stylings.
	 * 
	 * @param xySeriesList
	 * @param title
	 * @param stylingX
	 * @param stylingX2
	 * @param stylingX3
	 * @param stylingY
	 * @param stylingY2
	 * @param stylingY3
	 */
	
	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			IntervalStyling stylingX, IntervalStyling stylingX2,
			IntervalStyling stylingX3, IntervalStyling stylingY,
			IntervalStyling stylingY2, IntervalStyling stylingY3) {
		
		initialiseScaling(xySeriesList);
		
		xAxis.axisDraw.interval1.styling = stylingX;
		yAxis.axisDraw.interval1.styling = stylingY;
		xAxis.axisDraw.interval2.styling = stylingX2;
		yAxis.axisDraw.interval2.styling = stylingY2;
		xAxis.axisDraw.interval3.styling = stylingX3;
		yAxis.axisDraw.interval3.styling = stylingY3;
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}
	
	/**
	 * 
	 * @param values
	 * @param string
	 * @param yAxis2
	 * @param xAxis2
	 */
	
	public XYChart(ArrayList<DataPoint> values, String title, YAxis yAxis2,
			XAxis xAxis2) {

		this.setTitle(title);
		
		this.xAxis = xAxis2;
		this.yAxis = yAxis2;
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		XYDataSeries s = new XYDataSeries<DataPoint>(values, "");
		
		this.data.add(s);
	}
	

	
	/**
	 * Simple multiple constructor
	 * 
	 * @param title chart title
	 * @param x
	 * @param y
	 * @param series
	 */
	public XYChart(String title, String x, String y, XYDataSeries... series) {
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		for (XYDataSeries xyDataSeries : series) {
			xySeriesList.add(xyDataSeries);
		}
		
		ChartUtils.setUpSeriesStyle(xySeriesList, this);

		initialiseScaling(xySeriesList);
		
		this.xAxis.labelText = x;
		this.yAxis.labelText = y;
		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);

	}
	/**
	 * 
	 * @param xySeriesList
	 * @param string
	 * @param yAxis2
	 * @param xAxis2
	 * @param getAroundErasureVariable_IGNORE
	 */
	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			YAxis yAxis, XAxis xAxis, boolean getAroundErasureVariable_IGNORE) {
		
		this.setTitle(title);
		
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		
		this.data.addAll(xySeriesList);
		
	}

	
	/**
	 * 
	 * @param title
	 * @param xAxisTitle
	 * @param yAxisTitle
	 * @param intervalStyling
	 * @param intervalStyling2
	 * @param intervalStyling3
	 * @param series
	 */
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
		
		initialiseScaling(xySeriesList);
		ChartUtils.setUpSeriesStyle(xySeriesList, this);
		
		this.xAxis.labelText = xAxisTitle;
		this.yAxis.labelText = yAxisTitle;
		
		this.xAxis.axisDraw.interval1.styling = intervalStylingX1;
		this.xAxis.axisDraw.interval2.styling = intervalStylingX2;
		this.xAxis.axisDraw.interval3.styling = intervalStylingX3;

		this.yAxis.axisDraw.interval1.styling = intervalStylingY1;
		this.yAxis.axisDraw.interval2.styling = intervalStylingY2;
		this.yAxis.axisDraw.interval3.styling = intervalStylingY3;

		
		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
		
	}




	
	/**
	 * @param xySeriesList
	 */
	
	private void initialiseScalingForEnumeration(ArrayList<XYDataSeries> xySeriesList) {

		 // TODO at this point only on X

		
		DataRange drY = getDataRangeY(xySeriesList); //just the y
		
		//get sensible interval
		double initialIntervalY = getInterval(drY);
		
		NumericalInterval t1 = new NumericalInterval(initialIntervalY); 
		
		t1.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1.styling.lineLength = 6;
		
		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(drY.min, drY.max, t1, null, null), "Y TODO");
		
		
		this.yAxis = yAxis;
    	
    	EnumerationAxisDrawX xd = new EnumerationAxisDrawX();
    	xAxis = new XAxis(xd, "");
    	
    	double xMax = xd.maxValue;
    	double xMin = xd.minValue;
	
		ArrayList<DataPointBar> bars = xySeriesList.get(0).dataPoints; //will only have one series

        double xRange = (double) (xMax - xMin);

        //distance between points (bars)
        double pointDistance = (double) (xRange / (bars.size() + 1));

        ChartUtils.validityCheck(bars);
    	
        ArrayList<DataPointBar> dataPoints = new ArrayList<DataPointBar>();
    	
        int i = 1;
        for (DataPointBar bar : bars) {
        	bar.x = (int) (pointDistance * i);
            i++;
        }
	}
	
	
	/**
	 * Initialize, calculating best x,y scalings and intervals.
	 * 
	 * @param xySeriesList
	 * @param title
	 */
	private void initialiseScaling(ArrayList<XYDataSeries> xySeriesList) {

		
		//get padded range
		DataRange drY = getDataRangeY(xySeriesList);
		
		//get sensible interval
		double initialIntervalY = getInterval(drY);
		
		//get padded range
		DataRange drX = getDataRangeX(xySeriesList);
		
		//get sensible interval
		double initialIntervalX = getInterval(drX);
		

		NumericalInterval t1 = new NumericalInterval(initialIntervalY); 
		NumericalInterval t2 = new NumericalInterval(initialIntervalY/10); 
		
		
		t1.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1.styling.lineLength = 6;
		
		t2.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);
		t2.styling.lineLength = 3;
		

		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(drY.min, drY.max, t1, t2, null), "Y TODO");

		NumericalInterval t1x = new NumericalInterval(initialIntervalX); //, new GridLine(Color.GRAY, false, 1));
		NumericalInterval t2x = new NumericalInterval(initialIntervalX/10); //, new GridLine(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3x = new NumericalInterval(initialIntervalX/100); //, new GridLine(Color.LIGHT_GRAY, true, 1));

		t1x.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6; //new GridLine(Color.GRAY, false, 1);
		
		t2x.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);		
		t2x.styling.lineLength = 3; //new GridLine(Color.LIGHT_GRAY, true, 1);		

		//invisible!!! But not null
		t3x.styling.graphLine = new GridLine(Color.WHITE, false, 0);		
		t3x.styling.lineLength = 0; 

		
		XAxis xAxis = new XAxis(new LinearNumericalAxisDrawX(drX.min, drX.max, t1x, t2x, t3x), "X TODO");

		this.yAxis = yAxis;
		this.xAxis = xAxis;


	}

	private DataRange getDataRangeX(ArrayList<XYDataSeries> xySeriesList) {
		//get Max/Min
		double xMax = ChartUtils.calculateXAxisMax(xySeriesList, true);
		double xMin = ChartUtils.calculateXAxisMin(xySeriesList, true);

		//range with padding
		DataRange drX = ChartUtils.getDataRange(xMax, xMin, 10);
		return drX;
	}

	private DataRange getDataRangeY(ArrayList<XYDataSeries> xySeriesList) {
		//Get Max/Min
		double yMax = ChartUtils.calculateYAxisMax(xySeriesList, true);
		double yMin = ChartUtils.calculateYAxisMin(xySeriesList, true);
		
		//range with padding
		DataRange drY = ChartUtils.getDataRange(yMax, yMin, 10);
		return drY;
	}



	

	/**
	 * Get a sensible interval between two points. Ie. (34 --> 10,000) would be 1000 (36 --> 132) would be 10
	 * 
	 * 
	 * TODO less than zero and greater than 10000. Need generic algorithm here
	 * 
	 * @return
	 */
	private double getInterval(DataRange dr) {

		double yT = dr.max; 
		double yT2 = dr.min;;

		double magnitude = 10.0;

		boolean ok = isOrderMagnitudeAcceptableFirstInterval(yT, yT2, magnitude);

		if (!ok) {
			magnitude = 100.0;
			ok = isOrderMagnitudeAcceptableFirstInterval(yT, yT2, magnitude);
		}

		if (!ok) {
			magnitude = 1000.0;
			ok = isOrderMagnitudeAcceptableFirstInterval(yT, yT2, magnitude);
		}

		if (!ok) {
			magnitude = 10000.0;
			ok = isOrderMagnitudeAcceptableFirstInterval(yT, yT2, magnitude);
		}
		return magnitude;
	}

	private boolean isOrderMagnitudeAcceptableFirstInterval(double maxValue,
			double minValue, double orderOfMagnitude) {
		while (maxValue % orderOfMagnitude != 0) {
			maxValue--;
		}

		while (minValue % orderOfMagnitude != 0) {
			minValue++;
		}

		double numberTicks = (maxValue - minValue) / orderOfMagnitude;

		if (numberTicks < 10) { 
			return true;
		}
		return false;
	}

	/**
	 * Paint the background, Title, Grid and Axis. All the elements of the chart
	 * except for the actual data.
	 *
	 * @param g2d
	 * @param data
	 */
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
		yAxis.axisDraw.drawAllPre(g2d, this, data);
		xAxis.axisDraw.drawAllPre(g2d, this, data);

		// y axis
		yAxis.axisDraw.drawAll(g2d, this, data);
		yAxis.drawLabel(g2d, this);
		yAxis.drawBorderLine(g2d, this);

		// x axis
		xAxis.axisDraw.drawAll(g2d, this, data);
		xAxis.drawLabel(g2d, this);
		xAxis.drawBorderLine(g2d, this);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		// draws axis, frame etc
		this.prePaint(g2d, data);

		// draws actual data
		drawGraph(g2d);
	}

	@Override
	protected void drawGraph(Graphics g) {
			
        if (xAxis.axisDraw.getMaxValue() == xAxis.axisDraw.getMinValue()) {
        	
        	System.out.println("xAxis.axisDraw " + xAxis.axisDraw.toString());
        	System.out.println("xAxis.axisDraw " + xAxis.axisDraw.getMaxValue());
        	
        	
        	throw new RuntimeException("Bummer! range has not been set for enum axis " + xAxis.axisDraw.getMinValue());
        }

		if (xAxis.axisDraw instanceof TimeSeriesAxisDrawX) {
			new DatePlotter().drawLinesOrPoints((Graphics2D) g, this, yAxis,
					xAxis, data);
		} else {
			new NumericalPlotter().drawLinesOrPoints((Graphics2D) g, this,
					yAxis, xAxis, data);
		}

	}


	@Override
	public void drawLegend(Graphics2D g) {

		ArrayList<Category> categories = new ArrayList<Category>();

		//only one series. no need for legend
		if (data.size() == 1) {
			return;
		}

		for (XYDataSeries series : data) {

			Category category;

			if (series.type == XYDataSeriesType.BUBBLE) {
				category = new Category(series.name, series.seriesColor);
			} else {
				category = new Category(series.name, series.pointType,
						series.line);
			}
			categories.add(category);
		}
		super.drawLegend(g, categories);
	}

	/**
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

	/**
	 * Inner line just inside of the axis line. Potentially optional?? Eg
	 * colored area instead?
	 * 
	 */
	protected void drawLeftLine(Graphics2D g) {

		g.setStroke(chartBorderLine);
		g.setColor(borderLineColor);
		g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);
	}

	/**
	 * Inner line inside axis line. Not the same as the axis. Only used in
	 * XYYChart
	 * 
	 * @param g
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

}
