package com.frontangle.ichart.chart;

import java.util.ArrayList;

import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.DataPointPieChart;

/**
 * 
 * Magnitude needpieBubbleChartSettings to be multiplied by a factor
 * 
 * @author Oliver Watkins
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

		super("","","", listOfSeries, yAxis, xAxis, false);

		for (XYDataSeries xyDataSeries : listOfSeries) {

			ArrayList<DataPointPieChart> al = xyDataSeries.dataPoints;

			for (DataPointPieChart dp : al) {
				dp.magnitude = dp.magnitude * multipleMagnitudeBy;
			}
		}
	}

}
