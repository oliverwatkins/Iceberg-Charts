package com.frontangle.ichart.main.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.main.GenerateShowcase;

public class FileUtils {

	public static void writeChart(Chart chart, String path, Dimension dimension) {
		
		chart.setSize(dimension);
		chart.setMinimumSize(dimension);
		chart.setPreferredSize(dimension);
		
		BufferedImage image = new BufferedImage(dimension.width, dimension.height,  BufferedImage.TYPE_INT_RGB);

		System.out.println("path " + path);
		Graphics g2 = image.getGraphics();
		chart.paint(g2);

		try {
			ImageIO.write(image, "PNG", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("saving ");
	}
		
	
	public static String writeChartAndGetURL(Chart chart, String path, Dimension dimension) {
		
		String url = "";
		
		chart.setSize(dimension);
		chart.setMinimumSize(dimension);
		chart.setPreferredSize(dimension);
		
		BufferedImage image = new BufferedImage(chart.getWidth(), chart.getHeight(),  BufferedImage.TYPE_INT_ARGB);

		
		Graphics g2 = image.getGraphics();
		chart.paint(g2);

		try {
			ImageIO.write(image, "PNG", new File(path + chart.fileLocation + ".PNG"));
			
			url = path + chart.fileLocation + ".PNG";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("saving ");
		return url;
	}
	
	
	public static void writeFile(StringBuilder sbCodeSnippet, String path, String fileName){
		BufferedWriter writer = null;
		try {
			String s = GenerateShowcase.path + fileName;
			
			System.out.println("create new file " + s);
			
			
			File file = new File(s);
			file.createNewFile();
			
			System.out.println("file.getCanonicalFile " + file.getCanonicalFile());
			System.out.println("sbCodeSnippet length " + sbCodeSnippet.length());
			
			writer = new BufferedWriter(new FileWriter(file)); 
			writer.write(sbCodeSnippet.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
