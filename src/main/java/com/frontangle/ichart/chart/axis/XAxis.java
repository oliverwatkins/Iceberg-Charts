package com.frontangle.ichart.chart.axis;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.Orientation;
import com.frontangle.ichart.chart.draw.XAxisDrawUtil;
import com.frontangle.ichart.scaling.AxisScaling;

public class XAxis extends Axis {

    public XAxis(AxisScaling axisDraw, String name) {
        super(name, axisDraw);
        
        axisDraw.setOrientation(Orientation.X);
        
    }

	@Override
    public void drawLabel(Graphics g, Chart chart) {
    	XAxis axis = this;
    	
        Graphics2D g2d = (Graphics2D) g;
        
        XAxisDrawUtil.drawLabel(chart, axis, g2d);
    }

    @Override
    public void drawBorderLine(Graphics g, Chart chart) {

        int x1 = chart.leftOffset;
        int y1 = chart.topOffset + chart.heightChart + marginOffset;
        int x2 = chart.leftOffset + chart.widthChart;
        int y2 = y1;

    	g.setColor(axisColor);
        g.drawLine(x1, y1, x2, y2);
    }
    
    public String getName() {
        return "X Axis";
    }
}
