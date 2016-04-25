package com.bluewalrus.chart.bar;

import java.util.ArrayList;

import com.bluewalrus.chart.XYDataSeries;
import com.bluewalrus.chart.datapoint.DataPoint;
import com.bluewalrus.chart.datapoint.DataPointBar;

/**
 * A special case of XYDataSeries for bars. Contains extra rendering information
 * @author Oliver Watkins
 *
 */

public class XYBarDataSeries extends XYDataSeries<DataPoint>{

	public XYBarDataSeries(String name) {
		super(name);
	}

	public XYBarDataSeries(ArrayList barSeries,
			BarDisplayOptions bdo, Object object, String string) {
		
		super(barSeries, string);
		
	}

	public XYBarDataSeries() {
		super("");
		
//		this.dataPoints = new ArrayList<DataPoint>();
	}

	public void add(DataPointBar dataPointBar) {
		
		this.dataPoints.add(dataPointBar);
		
		
		// TODO Auto-generated method stub
		
	} 

}
