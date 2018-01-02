package com.frontangle.ichart.main.test.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.bar.XYBarDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.FileUtils;
import com.frontangle.ichart.main.test.Showcase;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataBar_1_Simple extends ChartTester {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";

	@Showcase
	public Chart getChart() {

		XYBarDataSeries barSeries = new XYBarDataSeries();
		barSeries.add(new DataPointBar("Apple", 98, Color.ORANGE));
		barSeries.add(new DataPointBar("Banana", 44, Color.GRAY));
		barSeries.add(new DataPointBar("Barley", 40, Color.DARK_GRAY));
		barSeries.add(new DataPointBar("Rice", 3, Color.BLUE));
		barSeries.add(new DataPointBar("Wheat", 50, Color.RED));
		barSeries.add(new DataPointBar("Oranges", 30, Color.BLACK));
		barSeries.add(new DataPointBar("Corn", 54, Color.CYAN));

		XYChart chart = new XYChart("Simple Bar Chart", "Commodity", "Price (USD)", barSeries);

//		chart.topOffset = 5;
//		chart.bottomOffset = 5;
		
		return chart;
	}

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

	/**
	 * Look at numerial values
	 * @param img
	 */
	
	public static void  lookAtByteArray(BufferedImage img) {
	    
		int width = img.getWidth();
	    int height = img.getHeight();
	    
	    StringBuffer db = new StringBuffer();
		
	    for (int y = 0; y < 50; y++) {
		      for (int x = 0; x < 50; x++) {
		    	  db.append(img.getRGB(x, y));
		      }
	    }
	    System.out.println(" sb " + db);
	}
	
	
	/**
	 * Compares two images pixel by pixel.
	 *
	 * @param imgA the first image.
	 * @param imgB the second image.
	 * @return whether the images are both the same or not.
	 */
	public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
	  // The images must be the same size.
	  if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
	    int width = imgA.getWidth();
	    int height = imgA.getHeight();

	    // Loop over every pixel.
	    for (int y = 0; y < height; y++) {
	      for (int x = 0; x < width; x++) {
	        // Compare the pixels for equality.
	        if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
	          return false;
	        }
	      }
	    }
	  } else {
	    return false;
	  }
	  return true;
	}
	
	

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_1_Simple();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Bar: Simple";
	}

}
