package com.frontangle.ichart.chart.util;


import java.awt.Dimension;

import com.frontangle.ichart.chart.Chart;

public class ChartAnimation extends AnimationSupport{

	private Chart chart;
	
	public ChartAnimation(Chart chart, int durationMs) {
		
		super(durationMs);
		this.chart = chart;
	}

	public void starting () {
//		sideBarSection.contentPane.setVisible(true);
	}
	
	protected void render(int value) {
		
		
//		chart
		
		
		
//		sideBarSection.setMaximumSize(new Dimension(Integer.MAX_VALUE, value));
//		
//		sideBarSection.contentPane.setVisible(true);
//
//		sideBarSection.revalidate();
	}

	public void stopped () {
		
//		sideBarSection.contentPane.setVisible(true);
//		sideBarSection.revalidate();
//		sideBarSection.printDimensions();
	}
}
