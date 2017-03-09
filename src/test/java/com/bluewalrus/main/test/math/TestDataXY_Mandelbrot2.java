package com.bluewalrus.main.test.math;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.Line;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.chart.draw.point.UIPointTriangle;
import com.bluewalrus.chart.draw.point.UIPointXY;
import com.bluewalrus.main.test.ChartTester;

public class TestDataXY_Mandelbrot2 extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Mandelbrot2();
		t.testChart(t.getChart());
	}

	public Chart getChart() {
		
		int max = 100;
		int height = 1000;
		int width = 1000; 
		
		int[] colors = new int[max];
		
        HashMap<Integer, XYDataSeries<DataPoint>> map = new HashMap<Integer, XYDataSeries<DataPoint>>();
        for (int i = 0; i<max; i++) {
        	
        	Color color = new Color(Color.HSBtoRGB(i/256f, 1, i/(i+8f)));
        	
        	XYDataSeries series = new XYDataSeries(new UIPointSquare(color,1), null, "");

        	series.dataPoints = new ArrayList<DataPoint>();
        	map.put(i, series);
        }
        XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLACK,1), null, "");
        series.dataPoints = new ArrayList<DataPoint>();
        map.put(0, series);
        
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		
		for (int row = 0; row < height; row++) {
			
			for (int col = 0; col < width; col++) {
				
				double c_re = (col - width / 2) * 4.0 / width;
				
				double c_im = (row - height / 2) * 4.0 / width;
				
				double x = 0, y = 0;
				
				int iterations = 0;
				
				while (x * x + y * y < 4 && iterations < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iterations++;
				}
				
                if (iterations < max) {
                	
//                	map.get(0).dataPoints.add(new DataPoint(col,row));
                	
                	XYDataSeries ser = map.get(iterations);
                	ser.dataPoints.add(new DataPoint(col,row));
                }
			}
		}
		
        for (int i = 0; i<max; i++) {
        	XYDataSeries d = map.get(i);
        	if (d.dataPoints.size() == 0) {
        		map.remove(i);
        	}
        }
        
        ArrayList<XYDataSeries> al = new ArrayList<XYDataSeries>(map.values());

		XYChart chart = new XYChart("Maths is Fun!!", "Real Axis", "Complex Axis", al);
		
		System.out.println("point number " + values.size());
		

		chart.setSize(1000, 500);
		chart.rightOffset = 200;

		chart.setTitleFont(new Font("Ariel", Font.PLAIN, 24));
		chart.setTitle("Maths is Fun!");

		return chart;
	}
	
	@Override
	public String getNiceTitle() {
		return "Mandelbrot Set";
	}
	
}