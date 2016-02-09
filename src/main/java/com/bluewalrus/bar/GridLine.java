package com.bluewalrus.bar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.Serializable;

public class GridLine implements Serializable{

    public Color color;
    public boolean dashed = false;
    public int thickness = 1;
    public GridFill gridFill;

    public GridLine(Color color) {
        this.color = color;
    }

    public GridLine(Color color, boolean dashed) {
        this.color = color;
        this.dashed = dashed;
    }
    
    public GridLine(Color color, boolean dashed, int thickness) {
        this.color = color;
        this.dashed = dashed;
        this.thickness = thickness;
    }

    public GridLine(Color color, boolean dashed, int thickness, GridFill gridFill) {
        this.color = color;
        this.dashed = dashed;
        this.thickness = thickness;
        this.gridFill = gridFill;
    }

    public void drawLine(Graphics2D g, int x1, int y1,
            int x2, int y2) {
        g.setColor(color);

        Stroke oldStroke = g.getStroke();

        float[] dashBit = {2f, 0f}; //normal, no dashing

        if (dashed) {
            dashBit = new float[]{2f, 2f};
        }

        BasicStroke dashed = new BasicStroke(
                thickness,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f,
                dashBit,
                0.0f);

        g.setStroke(dashed);

        g.drawLine(x1, y1, x2, y2);

        g.setStroke(oldStroke);
    }
    
    private boolean gradiant = true;
    
    public void fillArea(Graphics2D g, int x, int y,
            int width, int height) {

//    	g.setColor(gridFill.color1);
    	
    	if (gradiant) {
    		
    		GradientPaint redtowhite = new GradientPaint(0,0,color.RED,100, 0,color.WHITE);
    		g.setPaint(redtowhite);
    		
//    		g.setColor(gridFill.color2);
    		g.fill(new Rectangle(x, y, width, height));
    		
    	}else {
        	if((x%2)==0) {
        		g.setColor(gridFill.color1);
        	}else {
        		g.setColor(gridFill.color2);
        	}
        	
        	g.fillRect(x, y, width, height); //(x1, y1, x2, y2)
    	}
    	

    	;
//
//        Stroke oldStroke = g.getStroke();
//
//        float[] dashBit = {2f, 0f}; //normal, no dashing
//
//        if (dashed) {
//            dashBit = new float[]{2f, 2f};
//        }

//        BasicStroke dashed = new BasicStroke(
//                thickness,
//                BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_MITER,
//                10.0f,
//                dashBit,
//                0.0f);
//
//        g.setStroke(dashed);

        

//        g.setStroke(oldStroke);
    }
    

}