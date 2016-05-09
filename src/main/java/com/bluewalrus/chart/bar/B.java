package com.bluewalrus.chart.bar;

import java.util.ArrayList;

public class B extends A{
	B() {
//		items = new ArrayList<BItem>();
	}

	public void add(BItem i) {
		
		this.items.add(i);
	} 
}

class A {
	A() {
		items = new ArrayList<AItem>();
	}
	
	public ArrayList<? super AItem> items;
}

//items :
class AItem {}
class BItem extends AItem{}



