/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.datapoint;

import com.bluewalrus.pie.Segment;
import java.util.ArrayList;

//
/**
 *
 * @author oliver
 */
public class DataPointPieChart extends DataPoint {

    public double magnitude;
    public String name;
    public ArrayList<Segment> pievalues;
    
    
    public DataPointPieChart(ArrayList<Segment> pievalues, double xPoint, double yPoint, double magnitutde) {

        super(xPoint, yPoint);
        this.magnitude = magnitude;
        this.pievalues = pievalues;
        

    }

}
