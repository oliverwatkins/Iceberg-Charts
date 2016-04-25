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

}
