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
	 * Simple Single Constructor
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

		init(xySeriesList, title);
	}
	
	

	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			IntervalStyling stylingX, IntervalStyling stylingY) {
		
		this.data.addAll(xySeriesList);
		init(xySeriesList, title);
		
		xAxis.axisDraw.interval1.styling = stylingX;
		yAxis.axisDraw.interval1.styling = stylingY;

	}
	
	




	public XYChart(ArrayList<XYDataSeries> xySeriesList, String title,
			IntervalStyling stylingX, IntervalStyling stylingX2,
			IntervalStyling stylingX3, IntervalStyling stylingY,
			IntervalStyling stylingY2, IntervalStyling stylingY3) {
		
		this.data.addAll(xySeriesList);
		init(xySeriesList, title);
		
		xAxis.axisDraw.interval1.styling = stylingX;
		yAxis.axisDraw.interval1.styling = stylingY;
		xAxis.axisDraw.interval2.styling = stylingX2;
		yAxis.axisDraw.interval2.styling = stylingY2;
		xAxis.axisDraw.interval3.styling = stylingX3;
		yAxis.axisDraw.interval3.styling = stylingY3;
	}
	
	
	


	/**
	 * Simple Mutliple Constructor
	 * 
	 * @param title
	 * @param x
	 * @param y
	 * @param xySeriesList
	 */
	public XYChart(String title, String x, String y,
			ArrayList<XYDataSeries> xySeriesList) {

		setUpSeriesStyle(xySeriesList);

		init(xySeriesList, title);
		
		this.xAxis.labelText = x;
		this.yAxis.labelText = y;
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
		
		setUpSeriesStyle(xySeriesList);

		init(xySeriesList, title);
		
		this.xAxis.labelText = x;
		this.yAxis.labelText = y;

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






	public XYChart(String title, String xAxisTitle, String yAxisTitle,
			IntervalStyling intervalStyling, 
			IntervalStyling intervalStyling2,
			IntervalStyling intervalStyling3,
			XYDataSeries... series) {
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		for (XYDataSeries xyDataSeries : series) {
			xySeriesList.add(xyDataSeries);
		}
		
		init(xySeriesList, title);
		setUpSeriesStyle(xySeriesList);
		
		this.xAxis.labelText = xAxisTitle;
		this.yAxis.labelText = yAxisTitle;
		
		this.xAxis.axisDraw.interval1.styling = intervalStyling;
		this.xAxis.axisDraw.interval2.styling = intervalStyling2;
		this.xAxis.axisDraw.interval3.styling = intervalStyling3;
		
	}

	/**
	 * Set up some default styles
	 * 
	 * @param xySeriesList
	 */

	private void setUpSeriesStyle(ArrayList<XYDataSeries> xySeriesList) {
		int i = 0;
		for (XYDataSeries xyDataSeries : xySeriesList) {
			
			if (i == 0) {
				xyDataSeries.pointType = new UIPointSquare(Color.BLUE);
				xyDataSeries.line = new Line(Color.BLUE, false, 2);
				
			}else if (i == 1) {
				xyDataSeries.pointType = new UIPointCircle(Color.GREEN);
				xyDataSeries.line = new Line(Color.GREEN, false, 2);
			}else if (i == 2) {
				xyDataSeries.pointType = new UIPointTriangle(Color.RED);
				xyDataSeries.line = new Line(Color.RED, false, 2);
			}else if (i == 3) {
				xyDataSeries.pointType = new UIPointTriangle(Color.CYAN);
				xyDataSeries.line = new Line(Color.CYAN, false, 2);
				
			}else if (i == 4) {
				xyDataSeries.pointType = new UIPointCircle(Color.MAGENTA);
				xyDataSeries.line = new Line(Color.MAGENTA, false, 2);
			}else {
				//create random TODO
			}
			i++;
		}

		rightOffset = 200;
	}
	
	/**
	 * INitialize, calculating best x,y scalings and intervals.
	 * 
	 * @param xySeriesList
	 * @param title
	 */
	private void init(ArrayList<XYDataSeries> xySeriesList, String title) {

		double yMax = ChartUtils.calculateYAxisMax(xySeriesList, true);
		double yMin = ChartUtils.calculateYAxisMin(xySeriesList, true);
		double xMax = ChartUtils.calculateXAxisMax(xySeriesList, true);
		double xMin = ChartUtils.calculateXAxisMin(xySeriesList, true);

		// get the diffs
		double xDiff = xMax - xMin;
		double yDiff = yMax - yMin;

		// pad out to 10%
		double yMinAdj = yMin - (yDiff / 10);
		double xMinAdj = xMin - (xDiff / 10);
		double yMaxAdj = yMax + (yDiff / 10);
		double xMaxAdj = xMax + (xDiff / 10);

		// Needs to be floored. If decimal place then crashes later
		xMaxAdj = Math.floor(xMaxAdj);
		yMaxAdj = Math.floor(yMaxAdj);
		xMinAdj = Math.floor(xMinAdj);
		yMinAdj = Math.floor(yMinAdj);

		double magnitudeY = getInterval(yMinAdj, yMaxAdj);
		double magnitudeX = getInterval(xMinAdj, xMaxAdj);

		NumericalInterval t1 = new NumericalInterval(magnitudeY); 
		NumericalInterval t2 = new NumericalInterval(magnitudeY/10); 
		
		
		t1.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1.styling.lineLength = 6;
		
		t2.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);
		t2.styling.lineLength = 3;
		

		YAxis yAxis = new YAxis(new LinearNumericalAxisDrawY(yMinAdj, yMaxAdj, t1, t2, null), "Y TODO");

		NumericalInterval t1x = new NumericalInterval(magnitudeX); //, new GridLine(Color.GRAY, false, 1));
		NumericalInterval t2x = new NumericalInterval(magnitudeX/10); //, new GridLine(Color.LIGHT_GRAY, true, 1));
		NumericalInterval t3x = new NumericalInterval(magnitudeX/100); //, new GridLine(Color.LIGHT_GRAY, true, 1));

		t1x.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6; //new GridLine(Color.GRAY, false, 1);
		
		t2x.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);		
		t2x.styling.lineLength = 3; //new GridLine(Color.LIGHT_GRAY, true, 1);		

		//invisible!!!
		t3x.styling.graphLine = new GridLine(Color.WHITE, false, 0);		
		t3x.styling.lineLength = 0; 

		
		XAxis xAxis = new XAxis(new LinearNumericalAxisDrawX(xMinAdj, xMaxAdj, t1x, t2x, t3x), "X TODO");

		this.yAxis = yAxis;
		this.xAxis = xAxis;

		this.addMouseMotionListener(this);

		this.data.addAll(xySeriesList);

		this.setTitle(title);
	}

	
	/**
	 * Get a sensible interval between two points. Ie. (34 --> 10,000) would be 1000 (36 --> 132) would be 10
	 * 
	 * 
	 * TODO less than zero and greater than 10000. Need generic algorithm here
	 * 
	 * @param yMinAdj
	 * @param yMaxAdj
	 * @return
	 */
	private double getInterval(double yMinAdj, double yMaxAdj) {

		double yT = yMaxAdj;
		double yT2 = yMinAdj;

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

		if (numberTicks < 10) { // && numberTicks > 2) {
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

		/**
		 * TODO if the x-axis is enumerable then the data needs to be massaged.
		 * Upon creation of the datapoints the x y values were arbitrary. Now
		 * that the chart has size we can calculate the values where the points
		 * need to be positioned.
		 * 
		 * This does not look right here :( Maybe put into EnumeratioAxisDraw??
		 */
		if (xAxis.axisDraw instanceof EnumerationAxisDrawX) {
			massageXAxisData_forEnumeration((Graphics2D) g, this, data);
		}

		if (xAxis.axisDraw instanceof TimeSeriesAxisDrawX) {
			new DatePlotter().drawLinesOrPoints((Graphics2D) g, this, yAxis,
					xAxis, data);
		} else {
			new NumericalPlotter().drawLinesOrPoints((Graphics2D) g, this,
					yAxis, xAxis, data);
		}

	}

	/**
	 * Massage data on X axis.ONLY USED FOR ENUMERATION!!!!
	 * 
	 * @param g2d
	 * @param xyChart
	 * @param data
	 */
	public void massageXAxisData_forEnumeration(Graphics2D g2d,
			XYChart xyChart, ArrayList<XYDataSeries> data) {

		double xMax = this.xAxis.axisDraw.maxValue;
		double xMin = this.xAxis.axisDraw.minValue;

		double xFactor = ((double) xyChart.widthChart / (double) (xMax - xMin));

		XYFactor xyFactor = new XYFactor(xFactor, 123);
		xyFactor.xZeroOffsetInPixel = (double) ((-xMin / (xMax - xMin)) * xyChart.widthChart);

		ArrayList<DataPoint> dataPoints = data.get(0).dataPoints;

		double xRange = (double) (xMax - xMin);

		// distance between points (bars)
		double pointDistance = (double) (xyChart.widthChart / (dataPoints
				.size() + 1));

		int i = 1;
		for (DataPoint dataPoint : dataPoints) {

			double xShift2 = (pointDistance * i);

			int x = (int) (xyChart.leftOffset + (xShift2));

			dataPoint.x = x;

			i++;
		}
	}

	@Override
	public void drawLegend(Graphics2D g) {

		ArrayList<Category> categories = new ArrayList<Category>();

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
