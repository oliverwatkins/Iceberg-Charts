package com.bluewalrus.chart.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPointBar;
import com.bluewalrus.chart.draw.point.UIPointBar;

/**
 * A special case of XYDataSeries for bars. Contains extra rendering information
 * @author Oliver Watkins
 *
 */

public class XYBarDataSeries extends XYDataSeries<DataPointBar>{

	public BarDisplayOptions barDisplayOptions;
	
	public XYBarDataSeries(String name) {
		super(name);
	}

	public XYBarDataSeries(ArrayList<DataPointBar> barSeries,
			BarDisplayOptions bdo, Object object, String string) {
		
		super(barSeries, string);
		
		this.pointType = new UIPointBar(Color.RED, 50);
		
		this.barDisplayOptions = bdo;
	}

	public XYBarDataSeries() {
		
		super("");
		
		this.pointType = new UIPointBar(Color.RED, 50);
	}

	public void add(DataPointBar dataPointBar) {
		if (this.dataPoints == null) {
			this.dataPoints = new ArrayList();	
		}
		this.dataPoints.add(dataPointBar);
	}

	public void setUpBarDisplayOptions(BarDisplayOptions barDisplayOptions2) {
		
		this.barDisplayOptions = barDisplayOptions2;
		
		initBarDisplayOptions();
	}

	private void initBarDisplayOptions() {
		if (this.barDisplayOptions.gradiantRule != null) {
			
			double minY = dataPoints.get(0).y;
			double maxY = dataPoints.get(0).y;
			for (DataPointBar dataPointBar : dataPoints) {
				
				if (dataPointBar.y < minY) {
					minY = dataPointBar.y;
				}
				if (dataPointBar.y > maxY) {
					maxY = dataPointBar.y;
				}
			}
			
			double diff = maxY - minY;

			
			for (DataPointBar dataPointBar : dataPoints) {
				
				double percentChange = (double)((dataPointBar.y - minY) / diff);
				
				dataPointBar.color = this.barDisplayOptions.gradiantRule.getColor(percentChange);
			}
		}
	} 

}
