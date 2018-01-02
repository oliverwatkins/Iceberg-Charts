//package com.frontangle.ichart.chart;
//
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//public class FileUtils {
//
//	
////	public static String writeChart(Chart chart, String path, Dimension dimension) {
////		
////		String url = "";
////		
////		int width = chart.getWidth(); 
////		int height = chart.getHeight();
////
////		if (dimension == null) {
////			chart.setSize(defaultDimension);
////			chart.setMinimumSize(defaultDimension);
////			chart.setPreferredSize(defaultDimension);
////		}
////		
////		BufferedImage image = new BufferedImage(chart.getWidth(), chart.getHeight(), 
////				BufferedImage.TYPE_INT_ARGB);
////
////		
////		Graphics g2 = image.getGraphics();
////		chart.paint(g2);
////
////		try {
////			ImageIO.write(image, "PNG", new File(path + chart.fileLocation + ".PNG"));
////			
////			url = path + chart.fileLocation + ".PNG";
////			
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	
////		System.out.println("saving ");
////		return url;
////	}
//	
//}
