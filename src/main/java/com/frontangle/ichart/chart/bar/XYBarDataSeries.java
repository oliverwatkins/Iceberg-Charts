package com.frontangle.ichart.chart.bar;

import java.awt.Color;
import java.util.ArrayList;

import com.frontangle.ichart.chart.ChartUtils;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.draw.point.UIPointBar;

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

	
	//TODO move all this out of this class
	
	public void setUpBarDisplayOptions(BarDisplayOptions barDisplayOptions2) {
		
		this.barDisplayOptions = barDisplayOptions2;
		
		initBarDisplayOptions();
	}

	private void initBarDisplayOptions() {
		if (this.barDisplayOptions.color != null) {
			for (DataPointBar dataPointBar : dataPoints) {
				dataPointBar.color = this.barDisplayOptions.color;
			}
		}
		
		if (this.barDisplayOptions.transparancy != -1) {
			for (DataPointBar dataPointBar : dataPoints) {
				
				double tValue = 255 * this.barDisplayOptions.transparancy;
				
				Color newColor = new Color(this.barDisplayOptions.color.getRed(),
						this.barDisplayOptions.color.getGreen(),
						this.barDisplayOptions.color.getBlue(), (int)tValue
						);
				dataPointBar.color = newColor;
			}
		}
		
		if (this.barDisplayOptions.gradiantRule != null) {
			setUpGradiaentColors();
			
		}else if (this.barDisplayOptions.positiveColor != null && this.barDisplayOptions.negativeColor != null ) {
			setUpPositiveAndNegativeColors();
		}
	}

	private void setUpPositiveAndNegativeColors() {
		for (DataPointBar dataPointBar : dataPoints) {
			
			if (dataPointBar.y < 0) {
				dataPointBar.color = this.barDisplayOptions.negativeColor;
			}else {
				dataPointBar.color = this.barDisplayOptions.positiveColor;
			}
		}
	}

	private void setUpGradiaentColors() {
		double maxY = ChartUtils.getMaxYValue(dataPoints);
		double minY = ChartUtils.getMinYValue(dataPoints);
		
		double diff = maxY - minY;
		
		for (DataPointBar dataPointBar : dataPoints) {
			
			double percentChange = (double)((dataPointBar.y - minY) / diff);
			
			dataPointBar.color = this.barDisplayOptions.gradiantRule.getColor(percentChange);
		}
	} 
}
