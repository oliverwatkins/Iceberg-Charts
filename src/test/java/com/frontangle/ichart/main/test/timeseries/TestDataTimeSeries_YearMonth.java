package com.frontangle.ichart.main.test.timeseries;

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
import com.frontangle.ichart.chart.draw.point.UIPointSquare;
import com.frontangle.ichart.chart.draw.point.UIPointTriangle;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.scaling.LinearNumericalAxisScaling;
import com.frontangle.ichart.scaling.TimeSeriesAxisScaling;

public class TestDataTimeSeries_YearMonth extends ChartTester {

	@Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}
	
	
	public void createSomeRandomData() {
		//but don't use it because it will break unit tests
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.YEAR, 1991);
//		c.set(Calendar.MONTH, Calendar.JUNE);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		
        for (int i = 0; i < 100; i++) {
            double r = Math.random();

            c.add(Calendar.MONTH, 1);
    		
            System.out.println("values.add(new DataPoint(df.parse(\"" + df.format(c.getTime()) + "\") , " + (10 + i*i) * r + "));");
//            values.add(new DataPointBar("", (int) (100 * d), Color.GRAY));
        }
		
	}
	
	@Override
	public Chart getChart() throws ParseException {
		
//		createSomeRandomData() ;

		ArrayList<XYDataSeries> xySeriesList = new ArrayList<XYDataSeries>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		Date startDate = df.parse("1991-05-15 01-00-00"); 
		Date endDate = df.parse("1997-07-12 05-33-00");
		
		
//		String time = "2001-03-01 00-33-00";                              
//		Date dt4 = df.parse(time);                                      
//
//		time = "2001-04-01 00-33-00";                                                
//		Date dt5 = df.parse(time);                                      
//
//		time = "2001-07-20 05-33-00";                              
//		Date dt6 = df.parse(time);                                      
		
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(df.parse("1991-02-02 05-30-26") , 6.473663145417612));
		values.add(new DataPoint(df.parse("1991-03-02 05-30-26") , 10.66140751675095));
		values.add(new DataPoint(df.parse("1991-04-02 05-30-26") , 13.536254838232603));
		values.add(new DataPoint(df.parse("1991-05-02 05-30-26") , 17.19085146419578));
		values.add(new DataPoint(df.parse("1991-06-02 05-30-26") , 5.309869909958552));
		values.add(new DataPoint(df.parse("1991-07-02 05-30-26") , 29.971481347806765));
		values.add(new DataPoint(df.parse("1991-08-02 05-30-26") , 29.132245857006165));
		values.add(new DataPoint(df.parse("1991-09-02 05-30-26") , 43.99718610376167));
		values.add(new DataPoint(df.parse("1991-10-02 05-30-26") , 26.35784247870968));
		values.add(new DataPoint(df.parse("1991-11-02 05-30-26") , 47.58925374858533));
		values.add(new DataPoint(df.parse("1991-12-02 05-30-26") , 10.152851251826174));
		values.add(new DataPoint(df.parse("1992-01-02 05-30-26") , 107.8071760336675));
		values.add(new DataPoint(df.parse("1992-02-02 05-30-26") , 121.67411940202832));
		values.add(new DataPoint(df.parse("1992-03-02 05-30-26") , 57.47218315892903));
		values.add(new DataPoint(df.parse("1992-04-02 05-30-26") , 92.98730953918854));
		values.add(new DataPoint(df.parse("1992-05-02 05-30-26") , 125.44112190343155));
		values.add(new DataPoint(df.parse("1992-06-02 05-30-26") , 58.5116362550093));
		values.add(new DataPoint(df.parse("1992-07-02 05-30-26") , 184.6843748270669));
		values.add(new DataPoint(df.parse("1992-08-02 05-30-26") , 251.7349112347471));
		values.add(new DataPoint(df.parse("1992-09-02 05-30-26") , 199.46500270893546));
		values.add(new DataPoint(df.parse("1992-10-02 05-30-26") , 3.22814180367357));
		values.add(new DataPoint(df.parse("1992-11-02 05-30-26") , 347.08179262955764));
		values.add(new DataPoint(df.parse("1992-12-02 05-30-26") , 353.7571346604386));
		values.add(new DataPoint(df.parse("1993-01-02 05-30-26") , 345.8650831400277));
		values.add(new DataPoint(df.parse("1993-02-02 05-30-26") , 178.62112726404877));
		values.add(new DataPoint(df.parse("1993-03-02 05-30-26") , 79.78799530354861));
		values.add(new DataPoint(df.parse("1993-04-02 05-30-26") , 366.58107484535344));
		values.add(new DataPoint(df.parse("1993-05-02 05-30-26") , 508.7636926045164));
		values.add(new DataPoint(df.parse("1993-06-02 05-30-26") , 373.5434489937776));
		values.add(new DataPoint(df.parse("1993-07-02 05-30-26") , 838.4334059121674));
		values.add(new DataPoint(df.parse("1993-08-02 05-30-26") , 160.37055117148358));
		values.add(new DataPoint(df.parse("1993-09-02 05-30-26") , 191.35668318311883));
		values.add(new DataPoint(df.parse("1993-10-02 05-30-26") , 860.3670817368044));
		values.add(new DataPoint(df.parse("1993-11-02 05-30-26") , 688.4045905977462));
		values.add(new DataPoint(df.parse("1993-12-02 05-30-26") , 540.7828263463388));
		values.add(new DataPoint(df.parse("1994-01-02 05-30-26") , 490.7475286822383));
		values.add(new DataPoint(df.parse("1994-02-02 05-30-26") , 719.8611081585448));
		values.add(new DataPoint(df.parse("1994-03-02 05-30-26") , 493.7608569648253));
		values.add(new DataPoint(df.parse("1994-04-02 05-30-26") , 47.306944827957764));
		values.add(new DataPoint(df.parse("1994-05-02 05-30-26") , 520.7502386941234));
		values.add(new DataPoint(df.parse("1994-06-02 05-30-26") , 773.8642313698647));
		values.add(new DataPoint(df.parse("1994-07-02 05-30-26") , 80.30360671766395));
		values.add(new DataPoint(df.parse("1994-08-02 05-30-26") , 1679.1642897590689));
		values.add(new DataPoint(df.parse("1994-09-02 05-30-26") , 1797.8129414703665));
		values.add(new DataPoint(df.parse("1994-10-02 05-30-26") , 808.2955114119355));
		values.add(new DataPoint(df.parse("1994-11-02 05-30-26") , 1480.8121910534126));
		values.add(new DataPoint(df.parse("1994-12-02 05-30-26") , 740.5748573417785));
		values.add(new DataPoint(df.parse("1995-01-02 05-30-26") , 843.7604734380116));
		values.add(new DataPoint(df.parse("1995-02-02 05-30-26") , 154.08745608844572));
		values.add(new DataPoint(df.parse("1995-03-02 05-30-26") , 1684.9674511133148));
		values.add(new DataPoint(df.parse("1995-04-02 05-30-26") , 66.68558915947939));
		values.add(new DataPoint(df.parse("1995-05-02 05-30-26") , 165.84415188639488));
		values.add(new DataPoint(df.parse("1995-06-02 05-30-26") , 1148.0778684070872));
		values.add(new DataPoint(df.parse("1995-07-02 05-30-26") , 12.254401185656574));
		values.add(new DataPoint(df.parse("1995-08-02 05-30-26") , 572.7500354114181));
		values.add(new DataPoint(df.parse("1995-09-02 05-30-26") , 1450.4072438407802));
		values.add(new DataPoint(df.parse("1995-10-02 05-30-26") , 2833.2890877301497));
		values.add(new DataPoint(df.parse("1995-11-02 05-30-26") , 920.4489240320402));
		values.add(new DataPoint(df.parse("1995-12-02 05-30-26") , 337.2745707409935));
		values.add(new DataPoint(df.parse("1996-01-02 05-30-26") , 967.1008215979265));
		values.add(new DataPoint(df.parse("1996-02-02 05-30-26") , 2992.8902000406383));
		values.add(new DataPoint(df.parse("1996-03-02 05-30-26") , 2892.428843185049));
		values.add(new DataPoint(df.parse("1996-04-02 05-30-26") , 1062.8343852283426));
		values.add(new DataPoint(df.parse("1996-05-02 05-30-26") , 1477.8017790649328));
		values.add(new DataPoint(df.parse("1996-06-02 05-30-26") , 1538.0047275414902));
		values.add(new DataPoint(df.parse("1996-07-02 05-30-26") , 3003.294266893816));
		values.add(new DataPoint(df.parse("1996-08-02 05-30-26") , 1919.1460070698483));
		values.add(new DataPoint(df.parse("1996-09-02 05-30-26") , 930.9748608405328));
		values.add(new DataPoint(df.parse("1996-10-02 05-30-26") , 4430.934767523543));
		values.add(new DataPoint(df.parse("1996-11-02 05-30-26") , 1683.4102295772923));
		values.add(new DataPoint(df.parse("1996-12-02 05-30-26") , 3.791992093714037));
		values.add(new DataPoint(df.parse("1997-01-02 05-30-26") , 4635.9581522110075));
		values.add(new DataPoint(df.parse("1997-02-02 05-30-26") , 2999.8395085421407));
		values.add(new DataPoint(df.parse("1997-03-02 05-30-26") , 4301.2814284836995));
		values.add(new DataPoint(df.parse("1997-04-02 05-30-26") , 793.7599926543954));
		values.add(new DataPoint(df.parse("1997-05-02 05-30-26") , 2613.3281612124933));
		values.add(new DataPoint(df.parse("1997-06-02 05-30-26") , 1542.9775038156208));
		values.add(new DataPoint(df.parse("1997-07-02 05-30-26") , 5776.4170919139615));
		values.add(new DataPoint(df.parse("1997-08-02 05-30-26") , 1689.7108494208092));
		values.add(new DataPoint(df.parse("1997-09-02 05-30-26") , 2923.8888807737585));
		values.add(new DataPoint(df.parse("1997-10-02 05-30-26") , 6359.819708455538));
		values.add(new DataPoint(df.parse("1997-11-02 05-30-26") , 1590.8185790929022));
		values.add(new DataPoint(df.parse("1997-12-02 05-30-26") , 2553.6670058418836));
		values.add(new DataPoint(df.parse("1998-01-02 05-30-26") , 1613.7143466177176));
		values.add(new DataPoint(df.parse("1998-02-02 05-30-26") , 61.196016480895324));
		values.add(new DataPoint(df.parse("1998-03-02 05-30-26") , 701.7134310126022));
		values.add(new DataPoint(df.parse("1998-04-02 05-30-26") , 3537.633910957597));
		values.add(new DataPoint(df.parse("1998-05-02 05-30-26") , 2601.8354683563193));
		values.add(new DataPoint(df.parse("1998-06-02 05-30-26") , 4436.689256070722));
		values.add(new DataPoint(df.parse("1998-07-02 05-30-26") , 2093.939053801901));
		values.add(new DataPoint(df.parse("1998-08-02 05-30-26") , 92.57752288808099));
		values.add(new DataPoint(df.parse("1998-09-02 05-30-26") , 2495.385831285384));
		values.add(new DataPoint(df.parse("1998-10-02 05-30-26") , 3073.2612282583236));
		values.add(new DataPoint(df.parse("1998-11-02 05-30-26") , 281.2364913319287));
		values.add(new DataPoint(df.parse("1998-12-02 05-30-26") , 2998.196046281013));
		values.add(new DataPoint(df.parse("1999-01-02 05-30-26") , 8485.079454829127));
		values.add(new DataPoint(df.parse("1999-02-02 05-30-26") , 6938.123159756115));
		values.add(new DataPoint(df.parse("1999-03-02 05-30-26") , 557.9255977852073));
		values.add(new DataPoint(df.parse("1999-04-02 05-30-26") , 179.0930948508052));
		values.add(new DataPoint(df.parse("1999-05-02 05-30-26") , 6560.575366770052));

		XYDataSeries series = new XYDataSeries(new UIPointTriangle(Color.RED), new Line(Color.RED), "Something Blue");
		series.dataPoints = values;

		NumericalInterval t1 = new NumericalInterval(8, 1000.0, new Line(Color.GRAY, false, 1));

		YAxis yAxis = new YAxis(new LinearNumericalAxisScaling(-90.0, 10000.0, t1, null, null), "Y Axis");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("M");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
		
		TimeInterval timeInt2 = new TimeInterval(6, TimeInterval.Type.YEAR, new Line(Color.GRAY, false, 2), yearFormat);
		
		TimeInterval timeInt3 = new TimeInterval(2, TimeInterval.Type.MONTH, new Line(Color.GRAY, false, 1), monthFormat);

		
		timeInt2.styling.intervalFont =  new Font("Blackadder ITC", Font.BOLD, 16);
		timeInt3.styling.intervalFont =  new Font("Blackadder ITC", Font.PLAIN, 8);
		
		timeInt2.styling.graphFill =  new GridFill(Color.WHITE, new Color(224,235,235), false);
		
		XAxis xAxis = new XAxis(
				new TimeSeriesAxisScaling(
						startDate, 
						endDate, 
						timeInt2, 
						timeInt3, 
						null), "Time Series"); 

		xySeriesList.add(series);

		XYChart chart = new XYChart("","","",xySeriesList, yAxis, xAxis,false);
		chart.setTitle("Year Month (May 1991, July 2001)");

		return chart;
	}
	
	@Override
	public String getNiceTitle() {
		return "Time Series: Year Month";
	}
	
	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataTimeSeries_YearMonth();
		t.testChart(t.getChart());
	}	
}
