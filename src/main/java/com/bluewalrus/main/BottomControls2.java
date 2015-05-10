package com.bluewalrus.main;

import com.bluewalrus.chart.BubbleChart;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lauren
 */
public class BottomControls2 extends JPanel {

    BubbleChart chart;

        // Variables declaration - do not modify                     
    private javax.swing.JButton applyButton;
    
    private javax.swing.JTabbedPane incrementTab;
    private javax.swing.JTextField maxXValueField;
    private javax.swing.JTextField maxYValueField;
    private javax.swing.JTextField minXValueField;
    private javax.swing.JTextField minYValueField;
    
    private javax.swing.JTextField x1IncField;
    private javax.swing.JTextField x2IncField;
    private javax.swing.JTextField x3IncField;
    // End of variables declaration     
    /**
     * Creates new form BottomControls
     */
    public BottomControls2(BubbleChart cfs) {

        this.chart = cfs;

        initComponents();
    }
                        
    private void initComponents() {
 
        this.setLayout(new BorderLayout());
        
        minXValueField = new javax.swing.JTextField();
        minYValueField = new javax.swing.JTextField();
        maxXValueField = new javax.swing.JTextField();
        maxYValueField = new javax.swing.JTextField();
        applyButton = new javax.swing.JButton();
        incrementTab = new javax.swing.JTabbedPane();
        x1IncField = new javax.swing.JTextField();
        x2IncField = new javax.swing.JTextField();
        x3IncField = new javax.swing.JTextField();

        maxYValueField.setText(" ");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();

        int x = 0;
        gbc.gridx = x;
        gbc.gridy = 0;
        topPanel.add(new JLabel("min. X Value"), gbc);
        gbc.gridx = x++;
        topPanel.add(minXValueField, gbc);
        gbc.gridx = x++;
        topPanel.add(new JLabel("max. X Value"), gbc);
        gbc.gridx = x++;
        topPanel.add(maxXValueField, gbc);

        
        gbc.gridy = 1;
        x = 0;
        gbc.gridx = x;
        topPanel.add(new JLabel("min. Y Value"), gbc);
        gbc.gridx = x++;
        topPanel.add(minYValueField, gbc);
        gbc.gridx = x++;
        topPanel.add(new JLabel("max. Y Value"), gbc);
        gbc.gridx = x++;
        topPanel.add(maxYValueField, gbc);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(incrementTab, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.CENTER);
        
        
        JPanel xIncrements = new JPanel();
        JPanel yIncrements = new JPanel();
        
        
        incrementTab.addTab("X increments", xIncrements);
        incrementTab.addTab("Y increments", yIncrements);
    }                     

                                            

    private void x2IncFieldActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            

        try {
            int x1 = new Integer(minXValueField.getText());
            int y1 = new Integer(minYValueField.getText());
            int x2 = new Integer(maxXValueField.getText());
            int y2 = new Integer(maxYValueField.getText());

            chart.xAxis.minValue = x1;
            chart.xAxis.maxValue = x2;

            chart.yAxis.minValue = y1;
            chart.yAxis.maxValue = y2;
            
            chart.repaint();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }                                           
}
