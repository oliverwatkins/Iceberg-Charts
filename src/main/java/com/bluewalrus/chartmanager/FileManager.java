package com.bluewalrus.chartmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.bluewalrus.chart.XYChart;

public class FileManager {

	String fileName = "appConfig.ser";

	File appConfigFile = new File(fileName);

	public XYChart open(ChartManagerApp frame) throws Exception {
		
		JFileChooser c = new JFileChooser();
		
		int rVal = c.showOpenDialog(frame);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			
			
			System.out.println("c.getSelectedFile().getName() "
					+ c.getSelectedFile().getName());
			System.out.println("c.getCurrentDirectory().toString() "
					+ c.getCurrentDirectory().toString());
			
			
			FileInputStream fs = new FileInputStream(new File(c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName()));
			ObjectInputStream is = new ObjectInputStream(fs);

			Object o = is.readObject();

			return (XYChart)o;
		}
		return null;
	}
	
	

	public void saveAs(XYChart chart, ChartManagerApp frame) throws Exception {
		JFileChooser c = new JFileChooser();
		// Demonstrate "Save" dialog:
		int rVal = c.showSaveDialog(frame);
		
		if (rVal == JFileChooser.APPROVE_OPTION) {
			
			System.out.println("c.getSelectedFile().getName() " + c.getSelectedFile().getName());
			System.out.println("c.getCurrentDirectory().toString() " + c.getCurrentDirectory().toString());

			String location = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();

			ChartFile cf = new ChartFile("blah", location);

			File file = new File(location);
			file.createNewFile();

			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(chart);
			out.close();
			fileOut.close();

			addToLatest(cf, frame);

			// filename.setText(c.getSelectedFile().getName());
			// dir.setText(c.getCurrentDirectory().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			// filename.setText("You pressed cancel");
			// dir.setText("");
		}
	}

	private void addToLatest(ChartFile cf, ChartManagerApp chartManagerApp)
			throws Exception {
		ArrayList<ChartFile> al = this.getLatestSavedCharts(chartManagerApp);

		al.add(cf);

		FileOutputStream fileOut = new FileOutputStream(appConfigFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(al);
		out.close();
		fileOut.close();

	}

	public ArrayList<ChartFile> getLatestSavedCharts(
			ChartManagerApp chartManagerApp) throws Exception {

		ArrayList<ChartFile> recentCharts = new ArrayList<ChartFile>();

		if (appConfigFile.exists()) {

			FileInputStream fs = new FileInputStream(appConfigFile);
			ObjectInputStream is = new ObjectInputStream(fs);

			Object o = is.readObject();

			System.out.println("o = " + o);

			recentCharts = (ArrayList<ChartFile>) o;

		} else {

			System.out.println(" " + appConfigFile.getAbsolutePath());

			appConfigFile.createNewFile();

			FileOutputStream fileOut = new FileOutputStream(appConfigFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recentCharts);
			out.close();
			fileOut.close();
		}
		return recentCharts;
	}

	public void save(XYChart chart) {

	}

}
