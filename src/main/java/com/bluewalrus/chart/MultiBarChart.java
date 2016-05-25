//package com.bluewalrus.chart;
//
//import java.awt.Graphics2D;
//import java.util.ArrayList;
//
//import com.bluewalrus.chart.axis.XAxis;
//import com.bluewalrus.chart.axis.YAxis;
//import com.bluewalrus.chart.datapoint.DataPoint;
//import com.bluewalrus.chart.datapoint.DataPointBar;
//import com.bluewalrus.chart.datapoint.DataPointMultiBar;
//import com.bluewalrus.chart.datapoint.MultiBar;
//import com.bluewalrus.chart.draw.point.UIPointMultiBar;
//import com.bluewalrus.chart.draw.point.UIPointMultiBarStacked;
//
///**
// * Multibar chart can be stacked or side by side. It currently is only enumerable
// * on the X-Axis.
// * 
// * @author Oliver Watkins
// *
// */
//public class MultiBarChart extends XYChart { 
//
//
//    public MultiBarChart(XAxis xAxis, YAxis yAxis, ArrayList<MultiBar> bars,
//            boolean stacked) {
//        this(xAxis, yAxis, bars, 10, stacked);
//    }
//
//    
//    public MultiBarChart(ArrayList bars, String title, String xLabel,
//			String yLabel) {
//    	
//        this(bars, 10, false, title, xLabel, yLabel);
//	}
//    
//    
//
//	public MultiBarChart(ArrayList<DataPoint> bars, int barWidth, boolean b,
//			String title, String xLabel, String yLabel) {
//		
//		super(bars, "Tit", "x", "y");
//	}
//
//    
//    public MultiBarChart(XAxis xAxis, YAxis yAxis, ArrayList<MultiBar> bars, int barWidth, boolean stacked) {
//        super(xAxis, yAxis);
//
//        data = new ArrayList<XYDataSeries>();
//        
//        ArrayList<MultiBar> dataPoints = new ArrayList<MultiBar>();
//
//		double pointDistance = (double) (this.widthChart / (dataPoints.size() + 1));
//
//        for (MultiBar multibar : bars) {
//
//            ArrayList<DataPointBar> dataPointArray = new ArrayList<DataPointBar>();
//            
//            //arbitrary x/y values at this stage because it will be enumerable
//            MultiBar dpmb = new MultiBar(null, null, null); //-9999, 42); //CANNOT GUARANTEE X HERE :(((
//
//            for (DataPointBar bar : multibar.bars) {
//                dataPointArray.add(bar);
//            }
//
//            dpmb.bars = dataPointArray;
//            dpmb.name = multibar.name + "";
//
//            dataPoints.add(dpmb);
//        }
//
//        if (stacked) {
//            XYDataSeries<MultiBar> series = new XYDataSeries<MultiBar>(
//                    dataPoints,
//                    new UIPointMultiBarStacked(),
//                    null,
//                    "");
//
//            data.add(series);
//        } else {
//            XYDataSeries<MultiBar> series = new XYDataSeries<MultiBar>(
//                    dataPoints,
//                    new UIPointMultiBar(),
//                    null,
//                    "");
//
//            data.add(series);
//        }
//    }
//
//
//
//	@Override
//    public void drawLegend(Graphics2D g) {
//
//        ArrayList<Category> categories = new ArrayList<Category>();
//
//        XYDataSeries series = data.get(0);
//        MultiBar p = (MultiBar) series.dataPoints.get(0);
//
//        ArrayList<DataPointBar> dps = p.bars;
//        for (DataPointBar dpb : dps) {
//            Category category;
//
//        	category = new Category(dpb.name, series.pointType, null);
//
//            category.block = true;
//            category.color = dpb.color;
//            categories.add(category);
//        }
//
//        super.drawLegend(g, categories);
//    }
//
//}
