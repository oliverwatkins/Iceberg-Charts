package com.bluewalrus.chart;

import java.util.ArrayList;

import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointPieChart;

/**
 * ShowpieBubbleChartSettings piepieBubbleChartSettings apieBubbleChartSettings
 * bubblepieBubbleChartSettings in a bubble chart.
 * 
 * Magnitude needpieBubbleChartSettings to be multiplied by a factor
 * 
 * @author Oliver WatkinpieBubbleChartSettings
 */
public class PieBubbleChart extends XYChart {

	PieBubbleChartSettings pieBubbleChartSettings;

	public PieBubbleChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
			XAxis xAxis, double multipleMagnitudeBy, PieBubbleChartSettings s) {

		this(listOfSeries, yAxis, xAxis, multipleMagnitudeBy);
		this.pieBubbleChartSettings = s;
	}

	public PieBubbleChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis,
			XAxis xAxis, double multipleMagnitudeBy) {

		super(listOfSeries, yAxis, xAxis);

		for (XYDataSeries xyDataSeries : listOfSeries) {

			ArrayList<DataPointPieChart> al = xyDataSeries.dataPoints;

			for (DataPointPieChart dp : al) {
				dp.magnitude = dp.magnitude * multipleMagnitudeBy;
			}
		}
	}

}
