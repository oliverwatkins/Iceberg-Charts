package com.bluewalrus.main.test.threaded;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.xml.stream.events.StartDocument;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointCandleStick;

public class Threaded extends JFrame {
	

	public static void main(String[] args) throws Exception {
		
		
		TestStackedChart_threaded t2 = new TestStackedChart_threaded();

		Threaded t = new Threaded();
		
		JTabbedPane tabbedPaneBar = new JTabbedPane();

		final Chart chart = t2.getChart();
		
		tabbedPaneBar.addTab("1", chart);

		t.getContentPane().add(tabbedPaneBar);

		t.setSize(700, 700);
		t.setVisible(true);

		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		class Runner extends Thread {
			
			private Threaded t = null;
			Runner(Threaded t) {
				this.t = t;
			}

			@Override
			public void run() {
				
				for (int i = 0; i < 10000; i++) {
					try {
						Thread.sleep(100);
						
						this.t.incrementChart(chart, i);
						
//						Threaded.this.incrementChart(chart);

						/**
						 * Update UI
						 */
						chart.updateUI(); //iteration is happening in here
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		Runner runner = new Runner(t);
		
		runner.start();
	}
	
	private synchronized void incrementChart(final Chart chart, int i)
			throws InterruptedException {
		
		StackedXYChart syxy = (StackedXYChart)chart;
		
		addSomeRandomData(syxy, i);

		double ddd = syxy.xAxis.axisScaling.getMinValue();
		syxy.xAxis.axisScaling.setMinValue(ddd + 10);
		double ddd2 = syxy.xAxis.axisScaling.getMaxValue();
		syxy.xAxis.axisScaling.setMaxValue(ddd2 + 10);

	}

	private void addSomeRandomData(StackedXYChart syxy, int i) {
		
		XYChart c1 = syxy.getCharts().get(0);
		XYChart c2 = syxy.getCharts().get(1);
		
		double rand = Math.random();
		
		XYDataSeries<DataPoint> s = c2.data.get(0);
			
		s.dataPoints.add(new DataPoint(400 + (i*10), 40*rand));
		s.dataPoints.remove(0);
		
		XYDataSeries<DataPoint> s2 = c1.data.get(0);
			
		double top = 60;
		double open = 40;
		double close = 20;
		double bottom = 10;
		
		top = top*rand;
		open = open*rand;
		close = close*rand;
		bottom = bottom*rand;
		
		s2.dataPoints.add(new DataPointCandleStick(400 + (i*10), top, open, close, bottom, true));
		s2.dataPoints.remove(0);
	}
	
	

	public XYChart getChart() {


		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));
		
		XYChart lineChart = new XYChart(values, "My Easy Example", "X Axis", "Y Axis");
		
		JFrame frame = new JFrame();
		frame.add(lineChart);
		frame.setSize(700, 500);
		frame.setVisible(true);
		
		return lineChart;
	}
}
