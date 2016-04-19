package com.bluewalrus.scaling;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bluewalrus.bar.Orientation;
import com.bluewalrus.bar.Utils;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chart.axis.NumericalInterval;

public class LinearNumericalAxisScalingY extends LinearNumericalAxisScaling {

    public LinearNumericalAxisScalingY() {
		super(Orientation.Y);
	}
    
	public LinearNumericalAxisScalingY(double min, double max) {
		super(min, max, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double primaryIncrements,
			Double secondaryIncrements, Double tertiaryIncrements) {
		super(primaryIncrements, secondaryIncrements, tertiaryIncrements, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double minValue, Double maxValue,
			NumericalInterval interval1, NumericalInterval interval2, NumericalInterval interval3) {
		super(minValue, maxValue, interval1, interval2, interval3, Orientation.Y);
	}

	public LinearNumericalAxisScalingY(Double minValue, Double maxValue,
			Double primaryIncrements, Double secondaryIncrements,
			Double tertiaryIncrements) {
    	
		super(minValue, maxValue, primaryIncrements, secondaryIncrements,
				tertiaryIncrements, Orientation.Y);
	}

	@Override
	public void drawAll(Graphics2D g2d, XYChart xyChart, ArrayList<XYDataSeries> data) {
		
		//NOTE! data is ignored here. It's only used for enumeration axisdraw. TODO better way??
		
		
		
		drawAllIntervalTickAndLabels(g2d, xyChart);
		
		drawGridLines(g2d, xyChart);
		
	}
	
	@Override
	public void drawAllPre(Graphics2D g2d, XYChart xyChart,
			ArrayList<XYDataSeries> data) {
		
		drawGridFills(g2d, xyChart);
	}
	
	@Override
	protected double getFromStart(XYChart chart, double toFirstInPixels, double incrementInPixel, int i) {
		
		
		double fromTop = chart.heightChart + chart.topOffset - (i * incrementInPixel) - toFirstInPixels;
		return fromTop;
	}
	
	
    /**
     * If Y passes through ZERO then draw a line on the zero point.
     */
    public void drawGridLineOnZero(Graphics2D g, XYChart chart) {

        double factor = Utils.getFactor(chart.heightChart, maxValue, minValue);

        if (minValue < 0) {

            int fromTop = (int) (chart.heightChart + chart.topOffset + (minValue * factor));
            
            //TODO draw Y Line
//            YAxisDrawUtil.drawYGridLineOnZero(g, chart, fromTop, chart.yAxis);
        }
    }

    protected double getMultiplicationFactor(XYChart chart) {
    	return ((double) chart.heightChart / (double) (getMaxValue() - minValue));
	}

	@Override
	protected void drawGridLineOnZero(Graphics2D g) {
		// TODO Auto-generated method stub
	}

}
