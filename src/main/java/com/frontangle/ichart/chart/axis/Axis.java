/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontangle.ichart.chart.axis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.scaling.AxisScaling;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;

/**
 * Abstract Axis. Sub types are X and Y. Possible Z Axis in future
 *
 * @author Oliver Watkins
 */
public abstract class Axis implements Serializable {
	/**
	 * determines the schema of the Axis. How it is drawn, ie linear , time , logarithmic
	 */
	public AxisScaling axisScaling;


	public Font font = new Font("Arial", Font.PLAIN, 12);
	public String labelText = "";

	public Color axisColor = Color.BLACK;

	public int tickLabelOffset = 40; // TODO this should be seperate for each
										// Axis. X and Y have slightly different
										// spacing.
	public int labelOffset = 40;
	public int marginOffset = 6;

	public Font axisCatFont = new Font("Arial", Font.BOLD, 12);
	
	 //Default constructor
	public Axis(String name, AxisScaling axisDraw) {

		labelText = name;
		
		this.axisScaling = axisDraw;
	}
	
	
    public AxisScaling getAxisDraw() {
		return axisScaling;
	}

	public void setAxisDraw(AxisScaling axisDraw) {
		this.axisScaling = axisDraw;
	}
	
	

	/**
	 * The overall label for the axis, eg: Temperature, Day of Week.
	 * 
	 * @param g graphic context
	 * @param chart chart
	 */
	public abstract void drawLabel(Graphics g, Chart chart);

	/**
	 * Borderline is the OUTER line
	 * 
	 * @param g graphic context
	 * @param chart chart
	 */
	public abstract void drawBorderLine(Graphics g, Chart chart);


	public abstract String getName();
	
	public void turnOffGridLines() {
		axisScaling.interval1.styling.graphLine = null;
		axisScaling.interval2.styling.graphLine = null;
		axisScaling.interval3.styling.graphLine = null;
	}

	public double getMinValue() {
		return axisScaling.getMinValue();
	}

	public double getMaxValue() {
		return axisScaling.getMaxValue();
	}
	
	@Override
	public String toString() {
		return "Axis [axisScaling=" + axisScaling + ", font=" + font
				+ ", labelText=" + labelText + ", axisColor=" + axisColor
				+ ", tickLabelOffset=" + tickLabelOffset + ", labelOffset="
				+ labelOffset + ", marginOffset=" + marginOffset
				+ ", axisCatFont=" + axisCatFont + ", getMinValue()="
				+ getMinValue() + ", getMaxValue()=" + getMaxValue() + "]";
	}

	

}
