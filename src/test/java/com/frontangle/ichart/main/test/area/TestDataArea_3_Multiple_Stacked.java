package com.frontangle.ichart.main.test.area;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.frontangle.ichart.chart.Chart;
import com.frontangle.ichart.chart.XYChart;
import com.frontangle.ichart.chart.XYDataSeries;
import com.frontangle.ichart.chart.datapoint.DataPoint;
import com.frontangle.ichart.chart.draw.Area;
import com.frontangle.ichart.main.test.ChartTester;
import com.frontangle.ichart.main.test.Showcase;

/**
 * 
 * @author Oliver Watkins
 */
public class TestDataArea_3_Multiple_Stacked extends ChartTester {

	public static String path = "src\\main\\resources\\showcase\\screenshots\\";

	@Showcase
	public Chart getChart() {
		
		ArrayList<DataPoint> values = new ArrayList<DataPoint>();
		values.add(new DataPoint(5, 96));
		values.add(new DataPoint(58, 43));
		values.add(new DataPoint(101, 90));
		values.add(new DataPoint(135, 67));
		values.add(new DataPoint(150, 70));
		
		ArrayList<DataPoint> values2 = new ArrayList<DataPoint>();
		values2.add(new DataPoint(5, 1));
		values2.add(new DataPoint(58, 63));
		values2.add(new DataPoint(101, 290));
		values2.add(new DataPoint(135, 167));
		values2.add(new DataPoint(150, 80));
		
		ArrayList<DataPoint> values3 = new ArrayList<DataPoint>();
		values3.add(new DataPoint(5,6));
		values3.add(new DataPoint(58, 33));
		values3.add(new DataPoint(101, 77));
		values3.add(new DataPoint(135, 123));
		values3.add(new DataPoint(150, 321));
		
		
		XYDataSeries<DataPoint> xyDataSeries = new XYDataSeries<DataPoint>("a");
		xyDataSeries.dataPoints = values;
		xyDataSeries.setArea(new Area(new Color(173, 13, 213, 80)));

		XYDataSeries<DataPoint> xyDataSeries2 = new XYDataSeries<DataPoint>("b");
		xyDataSeries2.dataPoints = values2;
		xyDataSeries2.setArea(new Area(new Color(12, 42, 113, 80)));
		
		XYDataSeries<DataPoint> xyDataSeries3 = new XYDataSeries<DataPoint>("c");
		xyDataSeries3.dataPoints = values3;
		xyDataSeries3.setArea(new Area(new Color(12, 123,42, 80)));
		
		ArrayList<XYDataSeries> ds = new ArrayList<XYDataSeries>();
		ds.add(xyDataSeries);
		ds.add(xyDataSeries2);
		ds.add(xyDataSeries3);
		
		XYChart chart = new XYChart("Multiple Area with opacity and overlap", "X Axis",
				"Y Axis", getData());

		return chart;
	}


	@org.junit.Test
	public void testSnapshot() throws IOException, ParseException {
		super.testSnapshot();
	}

	public static void main(String[] args) throws Exception {
		ChartTester t = new TestDataArea_3_Multiple_Stacked();
		t.testChart(t.getChart());
	}

	@Override
	public String getNiceTitle() {
		return "Area:Simple";
	}
	
