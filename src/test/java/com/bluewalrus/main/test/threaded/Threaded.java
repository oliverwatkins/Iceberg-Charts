package com.bluewalrus.main.test.threaded;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.StackedXYChart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.datapoint.DataPoint;

public class Threaded extends JFrame {
	

	public static void main(String[] args) throws Exception {
		
		
		TestStackedChart2 t2 = new TestStackedChart2();
		
		

		Threaded t = new Threaded();
//		t.testChart();
		
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
						Thread.sleep(50);
						
						this.t.incrementChart(chart);
						
//						Threaded.this.incrementChart(chart);
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}


		}
		
		Runner runner = new Runner(t);
		
		runner.start();
	}
	
	private void incrementChart(final Chart chart)
			throws InterruptedException {
		
		StackedXYChart syxy = (StackedXYChart)chart;
		
		
		addSomeRandomData(syxy);
		
		

		double ddd = syxy.xAxis.axisScaling.getMinValue();

		syxy.xAxis.axisScaling.setMinValue(ddd + 2);

		double ddd2 = syxy.xAxis.axisScaling.getMaxValue();

		syxy.xAxis.axisScaling.setMaxValue(ddd2 + 2);

		syxy.updateUI();
	}

	private void addSomeRandomData(StackedXYChart syxy) {
		// TODO Auto-generated method stub
		
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
