package com.bluewalrus.main;

import java.util.ArrayList;

import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.main.test.TestDataXY_Boxplot;
import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_4_GradientColor;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;

public class GenerateShowcase {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";
	
	
    public static void main(String[] args) throws Exception {
    	
    	
//    	new TestDataXY_Boxplot().createImageAndTextFile();
    	new TestDataBar_1_Simple().createImageAndTextFile();
//    	new TestDataBar_4_GradientColor().createImageAndTextFile();
//    	new TestDataBar_5_PosNegColor().createImageAndTextFile();
//    	new TestDataBar_.createImageAndTextFile();
//    	new TestDataBar_1_Simple().createImageAndTextFile();

    }

}
