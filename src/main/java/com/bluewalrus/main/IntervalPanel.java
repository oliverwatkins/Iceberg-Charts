/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import com.bluewalrus.chart.Axis;
import com.bluewalrus.bar.Interval;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lauren
 */
public class IntervalPanel extends JPanel {
    
    private javax.swing.JTextField x1IncField;
    private javax.swing.JTextField x2IncField;
    private javax.swing.JTextField x3IncField;
    // End of variables declaration     
    
    private Axis axis;
    
    IntervalPanel(Axis axis) {
        
        this.axis = axis;
        
        x1IncField = new javax.swing.JTextField(10);
        x2IncField = new javax.swing.JTextField(10);
        x3IncField = new javax.swing.JTextField(10);
        
        if (axis.interval1 != null)
            x1IncField.setText("" + axis.interval1.increment);
        if (axis.interval2 != null)
            x2IncField.setText("" + axis.interval2.increment);
        if (axis.interval3 != null)
            x3IncField.setText("" + axis.interval3.increment);
        
        this.initComponents();
    }
    


    private void initComponents() {
        this.setBorder(BorderFactory.createTitledBorder("Intervals"));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        
        drawInterval(axis.interval1, gbc, 0, x1IncField);
        drawInterval(axis.interval2, gbc, 1, x2IncField);
        drawInterval(axis.interval3, gbc, 2, x3IncField);
        
    }

    private JTextField tickLength = new JTextField(10);
    
    public void drawInterval(Interval interval1, GridBagConstraints gbc, int position, JTextField incField) {
        gbc.gridx = 0;
        gbc.gridy = position;
        
        this.add(new JLabel("Increment " + position), gbc);
        gbc.gridx = 1;
        this.add(incField, gbc);

        gbc.gridx = 2;

        this.add(new JLabel("Tick Length "), gbc);
        
        gbc.gridx = 3;
        this.add(tickLength, gbc);
        
        
        
    }
    
    
    public void apply() {
        
//        axis
        
    }
}
