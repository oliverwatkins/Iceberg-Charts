/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.bar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.point.SimpleXYPoint;
import com.bluewalrus.point.XYPoint;

/**
 * LegendUI
 * 
 * @author lauren
 */
public class Legend implements Serializable{

    //padding between the chart and the legend
    int paddingLegendLeft = 10;
    //padding between the chart and the legend
    int paddingLegendRight = 10;
    //padding square and text
    int paddingBetweenLegendSquareAndText = 10;
    //padding for the display square that shows symbol/point
    int paddingAroundSquare = 3;
    //category width (also its height)
    int squareWidth = 30;

    Color legendBackgroundColor = new Color(243, 239, 239);

    Chart chart;

    public Font legendFont = new Font("Arial", Font.PLAIN, 10);

    private ArrayList<Category> categories = new ArrayList<Category>();

    public Legend(Font legendFont, Chart chart) {
        this.chart = chart;
        this.legendFont = legendFont;
    }

    public Legend(Font legendFont, Chart chart, int paddingLeft) {
        this.chart = chart;
        this.legendFont = legendFont;
        this.paddingLegendLeft = paddingLeft;
    }

    public void drawLegend(Graphics2D g, Chart chart, ArrayList<Category> data) {

        int legendX = (chart.width - chart.rightOffset) + paddingLegendLeft;
        int legendY = chart.topOffset; // + paddingBetweenChartAndLegend;

        int legendHeight = (data.size() * squareWidth); // - (2 * paddingBetweenChartAndLegend);
        int legendWidth = chart.rightOffset - (paddingLegendLeft);

        FontMetrics fmT = chart.getFontMetrics(legendFont);

        //draw outside rectangle
        g.setColor(legendBackgroundColor);

        g.fillRect(legendX, legendY, legendWidth - paddingLegendRight, legendHeight);
        //draw outside rectangle
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(legendX, legendY, legendWidth - paddingLegendRight, legendHeight);

        int i = 0;

        for (Category category : data) {

            if (category.block == true) {

                drawBlock(g, legendX, legendY, i, category);

            } else {
                Line l = category.line;

                if (l != null) {
                    drawLine(g, legendX, legendY, i, l);
                }

                XYPoint point = category.point;

                if (point != null) {
                    drawPoint(g, legendX, legendY, i, point);
                }
            }
            drawText((Graphics2D) g, legendX, legendY, fmT, i, category);

            i++;
        }
    }

    /**
     * 
     * @param g
     * @param legendX
     * @param legendY
     * @param fmT
     * @param i
     * @param category 
     */
    private void drawText(Graphics2D g, int legendX, int legendY,
            FontMetrics fmT, int i, Category category) {

        g.setFont(legendFont);

        int legendStringWidth = fmT.stringWidth(category.name);
        int legendStringHeight = fmT.getHeight();

        int textFactor = (squareWidth - legendStringHeight) / 2;

        int yPos = legendY + ((i + 1) * squareWidth) - textFactor;
        int xPos = legendX + squareWidth + paddingBetweenLegendSquareAndText;

        g.setColor(Color.BLACK);

        g.drawString(category.name, xPos, yPos);
    }

    private void drawBlock(Graphics g, int legendX, int legendY, int i, Category category) {
        g.setColor(category.color);

        int x = legendX + paddingAroundSquare;
        int y = legendY + (i * squareWidth) + paddingAroundSquare;
        int width = squareWidth - 2 * paddingAroundSquare;

        g.fillRect(x, y, width, width);
    }

    private void drawPoint(Graphics2D g, int legendX, int legendY, int i, XYPoint point) {
        g.setColor(point.color);
        int xPos = legendX + squareWidth / 2;
        int yPos = legendY + squareWidth / 2 + (i * squareWidth);

        Point p = new Point(xPos, yPos);

        if (point instanceof SimpleXYPoint) {
            point.draw(g, p, null, null);
        }

    }

    private void drawLine(Graphics2D g, int legendX, int legendY, int i, Line l) {

        int y1 = legendY + (i * squareWidth) + squareWidth / 2;
        int y2 = y1; //same

        int x1 = legendX + paddingAroundSquare;
        int x2 = legendX + squareWidth - (paddingAroundSquare);

        l.drawLine(g, x1, y1, x2, y2);
    }

    public Shape getChartBounds() {

        int legendX = (chart.width - chart.rightOffset) + paddingLegendLeft;
        int legendY = chart.topOffset; // + paddingBetweenChartAndLegend;

        int legendHeight = (categories.size() * squareWidth); // - (2 * paddingBetweenChartAndLegend);
        int legendWidth = chart.rightOffset - (paddingLegendLeft);

        return new Rectangle(legendX,
                legendY,
                legendWidth,
                legendHeight);
    }
}
