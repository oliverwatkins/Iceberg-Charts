package com.bluewalrus.chart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.XYDataSeriesType;
import com.bluewalrus.chart.axis.XAxis;
import com.bluewalrus.chart.axis.YAxis;
import com.bluewalrus.chart.draw.plotter.ChartPlotter;
import com.bluewalrus.datapoint.DataPointBar;


/**
 * XXYChart has line/point for one Y axis, and bar for the other Y axis. At this point in time
 * that is all that is planned for this chart.
 * 
 * Should this be pulled up into XYChart???
 * 
 * @author Oliver Watkins
 */
public class XYYChart extends XYChart { 

    public int barWidth = 30;
    public YAxis yAxis2;
//    public ArrayList<Bar> bars;
    public XYDataSeries<DataPointBar> barSeries;

    public ArrayList<XYDataSeries> xySeriesY2;

    
    
    public XYYChart(String title, 
    		String xLabel, 
    		String yLabel,
    		String y2Label, 
    		ArrayList<XYDataSeries> xySeries,
			ArrayList<XYDataSeries> xySeriesY2, boolean erasureFixme) {
    	
    	super(title, xLabel, yLabel, y2Label, xySeries, xySeriesY2);
    	
    	
    	
    	
//    	yAxis2 = new YAxis(new LinearNumericalAxisScalingX(0, 100), "hi");
//    	
//    	yAxis2.axisDraw.setOrientation(Orientation.Y2);
//    	
//    	
//    	
//    	this.xySeriesY2  = xySeriesY2;
    	
//    	yAxis2.setAxisDraw(new LinearNumericalAxisScalingX(0, 100));
    	
//		ChartUtils.setUpSeriesStyle(xySeriesList, this);
//
//		initialiseScaling(xySeriesList);
//		
//		this.xAxis.labelText = xLabel;
//		this.yAxis.labelText = yLabel;
//		
//		this.addMouseMotionListener(this);
//
//		this.data.addAll(xySeriesList);
//
//		this.setTitle(title);
		
		
//		yAxis2.axisDraw.setOrientation(Orientation.Y2);
		
		
    	/**
    	 * TODO this constructor to replace bar constructor
    	 * 
    	 * 
    	 */
    	
    	
	}
    





	@Override
    protected void drawGraphData(Graphics g) {
		
		super.drawGraphData(g);
		
//    	//draw bars first
//        drawLinesOrPointsXXY((Graphics2D) g, this, yAxis2, xAxis, barSeries);
//
//        //...then the other series.
//        new NumericalPlotter().drawLinesOrPoints((Graphics2D) g, this, yAxis, xAxis, data);
//
        Graphics2D g2d = (Graphics2D) g;
//
        drawRightLine(g2d);
        
        yAxis2.rightSide = true;
        
        yAxis2.axisDraw.drawAll(g2d, this, null); //drawTicksAndLabels(g, this);
        
        yAxis2.drawLabel(g, this);
//
        drawLegend((Graphics2D) g);
        
    }
    
    
    public static void drawLinesOrPointsXXY(Graphics2D g, XYChart chart,
            YAxis yAxis, XAxis xAxis, XYDataSeries<DataPointBar> barSeries) {

        ArrayList al = new ArrayList<>();
        al.add(barSeries);

        new ChartPlotter().drawLinesOrPoints(g, chart, yAxis, xAxis, al);
    }

    /**
     * This is slightly different to the super implementation, because an offset is used where the second
     * y axis is displyed.
     */
    @Override
    public void drawLegend(Graphics2D g) {

        ArrayList<Category> categories = new ArrayList<Category>();

        for (XYDataSeries series : data) {

            Category category;

            if (series.type == XYDataSeriesType.BUBBLE) {
                category = new Category(series.name, series.seriesColor);
            } else {
                category = new Category(series.name, series.pointType, series.line);
            }

            categories.add(category);
        }

        int offset = yAxis2.tickLabelOffset + yAxis2.labelOffset;

        super.drawLegend(g, categories, offset);
    }

}
