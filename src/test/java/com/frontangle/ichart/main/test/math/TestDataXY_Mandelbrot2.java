package com.frontangle.ichart.main.test.math;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;

public class TestDataXY_Mandelbrot2 extends ChartTester {

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataXY_Mandelbrot2();
		t.testChart(t.getChart());
	}

	public Chart getChart() {

		int max = 1000;
		int height = 1000;
		int width = 1000;

		HashMap<Integer, XYDataSeries<DataPoint>> map = new HashMap<Integer, XYDataSeries<DataPoint>>();
		
		for (int i = 0; i < max; i++) {

			Color color = new Color(Color.HSBtoRGB(i / 256f, 1, i / (i + 8f)));

			XYDataSeries series = new XYDataSeries(new UIPointSquare(color, 1),
					null, "");

			series.dataPoints = new ArrayList<DataPoint>();
			map.put(i, series);
		}
		
		XYDataSeries series = new XYDataSeries(
				new UIPointSquare(Color.BLACK, 1), null, "");
		series.dataPoints = new ArrayList<DataPoint>();
		map.put(0, series);

		ArrayList<DataPoint> values = new ArrayList<DataPoint>();

		/**
		 * Analyse every pixel along X and Y
		 */
		for (int y_pos = 0; y_pos < height; y_pos++) {

			for (int x_pos = 0; x_pos < width; x_pos++) {

				double c_re = (x_pos - width / 2) * 5.0 / width;

				double c_im = (y_pos - height / 2) * 5.0 / width;

				double x = 0, y = 0;

				int iterations = 0;

				/**
				 * Check how far x_pos, y_pos, whether it escapes, or is bounded to some degree
				 */
				while (x * x + y * y < 5 && iterations < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iterations++;
				}

				if (iterations < max) {

					XYDataSeries ser = map.get(iterations);
					ser.dataPoints.add(new DataPoint(x_pos, y_pos));
				}
			}
		}

		for (int i = 0; i < max; i++) {
			XYDataSeries d = map.get(i);
			if (d.dataPoints.size() == 0) {
				map.remove(i);
			}
		}

		ArrayList<XYDataSeries> al = new ArrayList<XYDataSeries>(map.values());

		XYChart chart = new XYChart("Maths is Fun!!", "Real Axis",
				"Complex Axis", al);

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