package com.bluewalrus.main;

import java.util.concurrent.Future;

import com.bluewalrus.main.test.FileUtils;
import com.bluewalrus.main.test.bar.TestDataBar_1_Simple;
import com.bluewalrus.main.test.bar.TestDataBar_4_GradientColor;
import com.bluewalrus.main.test.bar.TestDataBar_5_PosNegColor;
import com.bluewalrus.main.test.bubble.TestDataBubble_1_guns;
import com.bluewalrus.main.test.bubble.TestDataBubble_2_series;
import com.bluewalrus.main.test.bubble.TestDataPieBubble;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_1_gridSimple;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_2_GraphPaper;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_3_alternateGridFillX;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_4_alternateGridFillY;
import com.bluewalrus.main.test.gridlinefill.TestDataGrids_5_Gradiant;
import com.bluewalrus.main.test.math.TestDataXY_Math;
import com.bluewalrus.main.test.math.TestDataXY_Polynomals;
import com.bluewalrus.main.test.math.TestDataXY_SineCurve;
import com.bluewalrus.main.test.multibar.TestDataBar_MultiBar_SideBySide;
import com.bluewalrus.main.test.multibar.TestDataBar_MultiBar_Stacked;
import com.bluewalrus.main.test.pie.TestDataPie_IndicatorMany;
import com.bluewalrus.main.test.pie.TestDataPie_IndicatorSimple;
import com.bluewalrus.main.test.pie.TestDataPie_Multi;
import com.bluewalrus.main.test.pie.TestDataPie_SimplePie;
import com.bluewalrus.main.test.stacked.TestStackedChart2;
import com.bluewalrus.main.test.stacked.TestStackedChart3;
import com.bluewalrus.main.test.timeseries.TestDataGrids_6_alternateGridFillXTimesSeries;
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
    	
    	//XY
    	new TestDataXY_Simple().createImageAndTextFile(sBuilder);
    	new TestDataXY_Scatter().createImageAndTextFile(sBuilder);
    	new TestDataXY_Simple_Series().createImageAndTextFile(sBuilder);
    	new TestDataXY_LineExamples().createImageAndTextFile(sBuilder);
    	new TestDataXY_CandlePlot().createImageAndTextFile(sBuilder);
    	new TestDataXY_Boxplot().createImageAndTextFile(sBuilder);
    	new TestDataBar_FontFun().createImageAndTextFile(sBuilder);
    	
    	//Grid
    	new TestDataGrids_1_gridSimple().createImageAndTextFile(sBuilder);
    	new TestDataGrids_2_GraphPaper().createImageAndTextFile(sBuilder);
    	new TestDataGrids_3_alternateGridFillX().createImageAndTextFile(sBuilder);
    	new TestDataGrids_4_alternateGridFillY().createImageAndTextFile(sBuilder);
    	new TestDataGrids_5_Gradiant().createImageAndTextFile(sBuilder);
    	
    	//BAR
    	new TestDataBar_1_Simple().createImageAndTextFile(sBuilder);
    	new TestDataBar_4_GradientColor().createImageAndTextFile(sBuilder);
    	new TestDataBar_5_PosNegColor().createImageAndTextFile(sBuilder);
    	
    	//STACKED
    	new TestDataXY_Math().createImageAndTextFile(sBuilder);
    	new TestDataXY_SineCurve().createImageAndTextFile(sBuilder);
    	new TestDataXY_Polynomals().createImageAndTextFile(sBuilder);
    	
    	//STACKED
    	new TestStackedChart2().createImageAndTextFile(sBuilder);
    	new TestStackedChart3().createImageAndTextFile(sBuilder);
    	
    	//MUTI BAR
    	new TestDataBar_MultiBar_SideBySide().createImageAndTextFile(sBuilder);
    	new TestDataBar_MultiBar_Stacked().createImageAndTextFile(sBuilder);
    	
    	//BUBBLE
    	new TestDataBubble_1_guns().createImageAndTextFile(sBuilder);
    	new TestDataBubble_2_series().createImageAndTextFile(sBuilder); //error!
//    	new TestDataPieBubble().createImageAndTextFile();
    	
    	//PIE
    	new TestDataPie_SimplePie().createImageAndTextFile(sBuilder);
    	new TestDataPie_Multi().createImageAndTextFile(sBuilder);
//    	new TestDataPie_IndicatorMany().createImageAndTextFile(); //TODO cannot do this because it is a JPanel
    	new TestDataPie_IndicatorSimple().createImageAndTextFile(sBuilder);
    	
    	//XYY
    	new TestDataBar_2Y().createImageAndTextFile(sBuilder);

    	sBuilder.append("}]}");

    	FileUtils.writeFile(sBuilder, GenerateShowcase.path, "samples.json");
    	
    	
    	System.out.println("Completed Image Code Genereration !!!!");

    }

}
