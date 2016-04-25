package com.bluewalrus.bar;

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

	public XYBarDataSeries(ArrayList<DataPointBar> barSeries,
			BarDisplayOptions bdo, Object object, String string) {
		// TODO Auto-generated constructor stub
	} 

}
