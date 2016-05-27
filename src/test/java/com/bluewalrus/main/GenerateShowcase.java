package com.bluewalrus.main;

import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_4_GradientColor;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;
import com.bluewalrus.main.test.stacked.TestStackedChart2;
import com.bluewalrus.main.test.stacked.TestStackedChart3;

public class GenerateShowcase {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";
	
	
    public static void main(String[] args) throws Exception {
    	
    	
//    	new TestDataXY_Boxplot().createImageAndTextFile();
    	new TestDataBar_1_Simple().createImageAndTextFile();
    	new TestDataBar_4_GradientColor().createImageAndTextFile();
    	new TestDataBar_5_PosNegColor().createImageAndTextFile();
//    	new TestDataBar_.createImageAndTextFile();
//    	new TestDataBar_1_Simple().createImageAndTextFile();

    	
    	//these two stacked
    	new TestStackedChart2().createImageAndTextFile();
    	new TestStackedChart3().createImageAndTextFile();

    	

    }

}
