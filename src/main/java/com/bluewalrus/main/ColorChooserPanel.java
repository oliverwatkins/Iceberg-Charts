package com.bluewalrus.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ColorChooserPanel extends JPanel {
	
	
	public Color chosenColor = Color.WHITE;
	
	JButton b = new JButton("choose");
	
	public ColorChooserPanel(String string) {
		
		this.add(new JLabel(" " + string));
		
		this.add(b);
	}
	
	public ColorChooserPanel(String name, final ActionListener onColorChange) {
		
		this.add(new JLabel(" " + name));
		
		this.add(b);
		
		this.setOnColorChangeListener(onColorChange);
	}
	




	public void setOnColorChangeListener(final ActionListener onColorChange) {
		
		final JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//		JButton button = new JButton("Choose");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				final JDialog d = new JDialog(topFrame);
				
				final ColorChooserDialog d2 = new ColorChooserDialog(topFrame);
				d2.setVisible(true);
				
//				d2.getOkButton().addActionListener(onColorChange);
				
				
				
				
				
				d2.getOkButton().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						chosenColor = d2.getChosenColor();
						
						onColorChange.actionPerformed(null);
//						chart.setBackground(d2.getChosenColor());
//						
//						chart.backgroundColor = d2.getChosenColor();
//						
//						chart.updateUI();
					}
				});
			}
		});
		
	}

	public Color getChosenColor() {
		return chosenColor;
	}


}
