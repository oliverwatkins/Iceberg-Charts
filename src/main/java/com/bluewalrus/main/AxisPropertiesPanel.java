package com.bluewalrus.main;

import com.bluewalrus.bar.Axis;
import com.bluewalrus.chart.XYChart;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lauren
 */
public class AxisPropertiesPanel extends JPanel {

//    String axis = "";

    private JTextField maxValueField;
    private JTextField minValueField;

    private Axis axis;
    
    AxisPropertiesPanel(Axis axis) {
        
        
//        this.chart = chart;
//        this.chart.xAxis.maxValue
        
        this.axis = axis;

        minValueField = new javax.swing.JTextField(10);
        maxValueField = new javax.swing.JTextField(10);
        
        minValueField.setText("" + axis.minValue);
        maxValueField.setText("" + axis.maxValue);

        this.initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        int x = 0;
        gbc.gridx = x;
        gbc.gridy = 0;

        this.add(new JLabel("Min Value "), gbc);
        gbc.gridx = 1;
        this.add(minValueField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(new JLabel("Max Value "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(maxValueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        
        IntervalPanel interval = new IntervalPanel(this.axis);

        this.add(interval, gbc);
    }

}