	public ArrayList<XYDataSeries> getData() {
		
		ArrayList<XYDataSeries> al = new ArrayList<XYDataSeries>();
		
		ArrayList<DataPoint> dps = new ArrayList<DataPoint>();
		
//		North Africa & the Middle East Total	3.341	14.557	4.668	2.816	2.924	3.236	3.396	2.409	3.929	5.219	6.505	7.501	10.050	8.044	5.754	7.154	7.021	5.417	4.826	7.146
//		Africa excl North Africa total	1.437	2.279	3.355	3.655	3.783	4.725	5.076	4.351	3.097	2.780	4.976	7.521	7.638	4.830	4.061	3.728	2.823	2.570	3.249	4.857
//		Southeast Asia total	4.704	6.355	12.329	16.531	19.975	21.908	22.243	19.604	18.299	18.216	17.906	23.042	29.444	31.702	28.201	29.417	22.325	13.853	14.239	14.861
//		Northeast Asia total	1.782	2.788	3.251	2.860	2.644	2.711	3.774	3.504	4.567	7.631	8.191	8.937	12.672	15.874	16.395	22.100	21.473	12.504	8.045	9.899
//		Southern Asia total	1.564	1.544	1.829	1.345	1.331	1.636	2.335	2.558	3.318	4.765	4.486	6.256	6.716	7.025	6.011	9.389	10.594	6.632	5.482	7.616
//		Northern America total	2.088	1.833	1.791	1.787	2.064	2.523	2.881	2.767	2.271	2.366	2.653	2.863	3.085	3.068	3.015	2.811	2.570	2.021	2.002	2.576
//		South America, Central America & the Caribbean total
		
		
		XYDataSeries x1 = new XYDataSeries<DataPoint>("Oceana");
		x1.setArea(new Area(new Color(173, 13, 213, 80)));
		XYDataSeries x2 = new XYDataSeries<DataPoint>("Europe");
		x2.setArea(new Area(new Color(12, 42, 113, 80)));
		XYDataSeries x3 = new XYDataSeries<DataPoint>("USSR + Baltic states");
		x3.setArea(new Area(new Color(32, 42, 1, 80)));
		XYDataSeries x4 = new XYDataSeries<DataPoint>("North Africa & the Middle East ");
		x4.setArea(new Area(new Color(53, 23, 1, 80)));
		XYDataSeries x5 = new XYDataSeries<DataPoint>("Africa excl North Africa");
		x5.setArea(new Area(new Color(76, 231, 1, 80)));
		XYDataSeries x6 = new XYDataSeries<DataPoint>("Southeast Asia");
		x6.setArea(new Area(new Color(41, 211, 1, 80)));
		XYDataSeries x7 = new XYDataSeries<DataPoint>("Northeast Asia");
		x7.setArea(new Area(new Color(1, 145, 1, 80)));
		XYDataSeries x8 = new XYDataSeries<DataPoint>("Southern Asia ");
		x8.setArea(new Area(new Color(2, 1, 1, 80)));
		XYDataSeries x9 = new XYDataSeries<DataPoint>("Northern America ");
		x9.setArea(new Area(new Color(6, 111, 1, 80)));
		XYDataSeries x10 = new XYDataSeries<DataPoint>("South America, Central America & the Caribbean");
		x10.setArea(new Area(new Color(12, 42, 1, 80)));
	
		dps.add(new DataPoint("1975-76", 4772));
		dps.add(new DataPoint("1976-77", 6712));
		dps.add(new DataPoint("1977-78", 10000));
		dps.add(new DataPoint("1978-79", 12662));
		dps.add(new DataPoint("1979-80", 14972));
		dps.add(new DataPoint("1980-81", 19806));
		dps.add(new DataPoint("1981-82", 14184));
		dps.add(new DataPoint("1982-83", 8735));
		dps.add(new DataPoint("1983-84", 7297));
		dps.add(new DataPoint("1984-85", 10754));
		dps.add(new DataPoint("1985-86", 15703));
		dps.add(new DataPoint("1986-87", 16602));
		dps.add(new DataPoint("1987-88", 25657));
		dps.add(new DataPoint("1988-89", 27999));
		dps.add(new DataPoint("1989-90", 15270));
		dps.add(new DataPoint("1990-91", 10970));
		dps.add(new DataPoint("1991-92", 10362));
		dps.add(new DataPoint("1992-93", 9517));
		dps.add(new DataPoint("1993-94", 10196));
		dps.add(new DataPoint("1994-95", 13592));
		x1.dataPoints = dps;
		
		
//		Europe total	28.987	30.620	31.980	22.229	29.416	51.952	62.252	47.200	23.749	21.963	27.869	36.343	43.110	41.454	
//		36.640	31.468	24.803	18.996	18.523	23.183

		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 28987));
		dps.add(new DataPoint("1976-77", 30620));
		dps.add(new DataPoint("1977-78", 31980));
		dps.add(new DataPoint("1978-79", 22229));
		dps.add(new DataPoint("1979-80", 29416));
		dps.add(new DataPoint("1980-81", 51952));
		dps.add(new DataPoint("1981-82", 62252));
		dps.add(new DataPoint("1982-83", 47200));
		dps.add(new DataPoint("1983-84", 23749));
		dps.add(new DataPoint("1984-85", 21963));
		dps.add(new DataPoint("1985-86", 27869));
		dps.add(new DataPoint("1986-87", 36343));
		dps.add(new DataPoint("1987-88", 43110));
		dps.add(new DataPoint("1988-89", 41454));
		dps.add(new DataPoint("1989-90", 36640));
		dps.add(new DataPoint("1990-91", 31468));
		dps.add(new DataPoint("1991-92", 24803));
		dps.add(new DataPoint("1992-93", 18996));
		dps.add(new DataPoint("1993-94", 18523));
		dps.add(new DataPoint("1994-95", 23183));
		x2.dataPoints = dps;
		
		
