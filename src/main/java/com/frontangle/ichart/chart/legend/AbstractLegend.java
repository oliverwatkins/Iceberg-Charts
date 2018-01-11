package com.frontangle.ichart.chart.legend;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Category;
import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointSimpleXY;
import com.frontangle.ichart.chart.draw.point.UIPointXY;

public class AbstractLegend {

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

    //starting top left corner of the legend
    protected int legendX = -1;
    protected int legendY = -1;
    
    protected int legendHeight = -1;
    protected int legendWidth = -1;
    
	
    Color outsideRectangleColor = Color.LIGHT_GRAY;
	
    Color legendBackgroundColor = new Color(243, 239, 239);

    public Font legendFont = new Font("Arial", Font.PLAIN, 10);

    protected ArrayList<Category> categories = new ArrayList<Category>();
    
    Chart chart;
    
    public AbstractLegend(Font legendFont, Chart chart) {
        this.chart = chart;
        this.legendFont = legendFont;
    }
    
	protected void drawCategoryHorizontal(Graphics2D g, Chart chart, int i,
			Category category) {
		
		if (category.block == true) {

		    drawBlockHorizontal(g, i, category);

		} else {
			Line l = category.line;

		    if (l != null) {
		    	drawLineHorizontal(g, i, l);
		    }
		    UIPointXY point = category.point;

		    if (point != null) {
		        drawPointHorizontal(g, i, point);
		    }
		}
		FontMetrics fmT = chart.getFontMetrics(legendFont);
		drawTextHorizontal((Graphics2D) g, legendX, legendY, fmT, i, category);
		
	}
	
	protected int getStartingXPointHorizontal(Chart chart, ArrayList<Category> data) {
		int w = this.calculateWidthLegendHorizontal(data);

		//centered
		int cw = chart.getWidth() - chart.leftOffset - chart.rightOffset;
		int diff = cw - w;
		diff = diff/2;
		
		return chart.leftOffset + diff;
	}
	
	
	protected int calculateWidthLegendHorizontal(ArrayList<Category> data) {
		int s = (data.size() * squareWidth) + (data.size() * fixedTextLength);
		return s;
	}
	
	/**
	 *  Draw a category vertically ie top down.
	 *  
	 * @param g
	 * @param chart
	 * @param legendX
	 * @param legendY
	 * @param i
	 * @param category
	 */
	private void drawCategoryVertical(Graphics2D g, Chart chart, int i, Category category) {
		
		if (category.block == true) {

		    drawBlockVertical(g, i, category);

		} else {
			Line l = category.line;

		    if (l != null) {
		        drawLineVertical(g, i, l);
		    }
		    UIPointXY point = category.point;

		    if (point != null) {
		        drawPointVertical(g, i, point);
		    }
		}
		FontMetrics fmT = chart.getFontMetrics(legendFont);
		drawTextVertical((Graphics2D) g, legendX, legendY, fmT, i, category);
	}
	


	
    int fixedTextLength = 60;

	/**
     * 
     * @param g
     * @param legendX
     * @param legendY
     * @param fmT
     * @param i
     * @param category 
     */
    private void drawTextVertical(Graphics2D g, int legendX, int legendY,
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
    
	private void drawTextHorizontal(Graphics2D g, int legendX, int legendY,
			FontMetrics fmT, int i, Category category) {
		
        g.setFont(legendFont);

        int legendStringWidth = fmT.stringWidth(category.name);
        int legendStringHeight = fmT.getHeight();

        //half of the difference in height
        int textFactor = (squareWidth - legendStringHeight) / 2;

        int yPos = legendY +   textFactor + legendStringHeight;
        int xPos = legendX + (i * (squareWidth + fixedTextLength))  +squareWidth + paddingBetweenLegendSquareAndText;

        g.setColor(Color.BLACK);

        g.drawString(category.name, xPos, yPos);
		
	}

    
    private void drawBlockVertical(Graphics g, int i, Category category) {
        g.setColor(category.color);

        int x = legendX + paddingAroundSquare;
        int y = legendY + (i * squareWidth) + paddingAroundSquare;
        int width = squareWidth - 2 * paddingAroundSquare;

        g.fillRect(x, y, width, width);
    }
    
    private void drawBlockHorizontal(Graphics2D g, int i, Category category) {
        g.setColor(category.color);

        int x = legendX + (i * squareWidth)  + paddingAroundSquare;
        int y = legendY + paddingAroundSquare;
        int width = squareWidth - 2 * paddingAroundSquare;

        g.fillRect(x, y, width, width);
	}
    
    

    private void drawPointVertical(Graphics2D g, int i, UIPointXY point) {
        g.setColor(point.color);
        int xPos = legendX + squareWidth / 2;
        int yPos = legendY + squareWidth / 2 + (i * squareWidth);

        Point p = new Point(xPos, yPos);

        if (point instanceof UIPointSimpleXY) {
            point.draw(g, p, null, null, null, 0);
        }

    }
    

    
	private void drawPointHorizontal(Graphics2D g, int i, UIPointXY point) {
        g.setColor(point.color);
        int xPos = legendX + squareWidth / 2 + (i * (squareWidth+ fixedTextLength));
        int yPos = legendY + squareWidth / 2;

        Point p = new Point(xPos, yPos);

        if (point instanceof UIPointSimpleXY) {
            point.draw(g, p, null, null, null, 0);
        }
		
	}
	

    private void drawLineHorizontal(Graphics2D g, int i, Line l) {

        int y1 = legendY + squareWidth / 2;
        int y2 = y1; //same

        int x1 = legendX + (i * (squareWidth+ fixedTextLength)) + paddingAroundSquare;
        int x2 = legendX + (i * (squareWidth+ fixedTextLength)) + squareWidth - (paddingAroundSquare);

        l.drawLine(g, x1, y1, x2, y2);
    }
    
    private void drawLineVertical(Graphics2D g, int i, Line l) {

        int y1 = legendY + (i * squareWidth) + squareWidth / 2;
        int y2 = y1; //same

        int x1 = legendX + paddingAroundSquare;
        int x2 = legendX + squareWidth - (paddingAroundSquare);

        l.drawLine(g, x1, y1, x2, y2);
    }
    


	
}
