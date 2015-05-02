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
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    
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
        jLabel2 = new javax.swing.JLabel();
        minYValueField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        maxXValueField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        maxYValueField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        incrementTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        x1IncField = new javax.swing.JTextField();
        x2IncField = new javax.swing.JTextField();
        x3IncField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        jLabel1.setText("X Primary Increments");
        jLabel6.setText("X Primary Secondary Increments");
        jLabel7.setText("X Primary Tertiary Increments");

        jLabel2.setText("min. Y Value");
        jLabel3.setText("max. Y Value");
        jLabel4.setText("max. X Value");

        maxYValueField.setText(" ");
        
        jLabel5.setText("min. X Value");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        
//        jPanel1.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());
        
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

        incrementTab.addTab("X increments", jPanel1);

        incrementTab.addTab("Y increments", jPanel2);

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

//        x1IncField.getText();
//        x2IncField.getText();
//        x3IncField.getText();
        //apply
// TODO add your handling code here:
    }                                           

    private void x3IncFieldActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void maxYValueFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              


              
}
