package com.frontangle.ichart.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.datapoint.ValueType;
import com.frontangle.ichart.pie.Segment;

/**
 * General utils methods
 *
 * @author Oliver Watkins
 */
public class Utils {

    public static void makeGradients(Color green, Color blue,
            ArrayList<Segment> children) {

        ArrayList<Color> al = makeGradients(green, blue, children.size());

        for (int i = 0; i < children.size(); i++) {
            children.get(i).color = al.get(i);
        }
    }
    
    
	/**
	 * Check if XYDataSeries are enumerable or not.
	 * 
	 * @param xySeriesList list of XYDataSeries
	 * @return is enumerable
	 */
	public static boolean isSeriesListEnumerable(ArrayList<XYDataSeries> xySeriesList) {
		XYDataSeries first = xySeriesList.get(0);
		
		DataPoint dp = (DataPoint)first.dataPoints.get(0);

		if (dp.valueType == ValueType.X_ENUMARABLE) {
			return true;
		}
		return false;
	}
	

    public static ArrayList<Color> makeGradients(Color color1, Color color2, int numberColors) {
        int red = color1.getRed();
        int blue = color1.getBlue();
        int green = color1.getGreen();

        int red2 = color2.getRed();
        int blue2 = color2.getBlue();
        int green2 = color2.getGreen();

        int incrementRed = (red2 - red) / numberColors;
        int incrementBlue = (blue2 - blue) / numberColors;
        int incrementGreen = (green2 - green) / numberColors;

        ArrayList<Color> colors = new ArrayList<Color>();
        for (int i = 0; i < numberColors; i++) {

            int newRed = red + (i * incrementRed);
            int newGreen = green + (i * incrementGreen);
            int newBlue = blue + (i * incrementBlue);
            Color c = new Color(newRed, newGreen, newBlue);
            colors.add(c);
        }

        return colors;
    }

    public static double getFactor(int chartLengthOrHeight, double maxValue,
            double minValue) {

        double factor = ((double) chartLengthOrHeight / (double) (maxValue - minValue));
        return factor;
    }

    public static void outlineText(Graphics2D g, String text, int x, int y) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        FontRenderContext frc = g2.getFontRenderContext();

        Font f = new Font("Helvetica", 1, 60);

        TextLayout textTl = new TextLayout(text, f, frc);

        AffineTransform transform = new AffineTransform();

        Shape outline = textTl.getOutline(null);

        Rectangle outlineBounds = outline.getBounds();

        transform = g2.getTransform();
        transform.translate(x, y);

        g2.transform(transform);
        g2.setColor(Color.blue);
        g2.draw(outline);
        g2.setClip(outline);

        transform.translate(-x, -y);

        g2.transform(transform);

    }

}
