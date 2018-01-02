package com.frontangle.ichart.main.test.timeseries;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.frontangle.ichart.chart.datapoint.DataPointBar;
import com.frontangle.ichart.chart.draw.GridFill;
import com.frontangle.ichart.chart.draw.Line;
import com.frontangle.ichart.chart.draw.point.UIPointCircle;
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_WeekDay  extends ChartTester {
	
	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	public void createSomeRandomData() {
		//but don't use it because it will break unit tests
		
        for (int i = 0; i < 27; i++) {
            double r = Math.random();
    		
            System.out.println("values.add(new DataPoint(df.parse(\"2001-07-"+ i +  " 01-00-00\") , " + (10 + i*i) * r + "));");
//            values.add(new DataPointBar("", (int) (100 * d), Color.GRAY));
        }
		
	}
	
	
	@Override
	public Chart getChart() throws ParseException {
		

		createSomeRandomData();
        
        

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		//about 3.5 weeks
		Date startDate = df.parse("2001-07-1 01-00-00"); 
		Date endDate = df.parse("2001-07-27 05-33-00");
		
		
		ArrayList<DataPoint> values = createData1(df);
		
		ArrayList<DataPoint> values2 = createData2(df);

		XYDataSeries series = new XYDataSeries(new UIPointSquare(Color.BLUE), new Line(Color.BLUE), "Something Blue");
		series.dataPoints = values;

		XYDataSeries series2 = new XYDataSeries(new UIPointCircle(Color.GREEN), new Line(Color.GREEN), "Something Green");
		series2.dataPoints = values2;
		
		NumericalInterval t1 = new NumericalInterval(8, 20.0, new Line(Color.LIGHT_GRAY));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 500.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat dayFormat = new SimpleDateFormat("d");
		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.WEEK, new Line(Color.GRAY, false, 2), new SimpleDateFormat("yyyy-MM-dd"));
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.DAY, new Line(Color.LIGHT_GRAY, false, 1), dayFormat);

		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt3, 
						null), "Time Series"); 

		xySeriesList.add(series);
		xySeriesList.add(series2);
		
		
		timeInt2.styling.graphFill =  new GridFill(Color.WHITE, new Color(224,235,235), false);

		XYChart chart = new XYChart("","","",xySeriesList, yAxis, xAxis, false);
		chart.setTitle("Week Day Time Series");

		return chart;
	}

	private ArrayList<DataPoint> createData2(SimpleDateFormat df) throws ParseException {
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(df.parse("2001-07-0 01-00-00") , 8.93972299220859));
		values2.add(new DataPoint(df.parse("2001-07-1 01-00-00") , 9.148218387788779));
		values2.add(new DataPoint(df.parse("2001-07-2 01-00-00") , 11.499298934626909));
		values2.add(new DataPoint(df.parse("2001-07-3 01-00-00") , 3.034600260811585));
		values2.add(new DataPoint(df.parse("2001-07-4 01-00-00") , 15.238063920535113));
		values2.add(new DataPoint(df.parse("2001-07-5 01-00-00") , 33.018735063461044));
		values2.add(new DataPoint(df.parse("2001-07-6 01-00-00") , 14.43197910277364));
		values2.add(new DataPoint(df.parse("2001-07-7 01-00-00") , 45.86340260602795));
		values2.add(new DataPoint(df.parse("2001-07-8 01-00-00") , 59.64569478526348));
		values2.add(new DataPoint(df.parse("2001-07-9 01-00-00") , 12.575848661366255));
		values2.add(new DataPoint(df.parse("2001-07-10 01-00-00") , 36.67600872007173));
		values2.add(new DataPoint(df.parse("2001-07-11 01-00-00") , 130.62347531008774));
		values2.add(new DataPoint(df.parse("2001-07-12 01-00-00") , 89.96214763075164));
		values2.add(new DataPoint(df.parse("2001-07-13 01-00-00") , 16.573512414087627));
		values2.add(new DataPoint(df.parse("2001-07-14 01-00-00") , 30.080511269569627));
		values2.add(new DataPoint(df.parse("2001-07-15 01-00-00") , 49.792381572714405));
		values2.add(new DataPoint(df.parse("2001-07-16 01-00-00") , 116.48371039015468));
		values2.add(new DataPoint(df.parse("2001-07-17 01-00-00") , 54.77515243171983));
		values2.add(new DataPoint(df.parse("2001-07-18 01-00-00") , 143.6646726940613));
		values2.add(new DataPoint(df.parse("2001-07-19 01-00-00") , 105.938670211997));
		values2.add(new DataPoint(df.parse("2001-07-20 01-00-00") , 357.92638148226115));
		values2.add(new DataPoint(df.parse("2001-07-21 01-00-00") , 112.0086909990483));
		values2.add(new DataPoint(df.parse("2001-07-22 01-00-00") , 93.83066544242017));
		values2.add(new DataPoint(df.parse("2001-07-23 01-00-00") , 205.61993660929994));
		values2.add(new DataPoint(df.parse("2001-07-24 01-00-00") , 25.02846457522877));
		values2.add(new DataPoint(df.parse("2001-07-25 01-00-00") , 627.1788933346091));
		values2.add(new DataPoint(df.parse("2001-07-26 01-00-00") , 492.576968318892));
		return values2;
	}

	private ArrayList<DataPoint> createData1(SimpleDateFormat df) throws ParseException {
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(df.parse("2001-07-0 01-00-00") , 7.004228870991804));
		values.add(new DataPoint(df.parse("2001-07-1 01-00-00") , 7.4677922601981965));
		values.add(new DataPoint(df.parse("2001-07-2 01-00-00") , 6.552207592513781));
		values.add(new DataPoint(df.parse("2001-07-3 01-00-00") , 17.219712185829096));
		values.add(new DataPoint(df.parse("2001-07-4 01-00-00") , 25.946354585744913));
		values.add(new DataPoint(df.parse("2001-07-5 01-00-00") , 31.22530975250408));
		values.add(new DataPoint(df.parse("2001-07-6 01-00-00") , 7.421407973549752));
		values.add(new DataPoint(df.parse("2001-07-7 01-00-00") , 12.278858826602184));
		values.add(new DataPoint(df.parse("2001-07-8 01-00-00") , 16.84788170648941));
		values.add(new DataPoint(df.parse("2001-07-9 01-00-00") , 3.8827889197501184));
		values.add(new DataPoint(df.parse("2001-07-10 01-00-00") , 8.050504512001496));
		values.add(new DataPoint(df.parse("2001-07-11 01-00-00") , 125.71896576232886));
		values.add(new DataPoint(df.parse("2001-07-12 01-00-00") , 13.615680383647343));
		values.add(new DataPoint(df.parse("2001-07-13 01-00-00") , 14.405817145579775));
		values.add(new DataPoint(df.parse("2001-07-14 01-00-00") , 119.94837484938172));
		values.add(new DataPoint(df.parse("2001-07-15 01-00-00") , 82.50154904806297));
		values.add(new DataPoint(df.parse("2001-07-16 01-00-00") , 6.288638057033136));
		values.add(new DataPoint(df.parse("2001-07-17 01-00-00") , 37.003867420501685));
		values.add(new DataPoint(df.parse("2001-07-18 01-00-00") , 299.31524308019465));
		values.add(new DataPoint(df.parse("2001-07-19 01-00-00") , 307.4228661455943));
		values.add(new DataPoint(df.parse("2001-07-20 01-00-00") , 363.2669293209534));
		values.add(new DataPoint(df.parse("2001-07-21 01-00-00") , 200.34014897254926));
		values.add(new DataPoint(df.parse("2001-07-22 01-00-00") , 412.38842534418484));
		values.add(new DataPoint(df.parse("2001-07-23 01-00-00") , 253.05634107739422));
		values.add(new DataPoint(df.parse("2001-07-24 01-00-00") , 304.05599584470997));
		values.add(new DataPoint(df.parse("2001-07-25 01-00-00") , 216.45182464557809));
		values.add(new DataPoint(df.parse("2001-07-26 01-00-00") , 106.05333948753602));
		return values;
	}
	
	
	@Override
	public String getNiceTitle() {
		return "Time Series: Week Day";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_WeekDay();
		t.testChart(t.getChart());
	}
}
