package com.bluewalrus.main;

import java.util.Random;

import com.bluewalrus.chart.datapoint.DataPoint;

public class DataCreator {

	public static void main(String[] args) {
		double max = 100;
		double min = 0;
		
	    Random rand = new Random();
		
		for (int i = 0; i < 500; i++) {
			
		    double xRandom = rand.nextGaussian();
		    xRandom = (xRandom * 100);
		    		
		    double varianceRandom = rand.nextDouble();
		    varianceRandom = varianceRandom * 100;
		    
		    double variance = (varianceRandom / 10);
		    
		    
		    int dir = (i%2==0 ? -1 : 1);
		    
		    if (xRandom > 20)
		    	variance = variance;
		    if (xRandom > 40)
		    	variance = variance * 1;
		    
		    if (xRandom > 50)
		    	variance = variance * 1.1;
		    
		    if (xRandom > 70)
		    	variance = variance * 1.3;

		    
		    if (xRandom > 90)
		    	variance = variance * 2;

		    variance = dir * variance;
		    
		    xRandom = xRandom - 3;
		    
		    System.out.println("values.add(new DataPoint(" + xRandom + "," + (xRandom + variance) + "));");
		}

	}

}
