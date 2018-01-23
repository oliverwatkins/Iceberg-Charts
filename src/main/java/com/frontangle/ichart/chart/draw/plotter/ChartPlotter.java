package com.frontangle.ichart.chart.draw.plotter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.XYFactor;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointXY;
import com.frontangle.ichart.scaling.LogarithmicAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

/**
 * Plots chart data
 * 
 * TODO this class needs a lot of work
 * 
 * @author Oliver Watkins
 *
 */
public class ChartPlotter {

	protected Point lastPoint;
	private XYChart chart;

	/*
	 * Entry point
	 * 
	 * @param g
	 * 
	 * @param chart
	 * 
	 * @param yAxis
	 * 
	 * @param xAxis
	 * 
	 * @param xYDataSerieses
	 */
	public void plotData(Graphics2D g, XYChart chart, YAxis yAxis, XAxis xAxis,
			ArrayList<XYDataSeries> xyDataSerieses) {

		this.chart = chart;

		XYFactor xyFactor = getXYFactor(chart, xAxis, yAxis);

		
		xyFactor.xZeroOffsetInPixel = ChartUtils.getXZeroOffsetInPixel(chart, xAxis);
		xyFactor.yZeroOffsetInPixel = ChartUtils.getYZeroOffsetInPixel(chart, yAxis);

		int xShift = chart.leftOffset; // silly
//		int yShift = chart.topOffset + chart.heightChart;
		XYDataSeries prevXYDataSeries = null;
		
		
		
		System.out.println("AL before " + xyDataSerieses);
		
		if (xyDataSerieses.size() > 0 && xyDataSerieses.get(0).area != null && xyDataSerieses.get(0).area.type == Area.AreaType.STACKED) {
			
			/**
			 * The values need to be recalculated based on previous series values.
			 */
			xyDataSerieses = AreaPlotter.getAdjustValuesArray(xyDataSerieses);
		}
		
		
		for (XYDataSeries<DataPoint> xyDataSeries : xyDataSerieses) {

			ArrayList<DataPoint> dataPoints_ = xyDataSeries.dataPoints;

			// prevent concurrentmodification errors :
			CopyOnWriteArrayList<DataPoint> dataPoints = createCopySafeArray(dataPoints_);

			if (dataPoints.size() == 0) {
				continue;
			}


			// TODO this will crash if only ONE point is used in a chart!!
			// only for bar
			int pixBtnFirst2Pts = calculateDistanceBetweenFirstTwoPoints(dataPoints.get(0), dataPoints.get(1), xShift, xyFactor);

			PointPlotter.drawPoints(g, chart, xyFactor, xyDataSeries, dataPoints, pixBtnFirst2Pts);
			
			if (isLineSeries(xyDataSeries)) {
				LinePlotter.drawLines(g, chart, xyFactor, xyDataSeries, dataPoints);
			}
			
			if (isAreaSeries(xyDataSeries)) {
				
				checkAllSeriesHaveSameType(xyDataSerieses);
				
				if (xyDataSerieses.get(0).area.type == Area.AreaType.STACKED) {
					AreaPlotter.drawAreaStacked(g, chart, xyFactor, xyDataSeries, xyDataSerieses, prevXYDataSeries);
				}else {
					AreaPlotter.drawAreaOverlap(g, chart, xyFactor, xyDataSeries, prevXYDataSeries, dataPoints);
				}
			}
			prevXYDataSeries = xyDataSeries;
		}
	}


	private void checkAllSeriesHaveSameType(ArrayList<? extends XYDataSeries> xYDataSerieses) {
		Area.AreaType type = xYDataSerieses.get(0).area.type;
		
		for (XYDataSeries xYDataSeries : xYDataSerieses) {
			
			if (xYDataSeries.area == null) {
				throw new RuntimeException("Area is null. All XY Data Series should have the same area type, and cannot be null");
				
			}else {
				if (xYDataSeries.area.type != type)
					throw new RuntimeException("Area type is " + xYDataSeries.area.type + ". All XY Data Series should have the same area type, and cannot be null");
			}
		}
	}


	private boolean isAreaSeries(XYDataSeries xYDataSeries) {
		
		return (xYDataSeries.area != null);
	}


