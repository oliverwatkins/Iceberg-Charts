package com.frontangle.ichart.main.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.main.GenerateShowcase;
import com.frontangle.ichart.main.test.bar.TestDataBar_1_Simple;

public abstract class ChartTester extends JFrame{
	
	public abstract Chart getChart() throws ParseException;
	
	public abstract String getNiceTitle();

	Dimension defaultDimension = new Dimension(1000, 700);

	private Chart chart;
	
	public JPanel getChartPanel() throws ParseException {
		this.chart = this.getChart();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(chart);
		JPanel buttonPanel = new JPanel();
		
		JButton button = new JButton("Export PDF");
		
		buttonPanel.add(button);
		button.addActionListener(e -> exportImage());
		
		panel.add(buttonPanel, BorderLayout.SOUTH);
		return panel;
	}
	
	
	public void exportImage()  {
		int i = 0;
		
		Chart chart = this.chart;
			
		int width = 0;
		int height = 0;
		
		width = chart.getWidth(); 
		height = chart.getHeight();
		
		BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_ARGB);

		Graphics g2 = image.getGraphics();
		chart.paint(g2);

		try {
			ImageIO.write(image, "PNG", new File("src\\main\\resources\\chart-image-" + i + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("saving ");
		i++;
	}

	
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

        String urlChart = FileUtils.writeChartAndGetURL(chart, GenerateShowcase.path, this.defaultDimension);
        
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
	
	
	public void testSnapshot() throws IOException, ParseException {

		Class c = this.getClass();
		String simpleClassName = c.getSimpleName();

		System.out.println("c " + c.getName());
		System.out.println("c " + c.getSimpleName());

		String packageBit = c.getPackage().getName();

		String relPath = null;
		if (packageBit != null) {

			String t = packageBit.replace(".", "\\");
			System.out.println("t  " + t);

			relPath = "src\\test\\java\\" + packageBit.replace(".", "\\");
		}

		System.out.println("relPath " + relPath);

		Dimension dimension = new Dimension(560, 560);

		String iFile = relPath + "\\img\\" + simpleClassName + ".PNG";

		File imageFile = new File(iFile);

		Chart chart = (Chart)this.getChart();
		
		chart.topOffset = 5;
		chart.bottomOffset = 5;

		if (imageFile.exists()) {

			System.out.println("imageFile exists!!");

			BufferedImage imageInMemory = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);

			Graphics g2 = imageInMemory.getGraphics();
			
			chart.paint(g2);

			String path = relPath + "\\img\\" + simpleClassName + "_TEST_TO_COMPARE.PNG";

			FileUtils.writeChart((Chart)this.getChart(), path, dimension);
			BufferedImage imageFromFileSystemToCOmpare = ImageIO.read(new File(path));
			
			BufferedImage imageFromFileSystem = ImageIO.read(new File(iFile));
			
			if (TestDataBar_1_Simple.compareImages(imageFromFileSystemToCOmpare, imageFromFileSystem)) {
				System.out.println("equal!!");
			} else {
				throw new RuntimeException("not equal!! Please check TEST file");
			}
		} else {
			System.out.println("imageFile does not exist!! So creating");
			FileUtils.writeChart((Chart)this.getChart(), imageFile.getPath(), dimension);
		}
	}
	
	public String getClassName() {
		String className = this.getClass().getName();

		String lastWord = className.substring(className.lastIndexOf(".") + 1);

		return lastWord;
	}

	public void appendFileNameToChart(Chart chart) {
        String s = this.getClassName();
        chart.fileLocation = s;
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
