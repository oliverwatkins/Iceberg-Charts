package com.bluewalrus.main.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.main.GenerateShowcase;

public abstract class ChartTester extends JFrame{
	
	public abstract JPanel getChart() throws ParseException;
	
	
	public void testChart(JPanel chart) throws ParseException {

		JTabbedPane tabbedPaneBar = new JTabbedPane();

		tabbedPaneBar.addTab("1", chart);

		getContentPane().add(tabbedPaneBar);

		setSize(700, 700);
		setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String generateCodeSnippetFile() throws IOException {

		String className = this.getClass().getName();

		String lastWord = className.substring(className.lastIndexOf(".") + 1);

		System.out.println("className " + lastWord);

		FileSearch fSearch = new FileSearch();
		fSearch.searchDirectory(new File("."), lastWord + ".java");
		
		if (fSearch.getResult().size() != 1) {
			throw new RuntimeException("There should be exactly one file for " + lastWord + ".java and not " + fSearch.getResult().size());
		}
		
		StringBuilder sbCodeSnippet = new StringBuilder();
		
		
		BufferedReader br = new BufferedReader(new FileReader(fSearch.getResult().get(0)));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    boolean snippet = false;
		    
		    while (line != null) {
		    	
		    	if (snippet || (line.contains("public XYChart getChart()") || 
		    			line.contains("public Chart getChart()"))) {
		    		snippet = true;
		    		sbCodeSnippet.append(line);
		    		sbCodeSnippet.append("\n");
		    		
		    	}
		    	if (line.contains("return chart;")) {
		    		snippet = false;
		    	}
		    	
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    System.out.println("sbCodeSnippet " + sbCodeSnippet);
		} finally {
		    br.close();
		}
		
		
		if (sbCodeSnippet.length() < 10)
			throw new RuntimeException("Code snipped must be more than 10 chars " + sbCodeSnippet);
		
		writeFile(sbCodeSnippet, lastWord + ".txt");

		return null;
	}

	

	public void createImageAndTextFile() throws IOException, ParseException {
		
        XYChart chart = (XYChart)this.getChart();
        this.generateCodeSnippetFile();
        this.appendFileNameToChart(chart);

        this.writeChart(chart, GenerateShowcase.path);
	}

	private void writeFile(StringBuilder sbCodeSnippet, String fileName){
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

	
	public String getClassName() {
		String className = this.getClass().getName();

		String lastWord = className.substring(className.lastIndexOf(".") + 1);

		return lastWord;
	}

	public void appendFileNameToChart(XYChart chart) {
        String s = this.getClassName();
        chart.fileLocation = s;
	}

	public void writeChart(Chart chart, String path) {
		int width = chart.getWidth(); 
		int height = chart.getHeight();
		if (chart.getWidth() == 0) {
			width = 300;
		}
		if (chart.getHeight() == 0) {
			height = 300;
		}
		
		BufferedImage image = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_ARGB);

		Graphics g2 = image.getGraphics();
		chart.paint(g2);
		
//		String fileName = getFileFriendlyString(chart, i);

		try {
			ImageIO.write(image, "PNG", new File(path + chart.fileLocation + ".PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("saving ");
	}
	

	

}
