/**
 *
 *
 * Copyright (c) 2014, Oliver Watkins. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.frontangle.ichart.pie;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class MultiLevelPieChart extends AbstractPieChart {

    private int depth;

    
    /*
     * 
     * @param values
     * @param title
     */
    public MultiLevelPieChart(ArrayList<Segment> values, String title) {
        this(values, 200, 80, title);
    }

    
    /*
     * Multi Level
     * 
     * 
     * @param values initial pie slice values.
     * @param totalWidth total width of this component
     * @param chartWidth width of the chart (should be less than total width)
     * @param initialWidth width of the first pie segments. 
     * @param incrementWidth width of each outer layer of arches.
     */
    public MultiLevelPieChart(ArrayList<Segment> values, 
    		int initialWidth, 
    		int incrementWidth, 
    		String title) {

        this.initialWidth = initialWidth;
        this.incrementWidth = incrementWidth; 

        this.depth = getDepth(values);
        this.initialSegments = values;
        this.setTitle(title);
        
        
    	this.topOffset = 90;
    	this.leftOffset = 15;
    	this.rightOffset = 15;
    	this.bottomOffset = 15;
    }

    
    @Override
    protected void paintComponent(Graphics g) {
    	drawGraphData(g);
    }
    

	@Override
	protected void drawGraphData(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        calculateHeighAndWidthOfChart();
        
        calculateCenterPoint();
        

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

        
        this.circles = getCircleArray(depth);
        
        g.drawRect(0, 0, getWidth(), getHeight());
        g2d.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        
    	this.drawTitle(g);
    	
    	
    	
        paintFirstSegments(g2d);

        /**
         * Draw the texts
         */
        for (int i = 0; i < initialSegments.size(); i++) {

            Segment segment = initialSegments.get(i);

            paintTextOnSegment(g2d, segment);
        }
    }

    /*
     * Returns tree decenterPointth (SPECIFIC TO MLPC)
     *
     * 1 = simcenterPointle centerPointie chart with no extra dimensions 2 = two
     * level centerPointie chart 3 = 3 level etc..
     *
     *
     * @param values
     * @return
     */
    private int getDepth(ArrayList<Segment> values) {

        ArrayList<Segment> all = new ArrayList<Segment>();

        for (Segment segment : values) {

            ArrayList<Segment> al = getLeafs(segment);
            all.addAll(al);
        }
        int deepestLevel = 0;
        for (Segment segment : all) {
            if (segment.level > deepestLevel) {
                deepestLevel = segment.level;
            }
        }
        return deepestLevel;
    }

    private ArrayList<Segment> getLeafs(Segment segment) {
        ArrayList<Segment> kids = new ArrayList<Segment>();
        for (Segment s : segment.children) {
            if (s.children != null && !s.children.isEmpty()) {
                kids.addAll(getLeafs(s));
            } else { //leaf
                kids.add(s);
            }
        }
        return kids;
    }


}
