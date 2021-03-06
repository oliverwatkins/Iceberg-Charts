package com.frontangle.ichart.main;

import com.frontangle.ichart.main.test.FileUtils;
import com.frontangle.ichart.main.test.area.TestDataArea_2_Multiple_Layered;
import com.frontangle.ichart.main.test.area.TestDataArea_4_Multiple_Stacked;
import com.frontangle.ichart.main.test.bar.TestDataBar_1_Simple;
import com.frontangle.ichart.main.test.bar.TestDataBar_4_GradientColor;
import com.frontangle.ichart.main.test.bar.TestDataBar_5_PosNegColor;
import com.frontangle.ichart.main.test.bar.TestDataBar_6_Transparancy;
import com.frontangle.ichart.main.test.bubble.TestDataBubble_1_guns;
import com.frontangle.ichart.main.test.bubble.TestDataBubble_2_series;
import com.frontangle.ichart.main.test.bubble.TestDataPieBubble;
import com.frontangle.ichart.main.test.gridlinefill.TestDataGrids_3_alternateGridFillX;
import com.frontangle.ichart.main.test.gridlinefill.TestDataGrids_4_alternateGridFillY;
import com.frontangle.ichart.main.test.gridlinefill.TestDataGrids_5_Gradiant;
import com.frontangle.ichart.main.test.logarithmic.TestDataLog_1;
import com.frontangle.ichart.main.test.math.TestDataXY_Math;
import com.frontangle.ichart.main.test.math.TestDataXY_Polynomals;
import com.frontangle.ichart.main.test.math.TestDataXY_SineCurve;
import com.frontangle.ichart.main.test.multibar.TestDataBar_MultiBar_SideBySide;
import com.frontangle.ichart.main.test.multibar.TestDataBar_MultiBar_Stacked;
import com.frontangle.ichart.main.test.pie.TestDataPie_Multi;
import com.frontangle.ichart.main.test.pie.TestDataPie_SimplePie;
import com.frontangle.ichart.main.test.stacked.TestStackedChart2;
import com.frontangle.ichart.main.test.stacked.TestStackedChart3;
import com.frontangle.ichart.main.test.xy.TestDataBar_FontFun;
import com.frontangle.ichart.main.test.xy.TestDataXY_Boxplot;
import com.frontangle.ichart.main.test.xy.TestDataXY_CandlePlot;
import com.frontangle.ichart.main.test.xy.TestDataXY_LineExamples;
import com.frontangle.ichart.main.test.xy.TestDataXY_Scatter;
import com.frontangle.ichart.main.test.xy.TestDataXY_Simple;
import com.frontangle.ichart.main.test.xy.TestDataXY_Simple_Series;
import com.frontangle.ichart.main.test.xyy.TestDataBar_2Y;

/**
 * Generates code snippets and images into one big html file
 * 
 * @author Oliver Watkins
 *
 */
public class GenerateShowcase {

	public static String path = "target\\htmlGenerated\\";

    public static void main(String[] args) throws Exception {
    	
    	StringBuilder sBuilder = new StringBuilder();
    	
    	sBuilder.append("{");
    	sBuilder.append(" \"samples\": [{");
    	
    	StringBuilder sBuilder2 = new StringBuilder();
    	StringBuilder sBuilder3 = new StringBuilder();
    	
    	sBuilder2.append("<div>");
    	
    	sBuilder3.append("");
    	
    	//XY
    	new TestDataXY_Simple().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_Scatter().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_Simple_Series().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_LineExamples().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_CandlePlot().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_Boxplot().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBar_FontFun().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataArea_2_Multiple_Layered().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataArea_4_Multiple_Stacked().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//Grid
//    	new TestDataGrids_1_gridSimple().createImageAndTextFile(sBuilder, sBuilder2);
//    	new TestDataGrids_2_GraphPaper().createImageAndTextFile(sBuilder, sBuilder2);
    	new TestDataGrids_3_alternateGridFillX().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataGrids_4_alternateGridFillY().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataGrids_5_Gradiant().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//BAR
    	new TestDataBar_1_Simple().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBar_4_GradientColor().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBar_5_PosNegColor().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBar_6_Transparancy().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//LOG
    	new TestDataLog_1().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//Pie bubble
    	new TestDataPieBubble().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//STACKED
    	new TestDataXY_Math().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_SineCurve().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataXY_Polynomals().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//STACKED
    	new TestStackedChart2().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestStackedChart3().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//MUTI BAR
    	new TestDataBar_MultiBar_SideBySide().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBar_MultiBar_Stacked().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	
    	//BUBBLE
    	new TestDataBubble_1_guns().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataBubble_2_series().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3); //error!
//    	new TestDataPieBubble().createImageAndTextFile();
    	
    	//PIE
    	new TestDataPie_SimplePie().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
    	new TestDataPie_Multi().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);
//    	new TestDataPie_IndicatorMany().createImageAndTextFile(); //TODO cannot do this because it is a JPanel
//    	new TestDataPie_IndicatorSimple().createImageAndTextFile(sBuilder, sBuilder2);
    	
    	//XYY
    	new TestDataBar_2Y().createImageAndTextFile(sBuilder, sBuilder2, sBuilder3);

    	sBuilder.append("}]}");
    	
    	sBuilder2.append("</div>");

    	FileUtils.writeFile(sBuilder2, GenerateShowcase.path, "screenshots.html");
    	FileUtils.writeFile(sBuilder, GenerateShowcase.path, "samples.json");
    	FileUtils.writeFile(sBuilder3, GenerateShowcase.path, "nodeImport.txt");
    	
    	
    	System.out.println("Completed Image Code Genereration !!!!");
    }
}
