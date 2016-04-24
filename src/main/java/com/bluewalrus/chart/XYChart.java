package com.bluewalrus.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.GridLine;
import com.bluewalrus.bar.Legendable;
import com.bluewalrus.bar.Line;
import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.XYDataSeriesType;
import com.bluewalrus.chart.axis.Axis;
import com.bluewalrus.chart.axis.IntervalStyling;
import com.bluewalrus.chart.axis.NumericalInterval;
import com.bluewalrus.chart.axis.TimeInterval;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.DateUtils;
import com.bluewalrus.chart.draw.plotter.ChartPlotter;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.datapoint.DataPointWithMagnitude;
import com.bluewalrus.datapoint.ValueType;
import com.bluewalrus.point.UIPointBar;
import com.bluewalrus.point.UIPointSquare;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.scaling.EnumerationAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingX;
import com.bluewalrus.scaling.LinearNumericalAxisScalingY;
import com.bluewalrus.scaling.TimeSeriesAxisScalingX;

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
    public YAxis yAxis2;
	public XAxis xAxis;

	public ArrayList<XYDataSeries> data = new ArrayList<XYDataSeries>();
	
	public ArrayList<XYDataSeries> dataY2 = new ArrayList<XYDataSeries>();
	
	
	

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
	
	
	//XYY
	public XYChart(String title, String xLabel, String yLabel, String y2Label, ArrayList<XYDataSeries> xySeries,
			ArrayList<XYDataSeries> xySeriesY2) {
		
		
		this.data.addAll(xySeries);
		this.dataY2.addAll(xySeriesY2);
		
		ChartUtils.setUpSeriesStyle(xySeries, this);

		initEnum(xySeries);
		initialiseScaling(xySeries);
//		initialiseScaling(xySeriesY2);
		
		initialiseScalingX_enumeration(xySeriesY2);
		
		
		this.xAxis.labelText = xLabel;
		this.yAxis.labelText = yLabel;
		
		this.addMouseMotionListener(this);

		this.setTitle(title);
	}
	
	
	private void initEnum(ArrayList<XYDataSeries> xySeries) {
		
		if (isSeriesListEnumerable(xySeries)) {
			xAxis = initialiseScalingX_enumeration(xySeries);
		}
		
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

		xAxis = initialiseScalingX_enumeration(xySeriesList);
		yAxis = initialiseScalingY_numerical(xySeriesList);
		
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
			IntervalStyling stylingY2, IntervalStyling stylingY3, String xTitle, String yTitle) {
		
		initialiseScaling(xySeriesList);
		
		//replace the stylings defined in the intialise
		xAxis.axisDraw.interval1.styling = stylingX;
		yAxis.axisDraw.interval1.styling = stylingY;
		xAxis.axisDraw.interval2.styling = stylingX2;
		yAxis.axisDraw.interval2.styling = stylingY2;
		xAxis.axisDraw.interval3.styling = stylingX3;
		yAxis.axisDraw.interval3.styling = stylingY3;
		
		yAxis.labelText = yTitle;
		xAxis.labelText = xTitle;
				
		
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
	 * Bubble
	 * 
	 * @param listOfSeries
	 * @param yAxis
	 * @param xAxis
	 * @param multipleMagnitudeBy
	 */
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


	

	
	
	/**
	 * Initialize, calculating best x,y scalings and intervals.
	 * 
	 * @param xySeriesList
	 * @param title
	 */
	private void initialiseScaling(ArrayList<XYDataSeries> xySeriesList) {

		XAxis xAxis = null;
		YAxis yAxis = null;
		YAxis yAxis2 = null;
		
		
		DataPoint o = (DataPoint)xySeriesList.get(0).dataPoints.get(0);

		/**
		 * Initialise X
		 */
		if (o.xDate != null) {
			xAxis = initialiseScalingX_time(xySeriesList);
		} else if (isSeriesListEnumerable(xySeriesList)) {
			xAxis = initialiseScalingX_enumeration(xySeriesList);
		} else {
			xAxis = initialiseScalingX_numerical(xySeriesList);
		}
		/**
		 * Initialise Y
		 */
		yAxis = initialiseScalingY_numerical(xySeriesList);
		
		/**
		 * Initialise Y2
		 */
		if (this instanceof XYYChart) {
			
//			XYYChart d = (XYYChart)this;
			
			yAxis2 = initialiseScalingY_numerical(this.dataY2);
			yAxis2.axisDraw.setOrientation(Orientation.Y2);
		}
		
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.yAxis2 = yAxis2;
		
		
	}

	/**
	 * Check if XYDataSeries are enumerable or not.
	 * @param xySeriesList
	 * @return
	 */
	private boolean isSeriesListEnumerable(ArrayList<XYDataSeries> xySeriesList) {
		
		XYDataSeries first = xySeriesList.get(0);
		
		DataPoint dp = (DataPoint)first.dataPoints.get(0);

		if (dp.valueType == ValueType.X_ENUMARABLE) {
			return true;
		}
		
		return false;
	}

	private XAxis initialiseScalingX_time(ArrayList<XYDataSeries> xySeriesList) {
		
		DateRange drX = getDateRangeX(xySeriesList);
		
		//get sensible interval
		TimeInterval.Type interval1 = DateUtils.getIntervalTime(drX);
		TimeInterval.Type interval2 = DateUtils.getNextInterval(interval1);
		TimeInterval.Type interval3 = DateUtils.getNextInterval(interval2);
		
		TimeInterval t1x = new TimeInterval(4, interval1, null); 
		TimeInterval t2x = new TimeInterval(2, interval2, null);
		TimeInterval t3x = new TimeInterval(1, interval3, null);
		
		t1x.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6; 
		
		t2x.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);		
		t2x.styling.lineLength = 3; 
		
		//invisible!!! But not null
		t3x.styling.graphLine = new GridLine(Color.WHITE, false, 0);		
		t3x.styling.lineLength = 0; 
		
		
		XAxis xAxis = new XAxis(new TimeSeriesAxisScalingX(drX.min, drX.max, t1x, t2x, t3x), "X Ttime to do");
		return xAxis;
	}

	


	/**
	 * 
	 * @param xySeriesList
	 */
	private XAxis initialiseScalingX_enumeration(ArrayList<XYDataSeries> xySeriesList) {
    	
    	EnumerationAxisScalingX xd = new EnumerationAxisScalingX();
    	xAxis = new XAxis(xd, "");
    	
    	double xMax = xd.maxValue; //100
    	double xMin = xd.minValue; //0
    	

    	int lastLength = xySeriesList.get(0).dataPoints.size();
    	for (XYDataSeries xyDataSeries : xySeriesList) {
    		
    		int sizeXPoints = xyDataSeries.dataPoints.size();
    		if (sizeXPoints != lastLength) {
    			throw new RuntimeException("A list of series for an enumerable/category X Axis requires the exact "
    					+ "same number of data points for each series. "
    					+ "Variable number of series data points not currently supported");
    		}
		}
	
		ArrayList<DataPoint> dps = xySeriesList.get(0).dataPoints; //will only have one series
    	
        double xRange = (double) (xMax - xMin);
		
        //distance between points (bars)
        double pointDistance = (double) (xRange / (dps.size() + 1));

        
    	
    	for (XYDataSeries xyDataSeries : xySeriesList) {
    		ArrayList<DataPoint> dps2 = xyDataSeries.dataPoints;

            int i = 1;
            for (DataPoint dp : dps2) {
            	dp.x = (int) (pointDistance * i);
                i++;
            }
		}
    	
    	

        ChartUtils.validityCheck(dps);
    	

        
        return xAxis;
	}




	/**
	 * Numerical only
	 * @param xySeriesList
	 * @return
	 */
	
	private XAxis initialiseScalingX_numerical(ArrayList<XYDataSeries> xySeriesList) {
		//get padded range
		DataRange drX = getDataRangeX(xySeriesList);
		
		//get sensible interval
		double initialIntervalX = getInterval(drX);
		
		NumericalInterval t1x = new NumericalInterval(initialIntervalX); 
		NumericalInterval t2x = new NumericalInterval(initialIntervalX/10); 
		NumericalInterval t3x = new NumericalInterval(initialIntervalX/100); 

		t1x.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1x.styling.lineLength = 6; //new GridLine(Color.GRAY, false, 1);
		
		t2x.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);		
		t2x.styling.lineLength = 3; //new GridLine(Color.LIGHT_GRAY, true, 1);		

		//invisible!!! But not null
		t3x.styling.graphLine = new GridLine(Color.WHITE, false, 0);		
		t3x.styling.lineLength = 0; 

		
		XAxis xAxis = new XAxis(new LinearNumericalAxisScalingX(drX.min, drX.max, t1x, t2x, t3x), "X TODO");
		return xAxis;
	}

	private YAxis initialiseScalingY_numerical(ArrayList<XYDataSeries> xySeriesList) {
		//get padded range
		DataRange drY = getDataRangeY(xySeriesList);
		
		//get sensible interval
		double initialIntervalY = getInterval(drY);
		
		NumericalInterval t1 = new NumericalInterval(initialIntervalY); 
		NumericalInterval t2 = new NumericalInterval(initialIntervalY/10); 
		
		
		t1.styling.graphLine = new GridLine(Color.GRAY, false, 1);
		t1.styling.lineLength = 6;
		
		t2.styling.graphLine = new GridLine(Color.LIGHT_GRAY, true, 1);
		t2.styling.lineLength = 3;
		

		YAxis yAxis = new YAxis(new LinearNumericalAxisScalingY(drY.min, drY.max, t1, t2, null), "Y TODO");
		return yAxis;
	}

	private DataRange getDataRangeX(ArrayList<XYDataSeries> xySeriesList) {
		//get Max/Min
		double xMax = ChartUtils.calculateXAxisMax(xySeriesList, true);
		double xMin = ChartUtils.calculateXAxisMin(xySeriesList, true);

		//range with padding
		DataRange drX = ChartUtils.getDataRange(xMax, xMin, 10);
		return drX;
	}


		

	
	
	
	private DateRange getDateRangeX(ArrayList<XYDataSeries> xySeriesList) {
		
		//get Max/Min
		Date xMax = ChartUtils.calculateXAxisMaxDate(xySeriesList, true);
		Date xMin = ChartUtils.calculateXAxisMinDate(xySeriesList, true);

		//range with padding
		DateRange drX = ChartUtils.getDateRange(xMax, xMin, 10);
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
	 * TODO less than 1 and greater than 10000. Need generic algorithm here
	 * 
	 * @return
	 */
	private double getInterval(DataRange dr) {

		double max = dr.max; 
		double min = dr.min;
		
		if (max - min < 0.0000001) {
			throw new RuntimeException("data range cannot be less than XXXXXX. DataRange = " + dr);
		}else if (max - min > 100000000) {
			throw new RuntimeException("data range cannot be more than XXXXXX. DataRange = " + dr);
		}


		// starting at 0.00001 we go up by factors of 10 and check if the interval is acceptable.
		double magnitude = 0.00001;
		
		while (magnitude != 100000) {
			magnitude = magnitude * 10;
			
			if (isOrderMagnitudeAcceptableFirstInterval(max, min, magnitude)) {
				
				//found the correct magnitude
				break;
			}
		}
		return magnitude;
	}

	/**
	 * Is the magnitude acceptable as a first interval.
	 * 
	 * ie. 10 would be acceptable for 8 to 164
	 * (but not for 0.001 to 0.15)
	 * 
	 * This method checks if the magnitude is acceptable
	 * 
	 * 
	 * @param maxValue
	 * @param minValue
	 * @param orderOfMagnitude
	 * @return
	 */
	private boolean isOrderMagnitudeAcceptableFirstInterval(double maxValue,
			double minValue, double orderOfMagnitude) {
		
		
//		if (orderOfMagnitude < 1) {
//			
//			int multiplier = 1;
//			//
//			while (orderOfMagnitude != 1) {
//				
//				multiplier = multiplier * 10;
//				
//				orderOfMagnitude = 10 * orderOfMagnitude;
//				
//				System.out.println("orderOfMagnitude is now " + orderOfMagnitude);
//			}
//			
//			maxValue = maxValue * multiplier;
//			minValue = minValue * multiplier;
//		}
//		
//		
//		
//		
//		while ((int)maxValue % orderOfMagnitude != 0) {
//			maxValue--;
//		}
//
//		while ((int)minValue % orderOfMagnitude != 0) {
//			minValue++;
//		}

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
		drawGraphData(g2d);
	}

	@Override
	protected void drawGraphData(Graphics g) {
			
        if (xAxis.axisDraw.getMaxValue() == xAxis.axisDraw.getMinValue()) {
        	
        	throw new RuntimeException("Bummer! range has not been set for enum axis " + xAxis.axisDraw.getMinValue());
        }

//		if (xAxis.axisDraw instanceof TimeSeriesAxisScalingX) {
			new ChartPlotter().plotData((Graphics2D) g, this, yAxis, xAxis, data);
//		} else {
//			new ChartPlotter().plotData((Graphics2D) g, this, yAxis, xAxis, data);
//		}
		
		
		
		if (this instanceof XYYChart) {
//			XYYChart d = (XYYChart)this;
			
			new ChartPlotter().plotData((Graphics2D) g, this,
					yAxis2, xAxis, dataY2);
			
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