//		The Former USSR and the Baltic States total	
//		481	533	705	828	1.859	712	439	177	185	169	
//		230	201	450	984	1.746	865	2.067	3.204	1.950	2.340
	//	
		
		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 481));
		dps.add(new DataPoint("1976-77", 533));
		dps.add(new DataPoint("1977-78", 705));
		dps.add(new DataPoint("1978-79", 828));
		dps.add(new DataPoint("1979-80", 1859));
		dps.add(new DataPoint("1980-81", 712));
		dps.add(new DataPoint("1981-82", 439));
		dps.add(new DataPoint("1982-83", 177));
		dps.add(new DataPoint("1983-84", 185));
		dps.add(new DataPoint("1984-85", 169));
		dps.add(new DataPoint("1985-86", 230));
		dps.add(new DataPoint("1986-87", 201));
		dps.add(new DataPoint("1987-88", 450));
		dps.add(new DataPoint("1988-89", 984));
		dps.add(new DataPoint("1989-90", 1746));
		dps.add(new DataPoint("1990-91", 865));
		dps.add(new DataPoint("1991-92", 2067));
		dps.add(new DataPoint("1992-93", 3204));
		dps.add(new DataPoint("1993-94", 1950));
		dps.add(new DataPoint("1994-95", 2340));
		x3.dataPoints = dps;
		
		
		//	North Africa & the Middle East Total	
//		3.341	
//		14.557	4.668	2.816	2.924	3.236	3.396	2.409	3.929	5.219	6.505	7.501	10.050	
//		8.044	5.754	7.154	7.021	5.417	4.826	7.146

		
		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 14557));
		dps.add(new DataPoint("1976-77", 4668));
		dps.add(new DataPoint("1977-78", 2816));
		dps.add(new DataPoint("1978-79", 2924));
		dps.add(new DataPoint("1979-80", 3236));
		dps.add(new DataPoint("1980-81", 3396));
		dps.add(new DataPoint("1981-82", 2409));
		dps.add(new DataPoint("1982-83", 3929));
		dps.add(new DataPoint("1983-84", 5219));
		dps.add(new DataPoint("1984-85", 6505));
		dps.add(new DataPoint("1985-86", 7501));
		dps.add(new DataPoint("1986-87", 10050));
		dps.add(new DataPoint("1987-88", 8044));
		dps.add(new DataPoint("1988-89", 5754));
		dps.add(new DataPoint("1989-90", 7154));
		dps.add(new DataPoint("1990-91", 7021));
		dps.add(new DataPoint("1991-92", 5417));
		dps.add(new DataPoint("1992-93", 4826));
		dps.add(new DataPoint("1993-94", 7146));
		dps.add(new DataPoint("1994-95", 2340));
		x4.dataPoints = dps;
		
