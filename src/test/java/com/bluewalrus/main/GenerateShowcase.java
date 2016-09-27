package com.bluewalrus.main;

import com.bluewalrus.main.test.FileUtils;
import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_4_GradientColor;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;
import com.bluewalrus.main.test.bar.TestDataBar_6_Transparancy;
import com.bluewalrus.main.test.bubble.TestDataBubble_1_guns;
import com.bluewalrus.main.test.bubble.TestDataBubble_2_series;
import com.bluewalrus.main.test.bubble.TestDataPieBubble;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_3_alternateGridFillX;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_4_alternateGridFillY;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_5_Gradiant;
import com.bluewalrus.main.test.logarithmic.TestDataLog_1;
import com.bluewalrus.main.test.math.TestDataXY_Math;
import com.bluewalrus.main.test.math.TestDataXY_Polynomals;
import com.bluewalrus.main.test.math.TestDataXY_SineCurve;
import com.bluewalrus.main.test.multibar.TestDataBar_MultiBar_SideBySide;
import com.bluewalrus.main.test.multibar.TestDataBar_MultiBar_Stacked;
import com.bluewalrus.main.test.pie.TestDataPie_Multi;
import com.bluewalrus.main.test.pie.TestDataPie_SimplePie;
import com.bluewalrus.main.test.stacked.TestStackedChart2;
import com.bluewalrus.main.test.stacked.TestStackedChart3;
import com.bluewalrus.main.test.xy.TestDataBar_FontFun;
import com.bluewalrus.main.test.xy.TestDataXY_Boxplot;
import com.bluewalrus.main.test.xy.TestDataXY_CandlePlot;
import com.bluewalrus.main.test.xy.TestDataXY_LineExamples;
import com.bluewalrus.main.test.xy.TestDataXY_Scatter;
import com.bluewalrus.main.test.xy.TestDataXY_Simple;
import com.bluewalrus.main.test.xy.TestDataXY_Simple_Series;
import com.bluewalrus.main.test.xyy.TestDataBar_2Y;

public class GenerateShowcase {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";
	

    public static void main(String[] args) throws Exception {
    	
    	StringBuilder sBuilder = new StringBuilder();
    	
    	sBuilder.append("{");
    	sBuilder.append(" \"samples\": [{");
    	
    	StringBuilder sBuilder2 = new StringBuilder();
    	
    	sBuilder2.append("<div>");
    	
    	//XY
    	new TestDataXY_Simple().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_Scatter().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_Simple_Series().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_LineExamples().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_CandlePlot().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_Boxplot().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBar_FontFun().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//Grid
//    	new TestDataGrids_1_gridSimple().createImageAndTextFile(sBuilder, sBuilder2);
//    	new TestDataGrids_2_GraphPaper().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataGrids_3_alternateGridFillX().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataGrids_4_alternateGridFillY().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataGrids_5_Gradiant().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//BAR
    	new TestDataBar_1_Simple().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBar_4_GradientColor().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBar_5_PosNegColor().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBar_6_Transparancy().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//LOG
    	new TestDataLog_1().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//Pie bubble
    	new TestDataPieBubble().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//STACKED
    	new TestDataXY_Math().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_SineCurve().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataXY_Polynomals().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//STACKED
    	new TestStackedChart2().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestStackedChart3().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//MUTI BAR
    	new TestDataBar_MultiBar_SideBySide().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBar_MultiBar_Stacked().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//BUBBLE
    	new TestDataBubble_1_guns().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataBubble_2_series().createImageAndTextFile(sBuilder, sBuilder2); //error!
//    	new TestDataPieBubble().createImageAndTextFile();
    	
    	//PIE
    	new TestDataPie_SimplePie().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataPie_Multi().createImageAndTextFile(sBuilder, sBuilder2);
//    	new TestDataPie_IndicatorMany().createImageAndTextFile(); //TODO cannot do this because it is a JPanel
//    	new TestDataPie_IndicatorSimple().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//XYY
    	new TestDataBar_2Y().createImageAndTextFile(sBuilder, sBuilder2);

    	sBuilder.append("}]}");
    	
    	sBuilder2.append("</div>");

    	FileUtils.writeFile(sBuilder2, GenerateShowcase.path, "screenshots.html");
    	FileUtils.writeFile(sBuilder, GenerateShowcase.path, "samples.json");
    	
    	System.out.println("Completed Image Code Genereration !!!!");
    }
}
