package com.frontangle.ichart.pie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class SimpleIndicatorPieChart extends AbstractPieChart {

    private PieChartType type = PieChartType.SIMPLE_INDICATOR; // the type of pie
    // chart

    private double percent;

    private ArrayList<Double> gradingValues = new ArrayList<Double>();
    private ArrayList<Color> gradingColors = new ArrayList<Color>();

    public SimpleIndicatorPieChart(int percent, ArrayList<Segment> segments) {

        type = PieChartType.GRADED_INDICATOR;
    }

    public SimpleIndicatorPieChart(int percent, String string) {

        type = PieChartType.GRADED_INDICATOR;

    }

    public SimpleIndicatorPieChart(int percent, String title,
            int width) {

        this(percent, width, title);

        type = PieChartType.SIMPLE_INDICATOR;
    }

    public SimpleIndicatorPieChart(int percent, ArrayList<Segment> segments, String title) {
        this(percent, 200, title);

        for (Segment segment : segments) {
            gradingColors.add(segment.color);
            gradingValues.add(segment.magnitude);
        }

        type = PieChartType.GRADED_INDICATOR;
    }

    public SimpleIndicatorPieChart(int percent, int width,
            String title) {

        this.percent = percent;

        this.setSize(width, this.getSize().height);

        this.topOffset = 0;
        this.leftOffset = 0;
        this.rightOffset = 0;
        this.bottomOffset = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawGraphData(g);
    }

    @Override
    protected void drawGraphData(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

//		g2d.drawRect(0, 0, width, height);
//		g2d.setColor(backgroundColor);
//		g2d.fillRect(0, 0, width, height);
        calculateHeighAndWidthOfChart();

        calculateCenterPoint();

        if (type == PieChartType.SIMPLE_INDICATOR) {
            // colours used for simple indicator
            Color backgroundColor = Color.WHITE;
            Color mainColor = Color.BLUE;

            g2d.setColor(backgroundColor);
            g2d.fillOval(0, 0, getWidth(), getWidth());
            g2d.setColor(mainColor);
            Double angle = (percent / 100) * 360;
            g2d.fillArc(0, 0, getWidth(), getWidth(), -270, -angle.intValue());
            
        } else if (type == PieChartType.GRADED_INDICATOR) {

            int lastPoint = -270;

            double gradingAccum = 0;

            for (int i = 0; i < gradingValues.size(); i++) {
                g2d.setColor(gradingColors.get(i));
                Double val = gradingValues.get(i);
                gradingAccum = gradingAccum + val;
                Double angle = null;
                /**
                 * * If the sum of the gradings is greater than the percent,
                 * then we want to recalculate * the last wedge, and break out
                 * of drawing.
                 */
                if (gradingAccum > percent) {

					// get the previous accumulated segments. Segments minus
                    // last one
                    double gradingAccumMinusOneSegment = gradingAccum - val;

                    // make an adjusted calculation of the last wedge
                    angle = ((percent - gradingAccumMinusOneSegment) / 100) * 360;

                    g2d.fillArc(0, 0, getWidth(), getWidth(), lastPoint,
                            -angle.intValue());

                    lastPoint = lastPoint + -angle.intValue();

                    break;

                } else {

                    angle = (val / 100) * 360;

                    g2d.fillArc(0, 0, getWidth(), getWidth(), lastPoint,
                            -angle.intValue());

                    lastPoint = lastPoint + -angle.intValue();
                }
            }
        }
    }
}
