/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.bluewalrus.bar.Interval;
import com.bluewalrus.chart.Axis;
import com.bluewalrus.chart.Chart;

/**
 *
 * @author lauren
 */
public class IntervalPanel extends JPanel {

	private javax.swing.JTextField x1IncField;
	private javax.swing.JTextField x2IncField;
	private javax.swing.JTextField x3IncField;
	// End of variables declaration

	private JSpinner tickLength1 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
	private JSpinner tickLength2 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
	private JSpinner tickLength3 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

	private Axis axis;

	private  Chart chart;

	IntervalPanel(Axis axis, final Chart chart) {

		this.axis = axis;
		this.chart = chart;
		

		x1IncField = new javax.swing.JTextField(10);
		x2IncField = new javax.swing.JTextField(10);
		x3IncField = new javax.swing.JTextField(10);

		x1IncField.setMinimumSize(x1IncField.getPreferredSize());
		x2IncField.setMinimumSize(x2IncField.getPreferredSize());
		x3IncField.setMinimumSize(x3IncField.getPreferredSize());
		
		if (axis.interval1 != null)
			x1IncField.setText("" + axis.interval1.increment);
		if (axis.interval2 != null)
			x2IncField.setText("" + axis.interval2.increment);
		if (axis.interval3 != null)
			x3IncField.setText("" + axis.interval3.increment);
		
		if (axis.interval1 != null)
			tickLength1.setValue(axis.interval1.lineLength);
		if (axis.interval2 != null)
			tickLength2.setValue(axis.interval2.lineLength);
		if (axis.interval3 != null)
			tickLength3.setValue(axis.interval3.lineLength);
		
		tickLength1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				IntervalPanel.this.apply();
				chart.updateUI();
			}
		});
		tickLength2.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				IntervalPanel.this.apply();
				chart.updateUI();
			}
		});
		tickLength3.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				IntervalPanel.this.apply();
				chart.updateUI();
			}
		});
		
		
//		x1IncField.addPropertyChangeListener(new PropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent arg0) {
//				
//			}
//		});

		this.initComponents();
	}

	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder("Intervals"));
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		drawInterval(axis.interval1, gbc, 0, x1IncField, tickLength1);
		drawInterval(axis.interval2, gbc, 1, x2IncField, tickLength2);
		drawInterval(axis.interval3, gbc, 2, x3IncField, tickLength3);

	}

	public void drawInterval(Interval interval1, GridBagConstraints gbc,
			int position, JTextField incField, JSpinner tickField) {
		gbc.gridx = 0;
		gbc.gridy = position;

		this.add(new JLabel("Increment " + position), gbc);
		gbc.gridx = 1;
		this.add(incField, gbc);

		gbc.gridx = 2;

		this.add(new JLabel("Tick Length "), gbc);

		gbc.gridx = 3;
		this.add(tickField, gbc);

	}

	public void apply() {
			axis.interval1.lineLength = new Integer(tickLength1.getValue().toString());
			axis.interval2.lineLength = new Integer(tickLength2.getValue().toString());
			axis.interval3.lineLength = new Integer(tickLength3.getValue().toString());
			axis.interval1.increment = new Double(x1IncField.getText());
			axis.interval2.increment = new Double(x2IncField.getText());
			axis.interval3.increment = new Double(x3IncField.getText());
	}


}
