package com.bluewalrus.main.test.math;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.draw.point.UIPointSquare;
import com.bluewalrus.main.test.ChartTester;

public class TestDataXY_Mandelbrot extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Mandelbrot();
		t.testChart(t.getChart());
	}

	public Chart getChart() {

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		int width = 1000, height = 1000, max = 1000;
		
		double factor = 5.0;
		
		for (int y_pos = 0; y_pos < height; y_pos++) {

			for (int x_pos = 0; x_pos < width; x_pos++) {

				double c_re = (x_pos - width / 2) * factor/ width;

				double c_im = (y_pos - height / 2) * factor / width;

				double x = 0;
				double y = 0;

				int iterations = 0;

				/**
				 * Check how far x_pos, y_pos, whether it escapes, or is bounded to some degree
				 */
				while (x * x + y * y < factor && iterations < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iterations++;
				}
				if (iterations < max)
					values.add(new DataPoint(x_pos, y_pos));
			}
		}

		XYDataSeries series = new XYDataSeries(
				new UIPointSquare(Color.GRAY, 1), null, "");

		series.dataPoints = values;

		XYChart chart = new XYChart("Maths is fun!!", "Real", "Complex", series);
		chart.setSize(1000, 1000);
		chart.rightOffset = 200;

		return chart;
	}

	@Override
	public String getNiceTitle() {
		return "Mandelbrot Set";
	}
}