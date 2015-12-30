package com.bluewalrus.chart.axis;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.axis.Axis.AxisType;
import com.bluewalrus.chart.draw.AxisDraw;
import com.bluewalrus.chart.draw.LinearNumericalAxisDraw;
import com.bluewalrus.chart.draw.XAxisDrawUtil;

public class XAxis extends Axis {

    /**
     * @param name axis name
     */
    public XAxis(AxisDraw axisDraw, String name) {
        super(name, axisDraw);
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
