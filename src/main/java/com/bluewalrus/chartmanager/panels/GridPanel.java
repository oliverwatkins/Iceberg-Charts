package com.bluewalrus.chartmanager.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.bluewalrus.bar.Line;
import com.bluewalrus.chart.Chart;
import com.bluewalrus.chart.XYChart;
import com.bluewalrus.chartmanager.ChartManagerApp;

public class GridPanel extends JPanel { 
	
	XYChart chart;
	
	private JComboBox<Integer> grid1Combo = new JComboBox<Integer>(new Integer[]{1,2,3,4,5});
	private JComboBox<Integer> grid2Combo = new JComboBox<Integer>(new Integer[]{1,2,3,4,5});
	private JComboBox<Integer> grid3Combo = new JComboBox<Integer>(new Integer[]{1,2,3,4,5});
	// End of variables declaration

	private JButton colorButton1 = new JButton("");
	private JButton colorButton2 = new JButton("");
	private JButton colorButton3 = new JButton("");
	
	ChartManagerApp chartFrameSystem;
	
	public GridPanel(Chart chart, ChartManagerApp chartFrameSystem) {

		this.chartFrameSystem = chartFrameSystem;
		this.chart = (XYChart)chart;
		setBorder(BorderFactory.createTitledBorder("Grid"));

		initComponents();
	}

	private void initComponents() {

		
		grid1Combo.setRenderer(new LineWidthRenderer());
		grid2Combo.setRenderer(new LineWidthRenderer());
		grid3Combo.setRenderer(new LineWidthRenderer());
		
		GridBagConstraints gbc = new GridBagConstraints();

		this.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Grid lines 1"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(grid1Combo, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(colorButton1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Grid lines 2"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(grid2Combo, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(colorButton2, gbc);


		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(new JLabel("Grid lines 3"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(grid3Combo, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(colorButton3, gbc);

		
		
		
		grid1Combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("grid1Combo.getSelectedItem() " + grid1Combo.getSelectedItem());

				
				
				chart.xAxis.interval1.graphLine = new Line(Color.GRAY, false, (int)grid1Combo.getSelectedItem());
				chart.yAxis.interval1.graphLine = new Line(Color.GRAY, false, (int)grid1Combo.getSelectedItem());
				
				chartFrameSystem.applyAction.actionPerformed(null);
				
			}
		});
		
	}
	
	public class LineWidthRenderer extends JPanel implements ListCellRenderer {
		 
	    public LineWidthRenderer() {
	        setOpaque(true);
	        setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
	        setBackground(Color.BLUE);
	        setForeground(Color.YELLOW);
	    }
	    
	    Integer value = -1;
	     
	    @Override
	    public Component getListCellRendererComponent(JList list, Object value,
	            int index, boolean isSelected, boolean cellHasFocus) {
	    	
	    	this.value = (Integer)value;
//	        setText(value.toString());
	        return this;
	    }
	    
	    protected void paintComponent(Graphics g) {
	    	
	    	Graphics2D g2 = (Graphics2D)g;
	    	
	    	g2.setBackground(Color.WHITE);

	    	g2.setColor(Color.GRAY);

	    	switch (value) {
			case 0:
				break;
			case 1:
				g2.setStroke(new BasicStroke(1));
				break;
			case 2:
				g2.setStroke(new BasicStroke(2));
				break;
			case 3:
				g2.setStroke(new BasicStroke(3));
				break;
			case 4:
				g2.setStroke(new BasicStroke(4));
				break;
			case 5:
				g2.setStroke(new BasicStroke(5));
				break;

			default:
				break;
			}
	    	
	    	if (value != 0)
	    		g2.drawLine(0, (int)this.getSize().getHeight()/2, (int)this.getSize().getWidth(), (int)this.getSize().getHeight()/2);
	    }
	 
	}

	public void setChart(XYChart chart) {
		this.chart = chart;
	}
	

}
