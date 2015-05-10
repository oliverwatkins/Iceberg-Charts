package com.bluewalrus.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.bluewalrus.chart.Axis;
import com.bluewalrus.chart.Chart;

/**
 *
 * @author lauren
 */
public class AxisPropertiesPanel extends JPanel {

    
    private IntervalPanel interval;

//    String axis = "";
    private JTextField maxValueField;
    private JTextField minValueField;

    private Axis axis;
    private Chart chart;

    AxisPropertiesPanel(Axis axis, Chart chart) {

        setBorder(BorderFactory.createTitledBorder(axis.getName()));

        this.chart = chart;
//        this.chart.xAxis.maxValue
        this.axis = axis;

        minValueField = new javax.swing.JTextField(10);
        maxValueField = new javax.swing.JTextField(10);

        minValueField.setText("" + axis.minValue);
        maxValueField.setText("" + axis.maxValue);

        this.initComponents();
    }

    public void apply() {

        axis.minValue = new Double(minValueField.getText());
        axis.maxValue = new Double(maxValueField.getText());
        
        interval.apply();

    }

    private void initComponents() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        minValueField.setMinimumSize(minValueField.getPreferredSize());
        maxValueField.setMinimumSize(maxValueField.getPreferredSize());

        
        int x = 0;
        gbc.gridx = x;
        gbc.gridy = 0;
//        gbc.weightx = 1;

        this.add(new JLabel("Min Value "), gbc);
        gbc.gridx = 1;
        this.add(minValueField, gbc);
        gbc.gridx = 2;
        
        JSlider slider = new JSlider(0, 100);
        slider.setMinimumSize(minValueField.getPreferredSize());
        gbc.weightx = 1;
        this.add(slider, gbc);
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(new JLabel("Max Value "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(maxValueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        interval = new IntervalPanel(this.axis, this.chart);

        this.add(interval, gbc);
    }
}
