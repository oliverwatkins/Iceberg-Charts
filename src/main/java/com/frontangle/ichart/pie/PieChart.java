package com.frontangle.ichart.pie;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Category;
import com.frontangle.ichart.chart.legend.LegendVertical;
import com.frontangle.ichart.chart.legend.Legendable;

public class PieChart extends AbstractPieChart implements Legendable {

    private PieChartType type = PieChartType.MULTI_LEVEL; // the type of pie chart

    public int initialWidth = 200;
    public int incrementWidth = 80;

    private int depth;

    public PieChart(ArrayList<Segment> values, String title) {
        this(values, 200, 80, title);
    }

    public PieChart(ArrayList<Segment> values,
            int initialWidth,
            int incrementWidth, String title) {

//		if (type == PieChartType.SIMPLE_INDICATOR) {
//
//			// colours used for simple indicator
//			Color backgroundColor = Color.WHITE;
//			Color mainColor = Color.BLUE;
//
//			g2d.setColor(backgroundColor);
//			g2d.fillOval(0, 0, width, width);
//			g2d.setColor(mainColor);
//			Double angle = (percent / 100) * 360;
//			g2d.fillArc(0, 0, width, width, -270, -angle.intValue());
//		}
        this.initialWidth = initialWidth;
        this.incrementWidth = incrementWidth;

        this.depth = 1;//getDepth(values);
        this.initialSegments = values;
        this.setTitle(title);

        this.topOffset = 25;

        this.leftOffset = 15;

        this.rightOffset = 150;

        this.bottomOffset = 15;
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawGraphData(g);
    }

    @Override
    protected void drawGraphData(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        calculateHeighAndWidthOfChart();

        calculateCenterPoint();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

        this.circles = getCircleArray(depth);

        g2d.drawRect(0, 0, getWidth(), getHeight());
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawTitle(g2d);

        this.drawLegend(g2d);

        double startAngle = 0;

        /**
         * Draw the segments first
         */
        for (int i = 0; i < initialSegments.size(); i++) {

            Segment s = initialSegments.get(i);

            double angleOfThisSegment = (s.magnitude / 100) * 360;

            s.startAngle = startAngle;
            s.angle = angleOfThisSegment;

            paintSegment(g2d, i, s);

            startAngle += angleOfThisSegment;
        }
    }

    @Override
    public void drawLegend(Graphics2D g) {
        ArrayList<Category> categories = new ArrayList<Category>();

        
        legend = new LegendVertical(legendFont, this, new Point(100,100));

        for (Segment bar : initialSegments) {
            Category category = new Category(bar.name, bar.color);
            categories.add(category);
        }
        
		LegendVertical legend = new LegendVertical(this.legendFont, this, new Point(100,100));
		
		this.rightOffset = 200;
		
		legend.drawLegend(g, this, categories);
    }
}
