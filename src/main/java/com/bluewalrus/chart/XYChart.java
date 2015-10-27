/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.bluewalrus.bar.Category;
import com.bluewalrus.bar.Interval;
import com.bluewalrus.bar.Legendable;
import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.bar.XYDataSeriesType;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.point.UIPointXY;
import com.bluewalrus.renderer.LineRenderer;

public class XYChart extends Chart implements Legendable, MouseMotionListener {

	
	transient BasicStroke chartBorderLine = new BasicStroke(1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10.0f, new float[] { 2, 0 }, // no dash
			0.0f);
	
	
	public Color borderLineColor = Color.BLACK;
	
    public YAxis yAxis;
    public XAxis xAxis;

    public ArrayList<XYDataSeries> data = new ArrayList<XYDataSeries>();

    
    public XYChart(XAxis xAxis, YAxis yAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        
        this.addMouseMotionListener(this);
    }

    protected void drawGrid(Graphics2D g) {

        yAxis.drawYGridLineOnZero(g, this);

        drawGridLine(yAxis.interval3, g, 1);
        drawGridLine(yAxis.interval2, g, 1);
        drawGridLine(yAxis.interval1, g, 1);

        drawGridLine(xAxis.interval3, g, 0);
        drawGridLine(xAxis.interval2, g, 0);
        drawGridLine(xAxis.interval1, g, 0);
    }

    private void drawGridLine(Interval interval, Graphics2D g, int type) {

        if (interval != null && interval.graphLine != null) {
            if (type == 1) //type is y
            {
                yAxis.drawGridLine(interval, g, this);
            } else {
                xAxis.drawGridLine(interval, g, this);
            }
        }
    }

    /**
     * All XY Types need to have this. If you forget to call this your chart is
     * kinda gonna suck.
     *
     * @param g2d
     */
    protected void prePaint(Graphics2D g2d) {

        this.calculateHeighAndWidthOfChart();

        
        /**
         * Maybe we want a filled colored area instead of some lines???
         */
        drawBackground(g2d);
        drawBottomLine(g2d);
        drawLeftLine(g2d);

        drawTitle(g2d);

        drawGrid(g2d);

        yAxis.drawTicksAndLabels(g2d, this);
        
//        yAxis.drawIntervals(g2d, this);
//        yAxis.drawIntervalLabels(this.yAxis.interval1.getIncrement(), g2d, Color.BLACK, this);
        
        
        
        yAxis.drawLabel(g2d, this);

    }

    public XYChart(ArrayList<XYDataSeries> listOfSeries, YAxis yAxis, XAxis xAxis) {
        this(xAxis, yAxis);
        this.data.addAll(listOfSeries);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        this.prePaint(g2d);


        xAxis.drawTicksAndLabels(g2d, this);


        //drawing TICK and then LABEL
//        xAxis.drawIntervals(g, this);
//        xAxis.drawAllIntervalLabels(g, this);

        xAxis.drawLabel(g, this);

        xAxis.drawBorderLine(g2d, this);
        yAxis.drawBorderLine(g2d, this);

        drawGraph(g2d);

        drawLegend(g2d);
    }

    @Override
    protected void drawGraph(Graphics g) {
        LineRenderer.drawLinesOrPoints((Graphics2D) g, this, yAxis, xAxis, data);
    }

    @Override
    public void drawLegend(Graphics2D g) {

        ArrayList<Category> categories = new ArrayList<Category>();

        if (data.size() == 1) {
            return;
        }

        for (XYDataSeries series : data) {

            Category category;

            if (series.type == XYDataSeriesType.BUBBLE) {
                category = new Category(series.name, series.seriesColor);
            } else {
                category = new Category(series.name, series.pointType, series.line);
            }
            categories.add(category);
        }
        super.drawLegend(g, categories);
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		Point point = e.getPoint();
//		System.out.println("point = " + point);
		
		for (XYDataSeries xyDataSeries : data) {
			ArrayList al  = xyDataSeries.dataPoints;
			
			for (Object object : al) {
				DataPoint dp = (DataPoint) object;
				
				UIPointXY uip = dp.uiPointXY;
				
				boolean b = uip.doesShapeContainPoint(point);
				
				
				if (b)
					System.out.println("CONTAINS POINT!!");
			}
		}
		
		this.updateUI();
	}
	
	
	/**
	 * Inner line just inside of the axis line. Potentially optional??
	 * 
	 */
	protected void drawBottomLine(Graphics2D g) {

		if (chartBorderLine == null) {
			chartBorderLine = new BasicStroke(1, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, new float[] { 2, 0 }, // no dash
					0.0f);
		}
		
		g.setStroke(chartBorderLine);

		g.setColor(borderLineColor);

		g.drawLine(leftOffset, heightChart + topOffset,
				leftOffset + widthChart, heightChart + topOffset);
	}

	/**
	 * Inner line just inside of the axis line. Potentially optional?? Eg colored area instead?
	 * 
	 */
	protected void drawLeftLine(Graphics2D g) {

		g.setStroke(chartBorderLine);

		g.setColor(borderLineColor);

		g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);
	}

	protected void drawRightLine(Graphics2D g) {

		g.setStroke(chartBorderLine);

		g.setColor(borderLineColor);

		g.drawLine(leftOffset + widthChart, topOffset, leftOffset + widthChart,
				heightChart + topOffset);
	}

}
