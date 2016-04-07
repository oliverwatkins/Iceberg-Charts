package com.bluewalrus.chart.axis;

import com.bluewalrus.bar.GridFill;
import com.bluewalrus.bar.GridLine;

public class IntervalStyling {

	public GridLine graphLine;
	public GridFill graphFill;
	
    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    public int lineLength; 
    
	public IntervalStyling() {
	}
    
	public IntervalStyling(int lineLength, GridLine gridLine, GridFill gridFill) {
		
		this.lineLength = lineLength;
		
		this.graphLine = gridLine;
		this.graphFill = gridFill;
		
	}




    
}