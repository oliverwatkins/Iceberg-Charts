package com.frontangle.ichart.chart.axis;

import java.awt.Font;

import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;

public class IntervalStyling {

	public Line graphLine;
	public GridFill graphFill;
	
    /**
     * length in pixels of the 'Tick'. 
     * Usually about 5 pixels hanging to the side of axis
     */
    public int lineLength;
	public Font intervalFont; 
    
	public IntervalStyling() {
	}
    
	public IntervalStyling(int lineLength, Line gridLine, GridFill gridFill) {
		
		this.lineLength = lineLength;
		
		this.graphLine = gridLine;
		this.graphFill = gridFill;
	}
}
