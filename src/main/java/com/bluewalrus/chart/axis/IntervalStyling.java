package com.bluewalrus.chart.axis;

import java.awt.Font;

import com.bluewalrus.chart.draw.GridFill;
import com.bluewalrus.chart.draw.GridLine;

public class IntervalStyling {

	public GridLine graphLine;
	public GridFill graphFill;
	
    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    public int lineLength;
	public Font intervalFont; 
    
	public IntervalStyling() {
	}
    
	public IntervalStyling(int lineLength, GridLine gridLine, GridFill gridFill) {
		
		this.lineLength = lineLength;
		
		this.graphLine = gridLine;
		this.graphFill = gridFill;
	}
}
