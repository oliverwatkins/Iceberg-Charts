package com.bluewalrus.main.test;

import java.util.ArrayList;

import com.bluewalrus.bar.XYDataSeries;
import com.bluewalrus.datapoint.DataPoint;
import com.bluewalrus.datapoint.DataPointWithMagnitude;

public class CopyOfTestGenerics {
	
	public class SubType extends SuperType{

		public SubType(GenericObject<Y> g) {
			super(g);
		}
	}
	
	public class SuperType {

	    public ArrayList<GenericObject<?>> data = new ArrayList<GenericObject<? extends X>>();
		
	    public SuperType(GenericObject<? extends X> series) {
	        
	        data.add(series);
		}
	}
	
	class GenericObject<T extends X>{
	}
	
	class X{};
	class Y extends X{};
	
	
	
}
