//package com.bluewalrus.chartmanager.panels;
//
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//
//import javax.swing.BorderFactory;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JSlider;
//import javax.swing.JTextField;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//import com.bluewalrus.chart.Axis;
//import com.bluewalrus.chart.Chart;
//import com.bluewalrus.chart.XYChart;
//import com.bluewalrus.chartmanager.ChartManagerApp;
//
//
//public class AxisPropertiesPanel extends JPanel {
//    
//    private IntervalPanel interval;
//    
//    private JTextField maxValueField;
//    private JTextField minValueField;
//
//    private Axis axis;
//    public Chart chart;
//
//	private JSlider minValueSlider;
//
//	private JSlider maxValueSlider;
//
//    public AxisPropertiesPanel(Axis axis, final XYChart chart, final ChartManagerApp chartFrameSystem) {
//
//        setBorder(BorderFactory.createTitledBorder(axis.getName()));
//
//        this.chart = chart;
//        this.axis = axis;
//
//        minValueField = new javax.swing.JTextField(10);
//        maxValueField = new javax.swing.JTextField(10);
//
//        minValueField.setText("" + axis.minValue);
//        maxValueField.setText("" + axis.maxValue);
//        
//        double diff = axis.maxValue - axis.minValue;
//        
//        
//        int min1 = (int)(axis.minValue - diff);
//        int min2 = (int)(axis.minValue + diff);
//        
//        int max1 = (int)(axis.maxValue - diff);
//        int max2 = (int)(axis.maxValue + diff);
//        
//        minValueSlider = new JSlider(0, 100);
//        maxValueSlider = new JSlider(0, 100);
//        
//        minValueSlider.setMinimum(min1);
//        minValueSlider.setMaximum(min2);
//        
//        maxValueSlider.setMinimum(max1);
//        maxValueSlider.setMaximum(max2);
//        
//        
//        minValueSlider.setValue((int)axis.minValue);
//        maxValueSlider.setValue((int)axis.maxValue);
//        
//        
//        minValueSlider.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				
//				JSlider js = (JSlider)e.getSource();
//				
//				minValueField.setText("" + js.getValue());
//				
//				chartFrameSystem.applyAction.actionPerformed(null);
//			}
//        });
//        
//        maxValueSlider.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				
//				JSlider js = (JSlider)e.getSource();
//				
//				maxValueField.setText("" + js.getValue());
//				
//				chartFrameSystem.applyAction.actionPerformed(null);
//			}
//        });
//        		
//
//        this.initComponents();
//    }
//    
//    
//	public void setChart(XYChart chart, Axis axis) {
//		this.chart = chart;
//		
//	}
//	
//	
//
//    public void apply() {
//
//        axis.minValue = new Double(minValueField.getText());
//        axis.maxValue = new Double(maxValueField.getText());
//        
//        interval.apply();
//
//    }
//
//    private void initComponents() {
//
//        this.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        minValueField.setMinimumSize(minValueField.getPreferredSize());
//        maxValueField.setMinimumSize(maxValueField.getPreferredSize());
//
//        
//        int x = 0;
//        gbc.gridx = x;
//        gbc.gridy = 0;
////        gbc.weightx = 1;
//
//        this.add(new JLabel("Min Value "), gbc);
//        gbc.gridx = 1;
//        this.add(minValueField, gbc);
//        gbc.gridx = 2;
//        
//
////        minValueSlider.setMinimumSize(minValueField.getPreferredSize());
//        gbc.weightx = 1;
//        this.add(minValueSlider, gbc);
//        
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//
//        this.add(new JLabel("Max Value "), gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        this.add(maxValueField, gbc);
//        
//        
//        gbc.gridx = 2;
//        gbc.gridy = 1;
//
////        maxValueSlider.setMinimumSize(maxValueField.getPreferredSize());
//        gbc.weightx = 1;
//        this.add(maxValueSlider, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 3;
////        gbc.weightx = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        interval = new IntervalPanel(this.axis, this.chart);
//
//        this.add(interval, gbc);
//    }
//
//
//}
