/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import com.bluewalrus.bar.Axis;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        
        this.add(new JLabel("Increment 1 "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        this.add(x1IncField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(new JLabel("Increment 2"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;

        
        this.add(x2IncField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(new JLabel("Increment 3"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(x3IncField, gbc);
        
        
    }
}
