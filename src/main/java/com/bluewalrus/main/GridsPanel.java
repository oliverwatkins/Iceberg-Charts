package com.bluewalrus.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GridsPanel extends JPanel {

	
	public GridsPanel() {
		
		this.initComponents();
		
	}
	private void initComponents() {

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;

		this.setLayout(new GridBagLayout());
		
	}
	
	
	
}