//		Africa excl North Africa total	
//		1.437	2.279	3.355	3.655	3.783	4.725	5.076	4.351	3.097	2.780	
//		4.976	7.521	7.638	4.830	4.061	3.728	2.823	2.570	3.249	4.857

		
		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 1437));
		dps.add(new DataPoint("1976-77", 2279));
		dps.add(new DataPoint("1977-78", 3355));
		dps.add(new DataPoint("1978-79", 3655));
		dps.add(new DataPoint("1979-80", 3783));
		dps.add(new DataPoint("1980-81", 4725));
		dps.add(new DataPoint("1981-82", 5076));
		dps.add(new DataPoint("1982-83", 4351));
		dps.add(new DataPoint("1983-84", 3097));
		dps.add(new DataPoint("1984-85", 2780));
		dps.add(new DataPoint("1985-86", 4976));
		dps.add(new DataPoint("1986-87", 7521));
		dps.add(new DataPoint("1987-88", 7638));
		dps.add(new DataPoint("1988-89", 4830));
		dps.add(new DataPoint("1989-90", 4061));
		dps.add(new DataPoint("1990-91", 3728));
		dps.add(new DataPoint("1991-92", 2823));
		dps.add(new DataPoint("1992-93", 2570));
		dps.add(new DataPoint("1993-94", 3249));
		dps.add(new DataPoint("1994-95", 4857));
		x5.dataPoints = dps;
		
		//	Southeast Asia total	
//		4.704	6.355	12.329	16.531	19.975	21.908	22.243	19.604	18.299	18.216	
//		17.906	23.042	29.444	31.702	28.201	29.417	22.325	13.853	14.239	14.861

		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 4704));
		dps.add(new DataPoint("1976-77", 6355));
		dps.add(new DataPoint("1977-78", 12329));
		dps.add(new DataPoint("1978-79", 16531));
		dps.add(new DataPoint("1979-80", 19975));
		dps.add(new DataPoint("1980-81", 21908));
		dps.add(new DataPoint("1981-82", 22243));
		dps.add(new DataPoint("1982-83", 19604));
		dps.add(new DataPoint("1983-84", 18299));
		dps.add(new DataPoint("1984-85", 18216));
		dps.add(new DataPoint("1985-86", 17906));
		dps.add(new DataPoint("1986-87", 23042));
		dps.add(new DataPoint("1987-88", 29444));
		dps.add(new DataPoint("1988-89", 31702));
		dps.add(new DataPoint("1989-90", 28201));
		dps.add(new DataPoint("1990-91", 29417));
		dps.add(new DataPoint("1991-92", 22325));
		dps.add(new DataPoint("1992-93", 13853));
		dps.add(new DataPoint("1993-94", 14239));
		dps.add(new DataPoint("1994-95", 14861));
		x6.dataPoints = dps;
		
//		Northeast Asia total	
//		1.782	2.788	3.251	2.860	2.644	2.711	3.774	3.504	4.567	7.631	
//		8.191	8.937	12.672	15.874	16.395	22.100	21.473	12.504	8.045	9.899

		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 1782));
		dps.add(new DataPoint("1976-77", 2788));
		dps.add(new DataPoint("1977-78", 3251));
		dps.add(new DataPoint("1978-79", 2860));
		dps.add(new DataPoint("1979-80", 2644));
		dps.add(new DataPoint("1980-81", 2711));
		dps.add(new DataPoint("1981-82", 3774));
		dps.add(new DataPoint("1982-83", 3504));
		dps.add(new DataPoint("1983-84", 4567));
		dps.add(new DataPoint("1984-85", 7631));
		dps.add(new DataPoint("1985-86", 8191));
		dps.add(new DataPoint("1986-87", 8937));
		dps.add(new DataPoint("1987-88", 12672));
		dps.add(new DataPoint("1988-89", 15874));
		dps.add(new DataPoint("1989-90", 16395));
		dps.add(new DataPoint("1990-91", 22100));
		dps.add(new DataPoint("1991-92", 21473));
		dps.add(new DataPoint("1992-93", 12504));
		dps.add(new DataPoint("1993-94", 8045));
		dps.add(new DataPoint("1994-95", 9899));
		x7.dataPoints = dps;
		