	private boolean isLineSeries(XYDataSeries xYDataSeries) {
		return (xYDataSeries.line != null);
	}

	private CopyOnWriteArrayList<DataPoint> createCopySafeArray(ArrayList<DataPoint> dataPoints_) {
		CopyOnWriteArrayList<DataPoint> dataPoints = new CopyOnWriteArrayList<DataPoint>();

		for (DataPoint dataPoint : dataPoints_) {
			dataPoints.add(dataPoint);
		}
		return dataPoints;
	}

	/**
	 * Get point on chart for a given data point.

	 * @param xyFactor
	 * @param xYDataSeries
	 * @param dataPoint
	 * @param chart
	 * @return
	 */
	protected static Point getPoint(XYFactor xyFactor,
			DataPoint dataPoint, XYChart chart) {
		
		

		int yShift = chart.topOffset + chart.heightChart;
		int xShift = chart.leftOffset;
		int x = 0;

		if (chart.xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
			x = (int) ((dataPoint.xDate.getTime() * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);

		} else if (chart.xAxis.axisScaling instanceof LogarithmicAxisScaling) {
			
			double adjustedValue = convertLogValue(dataPoint, chart);

			x = (int) (xShift + (adjustedValue * chart.widthChart));
		} else {
			x = (int) ((dataPoint.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
		}
		int y = (int) (yShift - (int) (dataPoint.y * xyFactor.getyFactor()) - xyFactor.yZeroOffsetInPixel);
		return  new Point(x, y);
	}



	static void checkForRunawayProcess(XYFactor xyFactor, int x, int y) {
		// hack TODO
		if (xyFactor.getyFactor() * y > 200000) {
			System.err.println("ERROR!!! xyFactor.yFactor * y > 200000....  xyFactor.xFactor - "
					+ xyFactor.getyFactor() + " y - " + y);
			System.err
					.println("Potential runaway process!!!! Computer is probably going to crash now?");
		}
		if (xyFactor.getxFactor() * x > 200000) {
			System.err.println("ERROR!!! xyFactor.xFactor * x > 200000....  xyFactor.xFactor - "
					+ xyFactor.getxFactor() + " x - " + x);
			System.err
					.println("Potential runaway process!!!! Computer is probably going to crash now?");
		}
	}



	//move
	private static double convertLogValue(DataPoint dataPoint, XYChart chart) {
		double logMax = Math.log10(chart.xAxis.getMaxValue());
		double logMin = Math.log10(chart.xAxis.getMinValue());

		double logDP = Math.log10(dataPoint.x);

		double v = (logDP - logMin) / (logMax - logMin);
		return v;
	}

	protected int calculateDistanceBetweenFirstTwoPoints(DataPoint dataPoint, DataPoint dataPoint2,
			int xShift, XYFactor xyFactor) {

		int x = (int) ((dataPoint.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);
		int x2 = (int) ((dataPoint2.x * xyFactor.getxFactor()) + xShift + xyFactor.xZeroOffsetInPixel);

		return (x2 - x);
	}

	protected XYFactor getXYFactor(XYChart chart, XAxis xAxis, YAxis yAxis) {

		if (chart.heightChart < 0)
			throw new RuntimeException("Chart cannot have negative height");
		if (chart.widthChart < 0)
			throw new RuntimeException("Chart cannot have negative width");

		double yMax = yAxis.axisScaling.getMaxValue();
		double yMin = yAxis.axisScaling.getMinValue();

		double xFactor = -1;

		if (xAxis.axisScaling instanceof TimeSeriesAxisScaling) {
			long xMax = ((TimeSeriesAxisScaling) xAxis.axisScaling).dateEnd.getTime();
			long xMin = ((TimeSeriesAxisScaling) xAxis.axisScaling).dateStart.getTime();

			double diffX = xMax - xMin;

			xFactor = ((double) chart.widthChart / diffX);

		} else {
			double xMax = xAxis.axisScaling.getMaxValue();
			double xMin = xAxis.axisScaling.getMinValue();

			double diffX = xMax - xMin;

			xFactor = ((double) chart.widthChart / diffX);
		}

		double diffY = yMax - yMin;

		double yfactor = ((double) chart.heightChart / diffY);

		return new XYFactor(xFactor, yfactor);
	}



}
