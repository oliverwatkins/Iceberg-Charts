package com.bluewalrus.main.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		    	
		    	if (snippet || line.contains("public XYChart getChart()")) {
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
//		    String everything = sb.toString();
		    System.out.println("sbCodeSnippet " + sbCodeSnippet);
		} finally {
		    br.close();
		}
		
		writeFile(sbCodeSnippet, lastWord + ".txt");

		return null;
	}


	private void writeFile(StringBuilder sbCodeSnippet, String fileName){
		BufferedWriter writer = null;
		try {
			String s = GenerateShowcase.path + fileName;
			
			System.out.println("create new file " + s);
			
			
			File file = new File(s);
			file.createNewFile();
			
			System.out.println("file.getCanonicalFile " + file.getCanonicalFile());
			
			writer = new BufferedWriter(new FileWriter(file)); //GenerateShowcase.path + fileName));
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