//		Southern Asia total	1.564	1.544	1.829	1.345	1.331	1.636	2.335	2.558	3.318	4.765	4.486	6.256	6.716	7.025
//		6.011	9.389	10.594	6.632	5.482	7.616

		
		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 1564));
		dps.add(new DataPoint("1976-77", 1544));
		dps.add(new DataPoint("1977-78", 1829));
		dps.add(new DataPoint("1978-79", 1345));
		dps.add(new DataPoint("1979-80", 1331));
		dps.add(new DataPoint("1980-81", 1636));
		dps.add(new DataPoint("1981-82", 2335));
		dps.add(new DataPoint("1982-83", 2558));
		dps.add(new DataPoint("1983-84", 3318));
		dps.add(new DataPoint("1984-85", 4765));
		dps.add(new DataPoint("1985-86", 4486));
		dps.add(new DataPoint("1986-87", 6256));
		dps.add(new DataPoint("1987-88", 6716));
		dps.add(new DataPoint("1988-89", 7025));
		dps.add(new DataPoint("1989-90", 6011));
		dps.add(new DataPoint("1990-91", 9389));
		dps.add(new DataPoint("1991-92", 10594));
		dps.add(new DataPoint("1992-93", 6632));
		dps.add(new DataPoint("1993-94", 5482));
		dps.add(new DataPoint("1994-95", 7616));
		x8.dataPoints = dps;

		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 2088));
		dps.add(new DataPoint("1976-77", 1833));
		dps.add(new DataPoint("1977-78", 1791));
		dps.add(new DataPoint("1978-79", 2064));
		dps.add(new DataPoint("1979-80", 2523));
		dps.add(new DataPoint("1980-81", 2881));
		dps.add(new DataPoint("1981-82", 2767));
		dps.add(new DataPoint("1982-83", 2271));
		dps.add(new DataPoint("1983-84", 2366));
		dps.add(new DataPoint("1984-85", 2653));
		dps.add(new DataPoint("1985-86", 2863));
		dps.add(new DataPoint("1986-87", 3085));
		dps.add(new DataPoint("1987-88", 3068));
		dps.add(new DataPoint("1988-89", 3015));
		dps.add(new DataPoint("1989-90", 2811));
		dps.add(new DataPoint("1990-91", 2570));
		dps.add(new DataPoint("1991-92", 2021));
		dps.add(new DataPoint("1992-93", 2002));
		dps.add(new DataPoint("1993-94", 2002));
		dps.add(new DataPoint("1994-95", 2576));
		x9.dataPoints = dps;
		
		//	South America, Central America & the Caribbean total	
