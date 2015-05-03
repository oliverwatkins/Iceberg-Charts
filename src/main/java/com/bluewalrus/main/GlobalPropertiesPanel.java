/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bluewalrus.main;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lauren
 */
public class GlobalPropertiesPanel extends JPanel {
    
    String axis = "";
    
    private javax.swing.JTextField maxXValueField;
    private javax.swing.JTextField maxYValueField;
    private javax.swing.JTextField minXValueField;
    private javax.swing.JTextField minYValueField;
    
    private javax.swing.JTextField x1IncField;
    private javax.swing.JTextField x2IncField;
    private javax.swing.JTextField x3IncField;
    // End of variables declaration     
    
    
    
    GlobalPropertiesPanel(String axis) {
        this.axis = axis;
        this.initComponents();
    }
    
    private void initComponents() {

        this.setLayout(new GridBagLayout());

        this.add(new JLabel("Min Value "));
        this.add(minXValueField);
        this.add(new JLabel("Max Value "));
        this.add(maxXValueField);   
    }
}
