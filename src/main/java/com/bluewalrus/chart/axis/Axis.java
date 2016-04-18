/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.chart.axis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.draw.AxisDraw;
import com.bluewalrus.chart.draw.LinearNumericalAxisScaling;

/**
 * Abstract Axis. Sub types are X and Y. Possible Z Axis in future
 *
 * @author Oliver Watkins
 */
public abstract class Axis implements Serializable {

	/**
	 * determines the schema of the Axis. How it is drawn, ie linear , time , logarithmic
	 */
	public AxisDraw axisDraw;
	



	public Font font = new Font("Arial", Font.PLAIN, 12);
	public String labelText = "";

	public Color axisColor = Color.BLACK;

	public int tickLabelOffset = 40; // TODO this should be seperate for each
										// Axis. X and Y have slightly different
										// spacing.
	public int labelOffset = 40;
	public int marginOffset = 6;

	public Font axisCatFont = new Font("Arial", Font.BOLD, 12);


	/**
	 * Default constructor
	 *
	 * @param name
	 * @param type
	 */
	public Axis(String name, AxisDraw axisDraw) {

		labelText = name;
		
		this.axisDraw = axisDraw;
	}
	
	
    public AxisDraw getAxisDraw() {
		return axisDraw;
	}

	public void setAxisDraw(AxisDraw axisDraw) {
		this.axisDraw = axisDraw;
	}
	
	

	/**
	 * The overall label for the axis, eg: Temperature, Day of Week.
	 * 
	 * @param g
	 * @param chart
	 */
	public abstract void drawLabel(Graphics g, Chart chart);

	/**
	 * Borderline is the OUTER line
	 * 
	 * @param g
	 * @param chart
	 */
	public abstract void drawBorderLine(Graphics g, Chart chart);


	public abstract String getName();
	
	

}
