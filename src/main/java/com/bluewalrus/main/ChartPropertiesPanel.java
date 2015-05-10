/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import com.bluewalrus.chart.Chart;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author lauren
 */
public class ChartPropertiesPanel extends JPanel{
     
        ChartPropertiesPanel(Chart chart) {
        
            setBorder(BorderFactory.createTitledBorder("Chart Properties"));
        }

		public void apply() {
			// TODO Auto-generated method stub
			
		}
}
