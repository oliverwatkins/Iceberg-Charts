package com.bluewalrus.point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.bluewalrus.chart.XYChart;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointBar;
import com.bluewalrus.renderer.XYFactor;

public class UIPointBar extends UIPointAbstractBar {

    boolean doBorder = true;
    Color negativeColor;
//    public XYChart chart; //TODO Two way reference here :( Not good :(

    public UIPointBar(Color color, XYChart chart) {
        super(color);
//        this.chart = chart;
    }

    public UIPointBar(Color color, Color negativeColor, XYChart chart, int barWidth) {
        super(color);
        this.negativeColor = negativeColor;
//        this.chart = chart;
        this.barWidth = barWidth;
    }

    public void draw(Graphics2D g, Point point, DataPoint dataPoint, XYFactor xyFactor) {

        DataPointBar dp = (DataPointBar) dataPoint;

        Color colorToUse;

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (dp.y > 0) { // greater than zero
            x = point.x - (barWidth / 2);
            y = point.y;
            width = barWidth;
            height = (int) ((dp.y * xyFactor.yFactor));

            colorToUse = color;

        } else { // less than zero

            x = point.x - (barWidth / 2);
            y = point.y + (int) (dp.y * xyFactor.yFactor);
            width = barWidth;
            height = (int) ((-dp.y * xyFactor.yFactor));

            colorToUse = negativeColor;
        }

        if (dp.color != null) {

            colorToUse = dp.color;
        }

        Color muchmuchdarker = colorToUse.darker();

        g.setColor(colorToUse);

        //bottom rect
        g.fillRect(x,
                y,
                width,
                height); // - xyFactor.yZeroOffsetInPixel));

        g.setColor(muchmuchdarker);

        /**
         * Draw a border
         */
        if (doBorder) {
            //bottom rect
            g.drawRect(x,
                    y,
                    width,
                    height);

        }
    }

	@Override
	public boolean doesShapeContainPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
