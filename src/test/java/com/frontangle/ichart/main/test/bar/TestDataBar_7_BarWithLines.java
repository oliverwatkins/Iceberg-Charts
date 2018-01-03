package com.frontangle.ichart.main.test.bar;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.axis.NumericalInterval;
import com.frontangle.ichart.chart.axis.TimeInterval;
import com.frontangle.ichart.chart.axis.XAxis;
import com.frontangle.ichart.chart.axis.YAxis;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointBar;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataBar_7_BarWithLines  extends ChartTester {
	
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public void createSomeRandomData() {
		//but don't use it because it will break unit tests
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.YEAR, 2004);
		c.set(Calendar.MONTH, Calendar.FEBRUARY);
		
//		c.set(Calendar.MONTH, Calendar.JUNE);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		
        for (int i = 0; i < 100; i++) {
            double r = Math.random();

            
            
            c.add(Calendar.WEEK_OF_YEAR, 1);
//    		values.add(new DataPoint(df.parse("2001-03-01 00-33-00"), 5));
//    		values.add(new DataPoint(df.parse("2001-04-01 00-33-00"), 8));

            System.out.println("values.add(new DataPoint(df.parse(\"" + df.format(c.getTime()) + "\") , " + (2*i + i*i) * r + "));");

//            System.out.println("values.add(new DataPoint(df.parse(" + c.getTime() +"), 8));");
//            values.add(new DataPointBar("", (int) (100 * d), Color.GRAY));
        }
		
	}
	
	
	@Override
	public Chart getChart() throws ParseException {

//		createSomeRandomData();
		
		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		Date startDate = df.parse("2004-04-3 01-00-00"); 
		Date endDate = df.parse("2004-08-12 05-33-00");
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(df.parse("2004-02-09 05-58-43") , 0.0));
		values.add(new DataPoint(df.parse("2004-02-16 05-58-43") , 1.9191594427605434));
		values.add(new DataPoint(df.parse("2004-02-23 05-58-43") , 4.230117961412586));
		values.add(new DataPoint(df.parse("2004-03-01 05-58-43") , 13.971356317398515));
		values.add(new DataPoint(df.parse("2004-03-08 05-58-43") , 9.81931159653779));
		values.add(new DataPoint(df.parse("2004-03-15 05-58-43") , 13.140521238308658));
		values.add(new DataPoint(df.parse("2004-03-22 05-58-43") , 35.21824793609382));
		values.add(new DataPoint(df.parse("2004-03-29 05-58-43") , 35.29292490363721));
		values.add(new DataPoint(df.parse("2004-04-05 05-58-43") , 57.527533892261));
		values.add(new DataPoint(df.parse("2004-04-12 05-58-43") , 63.238960703140144));
		values.add(new DataPoint(df.parse("2004-04-19 05-58-43") , 100.27125557428218));
		values.add(new DataPoint(df.parse("2004-04-26 05-58-43") , 133.21102112423597));
		values.add(new DataPoint(df.parse("2004-05-03 05-58-43") , 50.39905171104289));
		values.add(new DataPoint(df.parse("2004-05-10 05-58-43") , 92.40743281402895));
		values.add(new DataPoint(df.parse("2004-05-17 05-58-43") , 74.11686407837203));
		values.add(new DataPoint(df.parse("2004-05-24 05-58-43") , 235.02407824527052));
		values.add(new DataPoint(df.parse("2004-05-31 05-58-43") , 252.09064049066635));
		values.add(new DataPoint(df.parse("2004-06-07 05-58-43") , 64.41992133652103));
		values.add(new DataPoint(df.parse("2004-06-14 05-58-43") , 340.85466696556375));
		values.add(new DataPoint(df.parse("2004-06-21 05-58-43") , 122.64340963966494));
		values.add(new DataPoint(df.parse("2004-06-28 05-58-43") , 335.2629193664546));
		values.add(new DataPoint(df.parse("2004-07-05 05-58-43") , 47.72624635154206));
		values.add(new DataPoint(df.parse("2004-07-12 05-58-43") , 64.09895536511307));
		values.add(new DataPoint(df.parse("2004-07-19 05-58-43") , 314.88685935348286));
		values.add(new DataPoint(df.parse("2004-07-26 05-58-43") , 505.9994937304733));
		
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(df.parse("2004-02-09 06-01-52") , 0.0));
		values2.add(new DataPoint(df.parse("2004-02-16 06-01-52") , 1.6280152737351408));
		values2.add(new DataPoint(df.parse("2004-02-23 06-01-52") , 0.12360254242078561));
		values2.add(new DataPoint(df.parse("2004-03-01 06-01-52") , 7.900182967006428));
		values2.add(new DataPoint(df.parse("2004-03-08 06-01-52") , 15.465070840002614));
		values2.add(new DataPoint(df.parse("2004-03-15 06-01-52") , 2.807151862339053));
		values2.add(new DataPoint(df.parse("2004-03-22 06-01-52") , 10.464126300340142));
		values2.add(new DataPoint(df.parse("2004-03-29 06-01-52") , 25.615634107889928));
		values2.add(new DataPoint(df.parse("2004-04-05 06-01-52") , 21.26158281953152));
		values2.add(new DataPoint(df.parse("2004-04-12 06-01-52") , 63.372183693962974));
		values2.add(new DataPoint(df.parse("2004-04-19 06-01-52") , 72.6468339141211));
		values2.add(new DataPoint(df.parse("2004-04-26 06-01-52") , 7.1991878239989955));
		values2.add(new DataPoint(df.parse("2004-05-03 06-01-52") , 110.56939276995584));
		values2.add(new DataPoint(df.parse("2004-05-10 06-01-52") , 79.15784481952026));
		values2.add(new DataPoint(df.parse("2004-05-17 06-01-52") , -85.20941063712397));
		values2.add(new DataPoint(df.parse("2004-05-24 06-01-52") , 231.52718224386695));
		values2.add(new DataPoint(df.parse("2004-05-31 06-01-52") , 276.15880291683123));
		values2.add(new DataPoint(df.parse("2004-06-07 06-01-52") , 251.57224490316074));
		values2.add(new DataPoint(df.parse("2004-06-14 06-01-52") , 349.4780823519509));
		values2.add(new DataPoint(df.parse("2004-06-21 06-01-52") , 262.4396091436224));
		values2.add(new DataPoint(df.parse("2004-06-28 06-01-52") , 432.2018328514377));
		values2.add(new DataPoint(df.parse("2004-07-05 06-01-52") , 434.8170381377188));
		values2.add(new DataPoint(df.parse("2004-07-12 06-01-52") , 129.59644300392864));
		values2.add(new DataPoint(df.parse("2004-07-19 06-01-52") , 526.354828449712));
		values2.add(new DataPoint(df.parse("2004-07-26 06-01-52") , 481.17715827191313));


		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;
		XYDataSeries series2 = new XYDataSeries(new UIPointBar(Color.ORANGE, Color.BLACK, 5), null, "Something Magenta");
		series2.dataPoints = values2;

		NumericalInterval t1 = new NumericalInterval(8, 100.0, new Line(Color.GRAY, false, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 1000.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat weekFormat = new SimpleDateFormat("'week'ww");
		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.MONTH, new Line(Color.GRAY, false, 2), monthFormat);
		
		TimeInterval timeInt1 = new TimeInterval(2, TimeInterval.Type.WEEK, new Line(Color.GRAY, false, 1), weekFormat);
		
		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt1, 
						null), "Time Series"); 

		xySeriesList.add(series);
		xySeriesList.add(series2);

		XYChart chart = new XYChart("","","",xySeriesList, yAxis, xAxis,false);
		
		chart.setTitle("Month Week - 2004, 3 Apr - 2004, 12 August");
		
		timeInt2.styling.intervalFont =  new Font("Blackadder ITC", Font.BOLD, 16);
		timeInt1.styling.intervalFont =  new Font("Blackadder ITC", Font.PLAIN, 12);
		
		timeInt2.styling.graphFill =  new GridFill(Color.WHITE, new Color(224,235,235), false);

		return chart;
	}
	
	
	@Override
	public String getNiceTitle() {
		return "Time Series: Month Week";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataBar_7_BarWithLines();
		t.testChart(t.getChart());
	}
}
