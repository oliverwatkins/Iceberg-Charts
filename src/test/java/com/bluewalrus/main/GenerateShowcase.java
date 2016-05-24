package com.bluewalrus.main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.main.test.TestDataXY_Boxplot;
import com.bluewalrus.main.test.TestDataXY_CandlePlot;

public class GenerateShowcase {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";
	
	
    public static void main(String[] args) throws Exception {
    	
    	final ArrayList<JComponent> charts = new ArrayList<JComponent>();

    	
        XYChart chart = new TestDataXY_Boxplot().getChart();
        charts.add(chart);
        new TestDataXY_Boxplot().generateCodeSnippetFile();
        
        
        
        XYChart chart2_ = new TestDataXY_CandlePlot().getChart();
        charts.add(chart2_);
        
		int i = 0;
		for (JComponent chart2 : charts) {
			
			int width = 0;
			int height = 0;
			
			if (chart2 instanceof Chart) {
				width = ((Chart)chart2).getWidth(); 
				height = ((Chart)chart2).getHeight();
			}else {
				width = chart2.getWidth(); 
				height = chart2.getHeight();
			}
			
			BufferedImage image = new BufferedImage(width, height, 
					BufferedImage.TYPE_INT_ARGB);

			Graphics g2 = image.getGraphics();
			chart2.paint(g2);
			
			String fileName = getFileFriendlyString(chart, i);

			try {
				ImageIO.write(image, "PNG", new File(path + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			System.out.println("saving ");
			i++;
		}
    }

	private static String getFileFriendlyString(XYChart chart, int i) {
		
		String ss = chart.title.getTitle().replace(" ", "-");
		
		String s = "showcase-chart-image-" + i + "-" + ss + ".png";
		return s;
	}
    
}
