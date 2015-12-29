package com.bluewalrus.chart;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Bar;
import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.datapoint.DataPointMultiBar;
import com.bluewalrus.datapoint.MultiBar;
import com.bluewalrus.point.UIPointMultiBar;
import com.bluewalrus.point.UIPointMultiBarStacked;

public class MultiBarChart extends XYChart { 

    public MultiBarChart(XAxis xAxis, YAxis yAxis, ArrayList<MultiBar> bars) {
        this(xAxis, yAxis, bars, 10, false);
    }

    public MultiBarChart(XAxis xAxis, YAxis yAxis, ArrayList<MultiBar> bars,
            boolean stacked) {
        this(xAxis, yAxis, bars, 10, stacked);
    }

    public MultiBarChart(XAxis xAxis, YAxis yAxis, ArrayList<MultiBar> bars, int barWidth, boolean stacked) {
        super(xAxis, yAxis);

        data = new ArrayList<XYDataSeries>();

        ArrayList<DataPointMultiBar> dataPoints = new ArrayList<DataPointMultiBar>();

        double xRange = (double) (this.xAxis.axisDraw.maxValue - this.xAxis.axisDraw.minValue);

//        space out
        //distance between points
        double pointDistance = (double) (xRange / (bars.size() + 1));

        int i = 1;
        for (MultiBar multibar : bars) {

            ArrayList<DataPointBar> dataPointArray = new ArrayList<DataPointBar>();

            DataPointMultiBar dpmb = new DataPointMultiBar((int) (pointDistance * i), (int) 42);

            for (DataPointBar bar : multibar.bars) {
                dataPointArray.add(bar);
            }
            i++;

            dpmb.datapointBars = dataPointArray;
            dpmb.name = multibar.name;

            dataPoints.add(dpmb);
        }

        if (stacked) {
            XYDataSeries<DataPointMultiBar> series = new XYDataSeries<DataPointMultiBar>(
                    dataPoints,
                    new UIPointMultiBarStacked(this),
                    null,
                    "");

            data.add(series);
        } else {
            XYDataSeries<DataPointMultiBar> series = new XYDataSeries<DataPointMultiBar>(
                    dataPoints,
                    new UIPointMultiBar(this),
                    null,
                    "");

            data.add(series);
        }
    }

    @Override
    public void drawLegend(Graphics2D g) {

        ArrayList<Category> categories = new ArrayList<Category>();

        XYDataSeries series = data.get(0);
        DataPointMultiBar p = (DataPointMultiBar) series.dataPoints.get(0);

        ArrayList<DataPointBar> dps = p.datapointBars;
        for (DataPointBar dpb : dps) {
            Category category;

        	category = new Category(dpb.xName, series.pointType, null);

            category.block = true;
            category.color = dpb.color;
            categories.add(category);
        }

        super.drawLegend(g, categories);
    }

}