//		3.592	3.678	3.252	2.468	1.748	1.451	1.437	1.700	2.097	3.645	
//		4.068	4.269	4.632	4.322	4.125	3.745	3.308	1.557	1.153	1.329

		dps = new ArrayList<DataPoint>();
		dps.add(new DataPoint("1975-76", 3592));
		dps.add(new DataPoint("1976-77", 3678));
		dps.add(new DataPoint("1977-78", 3252));
		dps.add(new DataPoint("1978-79", 2468));
		dps.add(new DataPoint("1979-80", 1748));
		dps.add(new DataPoint("1980-81", 1451));
		dps.add(new DataPoint("1981-82", 1437));
		dps.add(new DataPoint("1982-83", 1700));
		dps.add(new DataPoint("1983-84", 2097));
		dps.add(new DataPoint("1984-85", 3645));
		dps.add(new DataPoint("1985-86", 4068));
		dps.add(new DataPoint("1986-87", 4269));
		dps.add(new DataPoint("1987-88", 4632));
		dps.add(new DataPoint("1988-89", 4322));
		dps.add(new DataPoint("1989-90", 4125));
		dps.add(new DataPoint("1990-91", 3745));
		dps.add(new DataPoint("1991-92", 3308));
		dps.add(new DataPoint("1992-93", 1557));
		dps.add(new DataPoint("1993-94", 1153));
		dps.add(new DataPoint("1994-95", 1329));
		x10.dataPoints = dps;
		
		al.add(x1);
		al.add(x2);
		al.add(x3);
		al.add(x4);
		al.add(x5);
		al.add(x6);
		al.add(x7);
		al.add(x8);
		al.add(x9);
		al.add(x10);
		
		return al;
	}


	
//	Major Region	1975–76	1976–77	1977–78	1978–79	1979–80	1980–81	1981–82	1982–83	1983–84	1984–85	1985–86	1986–87	1987–88	1988–89	1989–90	1990–91	1991–92	1992–93	1993–94	1994–95
//	Oceania total	4.772	6.712	10.000	12.662	14.972	19.806	14.184	8.735	7.297	10.754	15.703	16.602	25.657	27.999	15.270	10.970	10.362	9.517	10.196	13.592
//	Europe total	28.987	30.620	31.980	22.229	29.416	51.952	62.252	47.200	23.749	21.963	27.869	36.343	43.110	41.454	36.640	31.468	24.803	18.996	18.523	23.183
//	The Former USSR and the Baltic States total	481	533	705	828	1.859	712	439	177	185	169	230	201	450	984	1.746	865	2.067	3.204	1.950	2.340
	//	North Africa & the Middle East Total	3.341	14.557	4.668	2.816	2.924	3.236	3.396	2.409	3.929	5.219	6.505	7.501	10.050	8.044	5.754	7.154	7.021	5.417	4.826	7.146
	//	Africa excl North Africa total	1.437	2.279	3.355	3.655	3.783	4.725	5.076	4.351	3.097	2.780	4.976	7.521	7.638	4.830	4.061	3.728	2.823	2.570	3.249	4.857
	//	Southeast Asia total	4.704	6.355	12.329	16.531	19.975	21.908	22.243	19.604	18.299	18.216	17.906	23.042	29.444	31.702	28.201	29.417	22.325	13.853	14.239	14.861
	//	Northeast Asia total	1.782	2.788	3.251	2.860	2.644	2.711	3.774	3.504	4.567	7.631	8.191	8.937	12.672	15.874	16.395	22.100	21.473	12.504	8.045	9.899
	//	Southern Asia total	1.564	1.544	1.829	1.345	1.331	1.636	2.335	2.558	3.318	4.765	4.486	6.256	6.716	7.025	6.011	9.389	10.594	6.632	5.482	7.616
	//	Northern America total	2.088	1.833	1.791	1.787	2.064	2.523	2.881	2.767	2.271	2.366	2.653	2.863	3.085	3.068	3.015	2.811	2.570	2.021	2.002	2.576

	//	South America, Central America & the Caribbean total	3.592	3.678	3.252	2.468	1.748	1.451	1.437	1.700	2.097	3.645	4.068	4.269	4.632	4.322	4.125	3.745	3.308	1.557	1.153	1.329

	//	Grand Total	52.752	70.916	73.171	67.192	80.748	110.689	118.031	93.011	68.813	77.508	92.590	113.541	143.466	145.316	121.227	121.688	107.391	76.330	69.768	87.428

}
