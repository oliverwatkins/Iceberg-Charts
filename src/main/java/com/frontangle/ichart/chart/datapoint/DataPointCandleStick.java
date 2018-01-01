package com.frontangle.ichart.chart.datapoint;

/**
 * Financial Charting
 * 
 * Open Body :
 * 
 * high - 
 * close - 
 * open - 
 * low - 
 * 
 * Closed Body
 * 
 * high -
 * open -
 * close -
 * low -
 * 
 * @author Oliver Watkins
 *
 */

public class DataPointCandleStick extends DataPoint{
	
	public DataPointCandleStick(double x, double high, double open, double close,
			double low, boolean filled) {
		super(x,high);
		this.high = high;
		this.open = open;
		this.close = close;
		this.low = low;
		
		if (open > close)
			this.filled = true;
		else
			this.filled = false;
	}

	public double high;
	public double open; 
	public double close; 
	public double low; 
	 
	public boolean filled;

}
