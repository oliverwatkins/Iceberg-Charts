//package com.bluewalrus.chartmanager;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JColorChooser;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//public class ColorChooserDialog extends JDialog {
//
//	private Color chosenColor = Color.BLUE;
//	private JButton okButton;
//	
//
//	public ColorChooserDialog(JFrame topFrame) {
//    	
//    	super(topFrame);
//    	
//    	setSize(300, 150);
//		
//		final JColorChooser tcc = new JColorChooser(Color.BLUE);
//		tcc.getSelectionModel().addChangeListener(new ChangeListener() {
//
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				
//				chosenColor = tcc.getColor();
//			}
//		});
//		tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));
//
//		add(tcc);
//		
//
//		okButton = new JButton("OK");
//		okButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//			}
//		});
//		
//		add(okButton, BorderLayout.SOUTH);
//	}
//	
//    public Color getChosenColor() {
//		return chosenColor;
//	}
//    
//    public JButton getOkButton() {
//		return okButton;
//	}
//}
