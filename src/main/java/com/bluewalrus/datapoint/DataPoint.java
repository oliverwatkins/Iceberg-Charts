package com.bluewalrus.datapoint;

import java.io.Serializable;

import com.bluewalrus.point.UIPointXY;

/**
 * Super type for boxplots, bar, multibar, piechart, bubble etc. But used individually for
 * things like scatter chart
 * 
 * @author lauren
 */
public class DataPoint implements Serializable{

    public double x;
    public double y;

    public String name; //may have a name
    
    
    public UIPointXY uiPointXY;

    /**
     * Y point only. The X value is determined by an equally spaced bar chart
     *
     * @param y
     */
    public DataPoint(double y) {
        this.y = y;
    }

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

	public void setPoinUI(UIPointXY uiPointXY) {
		
		this.uiPointXY = uiPointXY;
		
	}

}
