package com.bluewalrus.main.test;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.main.GenerateShowcase;

public abstract class ChartTester extends JFrame{
	
	public abstract JPanel getChart() throws ParseException;
	
	public abstract String getNiceTitle();

	
	public void testChart(JPanel chart) throws ParseException {

		JTabbedPane tabbedPaneBar = new JTabbedPane();

		tabbedPaneBar.addTab("1", chart);

		getContentPane().add(tabbedPaneBar);

		setSize(700, 700);
		setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public void createImageAndTextFile(StringBuilder samplesJSON, StringBuilder screenshotHTML) throws IOException, ParseException {
		
        Chart chart = (Chart)this.getChart();
        String s = this.generateCodeSnippetFile();
        this.appendFileNameToChart(chart);
        
        //create HTML div

        String urlChart = this.writeChart(chart, GenerateShowcase.path, null);
        
        this.writeHTML(s, urlChart, chart);
        
        screenshotHTML.append("<img src='partials/" + chart.fileLocation + ".PNG' class='screenshot-image'/>");
        
        samplesJSON.append("\"title\": \"" + this.getNiceTitle() + "\",");
        samplesJSON.append("\"url\": \"" + "partials/" + chart.fileLocation +  ".html" + "\" ");
        
        samplesJSON.append("}, {");
	}
	
	
	private void writeHTML(String s, String urlChart, Chart chart) {
		StringBuilder sb = new StringBuilder();
		sb.append("<img src='partials/" + chart.fileLocation  + ".PNG' style='width:400px;float:right'>\n");
		sb.append("<div>\n");
		sb.append("<pre>\n");
		sb.append("<span class='code_snippet' style='font-size: 11px'>\n");
		sb.append(s);
		sb.append("</span>");
		sb.append("</pre>");
		sb.append("</div>");
		
		String lastWord = chart.fileLocation;
		
		FileUtils.writeFile(sb, GenerateShowcase.path, lastWord + ".html");
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
		
	    StringBuilder sb = new StringBuilder();
	    
		BufferedReader br = new BufferedReader(new FileReader(fSearch.getResult().get(0)));
		try {
		    String line = br.readLine();

		    boolean snippet = false;
		    
		    while (line != null) {

		    	if (line.contains("return chart;")) {
//		    		snippet = false;
		    		break;
		    	}
		    	
		    	if (snippet == true) {
			        sb.append(line.trim());
			        sb.append(System.lineSeparator());
		    	}
		    	
		    	if ( (line.contains("public XYChart getChart()") || 
		    			line.contains("public Chart getChart()"))) {
		    		snippet = true;
//		    		sbCodeSnippet.append("\n");
		    	}

		        line = br.readLine();
		        if (line != null) {
				    line = line.replace("<", "&lt;");
				    line = line.replace(">", "&gt;");
		        }
			    
		    }
		    System.out.println("sbCodeSnippet " + sb);
		} finally {
		    br.close();
		}
		
//		
//		if (sbCodeSnippet.length() < 10)
//			throw new RuntimeException("Code snipped must be more than 10 chars " + sbCodeSnippet);
//		
		FileUtils.writeFile(sbCodeSnippet, GenerateShowcase.path, lastWord + ".txt");

		return sb.toString();
	}

	


//	private void writeFile(StringBuilder sbCodeSnippet, String fileName){
//		BufferedWriter writer = null;
//		try {
//			String s = GenerateShowcase.path + fileName;
//			
//			System.out.println("create new file " + s);
//			
//			
//			File file = new File(s);
//			file.createNewFile();
//			
//			System.out.println("file.getCanonicalFile " + file.getCanonicalFile());
//			System.out.println("sbCodeSnippet length " + sbCodeSnippet.length());
//			
//			writer = new BufferedWriter(new FileWriter(file)); 
//			writer.write(sbCodeSnippet.toString());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}finally{
//			try {
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	
	public String getClassName() {
		String className = this.getClass().getName();

		String lastWord = className.substring(className.lastIndexOf(".") + 1);

		return lastWord;
	}

	public void appendFileNameToChart(Chart chart) {
        String s = this.getClassName();
        chart.fileLocation = s;
	}
	
	Dimension defaultDimension = new Dimension(1000, 700);

	public String writeChart(Chart chart, String path, Dimension dimension) {
		
		String url = "";
		
		int width = chart.getWidth(); 
		int height = chart.getHeight();

		if (dimension == null) {
			chart.setSize(defaultDimension);
			chart.setMinimumSize(defaultDimension);
			chart.setPreferredSize(defaultDimension);
		}
		
		BufferedImage image = new BufferedImage(chart.getWidth(), chart.getHeight(), 
				BufferedImage.TYPE_INT_ARGB);

		
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
	
	
	class Test extends JComponent {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1000, 1000);
		}
	}

}
